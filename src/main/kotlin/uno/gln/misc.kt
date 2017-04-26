package uno.gln

import glm.set
import glm.vec2.Vec2i
import glm.vec4.Vec4
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.opengl.GL30.glBlitFramebuffer
import org.lwjgl.opengl.GL30.glClearBufferfv
import org.lwjgl.opengl.GL41
import uno.buffer.floatBufferBig
import uno.buffer.intBufferBig

/**
 * Created by elect on 18/04/17.
 */


fun glClearBuffer(buffer: Int, value: Float) = glClearBuffer(buffer, 0, value)
fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Float) {
    floatBuffer[0] = value
    glClearBufferfv(buffer, drawbuffer, floatBuffer)
}
fun glClearBuffer(buffer: Int, value: Vec4) = glClearBuffer(buffer, 0, value)
fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec4) = glClearBufferfv(buffer, drawbuffer, value to mat4Buffer)

fun glViewport(size: Vec2i) = glViewport(0, 0, size.x, size.y)

fun glBlitFramebuffer(size: Vec2i) = glBlitFramebuffer(
        0, 0, size.x, size.y,
        0, 0, size.x, size.y,
        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


fun glClearColor() = GL11.glClearColor(0f, 0f, 0f, 1f)
fun glClearDepthf() = GL41.glClearDepthf(1f)


val floatBuffer = floatBufferBig(1)
val intBuffer = intBufferBig(1)
val vec2Buffer = floatBufferBig(2)
val vec3Buffer = floatBufferBig(3)
val vec4Buffer = floatBufferBig(4)
val mat2Buffer = floatBufferBig(4)
val mat3Buffer = floatBufferBig(9)
val mat4Buffer = floatBufferBig(16)