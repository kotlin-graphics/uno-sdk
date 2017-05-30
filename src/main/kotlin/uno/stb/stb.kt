package uno.stb

import glm.c
import glm.i
import glm.shl
import glm.plus

object stb {

    // simple implementation that just takes the source data in a big block
    lateinit var out: UCharPointer
//    static FILE      *stb__outfile;
//    static unsigned int stb__outbytes;

    var window = 0x40000 // 256K
    var runningAdler = 0

    fun compress(input: UCharPointer) {

        out = UCharPointer(CharArray(1 shl 23))
//        stb__outfile = NULL;

        compressInner(input)

//        return stb__out - out;
    }

    fun compressInner(input: UCharPointer): CharArray {

        var literals = 0
        var len = 0
        var i = 0

//        unsigned char **chash
//        chash = (unsigned char**)malloc(stb__hashsize * sizeof(unsigned char*))
//        if (chash == NULL) return 0 // failure
//        for (i = 0; i < stb__hashsize; ++i)
//        chash[i] = NULL

        // stream signature
        out(0x57); out(0xbc)
        out2(0)

        out4(0)       // 64-bit length requires 32-bit leading 0
        out4(input.size)
        out4(window)

        runningAdler = 1

//        len = stb_compress_chunk(input, input, input + length, length, & literals, chash, stb__hashsize-1)
//        assert(len == length)
//
//        outliterals(input + length - literals, literals)
//
//        free(chash)
//
//        stb_out2(0x05fa) // end opcode
//
//        stb_out4(stb__running_adler)
//
//        return 1 // success
    }

    fun out(v: Int) {
        out.ptr++
        out set v
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
            chunk: CharArray,
            pendingLiterals: Int, // TODO save back
//    unsigned char **chash,
            mask: Int
    ) {
        val history = UCharPointer(chunk)
        val start = UCharPointer(chunk)
        val end = UCharPointer(chunk)
        val length = chunk.size

        var matchMax = 0
        var litStart = start - pendingLiterals
        var q = UCharPointer(start)

        fun SCRAMBLE(h: Int) = (h + (h shl 16)) and mask

        /*  stop short of the end so we don't scan off the end doing the hashing; this means we won't compress the last
            few bytes unless they were part of something longer */
        while (q < start + length && q + 12 < end) {
//            int m;
//            unsigned int h1, h2, h3, h4, h;
//            unsigned char *t;
//            int best = 2, dist = 0;
//
//            if (q + 65536 > end)
//                match_max = end - q;
//            else
//                match_max = 65536;
//
//            #define stb__nc(b,d)  ((d) <= window && ((b) > 9 || stb_not_crap(b,d)))
//
//            #define STB__TRY(t,p)  /* avoid retrying a match we already tried */ \
//            if (p ? dist != q-t : 1)                             \
//            if ((m = stb_matchlen(t, q, match_max)) > best)     \
//            if (stb__nc(m,q-(t)))                                \
//            best = m, dist = q - (t)
//
//            // rather than search for all matches, only try 4 candidate locations,
//            // chosen based on 4 different hash functions of different lengths.
//            // this strategy is inspired by LZO; hashing is unrolled here using the
//            // 'hc' macro
//            h = stb__hc3(q, 0, 1, 2); h1 = STB__SCRAMBLE(h);
//            t = chash[h1]; if (t) STB__TRY(t, 0);
//            h = stb__hc2(q, h, 3, 4); h2 = STB__SCRAMBLE(h);
//            h = stb__hc2(q, h, 5, 6);        t = chash[h2]; if (t) STB__TRY(t, 1);
//            h = stb__hc2(q, h, 7, 8); h3 = STB__SCRAMBLE(h);
//            h = stb__hc2(q, h, 9, 10);        t = chash[h3]; if (t) STB__TRY(t, 1);
//            h = stb__hc2(q, h, 11, 12); h4 = STB__SCRAMBLE(h);
//            t = chash[h4]; if (t) STB__TRY(t, 1);
//
//            // because we use a shared hash table, can only update it
//            // _after_ we've probed all of them
//            chash[h1] = chash[h2] = chash[h3] = chash[h4] = q;
//
//            if (best > 2)
//                assert(dist > 0);
//
//            // see if our best match qualifies
//            if (best < 3) { // fast path literals
//                ++q;
//            }
//            else if (best > 2 && best <= 0x80 && dist <= 0x100) {
//                outliterals(lit_start, q - lit_start); lit_start = (q += best);
//                stb_out(0x80 + best - 1);
//                stb_out(dist - 1);
//            }
//            else if (best > 5 && best <= 0x100 && dist <= 0x4000) {
//                outliterals(lit_start, q - lit_start); lit_start = (q += best);
//                stb_out2(0x4000 + dist - 1);
//                stb_out(best - 1);
//            }
//            else if (best > 7 && best <= 0x100 && dist <= 0x80000) {
//                outliterals(lit_start, q - lit_start); lit_start = (q += best);
//                stb_out3(0x180000 + dist - 1);
//                stb_out(best - 1);
//            }
//            else if (best > 8 && best <= 0x10000 && dist <= 0x80000) {
//                outliterals(lit_start, q - lit_start); lit_start = (q += best);
//                stb_out3(0x100000 + dist - 1);
//                stb_out2(best - 1);
//            }
//            else if (best > 9 && dist <= 0x1000000) {
//                if (best > 65536) best = 65536;
//                outliterals(lit_start, q - lit_start); lit_start = (q += best);
//                if (best <= 0x100) {
//                    stb_out(0x06);
//                    stb_out3(dist - 1);
//                    stb_out(best - 1);
//                }
//                else {
//                    stb_out(0x04);
//                    stb_out3(dist - 1);
//                    stb_out2(best - 1);
//                }
//            }
//            else {  // fallback literals if no match was a balanced tradeoff
//                ++q;
//            }
        }
//
//        // if we didn't get all the way, add the rest to literals
//        if (q - start < length)
//            q = start + length;
//
//        // the literals are everything from lit_start to q
//        *pending_literals = (q - lit_start);
//
//        stb__running_adler = stb_adler32(stb__running_adler, start, q - start);
//        return q - start;
    }

    class UCharPointer(var data: CharArray, var ptr: Int = 0) {
        constructor(charP: UCharPointer) : this(charP.data, charP.ptr)

        infix fun set(int: Int) {
            data[ptr] = int.c
        }

        operator fun minus(int: Int) = UCharPointer(data, ptr - int)
        fun cmp(int: Int) = UCharPointer(data, ptr - int)
    }


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

    fun in2(x: Int, i: CharArray) = (i[x] shl 8) + i[x + 1].i
    fun in3(x: Int, i: CharArray) = (i[x] shl 16) + in2(x + 1, i)
    fun in4(x: Int, i: CharArray) = (i[x] shl 24) + in3(x + 1, i)

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