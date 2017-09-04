package uno.gln

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import java.nio.IntBuffer


/**
 * Created by elect on 13/05/17.
 */

fun glRenderbufferStorageMultisample(target: Int, samples: Int, internalFormat: Int, size: Vec2i) =
        GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

fun glBindRenderbuffer(target: Int) = GL30.glBindRenderbuffer(target, 0)
fun glBindRenderbuffer(target: Int, buffer: IntBuffer) = GL30.glBindRenderbuffer(target, buffer[0])


fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
fun glRenderbufferStorage(target:Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)

inline fun initRenderbuffers(renderbuffers: IntBuffer, block: RenderBuffers.() -> Unit) {
    glGenRenderbuffers(renderbuffers)
    RenderBuffers.names = renderbuffers
    RenderBuffers.block()
}
inline fun initRenderbuffer(renderbuffers: IntBuffer, block: RenderBuffer.() -> Unit) {
    glGenRenderbuffers(renderbuffers)
    RenderBuffer.name = renderbuffers[0]
    RenderBuffer.block()
}

object RenderBuffers {

    var target = GL_RENDERBUFFER
    lateinit var names: IntBuffer

    inline fun at(index: Int, block: RenderBuffer.() -> Unit) {
        RenderBuffer.name = names[index] // bind
        RenderBuffer.block()
    }
}

object RenderBuffer {

    var target = GL_RENDERBUFFER
    var name = 0
        set(value) {
            glBindRenderbuffer(GL_RENDERBUFFER, value)
            field = name
        }

    fun storage(internalFormat: Int, width: Int, height: Int) = glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
    fun storage(internalFormat: Int, size: Vec2i) = glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
    fun storageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)
}