package uno.gl

import com.jogamp.opengl.GL.GL_STATIC_DRAW
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT
import com.jogamp.opengl.GL3
import glm.L
import uno.buffer.byteBufferBig
import uno.buffer.destroy
import uno.buffer.intBufferBig
import java.nio.ByteBuffer

/**
 * Created by GBarbieri on 29.03.2017.
 */

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
        gl.glGetIntegerv(GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT, uniformBufferAlignSize)

        blockOffset = uboSize
        blockOffset += uniformBufferAlignSize[0] - (blockOffset % uniformBufferAlignSize[0])

        val sizeMaterialUniformBuffer = blockOffset * arrayCount

        storage = byteBufferBig(arrayCount * blockOffset)

        uniformBufferAlignSize.destroy()
    }

    fun createBufferObject(gl: GL3): Int = with(gl) {

        val bufferObject = intBufferBig(1)
        glGenBuffers(1, bufferObject)

        uploadBufferObject(gl, bufferObject[0])

        val result = bufferObject[0]
        bufferObject.destroy()

        return result
    }

    fun uploadBufferObject(gl: GL3, bufferName: Int) = with(gl) {
        glBindBuffer(GL_UNIFORM_BUFFER, bufferName)
        glBufferData(GL_UNIFORM_BUFFER, storage.capacity().L, storage, GL_STATIC_DRAW)
        glBindBuffer(GL_UNIFORM_BUFFER, 0)
    }

    operator fun set(blockIndex: Int, buffer: ByteBuffer) {
        for (i in 0 until buffer.capacity())
            storage.put(blockIndex * blockOffset + i, buffer.get(i))
    }

    fun dispose() = storage.destroy()
}