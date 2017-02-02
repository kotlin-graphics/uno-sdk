package extensions

import com.jogamp.common.net.Uri
import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram

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

fun String.uri(context: Class<*>) = Uri.valueOf(context.getResource(this))
