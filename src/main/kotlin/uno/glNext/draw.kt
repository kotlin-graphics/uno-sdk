package uno.glNext

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.glDrawArrays
import org.lwjgl.opengl.GL11.glDrawElements
import org.lwjgl.opengl.GL31
import org.lwjgl.opengl.GL32.glDrawElementsBaseVertex

/**
 * Created by GBarbieri on 20.04.2017.
 */

fun glDrawArrays(count: Int) = glDrawArrays(GL11.GL_TRIANGLES, 0, count)
fun glDrawArrays(mode: Int, count: Int) = glDrawArrays(mode, 0, count)

fun glDrawElements(count: Int) = glDrawElements(GL11.GL_TRIANGLES, count, GL11.GL_UNSIGNED_INT, 0)
fun glDrawElements(count: Int, type: Int) = glDrawElements(GL11.GL_TRIANGLES, count, type, 0)
fun glDrawElements(mode: Int, count: Int, type: Int) = glDrawElements(mode, count, type, 0)

fun glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) =
        glDrawElementsBaseVertex(GL11.GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)


fun glDrawArraysInstanced(count: Int, primCount:Int) = GL31.glDrawArraysInstanced(GL11.GL_TRIANGLES, 0, count, primCount)