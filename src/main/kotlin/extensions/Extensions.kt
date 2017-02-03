package extensions

import com.jogamp.common.net.Uri
import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.GLBuffers
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram
import java.nio.FloatBuffer

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

val String.url
        get() = javaClass.getResource(this)

fun floatBufferOf(vararg elements: Float) = GLBuffers.newDirectFloatBuffer(elements)
fun floatBufferOf(vararg elements: Number) = GLBuffers.newDirectFloatBuffer(elements.map(Number::toFloat).toFloatArray())
fun intBufferOf(vararg elements: Int) = GLBuffers.newDirectIntBuffer(elements)
fun intBufferOf(vararg elements: Number) = GLBuffers.newDirectIntBuffer(elements.map(Number::toInt).toIntArray())
fun shortBufferOf(vararg elements: Short) = GLBuffers.newDirectShortBuffer(elements)
fun shortBufferOf(vararg elements: Number) = GLBuffers.newDirectShortBuffer(elements.map(Number::toShort).toShortArray())