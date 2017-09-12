package uno.gln

import glm_.L
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.glVertexAttribPointer
import org.lwjgl.opengl.GL30.glBindVertexArray
import org.lwjgl.opengl.GL30.glGenVertexArrays
import uno.gl.iBuf
import uno.glf.VertexAttribute
import uno.glf.VertexLayout
import java.nio.IntBuffer
import kotlin.properties.Delegates

/**
 * Created by elect on 18/04/17.
 */

var vertexArrayName: IntBuffer by Delegates.notNull()


inline fun glBindVertexArray(vertexArray: Enum<*>) = glBindVertexArray(vertexArrayName[vertexArray])
inline fun glBindVertexArray(vertexArray: IntBuffer) = glBindVertexArray(vertexArray[0])

inline fun glBindVertexArray() = glBindVertexArray(0)


inline fun initVertexArrays(block: VertexArrays.() -> Unit) = initVertexArrays(vertexArrayName, block)
inline fun initVertexArrays(vertexArrays: IntBuffer, block: VertexArrays.() -> Unit) {
    glGenVertexArrays(vertexArrays)
    VertexArrays.names = vertexArrays
    VertexArrays.block()
    glBindVertexArray(0)
}

inline fun initVertexArray(vertexArray: Enum<*>, block: VertexArray.() -> Unit) = vertexArrayName.put(vertexArray.ordinal, initVertexArray(block))
inline fun initVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) = vertexArray.put(0, initVertexArray(block))
inline fun initVertexArray(block: VertexArray.() -> Unit): Int {
    glGenVertexArrays(iBuf)
    val res = iBuf[0]
    VertexArray.name = res   // bind
    VertexArray.block()
    glBindVertexArray(0)
    return res
}

inline fun withVertexArray(vertexArray: Enum<*>, block: VertexArray.() -> Unit) = withVertexArray(vertexArrayName[vertexArray], block)
inline fun withVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) = withVertexArray(vertexArray[0], block)
inline fun withVertexArray(vertexArray: Int, block: VertexArray.() -> Unit) {
    VertexArray.name = vertexArray   // bind
    VertexArray.block()
    glBindVertexArray(0)
}

object VertexArray {

    var name = 0
        set(value) = glBindVertexArray(value)

    inline fun array(array: Enum<*>, format: VertexLayout) = array(bufferName[array], format)
    inline fun array(array: IntBuffer, format: VertexLayout) = array(array[0], format)
    inline fun array(array: Int, format: VertexLayout) {
        glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (attr in format.attribute) {
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, attr.interleavedStride, attr.pointer)
        }
        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    inline fun array(array: Enum<*>, format: VertexLayout, vararg offset: Int) = array(bufferName[array], format, *offset) // TODO check, slow operator
    inline fun array(array: IntBuffer, format: VertexLayout, vararg offset: Int) = array(array[0], format, *offset) // TODO check, slow operator
    inline fun array(array: Int, format: VertexLayout, vararg offset: Int) {
        glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (i in format.attribute.indices) {
            val attr = format.attribute[i]
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, 0, offset[i].L)
        }
        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    inline fun element(element: Enum<*>) = glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferName[element])
    inline fun element(element: IntBuffer) = glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, element[0])
    inline fun element(element: Int) = glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, element)
}

object VertexArrays {

    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: VertexArray.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: VertexArray.() -> Unit) {
        VertexArray.name = names[index]   // bind
        VertexArray.block()
    }
}


inline fun withVertexLayout(array: Enum<*>, element: Enum<*>, format: VertexLayout, block: () -> Unit) = withVertexLayout(bufferName[array], bufferName[element], format, block)
inline fun withVertexLayout(array: Int, element: Int, format: VertexLayout, block: () -> Unit) {
    glBindBuffer(GL_ARRAY_BUFFER, array)
    for (attr in format.attribute) {
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, attr.interleavedStride, attr.pointer)
    }
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, element)
    block()
    for (attr in format.attribute)
        GL20.glDisableVertexAttribArray(attr.index)
}

inline fun withVertexLayout(array: Enum<*>, format: VertexLayout, block: () -> Unit) = withVertexLayout(bufferName[array], format, block)
inline fun withVertexLayout(array: IntBuffer, format: VertexLayout, block: () -> Unit) = withVertexLayout(array[0], format, block)
inline fun withVertexLayout(array: Int, format: VertexLayout, block: () -> Unit) {
    glBindBuffer(GL_ARRAY_BUFFER, array)
    for (attr in format.attribute) {
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, attr.interleavedStride, attr.pointer)
    }
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    block()
    for (attr in format.attribute) GL20.glDisableVertexAttribArray(attr.index)
}


/** For un-interleaved, that is not-interleaved */
inline fun withVertexLayout(buffer: IntBuffer, format: VertexLayout, vararg offset: Int, block: () -> Unit) {
    glBindBuffer(GL_ARRAY_BUFFER, buffer[0])
    for (i in format.attribute.indices) {
        val attr = format.attribute[i]
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, 0, offset[i].L)
    }
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    block()
    for (attr in format.attribute)
        GL20.glDisableVertexAttribArray(attr.index)
}


inline fun glEnableVertexAttribArray(layout: VertexLayout) = GL20.glEnableVertexAttribArray(layout[0].index)
inline fun glEnableVertexAttribArray(attribute: VertexAttribute) = GL20.glEnableVertexAttribArray(attribute.index)

inline fun glDisableVertexAttribArray(layout: VertexLayout) = GL20.glDisableVertexAttribArray(layout[0].index)
inline fun glDisableVertexAttribArray(attribute: VertexAttribute) = GL20.glDisableVertexAttribArray(attribute.index)


inline fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int) = glVertexAttribPointer(index, size, type, normalized, stride, 0)
inline fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int) = GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer.L)

inline fun glVertexAttribPointer(layout: VertexLayout) = glVertexAttribPointer(layout[0])
inline fun glVertexAttribPointer(attribute: VertexAttribute) =
        glVertexAttribPointer(
                attribute.index,
                attribute.size,
                attribute.type,
                attribute.normalized,
                attribute.interleavedStride,
                attribute.pointer)

inline fun glVertexAttribPointer(layout: VertexLayout, offset: Int) = glVertexAttribPointer(layout[0], offset)
inline fun glVertexAttribPointer(attribute: VertexAttribute, offset: Int) =
        GL20.glVertexAttribPointer(
                attribute.index,
                attribute.size,
                attribute.type,
                attribute.normalized,
                0, // tightly packed
                offset.L)