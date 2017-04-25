package uno.gln

import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL31
import org.lwjgl.opengl.GL32.glDrawElementsBaseVertex
import org.lwjgl.opengl.GL40
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * Created by GBarbieri on 20.04.2017.
 */


fun glDrawArrays(count: Int) = glDrawArrays(GL_TRIANGLES, 0, count)
fun glDrawArrays(mode: Int, count: Int) = glDrawArrays(mode, 0, count)


fun glDrawArraysIndirect(indirect: Long) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: IntArray) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: IntBuffer) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: ByteBuffer) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)


fun glDrawArraysInstanced(count: Int, primCount:Int) = GL31.glDrawArraysInstanced(GL_TRIANGLES, 0, count, primCount)


fun glDrawElements(count: Int) = glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0)
fun glDrawElements(count: Int, type: Int) = glDrawElements(GL_TRIANGLES, count, type, 0)
fun glDrawElements(mode: Int, count: Int, type: Int) = glDrawElements(mode, count, type, 0)

fun glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) =
        glDrawElementsBaseVertex(GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)


