package glsl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER
import com.jogamp.opengl.GL3ES3.*
import com.jogamp.opengl.util.glsl.ShaderCode

/**
 * Created by GBarbieri on 21.02.2017.
 */

/**
 * https://www.khronos.org/opengles/sdk/tools/Reference-Compiler/
 *
 * .vert - a vertex shader
 * .tesc - a tessellation control shader
 * .tese - a tessellation evaluation shader
 * .geom - a geometry shader
 * .frag - a fragment shader
 * .comp - a compute shader
 * */
fun shaderCodeOf(string: String, gl: GL2ES2, context: Class<*>): ShaderCode =
        ShaderCode.create(
                gl,
                when (string.substringAfterLast('.')) { // type
                    "vert" -> GL_VERTEX_SHADER
                    "tesc" -> GL_TESS_CONTROL_SHADER
                    "tese" -> GL_TESS_EVALUATION_SHADER
                    "geom" -> GL_GEOMETRY_SHADER
                    "frag" -> GL_FRAGMENT_SHADER
                    "comp" -> GL_COMPUTE_SHADER
                    else -> throw Error("invalid shader extension")
                },
                context,
                if (string.contains('/')) string.substringBeforeLast('/') else "", // root
                null, // binary root
                string.substringAfterLast('/').substringBefore('.'), // src
                string.substringAfterLast('.'), // extension
                null, // binary extension
                true)   // mutable builder

