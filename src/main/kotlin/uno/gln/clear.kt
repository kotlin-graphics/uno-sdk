package uno.gln

import glm_.f
import glm_.set
import glm_.vec1.Vec1
import glm_.vec4.Vec4
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import uno.buffer.put
import uno.gl.fBuf
import uno.gl.m4Buf
import uno.gl.v4Buf

/**
 * Created by GBarbieri on 21.04.2017.
 */

inline fun clear(block: Clear.() -> Unit) = Clear.block()

object Clear {

    inline fun color() = color(0f, 0f, 0f, 1f)
    inline fun color(f: Float) = color(f, f, f, f)
    inline fun color(r: Float, g: Float, b: Float, a: Float) =
            GL30.glClearBufferfv(GL11.GL_COLOR, 0, m4Buf.put(0, r).put(1, g).put(2, b).put(3, a))

    inline fun color(n: Number) = color(n, n, n, n)
    inline fun color(r: Number, g: Number, b: Number, a: Number) =
            GL30.glClearBufferfv(GL11.GL_COLOR, 0, m4Buf.put(0, r.f).put(1, g.f).put(2, b.f).put(3, a.f))

    inline fun depth() = depth(1f)
    inline fun depth(depth: Float) = GL30.glClearBufferfv(GL11.GL_DEPTH, 0, fBuf.put(0, depth))
    inline fun depth(depth: Number) = depth(depth.f)

    // TODO stencil
}

inline fun glClearBuffer(buffer: Int, value: Float) = glClearBuffer(buffer, 0, value)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Float) = GL30.glClearBufferfv(buffer, drawbuffer, fBuf.put(0, value))

inline fun glClearDepthBuffer(value: Float = 1f) = GL30.glClearBufferfv(GL11.GL_DEPTH, 0, fBuf.put(0, value))
inline fun glClearColorBuffer(drawbuffer: Int, value: Vec4 = Vec4(0f, 0f, 0f, 1f)) = GL30.glClearBufferfv(GL11.GL_COLOR, drawbuffer, value to v4Buf)
inline fun glClearColorBuffer(value: Vec4 = Vec4(0f, 0f, 0f, 1f)) = GL30.glClearBufferfv(GL11.GL_COLOR, 0, value to v4Buf)
inline fun glClearColorBuffer(red: Float, green: Float, blue: Float, alpha: Float) = GL30.glClearBufferfv(GL11.GL_COLOR, 0, v4Buf.put(red, green, blue, alpha))

inline fun glClearBuffer(buffer: Int, value: Vec4) = GL30.glClearBufferfv(buffer, 0, value to m4Buf)

inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec1) = GL30.glClearBufferfv(buffer, drawbuffer, value to fBuf)
inline fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec4) = GL30.glClearBufferfv(buffer, drawbuffer, value to m4Buf)