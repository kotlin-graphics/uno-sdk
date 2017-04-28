package uno.gln

import glm.mat3x3.Mat3
import glm.mat4x4.Mat4
import glm.vec2.Vec2
import glm.vec3.Vec3
import glm.vec4.Vec4
import org.lwjgl.opengl.ARBUniformBufferObject.glGetUniformBlockIndex
import org.lwjgl.opengl.ARBUniformBufferObject.glUniformBlockBinding
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL31
import uno.buffer.byteBufferOf
import uno.buffer.destroy
import uno.glsl.Program

fun glCreatePrograms(ints: IntArray) = repeat(ints.size) { ints[it] = GL20.glCreateProgram() }

fun initPrograms(ints: IntArray, block: Programs.() -> Unit) {
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

    val String.location: Int
        get() = GL20.glGetUniformLocation(name, this)

    var String.blockIndex
        get() = glGetUniformBlockIndex(name, this)
        set(value) = glUniformBlockBinding(name, glGetUniformBlockIndex(name, this), value)

    var Int.int: Int
        get() = 0
        set(value) = GL20.glUniform1i(this, value)
    var Int.float: Float
        get() = 0f
        set(value) = GL20.glUniform1f(this, value)
    var Int.vec2: Vec2
        get() = Vec2()
        set(value) = GL20.glUniform2f(this, value.x, value.y)
    var Int.vec3: Vec3
        get() = Vec3()
        set(value) = GL20.glUniform3f(this, value.x, value.y, value.z)
    var Int.vec4: Vec4
        get() = Vec4()
        set(value) = GL20.glUniform4f(this, value.x, value.y, value.z, value.w)
    var Int.mat4: Mat4
        get() = Mat4()
        set(value) = GL20.glUniformMatrix4fv(this, false, value to mat4Buffer)
    var Int.mat3: Mat3
        get() = Mat3()
        set(value) = GL20.glUniformMatrix3fv(this, false, value to mat3Buffer)


    fun link() = GL20.glLinkProgram(name)

    infix fun Vec4.to(location: Int) = GL20.glUniform4fv(location, this to vec4Buffer)
    infix fun Mat4.to(location: Int) = GL20.glUniformMatrix4fv(location, false, this to mat4Buffer)
}

object ProgramBase {

    var name = 0

    var String.location
        get() = GL20.glGetUniformLocation(name, this)
        set(value) = GL20.glBindAttribLocation(name, value, this)

    // read only because no program is used
    val String.blockIndex
        get() = glGetUniformBlockIndex(name, this)

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        GL20.glUseProgram(0)
    }

    infix fun Int.blockBinding(uniformBlockBinding: Int) = glUniformBlockBinding(name, this, uniformBlockBinding)
    infix fun Int.getBlockIndex(uniformBlockName: String) = GL31.glGetUniformBlockIndex(this, uniformBlockName)

    fun link() = GL20.glLinkProgram(name)

    operator fun plusAssign(shader: Int) = GL20.glAttachShader(name, shader)
    infix fun attach(shader: Int) = GL20.glAttachShader(name, shader)
}

object Programs {

    lateinit var names: IntArray

    fun with(index: Int, block: ProgramBase.() -> Unit) {
        ProgramBase.name = names[index]
        ProgramBase.block()
    }

    fun using(index: Int, block: ProgramUse.() -> Unit) {
        ProgramUse.name = names[index]  // bind
        ProgramUse.block()
        GL20.glUseProgram(0)
    }
}


fun glUseProgram(program: Program) = GL20.glUseProgram(program.name)
fun glUseProgram() = GL20.glUseProgram(0)

fun glDeletePrograms(programs: IntArray) = programs.forEach { GL20.glDeleteProgram(it) }