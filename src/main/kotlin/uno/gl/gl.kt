package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL
import com.jogamp.opengl.GL.*
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT
import com.jogamp.opengl.GL3
import glm.L
import uno.buffer.byteBufferBig
import uno.buffer.destroy
import uno.buffer.intBufferBig
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * Created by elect on 05/03/17.
 */

inline infix fun GLWindow.gl3(crossinline inject: GL3.() -> Unit) {
    invoke(false) { glAutoDrawable ->
        glAutoDrawable.gl.gL3.inject()
        false
    }
}

fun checkError(gl: GL, location: String) {

    val error = gl.glGetError()
    if (error != GL_NO_ERROR) {
        val errorString: String
        when (error) {
            GL_INVALID_ENUM -> errorString = "GL_INVALID_ENUM"
            GL_INVALID_VALUE -> errorString = "GL_INVALID_VALUE"
            GL_INVALID_OPERATION -> errorString = "GL_INVALID_OPERATION"
            GL_INVALID_FRAMEBUFFER_OPERATION -> errorString = "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL_OUT_OF_MEMORY -> errorString = "GL_OUT_OF_MEMORY"
            else -> errorString = "UNKNOWN"
        }
        throw Error("OpenGL Error($errorString): $location")
    }
}

