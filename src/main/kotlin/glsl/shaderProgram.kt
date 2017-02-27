package glsl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.glsl.ShaderProgram

/**
 * Created by GBarbieri on 22.02.2017.
 */

fun programOf(gl: GL2ES2, context: Class<*>, vararg strings: String): Int {

    val shaders =
            if (strings[0].contains('.'))
                strings.toList()
            else {
                val root = if (strings[0].endsWith('/')) strings[0] else strings[0] + '/'
                strings.drop(1).map { root + it }
            }

    val shaderProgram = ShaderProgram()

    val shaderCodes = shaders.map { shaderCodeOf(it, gl, context) }

    shaderCodes.forEach { shaderProgram.add(gl, it, System.err) }

    shaderProgram.link(gl, System.err)

    shaderCodes.forEach {
        for (i in 0 until it.shader().capacity()) {
            gl.glDetachShader(shaderProgram.program(), it.shader()[i])
            gl.glDeleteShader(it.shader()[i])
        }
    }

    return shaderProgram.program()
}