package uno.convert

import glm_.i
import glm_.uc

fun decode85(src: String): CharArray {

    fun decode85Byte(c: Char) = (if (c >= '\\') c - 36 else c - 35).i

    var pSrc = 0
    val dst = CharArray(((src.length + 4) / 5) * 4)
    var pDst = 0

    while (pSrc < src.length - 1) {

//        if(pDst == 3036)
//            println("${src[pSrc]}, ${src[pSrc+1]}, ${src[pSrc+2]}, ${src[pSrc+3]}, ${src[pSrc+4]}")

        val tmp = decode85Byte(src[pSrc]) + 85 * (decode85Byte(src[pSrc + 1]) + 85 * (decode85Byte(src[pSrc + 2]) +
                85 * (decode85Byte(src[pSrc + 3]) + 85 * decode85Byte(src[pSrc + 4]))))
        dst[pDst] = ((tmp ushr 0) and 0xFF).uc
        dst[pDst + 1] = ((tmp ushr 8) and 0xFF).uc
        dst[pDst + 2] = ((tmp ushr 16) and 0xFF).uc
        dst[pDst + 3] = ((tmp ushr 24) and 0xFF).uc   // We can't assume little-endianness.
        pSrc += 5
        pDst += 4
    }
    return dst
}