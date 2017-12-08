package uno.stb

import glm_.*

object stb {

    /**
     * --------------------------------------Compression----------------------------------------------------------------
     */

    // simple implementation that just takes the source data in a big block
    lateinit var res: CharArray
    var out = 0

    var window = 0x40000 // 256K
    var runningAdler = 0

    private lateinit var array: CharArray
    private var length = 0

    val hashSize = 32768

    fun compress(input: String) = compress(
            (if (input.last() == '\u0000')
                input
            else
                "$input\u0000")
                    .toCharArray())

    fun compress(input: CharArray): CharArray {

        array = input
        length = array.size

        res = CharArray(1 shl 23)

        return compressInner()
    }

    fun compressInner(): CharArray {

        var len = 0
        var i = 0

        val chash = IntArray(hashSize, { -1 })

        // stream signature
        out(0x57); out(0xbc)
        out2(0)

        out4(0)       // 64-bit length requires 32-bit leading 0
        out4(length)
        out4(window)

        runningAdler = 1

        val literals = compressChunk(chash, hashSize - 1)

        outLiterals(length - literals, literals)

        out2(0x05fa) // end opcode

        out4(runningAdler)

        return CharArray(out, { res[it] })
    }

    fun out(v: Int) {
        res[out++] = v.uc
    }

    fun out2(v: Int) {
        out(v ushr 8)
        out(v)
    }

    fun out3(v: Int) {
        out(v ushr 16)
        out(v ushr 8)
        out(v)
    }

    fun out4(v: Int) {
        out(v ushr 24)
        out(v ushr 16)
        out(v ushr 8)
        out(v)
    }

    fun compressChunk(chash: IntArray, mask: Int): Int {

        val history = 0
        val start = 0
        val end = start + length

        var matchMax = 0
        var litStart = start
        var q = start

        fun SCRAMBLE(h: Int) = (h + (h ushr 16)) and mask

        fun notCrap(best: Int, dist: Int) = (best > 2 && dist <= 0x00100) || (best > 5 && dist <= 0x04000) || (best > 7 && dist <= 0x80000)

        /* note that you can play with the hashing functions all you want without needing to change the decompressor    */

        fun hc(q: Int, h: Int, c: Int) = (h shl 7) + (h ushr 25) + array[q + c]
        fun hc2(q: Int, h: Int, c: Int, d: Int) = (h shl 14) + (h ushr 18) + (array[q + c] shl 7) + array[q + d]
        fun hc3(q: Int, c: Int, d: Int, e: Int) = (array[q + c] shl 14) + (array[q + d] shl 7) + array[q + e]

        /*  stop short of the end so we don't scan off the end doing the hashing; this means we won't compress the last
            few bytes unless they were part of something longer */
        while (q < start + length && q + 12 < end) {

            var m = 0
            var h1 = 0
            var h2 = 0
            var h3 = 0
            var h4 = 0
            var h = 0
            var t = 0
            var best = 2
            var dist = 0

            matchMax = if (q + 65536 > end) end - q else 65536

            fun nc(b: Int, d: Int) = d <= window && (b > 9 || notCrap(b, d))

            /* avoid retrying a match we already tried */
            fun TRY(t: Int, p: Int) {
                if (if (p != 0) dist != q - t else true) {
                    m = matchLen(t, q, matchMax)
                    if (m > best)
                        if (nc(m, q - t)) {
                            best = m
                            dist = q - t
                        }
                }
            }

            /*  rather than search for all matches, only try 4 candidate locations, chosen based on 4 different hash
                functions of different lengths.
                this strategy is inspired by LZO; hashing is unrolled here using the 'hc' macro */
            h = hc3(q, 0, 1, 2); h1 = SCRAMBLE(h)
            t = chash[h1]; if (t != -1) TRY(t, 0)
            h = hc2(q, h, 3, 4); h2 = SCRAMBLE(h)
            h = hc2(q, h, 5, 6); t = chash[h2]; if (t != -1) TRY(t, 1)
            h = hc2(q, h, 7, 8); h3 = SCRAMBLE(h)
            h = hc2(q, h, 9, 10); t = chash[h3]; if (t != -1) TRY(t, 1)
            h = hc2(q, h, 11, 12); h4 = SCRAMBLE(h)
            t = chash[h4]; if (t != -1) TRY(t, 1)

            /* because we use a shared hash table, can only update it _after_ we've probed all of them  */
            chash[h1] = q
            chash[h2] = q
            chash[h3] = q
            chash[h4] = q

            if (best > 2)
                assert(dist > 0)

            // see if our best match qualifies
            if (best < 3)  // fast path literals
                ++q
            else if (best in 3..0x80 && dist <= 0x100) {
                outLiterals(litStart, q - litStart); q += best; litStart = q
                out(0x80 + best - 1)
                out(dist - 1)
            } else if (best in 6..0x100 && dist <= 0x4000) {
                outLiterals(litStart, q - litStart); q += best; litStart = q
                out2(0x4000 + dist - 1)
                out(best - 1)
            } else if (best in 8..0x100 && dist <= 0x80000) {
                outLiterals(litStart, q - litStart); q += best; litStart = q
                out3(0x180000 + dist - 1)
                out(best - 1)
            } else if (best in 9..0x10000 && dist <= 0x80000) {
                outLiterals(litStart, q - litStart); q += best; litStart = q
                out3(0x100000 + dist - 1)
                out2(best - 1)
            } else if (best > 9 && dist <= 0x1000000) {
                if (best > 65536) best = 65536
                outLiterals(litStart, q - litStart); q += best; litStart = q
                if (best <= 0x100) {
                    out(0x06)
                    out3(dist - 1)
                    out(best - 1)
                } else {
                    out(0x04)
                    out3(dist - 1)
                    out2(best - 1)
                }
            } else ++q  // fallback literals if no match was a balanced tradeoff
        }

        // if we didn't get all the way, add the rest to literals
        if (q - start < length)
            q = start + length

        // the literals are everything from lit_start to q
        val pendingLiterals = q - litStart

        runningAdler = adler32(runningAdler, start, q - start)

        assert(q - start == length)

        return pendingLiterals
    }

