package uno.gln

import glm.mat3x3.Mat3
import glm.mat4x4.Mat4
import glm.set
import glm.vec2.Vec2
import glm.vec3.Vec3
import glm.vec4.Vec4
import org.lwjgl.opengl.ARBUniformBufferObject.glGetUniformBlockIndex
import org.lwjgl.opengl.ARBUniformBufferObject.glUniformBlockBinding
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL31
import uno.buffer.byteBufferOf
import uno.buffer.destroy

fun glCreatePrograms(ints: IntArray) = repeat(ints.size) { ints[it] = glCreateProgram() }

fun initPrograms(ints: IntArray, block: Programs.() -> Unit) {
    repeat(ints.size) { ints[it] = glCreateProgram() }
    Programs.names = ints
    Programs.block()
}

fun glGetProgram(program: Int, pname: Int): Int {
    glGetProgramiv(program, pname, intBuffer)
    return intBuffer[0]
}

fun glGetProgramInfoLog(program: Int): String {

    glGetProgramiv(program, GL_INFO_LOG_LENGTH, intBuffer)
    val infoLogLength = intBuffer[0]

    val bufferInfoLog = byteBufferOf(infoLogLength)
    glGetProgramInfoLog(program, intBuffer, bufferInfoLog)

    val bytes = ByteArray(infoLogLength)
    bufferInfoLog.get(bytes).destroy()

    return String(bytes)
}

inline fun usingProgram(program: Int = 0, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program //glUse
    ProgramUse.block()
    glUseProgram(0)
}

inline fun withProgram(program: Int = 0, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program
    ProgramBase.block()
}

object ProgramUse {

    var name = 0
        set(value) {
            glUseProgram(value)
            field = value
        }

    val String.location: Int
        get() = glGetUniformLocation(name, this)

    var String.blockIndex
        get() = glGetUniformBlockIndex(name, this)
        set(value) = glUniformBlockBinding(name, glGetUniformBlockIndex(name, this), value)

    var Int.int: Int
        get() = 0
        set(value) = glUniform1i(this, value)
    var Int.float: Float
        get() = 0f
        set(value) = glUniform1f(this, value)
    var Int.vec2: Vec2
        get() = Vec2()
        set(value) = glUniform2f(this, value.x, value.y)
    var Int.vec3: Vec3
        get() = Vec3()
        set(value) = glUniform3f(this, value.x, value.y, value.z)
    var Int.vec4: Vec4
        get() = Vec4()
        set(value) = glUniform4f(this, value.x, value.y, value.z, value.w)
    var Int.mat4: Mat4
        get() = Mat4()
        set(value) = glUniformMatrix4fv(this, false, value to mat4Buffer)
    var Int.mat3: Mat3
        get() = Mat3()
        set(value) = glUniformMatrix3fv(this, false, value to mat3Buffer)


    fun link() = glLinkProgram(name)

    infix fun Vec4.to(location: Int) = glUniform4fv(location, this to vec4Buffer)
    infix fun Mat4.to(location: Int) = glUniformMatrix4fv(location, false, this to mat4Buffer)
}

object ProgramBase {

    var name = 0

    var String.location
        get() = glGetUniformLocation(name, this)
        set(value) = glBindAttribLocation(name, value, this)

    // read only because no program is used
    val String.blockIndex
        get() = glGetUniformBlockIndex(name, this)

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        glUseProgram(0)
    }

    infix fun Int.blockBinding(uniformBlockBinding: Int) = glUniformBlockBinding(name, this, uniformBlockBinding)
    infix fun Int.getBlockIndex(uniformBlockName: String) = GL31.glGetUniformBlockIndex(this, uniformBlockName)

    fun link() = glLinkProgram(name)

    operator fun plusAssign(shader: Int) = glAttachShader(name, shader)
    infix fun attach(shader: Int) = glAttachShader(name, shader)
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
        glUseProgram(0)
    }
}


fun glUseProgram() = glUseProgram(0)


fun glUniform2f(location: Int) = glUniform2f(location, 0f, 0f)
fun glUniform2f(location: Int, f: Float) = glUniform2f(location, f, f)
// TODO vec1
fun glUniform2f(location: Int, vec2: Vec2) = glUniform2f(location, vec2.x, vec2.y)

fun glUniform2f(location: Int, vec3: Vec3) = glUniform2f(location, vec3.x, vec3.y)
fun glUniform2f(location: Int, vec4: Vec4) = glUniform2f(location, vec4.x, vec4.y)

fun glUniform3f(location: Int) = glUniform3f(location, 0f, 0f, 0f)
fun glUniform3f(location: Int, f: Float) = glUniform3f(location, f, f, f)
fun glUniform3f(location: Int, vec2: Vec2) = glUniform3f(location, vec2.x, vec2.y, 0f)
fun glUniform3f(location: Int, vec3: Vec3) = glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun glUniform3f(location: Int, vec4: Vec4) = glUniform3f(location, vec4.x, vec4.y, vec4.z)

fun glUniform4(location: Int) = GL20.glUniform4f(location, 0f, 0f, 0f, 1f)
fun glUniform4(location: Int, f: Float) = GL20.glUniform4f(location, f, f, f, f)
fun glUniform4(location: Int, vec2: Vec2) = GL20.glUniform4f(location, vec2.x, vec2.y, 0f, 1f)
fun glUniform4(location: Int, vec3: Vec3) = GL20.glUniform4f(location, vec3.x, vec3.y, vec3.z, 1f)
fun glUniform4(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun glUniform(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)
fun glUniform(location: Int, mat4: Mat4) = GL20.glUniform4fv(location, mat4 to mat4Buffer)

fun glUniformMatrix4f(location: Int, value: FloatArray) {
    for (i in 0..15)
        mat4Buffer[i] = value[i]
    glUniformMatrix4fv(location, false, mat4Buffer)
}

fun glUniformMatrix4f(location: Int, value: Mat4) = glUniformMatrix4fv(location, false, value to mat4Buffer)
fun glUniformMatrix3f(location: Int, value: Mat3) = glUniformMatrix3fv(location, false, value to mat4Buffer)
fun glUniformMatrix3f(location: Int, value: Mat4) {
    mat4Buffer[0] = value[0][0]
    mat4Buffer[1] = value[0][1]
    mat4Buffer[2] = value[0][2]
    mat4Buffer[3] = value[1][0]
    mat4Buffer[4] = value[1][1]
    mat4Buffer[5] = value[1][2]
    mat4Buffer[6] = value[2][0]
    mat4Buffer[7] = value[2][1]
    mat4Buffer[8] = value[2][2]
    glUniformMatrix3fv(location, false, value to mat4Buffer)
}


fun glDeletePrograms(programs: IntArray) = programs.forEach { glDeleteProgram(it) }