package glsl

/**
 * Created by GBarbieri on 24.01.2017.
 */


import com.jogamp.common.net.Uri
import com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER
import com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER
import com.jogamp.opengl.GL3
import com.jogamp.opengl.GL3ES3.*
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram
import extensions.uri

/**

 * @author GBarbieri
 */
class Program {

    var name: Int = 0
    val uniforms = HashMap<String, Int>()

    constructor(gl: GL3, shadersRoot: String, shadersSrc: String) : this(gl, shadersRoot, shadersSrc, shadersSrc)

    constructor(
            gl: GL3,
            shadersRoot: String,
            shadersSrc: String,
            replaceVertOld: Array<String>,
            replaceVertNew: Array<String>,
            replaceFragOld: Array<String>,
            replaceFragNew: Array<String>)

            : this(gl, shadersRoot, shadersSrc, shadersSrc, replaceVertOld, replaceVertNew, replaceFragOld, replaceFragNew)


    @JvmOverloads constructor(
            gl: GL3,
            shadersRoot: String,
            vertSrc: String,
            fragSrc: String,
            replaceVertOld: Array<String>? = null,
            replaceVertNew: Array<String>? = null,
            replaceFragOld: Array<String>? = null,
            replaceFragNew: Array<String>? = null) {

        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, javaClass, shadersRoot, null, vertSrc,
                "vert", null, true)
        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, javaClass, shadersRoot, null, fragSrc,
                "frag", null, true)

        if (replaceVertOld != null && replaceVertNew != null)
            repeat(replaceVertOld.size, { vertShader.replaceInShaderSource(replaceVertOld[it], replaceVertNew[it]) })

        if (replaceFragOld != null && replaceFragNew != null)
            repeat(replaceFragOld.size, { fragShader.replaceInShaderSource(replaceFragOld[it], replaceFragNew[it]) })

        val shaderProgram = ShaderProgram()

        shaderProgram.add(vertShader)
        shaderProgram.add(fragShader)

        shaderProgram.link(gl, System.err)

        vertShader.destroy(gl)
        fragShader.destroy(gl)

        name = shaderProgram.program()
    }

    constructor(gl: GL3, vertUri: Uri, fragUri: Uri) {

        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, 1, arrayOf(vertUri), false)
        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, 1, arrayOf(fragUri), false)

        val shaderProgram = ShaderProgram()

        shaderProgram.add(vertShader)
        shaderProgram.add(fragShader)

        shaderProgram.link(gl, System.err)

        vertShader.destroy(gl)
        fragShader.destroy(gl)

        name = shaderProgram.program()
    }

    constructor(gl: GL3, shaders: Array<String>, uniforms: Array<String> = arrayOf()) {

        val shaderProgram = ShaderProgram()

        val shaderCodes = shaders.map { ShaderCode.create(gl, it.type, 1,
                arrayOf(Uri.valueOf(javaClass.classLoader.getResource(it))), false) }
        shaderCodes.forEach { shaderProgram.add(it) }

        shaderProgram.link(gl, System.err)

        name = shaderProgram.program()

        shaderCodes.forEach { it.destroy(gl) }

        uniforms.forEach {
            val i = gl.glGetUniformLocation(name, it)
            if (i != -1)
                this.uniforms[it] = i
        }
    }

    val String.extension
        get() = substring(lastIndexOf('.') + 1)

    /**
     * https://www.khronos.org/opengles/sdk/tools/Reference-Compiler/
     *
     * .vert - a vertex shader
     * .tesc - a tessellation control shader
     * .tese - a tessellation evaluation shader
     * .geom - a geometry shader
     * .frag - a fragment shader
     * .comp - a compute shader     */
    val String.type
        get() = when (extension) {
            "vert" -> GL_VERTEX_SHADER
            "tesc" -> GL_TESS_CONTROL_SHADER
            "tese" -> GL_TESS_EVALUATION_SHADER
            "geom" -> GL_GEOMETRY_SHADER
            "frag" -> GL_FRAGMENT_SHADER
            "comp" -> GL_COMPUTE_SHADER
            else -> throw Error("invalid shader extension")
        }

    operator fun get(s: String) = uniforms[s]
}