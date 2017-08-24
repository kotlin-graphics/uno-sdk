package uno.gln.jogl

import com.jogamp.opengl.GL
import com.jogamp.opengl.GL2ES3
import com.jogamp.opengl.GL3
import glm_.f
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import uno.gl.*


fun GL3.glViewport(size: Vec2i) = glViewport(0, 0, size.x, size.y)

fun GL3.glViewport(viewport: Vec4i) = glViewport(viewport.x, viewport.y, viewport.z, viewport.w)

fun GL3.glScissor(size: Vec2i) = glScissor(0, 0, size.x, size.y)
fun GL3.glScissor(scissor: Vec4i) = glScissor(scissor.x, scissor.y, scissor.z, scissor.w)

fun GL3.glBlitFramebuffer(size: Vec2i) = glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL.GL_COLOR_BUFFER_BIT, GL.GL_LINEAR)



fun GL3.glClearBufferf(buffer: Int, f: Float) = when (buffer) {
    GL2ES3.GL_COLOR -> glClearBufferf(buffer, f, f, f, f)
    GL2ES3.GL_DEPTH -> glClearBufferfv(buffer, 0, m4Buf.put(0, f))
    else -> throw Error()
}

fun GL3.glClearBufferf(buffer: Int, r: Float, g: Float, b: Float, a: Float) = glClearBufferfv(buffer, 0, m4Buf.put(0, r).put(1, g).put(2, b).put(3, a))
fun GL3.glClearBufferf(buffer: Int, n: Number) = when (buffer) {
    GL2ES3.GL_COLOR -> glClearBufferf(buffer, n.f, n.f, n.f, n.f)
    GL2ES3.GL_DEPTH -> glClearBufferfv(buffer, 0, m4Buf.put(0, n.f))
    else -> throw Error()
}

fun GL3.glClearBufferf(buffer: Int) = when (buffer) {
    GL2ES3.GL_COLOR -> glClearBufferfv(buffer, 0, m4Buf.put(0, 0f).put(1, 0f).put(2, 0f).put(3, 1f))
    GL2ES3.GL_DEPTH -> glClearBufferfv(buffer, 0, m4Buf.put(0, 1f))
    else -> throw Error()
}

fun GL3.glClearBufferf(buffer: Int, r: Number, g: Number, b: Number, a: Number) = glClearBufferf(buffer, r.f, g.f, b.f, a.f)


fun GL3.glViewport(width: Int, height: Int) = glViewport(0, 0, width, height)



inline fun GL3.clear(block: Clear.() -> Unit) {
    Clear.gl = this
    Clear.block()
}

object Clear {

    lateinit var gl: GL3


    fun color() = color(0f, 0f, 0f, 1f)
    fun color(f: Float) = color(f, f, f, f)
    fun color(r: Float, g: Float, b: Float, a: Float) =
            gl.glClearBufferfv(GL2ES3.GL_COLOR, 0, m4Buf.put(0, r).put(1, g).put(2, b).put(3, a))

    fun color(n: Number) = color(n, n, n, n)
    fun color(r: Number, g: Number, b: Number, a: Number)
            = gl.glClearBufferfv(GL2ES3.GL_COLOR, 0, m4Buf.put(0, r.f).put(1, g.f).put(2, b.f).put(3, a.f))

    fun depth() = depth(1f)
    fun depth(depth: Float) = gl.glClearBufferfv(GL2ES3.GL_DEPTH, 0, m4Buf.put(0, depth))
    fun depth(depth: Number) = depth(depth.f)
}





fun GL3.glGetInteger(pname: Int): Int {
    glGetIntegerv(pname, iBuf)
    return iBuf[0]
}

infix fun GL3.glEnable(cap: Int) = glEnable(cap)
infix fun GL3.disable(cap: Int) = glDisable(cap)


fun GL3.checkError(location: String) = checkError(gl, location)




fun GL3.glGetVec2(pname: Int): Vec2 {
    glGetFloatv(pname, v2Buf)
    return Vec2(v2Buf)
}

fun GL3.glGetVec2i(pname: Int): Vec2i {
    glGetIntegerv(pname, v2iBuf)
    return Vec2i(v2iBuf)
}

fun GL3.glGetVec4(pname: Int): Vec4 {
    glGetFloatv(pname, v4Buf)
    return Vec4(v4Buf)
}

fun GL3.glGetVec4i(pname: Int): Vec4i {
    glGetIntegerv(pname, v4iBuf)
    return Vec4i(v4iBuf)
}

fun checkError(gl: GL, location: String) {

    val error = gl.glGetError()
    if (error != GL.GL_NO_ERROR) {
        val errorString: String
        when (error) {
            GL.GL_INVALID_ENUM -> errorString = "GL_INVALID_ENUM"
            GL.GL_INVALID_VALUE -> errorString = "GL_INVALID_VALUE"
            GL.GL_INVALID_OPERATION -> errorString = "GL_INVALID_OPERATION"
            GL.GL_INVALID_FRAMEBUFFER_OPERATION -> errorString = "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL.GL_OUT_OF_MEMORY -> errorString = "GL_OUT_OF_MEMORY"
            else -> errorString = "UNKNOWN"
        }
        throw Error("OpenGL Error($errorString): $location")
    }
}