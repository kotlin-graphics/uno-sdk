package uno.gln

import glm_.L
import glm_.mat4x4.Mat4
import glm_.set
import org.lwjgl.opengl.ARBUniformBufferObject.GL_UNIFORM_BUFFER
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjgl.system.MemoryUtil.NULL
import uno.gl.iBuf
import uno.gl.m4Buf
import java.nio.*

/**
 * Created by elect on 18/04/17.
 */


fun glBufferData(target: Int, size: Int, usage: Int) = nglBufferData(target, size.L, NULL, usage);

// ----- Mat4 -----
fun glBufferData(target: Int, mat: Mat4, usage: Int) = glBufferData(target, mat to m4Buf, usage)

fun glBufferSubData(target: Int, offset: Int, mat4: Mat4) = glBufferSubData(target, offset.L, mat4 to m4Buf)
fun glBufferSubData(target: Int, mat: Mat4) = glBufferSubData(target, 0, mat to m4Buf)

fun glBindBuffer(target: Int) = glBindBuffer(target, 0)
fun glBindBuffer(target: Int, buffer: IntBuffer) = glBindBuffer(target, buffer[0])

fun glBindBufferRange(target: Int, index: Int, buffer: IntBuffer, offset: Int, size: Int) =
        glBindBufferRange(target, index, buffer[0], offset.L, size.L)

fun glBindBufferBase(target: Int, index: Int) = glBindBufferBase(target, index, 0)

inline fun initArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_ARRAY_BUFFER, block)
}

inline fun initElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_ELEMENT_ARRAY_BUFFER, block)
}

inline fun initUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_UNIFORM_BUFFER, block)
}

inline fun initUniformBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    Buffers.target = GL_UNIFORM_BUFFER
    glGenBuffers(buffers)
    Buffers.buffers = buffers
    Buffers.block()
    glBindBuffer(GL_UNIFORM_BUFFER, 0)
}

inline fun initBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    glGenBuffers(buffers)
    Buffers.buffers = buffers
    Buffers.block()
}

inline fun initBuffer(target: Int, block: Buffer.() -> Unit): Int {
    Buffer.target = target
    glGenBuffers(iBuf)
    val name = iBuf[0]
    Buffer.name = name // bind
    Buffer.block()
    glBindBuffer(target, 0)
    return name
}

inline fun withBuffer(target: Int, buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(target, buffer[0], block)
inline fun withBuffer(target: Int, buffer: Int, block: Buffer.() -> Unit) {
    Buffer.target = target
    Buffer.name = buffer   // bind
    Buffer.block()
    glBindBuffer(target, 0)
}

inline fun withArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_ARRAY_BUFFER, buffer[0], block)
inline fun withArrayBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_ARRAY_BUFFER, buffer, block)
inline fun withElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer[0], block)
inline fun withElementBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer, block)
inline fun withUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_UNIFORM_BUFFER, buffer[0], block)
inline fun withUniformBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_UNIFORM_BUFFER, buffer, block)

object Buffer {

    var target = 0
    var name = 0
        set(value) {
            glBindBuffer(target, value)
            field = value
        }

    fun data(data: ByteBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: ShortBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: IntBuffer, usage: Int) = glBufferData(target, data, usage)
    //fun data(data: LongBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: FloatBuffer, usage: Int) = glBufferData(target, data, usage)

    fun data(data: DoubleBuffer, usage: Int) = glBufferData(target, data, usage)

    fun data(size: Int, usage: Int) = glBufferData(target, size.L, usage)

    fun subData(offset: Int, data: ByteBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: ShortBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: IntBuffer) = glBufferSubData(target, offset.L, data)
    //fun subData(offset: Int, data: LongBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: FloatBuffer) = glBufferSubData(target, offset.L, data)

    fun subData(offset: Int, data: DoubleBuffer) = glBufferSubData(target, offset.L, data)

    fun subData(data: ByteBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: ShortBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: IntBuffer) = glBufferSubData(target, 0, data)
    //fun subData(data: LongBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: FloatBuffer) = glBufferSubData(target, 0, data)

    fun subData(data: DoubleBuffer) = glBufferSubData(target, 0, data)


