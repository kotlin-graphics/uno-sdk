package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL
import com.jogamp.opengl.GL.*
import com.jogamp.opengl.GL2ES3
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER
import com.jogamp.opengl.GL3
import glm.L
import uno.buffer.byteBufferBig
import uno.buffer.destroy
import uno.buffer.intBufferBig
import java.nio.ByteBuffer

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

/** This object can only be constructed after an OpenGL context has been created and initialized.   */
class UniformBlockArray @JvmOverloads constructor(gl: GL3, uboSize: Int, private val arrayCount: Int, private var blockOffset: Int = 0) {

    private val storage: ByteBuffer
    val size
        get() = arrayCount
    /** The array offset should be multiplied by the array index to get the offset for a particular element. */
    val arrayOffset
        get() = blockOffset

    init {
        val uniformBufferAlignSize = intBufferBig(1)
        gl.glGetIntegerv(GL2ES3.GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT, uniformBufferAlignSize)

        blockOffset = uboSize
        blockOffset += uniformBufferAlignSize[0] - (blockOffset % uniformBufferAlignSize[0])

        val sizeMaterialUniformBuffer = blockOffset * arrayCount

        storage = byteBufferBig(arrayCount * blockOffset)

        uniformBufferAlignSize.destroy()
    }

    fun createBufferObject(gl: GL3): Int = with(gl) {

        val bufferObject = intBufferBig(1)
        glGenBuffers(1, bufferObject)
        glBindBuffer(GL_UNIFORM_BUFFER, bufferObject[0])
        glBufferData(GL_UNIFORM_BUFFER, storage.capacity().L, storage, GL_STATIC_DRAW)
        glBindBuffer(GL_UNIFORM_BUFFER, 0)

        val result = bufferObject[0]
        bufferObject.destroy()

        return result
    }

    operator fun set(blockIndex: Int, buffer: ByteBuffer) {
        for(i in 0 until buffer.capacity())
            storage.put(blockIndex * blockOffset + i, buffer.get(i))
    }
}