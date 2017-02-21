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


fun floatBufferOf(vararg elements: Float): FloatBuffer = GLBuffers.newDirectFloatBuffer(elements)
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