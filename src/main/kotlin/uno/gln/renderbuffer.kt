package uno.gln

import glm_.vec2.Vec2i
import org.lwjgl.opengl.GL30
import uno.gl.iBuf
import java.nio.IntBuffer
import kotlin.properties.Delegates


/**
 * Created by elect on 13/05/17.
 */

var renderbufferName: IntBuffer by Delegates.notNull()


inline fun glRenderbufferStorageMultisample(target: Int, samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

inline fun glBindRenderbuffer(target: Int) = GL30.glBindRenderbuffer(target, 0)
inline fun glBindRenderbuffer(target: Int, renderbuffer: IntBuffer) = GL30.glBindRenderbuffer(target, renderbuffer[0])
inline fun glBindRenderbuffer(target: Int, renderbuffer: Enum<*>) = GL30.glBindRenderbuffer(target, renderbufferName[renderbuffer])


inline fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
inline fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
inline fun glRenderbufferStorage(target: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)


inline fun initRenderbuffers(block: RenderBuffers.() -> Unit) = initRenderbuffers(renderbufferName, block)
inline fun initRenderbuffers(renderbuffers: IntBuffer, block: RenderBuffers.() -> Unit) {
    GL30.glGenRenderbuffers(renderbuffers)
    RenderBuffers.names = renderbuffers
    RenderBuffers.block()
}

inline fun initRenderbuffer(renderbuffer: Enum<*>, block: RenderBuffer.() -> Unit) = renderbufferName.put(renderbuffer.ordinal, initRenderbuffer(block))
inline fun initRenderbuffer(renderbuffer: IntBuffer, block: RenderBuffer.() -> Unit) = renderbuffer.put(0, initRenderbuffer(block))
inline fun initRenderbuffer(block: RenderBuffer.() -> Unit): Int {
    GL30.glGenRenderbuffers(iBuf)
    val res = iBuf[0]
    RenderBuffer.name = res
    RenderBuffer.block()
    return res
}

inline fun withRenderbuffer(renderbuffer: Enum<*>, block: RenderBuffer.() -> Unit) = withRenderbuffer(renderbufferName[renderbuffer], block)
inline fun withRenderbuffer(renderbuffer: IntBuffer, block: RenderBuffer.() -> Unit) = withRenderbuffer(renderbuffer[0], block)
inline fun withRenderbuffer(renderbuffer: Int, block: RenderBuffer.() -> Unit) {
    RenderBuffer.name = renderbuffer
    RenderBuffer.block()
}

object RenderBuffers {

    var target = GL30.GL_RENDERBUFFER
    lateinit var names: IntBuffer

    inline fun at(index: Enum<*>, block: RenderBuffer.() -> Unit) = at(index.ordinal, block)
    inline fun at(index: Int, block: RenderBuffer.() -> Unit) {
        RenderBuffer.name = names[index] // bind
        RenderBuffer.block()
        RenderBuffer.name = 0   // unbind
    }
}

object RenderBuffer {

    var target = GL30.GL_RENDERBUFFER
    var name = 0
        set(value) {
            GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, value)
            field = name
        }

    inline fun storage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, width, height)
    inline fun storage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalFormat, size.x, size.y)
    inline fun storageMultisample(samples: Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorageMultisample(target, samples, internalFormat, size.x, size.y)

    val size get() = Vec2i(GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_WIDTH), GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_HEIGHT))
    val samples get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_SAMPLES)
    val format get() = GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, GL30.GL_RENDERBUFFER_INTERNAL_FORMAT)
}