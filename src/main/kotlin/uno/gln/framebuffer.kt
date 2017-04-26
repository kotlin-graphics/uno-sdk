package uno.gln

import glm.vec2.Vec2i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
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


fun initFramebuffer(framebuffer: IntBuffer, block: Framebuffer.() -> Unit) {
    glGenFramebuffers(framebuffer)
    Framebuffer.name = framebuffer[0]   // bind
    Framebuffer.block()
    glBindFramebuffer(GL_FRAMEBUFFER, 0)
}


object Framebuffer {

    var name = 0
        set(value) {
            glBindFramebuffer(GL_FRAMEBUFFER, value)
            field = value
        }

    fun texture2D(target: Int, attachment: Int, texture: Int, level: Int = 0) =
            GL30.glFramebufferTexture2D(target, attachment, GL11.GL_TEXTURE_2D, texture, level)

    val Int.colorEncoding
        get() = glGetFramebufferAttachmentParameteri(GL_FRAMEBUFFER, this, GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING)
}