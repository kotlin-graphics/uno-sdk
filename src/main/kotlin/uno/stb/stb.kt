package uno.stb

import glm.*

object stb {

    // simple implementation that just takes the source data in a big block
    lateinit var out: CharArray
    var pOut = 0
//    static FILE      *stb__outfile;
//    static unsigned int stb__outbytes;

    var window = 0x40000 // 256K
    var runningAdler = 0

    private lateinit var array: CharArray
    private var length = 0

    fun compress(input: CharArray) {

        array = input
        length = array.size

        out = CharArray(1 shl 23)
//        stb__outfile = NULL;

        compressInner()

//        return stb__out - out;
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

        val (res, literals) = compressChunk(chash, hashSize - 1)

        assert(res.size == length)

        outLiterals(length - literals, literals)

        out2(0x05fa) // end opcode

        out4(runningAdler)

        return res
    }

    fun out(v: Int) {
        out[pOut++] = v.c
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

    fun compressChunk(
            chash: IntArray,
            mask: Int
    ): Pair<CharArray, Int> {

        val history = 0
        val start = 0
        val end = start + length

        var matchMax = 0
        var litStart = start
        var q = start

        fun SCRAMBLE(h: Int) = (h + (h ushr 16)) and mask

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

        val res = CharArray(q - start, { array[it] })
        return res to pendingLiterals
    }

//    class UCharPointer(var data: CharArray, var ptr: Int = 0) {
//        constructor(charP: UCharPointer) : this(charP.data, charP.ptr)
//
//        infix fun set(int: Int) {
//            data[ptr] = int.c
//        }
//
//        operator fun minus(int: Int) = UCharPointer(data, ptr - int)
//        fun cmp(int: Int) = UCharPointer(data, ptr - int)
//    }


    fun decompressLength(input: CharArray) = (input[8] shl 24) + (input[9] shl 16) + (input[10] shl 8) + input[11]

    var barrier = 0
    var barrier2 = 0
    var barrier3 = 0
    var barrier4 = 0
    var dout = 0

//    fun match(data: CharArray) {
//        // INVERSE of memmove... write each byte before copying the next...
//        assert(dout + data.size <= barrier)
//        if (dout + data.size > barrier) {
//            dout += data.size
//            return
//        }
//        if (data < barrier4) {
//            stb__dout = stb__barrier + 1; return; }
//        while (length--) * stb__dout++ = * data ++
//    }

//    fun decompress(output: CharArray, i: CharArray): Int {
//
//        if (in4(0, i) != 0x57bC0000) return 0
//        if (in4(4, i) != 0) return 0 // error! stream is > 4GB
//        val olen = decompressLength(i)
//        var iPtr = 0
//        var outputPtr = 0
//        barrier2 = iPtr
//        barrier3 = iPtr + i.size
//        barrier = outputPtr + olen
//        barrier4 = outputPtr
//        iPtr += 16
//
//        dout = outputPtr
//        while (true) {
//            var old_i = iPtr
//            i = stb_decompress_token(i)
//            if (i == old_i) {
//                if ( * i == 0x05 && i[1] == 0xfa) {
//                    IM_ASSERT(stb__dout == output + olen)
//                    if (stb__dout != output + olen) return 0
//                    if (stb_adler32(1, output, olen) != (unsigned int) stb__in4 (2))
//                        return 0
//                    return olen
//                } else {
//                    IM_ASSERT(0) /* NOTREACHED */
//                    return 0
//                }
//            }
//            IM_ASSERT(stb__dout <= output + olen)
//            if (stb__dout > output + olen)
//                return 0
//        }
//    }

    fun adler32(adler32: Int, buffer: Int, bufLen: Int): Int {
        var buffer_ = buffer
        var bufLen_ = bufLen.L
        val ADLER_MOD = 65521
        var s1 = adler32 and 0xffff
        var s2 = adler32 ushr 16
        var blockLen = 0L
        var i = 0L

        blockLen = (bufLen_ % 5552).L
        while (bufLen_ != 0L) {
            for (i in 0..blockLen - 8 step 8) {
                s1 += array[buffer_]; s2 += s1
                s1 += array[buffer_ + 1]; s2 += s1
                s1 += array[buffer_ + 2]; s2 += s1
                s1 += array[buffer_ + 3]; s2 += s1
                s1 += array[buffer_ + 4]; s2 += s1
                s1 += array[buffer_ + 5]; s2 += s1
                s1 += array[buffer_ + 6]; s2 += s1
                s1 += array[buffer_ + 7]; s2 += s1

                buffer_ += 8
            }

            while (i < blockLen)
                s1 += array[buffer_++]; s2 += s1

            s1 %= ADLER_MOD; s2 %= ADLER_MOD
            bufLen_ -= blockLen
            blockLen = 5552
        }
        return (s2 shl 16) + s1
    }

    fun in2(x: Int, i: CharArray) = (i[x] shl 8) + i[x + 1].i
    fun in3(x: Int, i: CharArray) = (i[x] shl 16) + in2(x + 1, i)
    fun in4(x: Int, i: CharArray) = (i[x] shl 24) + in3(x + 1, i)

    fun outLiterals(i: Int, numLit: Int) {
        var in_ = i
        var numLit_ = numLit
        while (numLit > 65536) {
            outLiterals(i, 65536)
            in_ += 65536
            numLit_ -= 65536
        }

        if (numLit == 0)
        else if (numLit <= 32) out(0x000020 + numLit - 1)
        else if (numLit <= 2048) out2(0x000800 + numLit - 1)
        else /*  numlit <= 65536) */ out3(0x070000 + numLit - 1)

        repeat(numLit) { out[pOut] = array[in_] }
        pOut += numLit
    }

    fun notCrap(best: Int, dist: Int) = (best > 2 && dist <= 0x00100) || (best > 5 && dist <= 0x04000) || (best > 7 && dist <= 0x80000)

    val hashSize = 32768

    fun matchLen(m1: Int, m2: Int, maxLen: Int): Int {
        var i = 0
        for (i in 0 until maxLen)
            if (array[m1 + i] != array[m2 + i]) return i
        return i
    }

    /* note that you can play with the hashing functions all you want without needing to change the decompressor    */

    fun hc(q: Int, h: Int, c: Int) = (h shl 7) + (h ushr 25) + array[q + c]
    fun hc2(q: Int, h: Int, c: Int, d: Int) = (h shl 14) + (h ushr 18) + (array[q + c] shl 7) + array[q + d]
    fun hc3(q: Int, c: Int, d: Int, e: Int) = (array[q + c] shl 14) + (array[q + d] shl 7) + array[q + e]

    // TODO move
    fun decode85(src: String): CharArray {

        fun decode85Byte(c: Char) = (if (c >= '\\') c - 36 else c - 35).i

        var pSrc = 0
        var dst = CharArray(((src.length + 4) / 5) * 4)
        var pDst = 0

        while (pSrc < src.length - 1) {
            val tmp = decode85Byte(src[pSrc]) + 85 * (decode85Byte(src[pSrc + 1]) + 85 * (decode85Byte(src[pSrc + 2]) +
                    85 * (decode85Byte(src[pSrc + 3]) + 85 * decode85Byte(src[pSrc + 4]))))
            dst[pDst] = ((tmp ushr 0) and 0xFF).c
            dst[pDst + 1] = ((tmp ushr 8) and 0xFF).c
            dst[pDst + 2] = ((tmp ushr 16) and 0xFF).c
            dst[pDst + 3] = ((tmp ushr 24) and 0xFF).c   // We can't assume little-endianness.
            pSrc += 5
            pDst += 4
        }
        return dst
    }
}