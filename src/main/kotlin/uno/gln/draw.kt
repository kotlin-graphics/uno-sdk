package uno.gln

/**
 * Created by GBarbieri on 20.04.2017.
 */

import glm.BYTES
import glm.size
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL32.glDrawElementsBaseVertex
import java.nio.ByteBuffer
import java.nio.IntBuffer


fun glDrawArrays(count: Int) = GL11.glDrawArrays(GL_TRIANGLES, 0, count)
fun glDrawArrays(mode: Int, count: Int) = GL11.glDrawArrays(mode, 0, count)

fun glMultiDrawArrays(first: IntArray, count: IntArray) = GL14.glMultiDrawArrays(GL_TRIANGLES, first, count)
fun glMultiDrawArrays(first: IntBuffer, count: IntBuffer) = GL14.glMultiDrawArrays(GL_TRIANGLES, first, count)

fun glDrawArraysInstanced(count: Int, primCount: Int) = GL31.glDrawArraysInstanced(GL_TRIANGLES, 0, count, primCount)
fun glDrawArraysInstanced(mode: Int, count: Int, primCount: Int) = GL31.glDrawArraysInstanced(mode, 0, count, primCount)

fun glDrawArraysIndirect(indirect: Long) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: IntArray) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: IntBuffer) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)
fun glDrawArraysIndirect(indirect: ByteBuffer) = GL40.glDrawArraysIndirect(GL_TRIANGLES, indirect)

fun glDrawArraysInstancedBaseInstance(count: Int, primCount: Int, baseInstance: Int) = GL42.glDrawArraysInstancedBaseInstance(GL_TRIANGLES, 0, count, primCount, baseInstance)
fun glDrawArraysInstancedBaseInstance(mode: Int, count: Int, primCount: Int, baseInstance: Int) = GL42.glDrawArraysInstancedBaseInstance(mode, 0, count, primCount, baseInstance)

fun glMultiDrawArraysIndirect(indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(GL_TRIANGLES, indirect, indirect.size / (4 * Int.BYTES), 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: ByteBuffer) = GL43.glMultiDrawArraysIndirect(mode, indirect, indirect.size / (4 * Int.BYTES), 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: ByteBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)
fun glMultiDrawArraysIndirect(indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(GL_TRIANGLES, indirect, indirect.capacity() / 4, 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: IntBuffer) = GL43.glMultiDrawArraysIndirect(GL_TRIANGLES, indirect, indirect.capacity() / 4, 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: IntBuffer, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)
fun glMultiDrawArraysIndirect(indirect: IntArray) = GL43.glMultiDrawArraysIndirect(GL_TRIANGLES, indirect, indirect.size / 4, 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: IntArray) = GL43.glMultiDrawArraysIndirect(GL_TRIANGLES, indirect, indirect.size / 4, 0)
fun glMultiDrawArraysIndirect(mode: Int, indirect: IntArray, primCount: Int) = GL43.glMultiDrawArraysIndirect(mode, indirect, primCount, 0)

fun glMultiDrawArraysIndirectBindlessNV(indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirect.glMultiDrawArraysIndirectBindlessNV(GL_TRIANGLES, indirect, drawCount, 0, vertexBufferCount)
fun glMultiDrawArraysIndirectBindlessNV(mode: Int, indirect: ByteBuffer, drawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirect.glMultiDrawArraysIndirectBindlessNV(mode, indirect, drawCount, 0, vertexBufferCount)


fun glMultiDrawArraysIndirectBindlessNV(indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirectCount.glMultiDrawArraysIndirectBindlessCountNV(GL_TRIANGLES, indirect, drawCount, maxDrawCount, 0, vertexBufferCount)
fun glMultiDrawArraysIndirectBindlessNV(mode: Int, indirect: ByteBuffer, drawCount: Long, maxDrawCount: Int, vertexBufferCount: Int) = NVBindlessMultiDrawIndirectCount.glMultiDrawArraysIndirectBindlessCountNV(mode, indirect, drawCount, maxDrawCount, 0, vertexBufferCount)


fun glDrawElements(count: Int) = glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0)
fun glDrawElements(count: Int, type: Int) = glDrawElements(GL_TRIANGLES, count, type, 0)
fun glDrawElements(mode: Int, count: Int, type: Int) = glDrawElements(mode, count, type, 0)

fun glDrawElementsBaseVertex(count: Int, type: Int, indices_buffer_offset: Long, basevertex: Int) =
        glDrawElementsBaseVertex(GL_TRIANGLES, count, type, indices_buffer_offset, basevertex)


