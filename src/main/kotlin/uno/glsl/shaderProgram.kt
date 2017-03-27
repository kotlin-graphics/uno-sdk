package uno.glsl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram

/**
 * Created by GBarbieri on 22.02.2017.
 */

val ShaderProgram.name
    get() = program()

fun ShaderProgram.add(gl:GL2ES2, shader: ShaderCode) = add(gl, shader, System.err)

fun ShaderProgram.link(gl:GL2ES2) = link(gl, System.err)