    fun matchLen(m1: Int, m2: Int, maxLen: Int): Int {
        var i = 0
        while (i < maxLen) {
            if (array[m1 + i] != array[m2 + i]) return i
            ++i
        }
        return i
    }

    fun adler32(adler32: Int, buffer: Int, bufLen: Int): Int {

        var _buffer = buffer
        var _bufLen = bufLen.L
        val ADLER_MOD = 65521
        var s1 = adler32 and 0xffff
        var s2 = adler32 ushr 16
        var blockLen = 0L
        var i = 0L

        blockLen = _bufLen % 5552
        while (_bufLen != 0L) {
            while (i + 7 < blockLen) {
                s1 += array[_buffer]; s2 += s1
                s1 += array[_buffer + 1]; s2 += s1
                s1 += array[_buffer + 2]; s2 += s1
                s1 += array[_buffer + 3]; s2 += s1
                s1 += array[_buffer + 4]; s2 += s1
                s1 += array[_buffer + 5]; s2 += s1
                s1 += array[_buffer + 6]; s2 += s1
                s1 += array[_buffer + 7]; s2 += s1

                _buffer += 8
                i += 8
            }

            while (i < blockLen) {
                s1 += array[_buffer++]; s2 += s1
                ++i
            }

            s1 %= ADLER_MOD; s2 %= ADLER_MOD
            _bufLen -= blockLen
            blockLen = 5552
        }
        return (s2 shl 16) + s1
    }

    fun outLiterals(i: Int, numLit: Int) {
        var _in = i
        var _numLit = numLit
        while (numLit > 65536) {
            outLiterals(i, 65536)
            _in += 65536
            _numLit -= 65536
        }

        if (numLit == 0)
        else if (numLit <= 32) out(0x000020 + numLit - 1)
        else if (numLit <= 2048) out2(0x000800 + numLit - 1)
        else /*  numlit <= 65536) */ out3(0x070000 + numLit - 1)

        System.arraycopy(array, _in, res, out, numLit)
        out += numLit
    }


