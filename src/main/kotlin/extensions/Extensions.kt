package extensions

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram
import com.sun.xml.internal.fastinfoset.util.StringArray

/**
 * Created by GBarbieri on 27.01.2017.
 */

val ShaderProgram.name
    get() = program()

fun ShaderCode.create(gl: GL2ES2, type: Int, context: Class<*>, sourceFiles: Array<String>)
        = ShaderCode.create(gl, type, sourceFiles.size, context, sourceFiles, false)