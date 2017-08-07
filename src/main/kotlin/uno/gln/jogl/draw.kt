package uno.gln.jogl

import com.jogamp.opengl.GL
import com.jogamp.opengl.GL3

fun GL3.glDrawArrays(count: Int) = glDrawArrays(GL.GL_TRIANGLES, 0, count)
fun GL3.glDrawArrays(mode: Int, count: Int) = glDrawArrays(mode, 0, count)

fun GL3.glDrawElements(count: Int, type: Int) = glDrawElements(GL.GL_TRIANGLES, count, type, 0)
fun GL3.glDrawElements(mode: Int, count: Int, type: Int) = glDrawElements(mode, count, type, 0)

fun GL3.glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) =
        glDrawElementsBaseVertex(GL.GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)