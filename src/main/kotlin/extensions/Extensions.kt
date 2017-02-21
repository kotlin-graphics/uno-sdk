package extensions

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.GLBuffers
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram
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


fun floatBufferOf(floats: FloatArray): FloatBuffer = GLBuffers.newDirectFloatBuffer(floats)
fun floatBufferOf(elements: Array<Number>): FloatBuffer = GLBuffers.newDirectFloatBuffer(elements.map(Number::toFloat).toFloatArray())

fun doubleBufferOf(doubles: DoubleArray): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(doubles)
fun doubleBufferOf(elements: Array<Number>): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(elements.map(Number::toDouble).toDoubleArray())

fun byteBufferOf(elements: ByteArray): ByteBuffer = GLBuffers.newDirectByteBuffer(elements)
fun byteBufferOf(elements: Array<Number>): ByteBuffer = GLBuffers.newDirectByteBuffer(elements.map(Number::toByte).toByteArray())

fun shortBufferOf(elements: ShortArray): ShortBuffer = GLBuffers.newDirectShortBuffer(elements)
fun shortBufferOf(elements: Array<Number>): ShortBuffer = GLBuffers.newDirectShortBuffer(elements.map(Number::toShort).toShortArray())

fun intBufferOf(elements: IntArray): IntBuffer = GLBuffers.newDirectIntBuffer(elements)
fun intBufferOf(elements: Array<Number>): IntBuffer = GLBuffers.newDirectIntBuffer(elements.map(Number::toInt).toIntArray())

fun longBufferOf(elements: LongArray): LongBuffer = GLBuffers.newDirectLongBuffer(elements)
fun longBufferOf(elements: Array<Number>): LongBuffer = GLBuffers.newDirectLongBuffer(elements.map(Number::toLong).toLongArray())


fun floatBufferBig(size: Int): FloatBuffer = GLBuffers.newDirectFloatBuffer(size)
fun doubleBufferBig(size: Int): DoubleBuffer = GLBuffers.newDirectDoubleBuffer(size)

fun byteBufferBig(size: Int): ByteBuffer = GLBuffers.newDirectByteBuffer(size)
fun shortBufferBig(size: Int): ShortBuffer = GLBuffers.newDirectShortBuffer(size)
fun intBufferBig(size: Int): IntBuffer = GLBuffers.newDirectIntBuffer(size)
fun longBufferBig(size: Int): LongBuffer = GLBuffers.newDirectLongBuffer(size)