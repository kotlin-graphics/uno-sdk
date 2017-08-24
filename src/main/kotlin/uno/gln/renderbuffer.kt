package uno.gln

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import java.nio.IntBuffer


/**
 * Created by elect on 13/05/17.
 */

fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
fun glRenderbufferStorage(target:Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)

fun glGenRenderbuffers(renderbuffers: IntBuffer, block: RenderBuffers.() -> Unit) {
    glGenRenderbuffers(renderbuffers)
//    RenderBuffers.target = GL_RENDERBUFFER TODO?
    RenderBuffers.names = renderbuffers
    RenderBuffers.block()
}

object RenderBuffers {

//    var target = 0
    lateinit var names: IntBuffer

    fun at(index: Int, block: RenderBuffer.() -> Unit) {
        RenderBuffer.name = names[index] // bind
        RenderBuffer.block()
    }
}

object RenderBuffer {

    var target = 0
    var name = 0
        set(value) {
            glBindRenderbuffer(GL_RENDERBUFFER, value)
            field = name
        }

    fun storage(internalFormat: Int, width: Int, height: Int) = glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
    fun storage(internalFormat: Int, size: Vec2i) = glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
}