    /**
     * --------------------------------------Decompression--------------------------------------------------------------
     */

    var barrier = 0
    var barrier2 = 0
    var barrier3 = 0
    var barrier4 = 0
    var dout = 0

    fun decompress(input: CharArray): CharArray {

        array = input
        val olen = (input[8] shl 24) + (input[9] shl 16) + (input[10] shl 8) + input[11]
        res = CharArray(olen)

        var i = 0
        out = 0
        length = input.size

        if (in4(0, i) != 0x57bC0000) Error()
        if (in4(4, i) != 0) Error("stream is > 4GB")
        barrier2 = i
        barrier3 = i + length
        barrier = out + olen
        barrier4 = out
        i += 16

        dout = out
        while (true) {
            val old_i = i
            i = decompressToken(i)
            if (i == old_i) {
                if (array[i].i == 0x05 && array[i + 1].i == 0xfa) {
                    assert(dout == out + olen)
                    if (adler32(1, out, olen) != in4(2, i))
                        Error()
                    return res
                } else
                    Error()
            }
            assert(dout <= out + olen)
        }
    }

    fun decompressToken(i: Int): Int {
        var _i = i
        if (array[_i] >= 0x20) { // use fewer if's for cases that expand small
            if (array[_i] >= 0x80) {
                match(dout - array[_i + 1] - 1, array[_i + 0].i - 0x80 + 1)
                _i += 2
            } else if (array[_i] >= 0x40) {
                match(dout - (in2(0, _i) - 0x4000 + 1), array[_i + 2].i + 1)
                _i += 3
            } else { /* *i >= 0x20 */
                lit(_i + 1, array[_i].i - 0x20 + 1)
                _i += 1 + (array[_i] - 0x20 + 1)
            }
        } else { // more ifs for cases that expand large, since overhead is amortized
            if (array[_i] >= 0x18) {
                match(dout - (in3(0, _i) - 0x180000 + 1), array[_i + 3].i + 1)
                _i += 4
            } else if (array[_i] >= 0x10) {
                match(dout - (in3(0, _i) - 0x100000 + 1), in2(3, _i) + 1)
                _i += 5
            } else if (array[_i] >= 0x08) {
                lit(_i + 2, in2(0, _i) - 0x0800 + 1)
                _i += 2 + (in2(0, _i) - 0x0800 + 1)
            } else if (array[_i].i == 0x07) {
                lit(_i + 3, in2(1, _i) + 1)
                _i += 3 + (in2(1, _i) + 1)
            } else if (array[_i].i == 0x06) {
                match(dout - (in3(1, _i) + 1), array[_i + 4].i + 1)
                _i += 5
            } else if (array[_i].i == 0x04) {
                match(dout - (in3(1, _i) + 1), in2(4, _i) + 1)
                _i += 6
            }
        }
        return _i
    }

    fun in2(x: Int, i: Int) = (array[i + x] shl 8) + array[i + x + 1].i
    fun in3(x: Int, i: Int) = (array[i + x] shl 16) + in2(x + 1, i)
    fun in4(x: Int, i: Int) = (array[i + x] shl 24) + in3(x + 1, i)

    fun match(data: Int, length: Int) {
        var _data = data
        var _length = length
        // INVERSE of memmove... write each byte before copying the next...
        assert(dout + _length <= barrier)
        if (dout + _length > barrier) {
            dout += _length
            return
        }
        if (_data < barrier4) {
            dout = barrier + 1
            return
        }
        while (_length != 0) {
            res[dout++] = res[_data++]
            _length--
        }
    }

    fun lit(data: Int, length: Int) {
        assert(dout + length <= barrier)
        if (dout + length > barrier) {
            dout += length
            return
        }
        if (data < barrier2) {
            dout = barrier + 1
            return
        }
        System.arraycopy(array, data, res, dout, length)
        dout += length
    }
}