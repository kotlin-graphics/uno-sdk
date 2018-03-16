package uno.glsl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER
import com.jogamp.opengl.GL3ES3.*
import com.jogamp.opengl.util.glsl.ShaderCode

/**
 * Created by GBarbieri on 21.02.2017.
 */

fun shaderCodeOf(gl: GL2ES2, context: Class<*>, shader: String): ShaderCode {
    val root = if (shader.contains('/')) shader.substringBeforeLast('/') else ""
    val name = shader.substringAfterLast('/').substringBeforeLast('.')
    val extension = shader.substringAfterLast('.')
    return ShaderCode.create(gl, shader.type, context, root, null, name, extension, null, true)
}

fun shaderCodeOf(gl: GL2ES2, context: Class<*>, root: String, shader: String): ShaderCode {
    val name = shader.substringBeforeLast('.')
    val extension = shader.substringAfterLast('.')
    return ShaderCode.create(gl, shader.type, context, root, null, name, extension, null, true)
}

fun ShaderCode.create(gl: GL2ES2, type: Int, context: Class<*>, sourceFiles: Array<String>): ShaderCode
        = ShaderCode.create(gl, type, sourceFiles.size, context, sourceFiles, false)

/**
 * https://www.khronos.org/opengles/sdk/tools/Reference-Compiler/
 *
 * .vert - a vertex shader
 * .tesc - a tessellation control shader
 * .tese - a tessellation evaluation shader
 * .geom - a geometry shader
 * .frag - a fragment shader
 * .comp - a compute shader     */
private val String.type
    get() = when (substringAfterLast('.')) {
        "vert" -> GL_VERTEX_SHADER
        "tesc" -> GL_TESS_CONTROL_SHADER
        "tese" -> GL_TESS_EVALUATION_SHADER
        "geom" -> GL_GEOMETRY_SHADER
        "frag" -> GL_FRAGMENT_SHADER
        "comp" -> GL_COMPUTE_SHADER
        else -> throw Error("invalid shader extension")
    }