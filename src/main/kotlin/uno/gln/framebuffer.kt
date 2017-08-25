package uno.gln

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjgl.opengl.GL32
import java.nio.IntBuffer

/**
 * Created by elect on 18/04/17.
 */


fun glRenderbufferStorageMultisample(target: Int, samples: Int, internalformat: Int, size: Vec2i) =
        GL30.glRenderbufferStorageMultisample(target, samples, internalformat, size.x, size.y)

fun glBindRenderbuffer(target: Int) = GL30.glBindRenderbuffer(target, 0)
fun glBindRenderbuffer(target: Int, buffer: IntBuffer) = GL30.glBindRenderbuffer(target, buffer[0])

fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: IntBuffer) =
        glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer[0])


fun glBindFramebuffer(target: Int, framebuffer: IntBuffer) = glBindFramebuffer(target, framebuffer[0])
fun glBindFramebuffer(target: Int) = glBindFramebuffer(target, 0)


fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int) =
        GL30.glFramebufferTexture2D(target, attachment, textarget, texture, 0)


inline fun initFramebuffer(framebuffer: IntBuffer, block: Framebuffer.() -> Unit) {
    glGenFramebuffers(framebuffer)
    Framebuffer.name = framebuffer[0]   // bind
    Framebuffer.block()
    glBindFramebuffer(GL_FRAMEBUFFER, 0)
}

inline fun initFramebuffers(framebuffer: IntBuffer, block: Framebuffers.() -> Unit) {
    glGenFramebuffers(framebuffer)
    Framebuffers.names = framebuffer
    Framebuffers.block()
}


object Framebuffer {

    var name = 0
        set(value) {
            glBindFramebuffer(GL_FRAMEBUFFER, value)
            field = value
        }

    fun texture(attachment: Int, texture: Int, level: Int = 0) = GL32.glFramebufferTexture(GL_FRAMEBUFFER, attachment, texture, level)

    fun texture2D(target: Int, attachment: Int, texture: Int, level: Int = 0) =
            GL30.glFramebufferTexture2D(target, attachment, GL11.GL_TEXTURE_2D, texture, level)

    fun renderbuffer(attachment: Int, renderbuffer: Int) = GL30.glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer)

    val complete: Boolean
        get() {
            val status = glCheckFramebufferStatus(GL_FRAMEBUFFER)
            val error = when (status) {
                GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED" // TODO there is enum
                else -> "$status"
            }
            println("OpenGL Error($error)")
            return status == GL_FRAMEBUFFER_COMPLETE
        }

    val Int.colorEncoding
        get() = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, this, GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING)
}

object Framebuffers {

    lateinit var names: IntBuffer

    fun at(index: Int, block: Framebuffer.() -> Unit) {
        Framebuffer.name = names[index] // bind
        Framebuffer.block()
    }

}