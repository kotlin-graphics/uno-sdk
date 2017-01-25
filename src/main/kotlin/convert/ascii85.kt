//package convert
//
//import java.nio.ByteBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.regex.Pattern;
//
//
///**
// * Created by GBarbieri on 25.01.2017.
// */
//
//
//private val ASCII_SHIFT = 33
//
//private val BASE85_POW = intArrayOf(
//        1,
//        85,
//        85 * 85,
//        85 * 85 * 85,
//        85 * 85 * 85 * 85)
//
//private val REMOVE_WHITESPACE = Pattern.compile("\\s+")
//
//fun encode(payload: ByteArray?): String {
//    if (payload == null || payload.isEmpty())
//        throw IllegalArgumentException("You must provide a non-zero length input")
//
//    //By using five ASCII characters to represent four bytes of binary data the encoded size ¹⁄₄ is larger than the original
//    val stringBuff = StringBuilder(payload.size * 5 / 4)
//    //We break the payload into int (4 bytes)
//    val chunk = ByteArray(4)
//    var chunkIndex = 0
//    for (i in payload.indices) {
//        val currByte = payload[i]
//        chunk[chunkIndex++] = currByte
//
//        if (chunkIndex == 4) {
//            val value = byteToInt(chunk)
//            //Because all-zero data is quite common, an exception is made for the sake of data compression,
//            //and an all-zero group is encoded as a single character "z" instead of "!!!!!".
//            stringBuff.append(
//                    if (value == 0) 'z'
//                    else encodeChunk(value))
//
//            Arrays.fill(chunk, 0.toByte())
//            chunkIndex = 0
//        }
//    }
//
//    //If we didn't end on 0, then we need some padding
//    if (chunkIndex > 0) {
//        val numPadded = chunk.size - chunkIndex
//        Arrays.fill(chunk, chunkIndex, chunk.size, 0.toByte())
//        val value = byteToInt(chunk)
//        val encodedChunk = encodeChunk(value)
//        for (i in 0 until encodedChunk.size - numPadded)
//            stringBuff.append(encodedChunk[i])
//    }
//
//    return stringBuff.toString()
//}
//
//private fun encodeChunk(value: Int): CharArray {
//    var value = value
//    val encodedChunk = CharArray(5)
//    for (i in encodedChunk.indices) {
//        encodedChunk[i] = (value / BASE85_POW[4 - i] + ASCII_SHIFT).toChar()
//        value %= BASE85_POW[4 - i]
//    }
//    return encodedChunk
//}
//
///**
// * This is a very simple base85 decoder. It respects the 'z' optimization for empty chunks, &
// * strips whitespace between characters to respect line limits.
// *
// * @see <a href="https://en.wikipedia.org/wiki/Ascii85">Ascii85</a>
// * @param chars The input characters that are base85 encoded.
// * @return The binary data decoded from the input
// */
//fun decode(chars: String): ByteArray {
//    var c = chars
//    if (c.isEmpty())
//        throw IllegalArgumentException("You must provide a non-zero length input")
//
//    //By using five ASCII characters to represent four bytes of binary data the encoded size ¹⁄₄ is larger than the original
//    val bytebuff = ByteBuffer.allocate(chars.length * 4 / 5)
//    //1. Whitespace characters may occur anywhere to accommodate line length limitations. So lets strip it.
//    c = REMOVE_WHITESPACE.matcher(chars).replaceAll("");
//    //Since Base85 is an ascii encoder, we don't need to get the bytes as UTF-8.
//    val payload = c.toByteArray(StandardCharsets.US_ASCII)
//    val chunk = ByteArray(5, { 0 })
//    var chunkIndex = 0
//    for (i in 0 until payload.size) {
//        val currByte = payload [i]
//        //Because all-zero data is quite common, an exception is made for the sake of data compression,
//        //and an all-zero group is encoded as a single character "z" instead of "!!!!!".
//        if (currByte == 'z'.toByte()) {
//            if (chunkIndex > 0)
//                throw IllegalArgumentException("The payload is not base 85 encoded.");
//
//            chunk[chunkIndex++] = '!'.toByte()
//            chunk[chunkIndex++] = '!'.toByte()
//            chunk[chunkIndex++] = '!'.toByte()
//            chunk[chunkIndex++] = '!'.toByte()
//            chunk[chunkIndex++] = '!'.toByte()
//        } else
//            chunk[chunkIndex++] = currByte
//
//
//        if (chunkIndex == 5) {
//            bytebuff.put(decodeChunk(chunk))
//            Arrays.fill(chunk, 0)
//            chunkIndex = 0
//        }
//    }
//
//    //If we didn't end on 0, then we need some padding
//    if (chunkIndex > 0) {
//        val numPadded = chunk.size - chunkIndex
//        Arrays.fill(chunk, chunkIndex, chunk.size, 'u'.toByte())
//        val paddedDecode = decodeChunk(chunk)
//        for (i in 0 until paddedDecode.size - numPadded) {
//            bytebuff.put(paddedDecode[i]);
//        }
//    }
//
//    bytebuff.flip();
//    return Arrays.copyOf(bytebuff.array(), bytebuff.limit());
//}
//
//private fun decodeChunk(chunk: ByteArray): ByteArray {
//    if (chunk.size != 5)
//        throw IllegalArgumentException("You can only decode chunks of size 5.")
//
//    var value = 0
//    value += (chunk[0] - ASCII_SHIFT) * BASE85_POW[4]
//    value += (chunk[1] - ASCII_SHIFT) * BASE85_POW[3]
//    value += (chunk[2] - ASCII_SHIFT) * BASE85_POW[2]
//    value += (chunk[3] - ASCII_SHIFT) * BASE85_POW[1]
//    value += (chunk[4] - ASCII_SHIFT) * BASE85_POW[0]
//
//    return intToByte(value)
//}
//
//private fun byteToInt(value: ByteArray?): Int {
//    if (value == null || value.size != 4)
//        throw IllegalArgumentException("You cannot create an int without exactly 4 bytes.")
//
//    return ByteBuffer.wrap(value).int
//}
//
//private fun intToByte(value: Int) = byteArrayOf(value.ushr(24).toByte(), value.ushr(16).toByte(), value.ushr(8).toByte(), value.toByte())
//
//fun main(args: Array<String>) {
//
//    val string = "Man is distinguished, not only by his reason, but by this singular passion from other animals, " +
//            "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable " +
//            "generation of knowledge, exceeds the short vehemence of any carnal pleasure."
//
//    println(encode(string.toByteArray()))
//
//    println(String(decode(encode(string.toByteArray()))))
//}