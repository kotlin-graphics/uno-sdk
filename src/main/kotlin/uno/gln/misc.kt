package uno.gln

import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.GL_INVALID_FRAMEBUFFER_OPERATION
import org.lwjgl.opengl.GL30.glBlitFramebuffer
import org.lwjgl.opengl.GL41
import uno.gl.v2Buf
import uno.gl.v2iBuf
import uno.gl.v4Buf
import uno.gl.v4iBuf

/**
 * Created by elect on 18/04/17.
 */


fun glViewport(size: Vec2i) = glViewport(0, 0, size.x, size.y)

fun glViewport(viewport: Vec4i) = glViewport(viewport.x, viewport.y, viewport.z, viewport.w)

fun glScissor(size: Vec2i) = glScissor(0, 0, size.x, size.y)
fun glScissor(scissor: Vec4i) = glScissor(scissor.x, scissor.y, scissor.z, scissor.w)

fun glBlitFramebuffer(size: Vec2i) = glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


fun glClearColor() = GL11.glClearColor(0f, 0f, 0f, 1f)
fun glClearColor(color: Vec3) = GL11.glClearColor(color.x, color.y, color.z, 1f)
fun glClearColor(color: Vec4) = GL11.glClearColor(color.x, color.y, color.z, color.w)
fun glClearDepthf() = GL41.glClearDepthf(1f)


fun glGetVec2(pname: Int): Vec2 {
    GL11.glGetFloatv(pname, v2Buf)
    return Vec2(v2Buf)
}

fun glGetVec2i(pname: Int): Vec2i {
    GL11.glGetIntegerv(pname, v2iBuf)
    return Vec2i(v2iBuf)
}

fun glGetVec4(pname: Int): Vec4 {
    GL11.glGetFloatv(pname, v4Buf)
    return Vec4(v4Buf)
}

fun glGetVec4i(pname: Int): Vec4i {
    GL11.glGetIntegerv(pname, v4iBuf)
    return Vec4i(v4iBuf)
}

fun checkError(location: String) {

    val error = GL11.glGetError()
    if (error != GL_NO_ERROR) {
        val errorString: String
        when (error) {
            GL_INVALID_ENUM -> errorString = "GL_INVALID_ENUM"
            GL_INVALID_VALUE -> errorString = "GL_INVALID_VALUE"
            GL_INVALID_OPERATION -> errorString = "GL_INVALID_OPERATION"
            GL_INVALID_FRAMEBUFFER_OPERATION -> errorString = "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL_OUT_OF_MEMORY -> errorString = "GL_OUT_OF_MEMORY"
            else -> errorString = "UNKNOWN"
        }
        throw Error("OpenGL Error($errorString): $location")
    }
}


