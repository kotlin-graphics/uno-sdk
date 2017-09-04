package uno.gln

import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import org.lwjgl.opengl.ARBUniformBufferObject.glGetUniformBlockIndex
import org.lwjgl.opengl.ARBUniformBufferObject.glUniformBlockBinding
import org.lwjgl.opengl.GL20
import uno.gl.*
import uno.glsl.Program

fun glCreatePrograms(ints: IntArray) = repeat(ints.size) { ints[it] = GL20.glCreateProgram() }

inline fun glCreateProgram(block: ProgramBase.() -> Unit): Int {
    ProgramBase.name = GL20.glCreateProgram()
    ProgramBase.block()
    return ProgramBase.name
}

inline fun initPrograms(ints: IntArray, block: Programs.() -> Unit) {
    repeat(ints.size) { ints[it] = GL20.glCreateProgram() }
    Programs.names = ints
    Programs.block()
}

inline fun usingProgram(program: Int = 0, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program //glUse
    ProgramUse.block()
    GL20.glUseProgram(0)
}

inline fun usingProgram(program: Program, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program.name //glUse
    ProgramUse.block()
    GL20.glUseProgram(0)
}

inline fun withProgram(program: Int = 0, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program
    ProgramBase.block()
}

inline fun withProgram(program: Program, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program.name
    ProgramBase.block()
}

object ProgramUse {

    var name = 0
        set(value) {
            GL20.glUseProgram(value)
            field = value
        }

    val String.uniform: Int
        get() = GL20.glGetUniformLocation(name, this)

    var String.blockIndex
        get() = glGetUniformBlockIndex(name, this)
        set(value) = glUniformBlockBinding(name, blockIndex, value)

    var String.unit: Int
        get() = uniform
        set(value) = GL20.glUniform1i(uniform, value)


    fun link() = GL20.glLinkProgram(name)

    infix fun Int.to(location: Int) = GL20.glUniform1i(location, this)
    infix fun Float.to(location: Int) = GL20.glUniform1f(location, this)

    infix fun Vec2.to(location: Int) = GL20.glUniform2fv(location, this to v2Buf)
    infix fun Vec3.to(location: Int) = GL20.glUniform3fv(location, this to v3Buf)
    infix fun Vec4.to(location: Int) = GL20.glUniform4fv(location, this to v4Buf)

    infix fun Mat2.to(location: Int) = GL20.glUniformMatrix2fv(location, false, this to m2Buf)
    infix fun Mat3.to(location: Int) = GL20.glUniformMatrix3fv(location, false, this to m3Buf)
    infix fun Mat4.to(location: Int) = GL20.glUniformMatrix4fv(location, false, this to m4Buf)

    infix fun Int.to(uniform: String) = GL20.glUniform1i(uniform.uniform, this)
    infix fun Float.to(uniform: String) = GL20.glUniform1f(uniform.uniform, this)

    infix fun Vec2.to(uniform: String) = GL20.glUniform2fv(uniform.uniform, this to v2Buf)
    infix fun Vec3.to(uniform: String) = GL20.glUniform3fv(uniform.uniform, this to v3Buf)
    infix fun Vec4.to(uniform: String) = GL20.glUniform4fv(uniform.uniform, this to v4Buf)

    infix fun Mat2.to(uniform: String) = GL20.glUniformMatrix2fv(uniform.uniform, false, this to m2Buf)
    infix fun Mat3.to(uniform: String) = GL20.glUniformMatrix3fv(uniform.uniform, false, this to m3Buf)
    infix fun Mat4.to(uniform: String) = GL20.glUniformMatrix4fv(uniform.uniform, false, this to m4Buf)
}

object ProgramBase {

    var name = 0

    val String.uniform
        get() = GL20.glGetUniformLocation(name, this)

    var String.attrib
        get() = GL20.glGetAttribLocation(name, this)
        set(value) = GL20.glBindAttribLocation(name, value, this)

    var String.blockIndex
        get() = glGetUniformBlockIndex(name, this)
        set(value) = glUniformBlockBinding(name, blockIndex, value)

    // only get, no program use
    val String.unit get() = uniform

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        GL20.glUseProgram(0)
    }

    fun link() = GL20.glLinkProgram(name)

    operator fun plusAssign(shader: Int) = GL20.glAttachShader(name, shader)
    infix fun attach(shader: Int) = GL20.glAttachShader(name, shader)
    fun attach(vararg shader: Int) = shader.forEach { GL20.glAttachShader(name, it) }
}

object Programs {

    lateinit var names: IntArray

    inline fun with(index: Int, block: ProgramBase.() -> Unit) {
        ProgramBase.name = names[index]
        ProgramBase.block()
    }

    inline fun using(index: Int, block: ProgramUse.() -> Unit) {
        ProgramUse.name = names[index]  // bind
        ProgramUse.block()
        GL20.glUseProgram(0)
    }
}


fun glUseProgram(program: Program) = GL20.glUseProgram(program.name)
fun glUseProgram() = GL20.glUseProgram(0)

fun glDeletePrograms(programs: IntArray) = programs.forEach { GL20.glDeleteProgram(it) }
fun glDeleteProgram(program: Program) = GL20.glDeleteProgram(program.name)
fun glDeletePrograms(vararg programs: Program) = programs.forEach { GL20.glDeleteProgram(it.name) }