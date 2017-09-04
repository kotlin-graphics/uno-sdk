package uno.gln

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import org.lwjgl.opengl.GL32
import org.lwjgl.opengl.GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS
import java.nio.IntBuffer

/**
 * Created by elect on 18/04/17.
 */


fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: IntBuffer) =
        glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer[0])


fun glBindFramebuffer(target: Int, framebuffer: IntBuffer) = glBindFramebuffer(target, framebuffer[0])
fun glBindFramebuffer(framebuffer: Int) = glBindFramebuffer(GL_FRAMEBUFFER, framebuffer)
fun glBindFramebuffer(framebuffer: IntBuffer) = glBindFramebuffer(GL_FRAMEBUFFER, framebuffer[0])
fun glBindFramebuffer() = glBindFramebuffer(GL_FRAMEBUFFER, 0)


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

inline fun withFramebuffer(framebuffer: Int, block: Framebuffer.() -> Unit) {
    Framebuffer.name = framebuffer
    Framebuffer.block()
}

inline fun withFramebuffer(block: Framebuffer.() -> Unit) {
    Framebuffer.name = 0
    Framebuffer.block()
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

    fun texture2D(attachment: Int, texture: Int, level: Int = 0) =
            GL30.glFramebufferTexture2D(GL_FRAMEBUFFER, attachment, GL11.GL_TEXTURE_2D, texture, level)

    fun renderbuffer(attachment: Int, renderbuffer: Int) = GL30.glFramebufferRenderbuffer(GL_FRAMEBUFFER, attachment, GL_RENDERBUFFER, renderbuffer)

    val complete: Boolean
        get() {
            val status = glCheckFramebufferStatus(GL_FRAMEBUFFER)
            return if (status != GL_FRAMEBUFFER_COMPLETE) {
                println("framebuffer incomplete, status: ${when (status) {
                    GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT"
                    GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT -> "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT"
                    GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER"
                    GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER -> "GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER"
                    GL_FRAMEBUFFER_UNSUPPORTED -> "GL_FRAMEBUFFER_UNSUPPORTED"
                    GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE -> "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE"
                    GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS -> "GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS"
                    GL_FRAMEBUFFER_UNDEFINED -> "GL_FRAMEBUFFER_UNDEFINED"
                    else -> throw IllegalStateException()
                }}")
                false
            } else true
        }

    fun getParameter(attachment: Int, pName: Int) = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, attachment, pName)

    fun getDepthParameter(pName: Int) = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, pName)
    fun getStencilParameter(pName: Int) = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, GL_STENCIL_ATTACHMENT, pName)
    fun getDepthStencilParameter(pName: Int) = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, pName)

    fun getColorEncoding(index: Int = 0) = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0 + index, GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING)
}

object Framebuffers {

    lateinit var names: IntBuffer

    inline fun at(index: Int, block: Framebuffer.() -> Unit) {
        Framebuffer.name = names[index] // bind
        Framebuffer.block()
    }

}