    // ----- Mat4 -----
    fun data(mat: Mat4, usage: Int) = glBufferData(target, mat to m4Buf, usage)

    fun subData(offset: Int, mat: Mat4) = glBufferSubData(target, offset.L, mat to m4Buf)
    fun subData(mat: Mat4) = glBufferSubData(target, 0, mat to m4Buf)


    fun bindRange(index: Int, offset: Int, size: Int) = glBindBufferRange(target, index, name, offset.L, size.L)
    fun bindBase(index: Int) = glBindBufferBase(target, index, 0)

    fun mapRange(length: Int, access: Int) = mapRange(0, length, access)
    fun mapRange(offset: Int, length: Int, access: Int): ByteBuffer = glMapBufferRange(target, offset.L, length.L, access)
}

object Buffers {

    lateinit var buffers: IntBuffer
    var target = 0
    var name = 0

    fun data(data: ByteBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: ShortBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: IntBuffer, usage: Int) = glBufferData(target, data, usage)
    //fun data(data: LongBuffer, usage: Int) = glBufferData(target, data, usage)
    fun data(data: FloatBuffer, usage: Int) = glBufferData(target, data, usage)

    fun data(data: DoubleBuffer, usage: Int) = glBufferData(target, data, usage)

    fun data(size: Int, usage: Int) = glBufferData(target, size.L, usage)

    fun subData(offset: Int, data: ByteBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: ShortBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: IntBuffer) = glBufferSubData(target, offset.L, data)
    //fun subData(offset: Int, data: LongBuffer) = glBufferSubData(target, offset.L, data)
    fun subData(offset: Int, data: FloatBuffer) = glBufferSubData(target, offset.L, data)

    fun subData(offset: Int, data: DoubleBuffer) = glBufferSubData(target, offset.L, data)

    fun subData(data: ByteBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: ShortBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: IntBuffer) = glBufferSubData(target, 0, data)
    //fun subData(data: LongBuffer) = glBufferSubData(target, 0, data)
    fun subData(data: FloatBuffer) = glBufferSubData(target, 0, data)

    fun subData(data: DoubleBuffer) = glBufferSubData(target, 0, data)


    // ----- Mat4 -----
    fun data(mat: Mat4, usage: Int) = glBufferData(target, mat to m4Buf, usage)

    fun subData(offset: Int, mat: Mat4) = glBufferSubData(target, offset.L, mat to m4Buf)
    fun subData(mat: Mat4) = glBufferSubData(target, 0, mat to m4Buf)


    fun range(index: Int, offset: Int, size: Int) = glBindBufferRange(target, index, name, offset.L, size.L)
    fun base(index: Int) = glBindBufferBase(target, index, 0)


    inline fun at(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = target
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
    }

    inline fun withArray(block: Buffer.() -> Unit) = withArrayAt(0, block)
    inline fun withArrayAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    inline fun withElement(block: Buffer.() -> Unit) = withElementAt(0, block)
    inline fun withElementAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_ELEMENT_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    inline fun withUniform(block: Buffer.() -> Unit) = withUniformAt(0, block)
    inline fun withUniformAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_UNIFORM_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GL15.glBindBuffer(GL_UNIFORM_BUFFER, 0)
    }
}

inline fun mappingUniformBufferRange(buffer: Int, length: Int, access: Int, block: MappedBuffer.() -> Unit) {
    MappedBuffer.target = GL_UNIFORM_BUFFER
    MappedBuffer.name = buffer    // bind
    MappedBuffer.mapRange(length, access)
    MappedBuffer.block()
    glUnmapBuffer(GL_UNIFORM_BUFFER)
    GL15.glBindBuffer(GL_UNIFORM_BUFFER, 0)
}

object MappedBuffer {

    var target = 0
    var name = 0
        set(value) {
            glBindBuffer(target, value)
            field = value
        }

    fun mapRange(length: Int, access: Int) {
        _pointer = GL30.glMapBufferRange(target, 0L, length.L, access)
    }

    private lateinit var _pointer: ByteBuffer

    var pointer: Any
        get() = 0
        set(value) {
            if (value is Mat4) value to _pointer
        }

}