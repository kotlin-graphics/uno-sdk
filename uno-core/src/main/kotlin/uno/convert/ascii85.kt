package uno.convert

import glm_.b
import glm_.i
import uno.glm.toBidDec
import unsigned.toULong
import java.nio.ByteBuffer

object Ascii85 {

    private val ASCII_SHIFT = 33

    private val BASE85_POW = intArrayOf(
            1,
            85,
            85 * 85,
            85 * 85 * 85,
            85 * 85 * 85 * 85)

    fun encode(payload: ByteArray): String {

        if (payload.isEmpty()) throw IllegalArgumentException("You must provide a non-zero length input")

        /*  By using five ASCII characters to represent four bytes of binary data the encoded size ¹⁄₄ is larger than
            the original    */
        val stringBuff = StringBuilder(payload.size * 5 / 4)
        //  We break the payload into int (4 bytes)
        val chunk = ByteArray(4)
        var chunkIndex = 0
        for (currByte in payload) {

            chunk[chunkIndex++] = currByte

            if (chunkIndex == 4) {

                val value = chunk.int
                /*  Because all-zero data is quite common, an exception is made for the sake of data compression, and an
                    all-zero group is encoded as a single character "z" instead of "!!!!!". */
                if (value == 0)
                    stringBuff.append('z')
                else
                    stringBuff.append(encodeChunk(value))

                chunk.fill(0)
                chunkIndex = 0
            }
        }

        //  If we didn't end on 0, then we need some padding
        if (chunkIndex > 0) {

            val numPadded = chunk.size - chunkIndex
            chunk.fill(0, chunkIndex, chunk.size)
            val value = chunk.int
            val encodedChunk = encodeChunk(value)
            for (i in 0 until encodedChunk.size - numPadded)
                stringBuff.append(encodedChunk[i])
        }

        return stringBuff.toString()
    }

    private fun encodeChunk(value: Int): CharArray {
        //  transform value to unsigned long
        var longValue = value.toULong()
        val encodedChunk = CharArray(5)
        for (i in encodedChunk.indices) {
            encodedChunk[i] = (longValue / BASE85_POW[4 - i] + ASCII_SHIFT).toChar()
            longValue %= BASE85_POW[4 - i]
        }
        return encodedChunk
    }

    /**
     * This is a very simple base85 decoder. It respects the 'z' optimization for empty chunks, and strips whitespace
     * between characters to respect line limits.
     *
     * @param chars The input characters that are base85 encoded.
     * @return The binary data decoded from the input
     * @see [Ascii85](https://en.wikipedia.org/wiki/Ascii85)
     */
    fun decode(chars: String): ByteArray {

        if (chars.isEmpty()) throw IllegalArgumentException("You must provide a non-zero length input")

        /*  By using five ASCII characters to represent four bytes of binary data the encoded size ¹⁄₄ is larger than
            the original    */
        val decodedLength = chars.length.toBidDec() * 4.toBidDec() / 5.toBidDec()
        val buffer = ByteBuffer.allocate(decodedLength.i)
        val payload = chars
                /*  Whitespace and new lines characters may occur anywhere to accommodate line length limitations.
                    So lets strip it.   */
                .filter { it != ' ' && it != '\n' }
                .toByteArray()  //  Since Base85 is an ascii encoder, we don't need to get the bytes as UTF-8.

        val chunk = ByteArray(5)
        var chunkIndex = 0
        for (byte in payload) {
            /*  Because all-zero data is quite common, an exception is made for the sake of data compression, and an
                all-zero group is encoded as a single character "z" instead of "!!!!!". */
            if (byte == 'z'.b) {

                if (chunkIndex > 0) throw IllegalArgumentException("The payload is not base 85 encoded.")

                chunk[chunkIndex++] = '!'.b
                chunk[chunkIndex++] = '!'.b
                chunk[chunkIndex++] = '!'.b
                chunk[chunkIndex++] = '!'.b
                chunk[chunkIndex++] = '!'.b

            } else
                chunk[chunkIndex++] = byte

            if (chunkIndex == 5) {
                buffer.put(decodeChunk(chunk))
                chunk.fill(0)
                chunkIndex = 0
            }
        }

        //If we didn't end on 0, then we need some padding
        if (chunkIndex > 0) {

            val numPadded = chunk.size - chunkIndex
            chunk.fill('u'.b, chunkIndex, chunk.size)
            val paddedDecode = decodeChunk(chunk)
            for (i in 0 until paddedDecode.size - numPadded)
                buffer.put(paddedDecode[i])
        }

        buffer.flip()
        return buffer.array().copyOf(buffer.limit())
    }

    private fun decodeChunk(chunk: ByteArray): ByteArray {

        if (chunk.size != 5) throw IllegalArgumentException("You can only decode chunks of size 5.")

        return (0..4)
                .sumBy { (chunk[it] - ASCII_SHIFT) * BASE85_POW[4 - it] }
                .byteArray
    }

    private val ByteArray.int: Int
        get() {
            if (size != 4) throw IllegalArgumentException("You cannot create an int without exactly 4 bytes.")
            return ByteBuffer.wrap(this).int
        }

    private val Int.byteArray get() = byteArrayOf(ushr(24).b, ushr(16).b, ushr(8).b, b)
}