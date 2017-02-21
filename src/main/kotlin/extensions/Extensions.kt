package extensions

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.GLBuffers
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram
import main.BYTES
import java.net.URL
import java.nio.*

/**
 * Created by GBarbieri on 27.01.2017.
 */

val ShaderProgram.name
    get() = program()

fun ShaderCode.create(gl: GL2ES2, type: Int, context: Class<*>, sourceFiles: Array<String>)
        = ShaderCode.create(gl, type, sourceFiles.size, context, sourceFiles, false)

fun createShaderProgram(gl: GL2ES2, context: Class<*>, shaderSrc: String): ShaderProgram {

    val program = ShaderProgram()

    val vertShader = ShaderCode.create(gl, GL2ES2.GL_VERTEX_SHADER, 1, context, arrayOf("$shaderSrc.vert"), false)
    val fragShader = ShaderCode.create(gl, GL2ES2.GL_FRAGMENT_SHADER, 1, context, arrayOf("$shaderSrc.frag"), false)

    program.add(vertShader)
    program.add(fragShader)

    program.init(gl)

    program.link(gl, System.err)

    vertShader.destroy(gl)
    fragShader.destroy(gl)

    return program
}

class ShaderProgramUtil() {
    companion object {
        @JvmStatic fun create(gl: GL2ES2, context: Class<*>, shaderSrc: String): ShaderProgram =
                createShaderProgram(gl, context, shaderSrc)
    }
}

fun String.URL(clazz: Class<*>): URL = clazz.classLoader.getResource(this)


fun FloatArray.toFloatBuffer(): FloatBuffer = GLBuffers.newDirectFloatBuffer(this)
fun FloatArray.toByteBuffer(): ByteBuffer {
    val res = byteBufferBig(size * Float.BYTES)
    forEachIndexed { i, f -> res.putFloat(i * Float.BYTES, f) }
    return res
}

fun DoubleArray.toDoubleBuffer(): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(this)
fun DoubleArray.toByteBuffer(): ByteBuffer {
    val res = byteBufferBig(size * Double.BYTES)
    forEachIndexed { i, d -> res.putDouble(i * Double.BYTES, d) }
    return res
}

fun ByteArray.toByteBuffer(): ByteBuffer = GLBuffers.newDirectByteBuffer(this)

fun ShortArray.toShortBuffer(): ShortBuffer = GLBuffers.newDirectShortBuffer(this)
fun ShortArray.toByteBuffer(): ByteBuffer {
    val res = byteBufferBig(size * Short.BYTES)
    forEachIndexed { i, s -> res.putShort(i * Short.BYTES, s) }
    return res
}

fun IntArray.toIntBuffer(): IntBuffer = GLBuffers.newDirectIntBuffer(this)
fun IntArray.toByteBuffer(): ByteBuffer {
    val res = byteBufferBig(size * Int.BYTES)
    forEachIndexed { i, int -> res.putInt(i * Int.BYTES, int) }
    return res
}

fun LongArray.toLongBuffer(): LongBuffer = GLBuffers.newDirectLongBuffer(this)
fun LongArray.toByteBuffer(): ByteBuffer {
    val res = byteBufferBig(size * Long.BYTES)
    forEachIndexed { i, long -> res.putLong(i * Long.BYTES, long) }
    return res
}

fun floatBufferOf(vararg floats: Float): FloatBuffer = GLBuffers.newDirectFloatBuffer(floats)
fun floatBufferOf(vararg elements: Number): FloatBuffer = GLBuffers.newDirectFloatBuffer(elements.map(Number::toFloat).toFloatArray())

fun doubleBufferOf(vararg elements: Double): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(elements)
fun doubleBufferOf(vararg elements: Number): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(elements.map(Number::toDouble).toDoubleArray())

fun byteBufferOf(vararg elements: Byte): ByteBuffer = GLBuffers.newDirectByteBuffer(elements)
fun byteBufferOf(vararg elements: Number): ByteBuffer = GLBuffers.newDirectByteBuffer(elements.map(Number::toByte).toByteArray())

fun shortBufferOf(vararg elements: Short): ShortBuffer = GLBuffers.newDirectShortBuffer(elements)
fun shortBufferOf(vararg elements: Number): ShortBuffer = GLBuffers.newDirectShortBuffer(elements.map(Number::toShort).toShortArray())

fun intBufferOf(vararg elements: Int): IntBuffer = GLBuffers.newDirectIntBuffer(elements)
fun intBufferOf(vararg elements: Number): IntBuffer = GLBuffers.newDirectIntBuffer(elements.map(Number::toInt).toIntArray())

fun longBufferOf(vararg elements: Long): LongBuffer = GLBuffers.newDirectLongBuffer(elements)
fun longBufferOf(vararg elements: Number): LongBuffer = GLBuffers.newDirectLongBuffer(elements.map(Number::toLong).toLongArray())


fun floatBufferBig(size: Int): FloatBuffer = GLBuffers.newDirectFloatBuffer(size)
fun doubleBufferBig(size: Int): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(size)

fun byteBufferBig(size: Int): ByteBuffer = GLBuffers.newDirectByteBuffer(size)
fun shortBufferBig(size: Int): ShortBuffer = GLBuffers.newDirectShortBuffer(size)
fun intBufferBig(size: Int): IntBuffer = GLBuffers.newDirectIntBuffer(size)
fun longBufferBig(size: Int): LongBuffer = GLBuffers.newDirectLongBuffer(size)