package uno.glsl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.glsl.ShaderProgram

/**
 * Created by elect on 05/03/17.
 */

@JvmOverloads fun programOf(gl: GL2ES2, context: Class<*>, vararg strings: String, bindAttribLocation: Map<String, Int> = emptyMap()): Int {

    val shaders =
            if (strings[0].contains('.'))
                strings.toList()
            else {
                val root = if (strings[0].endsWith('/')) strings[0] else strings[0] + '/'
                strings.drop(1).map { root + it }
            }

    val shaderProgram = ShaderProgram()
    shaderProgram.init(gl)

    val name = shaderProgram.program()

    val shaderCodes = shaders.map { shaderCodeOf(gl, context, it) }

    shaderCodes.forEach { shaderProgram.add(gl, it) }

    bindAttribLocation.forEach { attr, location -> gl.glBindAttribLocation(name, location, attr) }

    shaderProgram.link(gl)

    shaderCodes.forEach {
        for (i in 0 until it.shader().capacity()) {
            gl.glDetachShader(name, it.shader()[i])
            gl.glDeleteShader(it.shader()[i])
        }
    }

    return name
}