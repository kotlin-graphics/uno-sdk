package uno.gln

import glm_.BYTES
import glm_.bool
import glm_.glm
import org.lwjgl.opengl.GL31
import org.lwjgl.opengl.GL40
import org.lwjgl.opengl.GL43
import org.lwjgl.system.MemoryUtil
import uno.gl.buf
import uno.gl.iBuf

inline fun withUniformBlock(program: Enum<*>, uniformBlockIndex: Int, block: UniformBlock.() -> Unit) = withUniformBlock(programName[program], uniformBlockIndex, block)
inline fun withUniformBlock(program: IntArray, uniformBlockIndex: Int, block: UniformBlock.() -> Unit) = withUniformBlock(program[0], uniformBlockIndex, block)
inline fun withUniformBlock(program: Int, uniformBlockIndex: Int, block: UniformBlock.() -> Unit) {
    UniformBlock.programName = program
    UniformBlock.blockIndex = uniformBlockIndex
    UniformBlock.block()
}

object UniformBlock {

    var programName = 0
    var blockIndex = 0

    inline val name: String
        get() {
            GL31.nglGetActiveUniformBlockName(programName, blockIndex, buf.capacity(), MemoryUtil.memAddress(iBuf), MemoryUtil.memAddress(buf))
            val bytes = ByteArray(iBuf[0] - 1)
            return String(bytes)
        }
    inline val binding get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_BINDING, MemoryUtil.memAddress(iBuf)).run { iBuf[0] }
    inline val dataSize get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_DATA_SIZE, MemoryUtil.memAddress(iBuf)).run { iBuf[0] }
    inline val nameLength get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_NAME_LENGTH, MemoryUtil.memAddress(iBuf)).run { iBuf[0] }
    inline val activeUniforms get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, MemoryUtil.memAddress(iBuf)).run { iBuf[0] }
    inline val activeUniformsIndices: IntArray
        get() {
            GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS, MemoryUtil.memAddress(buf))
            return IntArray(glm.min(activeUniforms, 32), { buf.getInt(it * Int.BYTES) })
        }
    inline val byVertexShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
    inline val byTessControlShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL40.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
    inline val byTessEvaluationShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL40.GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
    inline val byGeometryShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
    inline val byFragmentShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL31.GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
    inline val byComputeShader get() = GL31.nglGetActiveUniformBlockiv(programName, blockIndex, GL43.GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER, MemoryUtil.memAddress(iBuf)).run { iBuf[0].bool }
}