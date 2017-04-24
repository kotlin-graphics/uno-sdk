package uno.gln

import glm.L
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.glVertexAttribPointer
import org.lwjgl.opengl.GL30.glBindVertexArray
import org.lwjgl.opengl.GL30.glGenVertexArrays
import uno.glf.VertexAttribute
import uno.glf.VertexLayout
import java.nio.IntBuffer

/**
 * Created by elect on 18/04/17.
 */


fun glBindVertexArray(vertexArray: IntBuffer) = glBindVertexArray(vertexArray[0])

fun glBindVertexArray() = glBindVertexArray(0)


inline fun initVertexArrays(vertexArrays: IntBuffer, block: VertexArrays.() -> Unit) {
    VertexArrays.names = vertexArrays
    VertexArrays.block()
    glBindVertexArray(0)
}

inline fun initVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) {
    glGenVertexArrays(vertexArray)
    VertexArray.name = vertexArray[0]   // bind
    VertexArray.block()
    glBindVertexArray(0)
}

inline fun withVertexArray(vertexArray: IntBuffer, block: VertexArray.() -> Unit) = withVertexArray(vertexArray[0], block)
inline fun withVertexArray(vertexArray: Int, block: VertexArray.() -> Unit) {
    VertexArray.name = vertexArray   // bind
    VertexArray.block()
    glBindVertexArray(0)
}

object VertexArray {

    var name = 0
        set(value) = glBindVertexArray(value)

    fun array(array: Int, format: VertexLayout) {
        glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (attr in format.attribute) {
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, attr.interleavedStride, attr.pointer)
        }
        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    fun array(array: Int, format: VertexLayout, vararg offset: Int) {
        glBindBuffer(GL15.GL_ARRAY_BUFFER, array)
        for (i in format.attribute.indices) {
            val attr = format.attribute[i]
            GL20.glEnableVertexAttribArray(attr.index)
            GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, 0, offset[i].L)
        }
        glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    fun element(element: Int) = glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, element)
    fun element(element: IntBuffer) = glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, element[0])
}

object VertexArrays {

    lateinit var names: IntBuffer

    fun at(index: Int, block: VertexArray.() -> Unit) {
        VertexArray.name = names[index]   // bind
        VertexArray.block()
    }
}


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

inline fun withVertexLayout(array: IntBuffer, format: VertexLayout, block: () -> Unit) = withVertexLayout(array[0], format, block)
inline fun withVertexLayout(array: Int, format: VertexLayout, block: () -> Unit) {
    glBindBuffer(GL_ARRAY_BUFFER, array)
    for (attr in format.attribute) {
        GL20.glEnableVertexAttribArray(attr.index)
        GL20.glVertexAttribPointer(attr.index, attr.size, attr.type, attr.normalized, attr.interleavedStride, attr.pointer)
    }
    glBindBuffer(GL_ARRAY_BUFFER, 0)
    block()
    for (attr in format.attribute)
        GL20.glDisableVertexAttribArray(attr.index)
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


fun glEnableVertexAttribArray(layout: VertexLayout) = GL20.glEnableVertexAttribArray(layout[0].index)
fun glEnableVertexAttribArray(attribute: VertexAttribute) = GL20.glEnableVertexAttribArray(attribute.index)

fun glDisableVertexAttribArray(layout: VertexLayout) = GL20.glDisableVertexAttribArray(layout[0].index)
fun glDisableVertexAttribArray(attribute: VertexAttribute) = GL20.glDisableVertexAttribArray(attribute.index)


fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int) =
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer.L)

fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int) =
        glVertexAttribPointer(index, size, type, normalized, stride, 0)

fun glVertexAttribPointer(layout: VertexLayout) = glVertexAttribPointer(layout[0])
fun glVertexAttribPointer(attribute: VertexAttribute) =
        glVertexAttribPointer(
                attribute.index,
                attribute.size,
                attribute.type,
                attribute.normalized,
                attribute.interleavedStride,
                attribute.pointer)

fun glVertexAttribPointer(layout: VertexLayout, offset: Int) = glVertexAttribPointer(layout[0], offset)
fun glVertexAttribPointer(attribute: VertexAttribute, offset: Int) =
        GL20.glVertexAttribPointer(
                attribute.index,
                attribute.size,
                attribute.type,
                attribute.normalized,
                0, // tightly packed
                offset.L)