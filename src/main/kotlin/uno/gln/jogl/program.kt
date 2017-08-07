package uno.gln.jogl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL3
import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import uno.buffer.byteBufferOf
import uno.buffer.destroy
import uno.gln.*
import uno.glsl.Program

fun GL3.glGetProgram(program: Int, pname: Int): Int {
    glGetProgramiv(program, pname, intBuffer)
    return intBuffer[0]
}

fun GL3.glGetProgramInfoLog(program: Int): String {

    val infoLogLength = glGetProgram(program, GL2ES2.GL_INFO_LOG_LENGTH)

    val bufferInfoLog = byteBufferOf(infoLogLength)
    glGetProgramInfoLog(program, infoLogLength, null, bufferInfoLog)

    val bytes = ByteArray(infoLogLength)
    bufferInfoLog.get(bytes).destroy()

    return String(bytes)
}

inline fun GL3.usingProgram(program: Int = 0, block: ProgramUse.() -> Unit) {
    ProgramUse.gl = this
    ProgramUse.name = program //glUse
    ProgramUse.block()
    glUseProgram(0)
}

inline fun GL3.usingProgram(program: Program, block: ProgramUse.() -> Unit) {
    ProgramUse.gl = this
    ProgramUse.name = program.name //glUse
    ProgramUse.block()
    glUseProgram(0)
}

inline fun GL3.withProgram(program: Int = 0, block: ProgramBase.() -> Unit) {
    ProgramBase.gl = this
    ProgramBase.name = program
    ProgramBase.block()
}

inline fun GL3.withProgram(program: Program, block: ProgramBase.() -> Unit) {
    ProgramBase.gl = this
    ProgramBase.name = program.name
    ProgramBase.block()
}

object ProgramUse {

    lateinit var gl:GL3

    var name = 0
        set(value) {
            gl.glUseProgram(value)
            field = value
        }

    val String.location: Int
        get() = gl.glGetUniformLocation(name, this)

    var String.blockIndex
        get() = gl.glGetUniformBlockIndex(name, this)
        set(value) = gl.glUniformBlockBinding(name, gl.glGetUniformBlockIndex(name, this), value)

    var String.unit: Int
        get() = location
        set(value) = gl.glUniform1i(location, value)


    fun link() = gl.glLinkProgram(name)

//    infix fun Int.to(location: Int) = GL20.glUniform1i(location, this)
//    infix fun Float.to(location: Int) = GL20.glUniform1f(location, this)
//
//    infix fun Vec2.to(location: Int) = GL20.glUniform2fv(location, this to vec2Buffer)
//    infix fun Vec3.to(location: Int) = GL20.glUniform3fv(location, this to vec3Buffer)
//    infix fun Vec4.to(location: Int) = GL20.glUniform4fv(location, this to vec4Buffer)
//
//    infix fun Mat2.to(location: Int) = GL20.glUniformMatrix2fv(location, false, this to mat2Buffer)
//    infix fun Mat3.to(location: Int) = GL20.glUniformMatrix3fv(location, false, this to mat3Buffer)
//    infix fun Mat4.to(location: Int) = GL20.glUniformMatrix4fv(location, false, this to mat4Buffer)
//
//    infix fun Int.to(uniform: String) = GL20.glUniform1i(uniform.location, this)
//    infix fun Float.to(uniform: String) = GL20.glUniform1f(uniform.location, this)
//
//    infix fun Vec2.to(uniform: String) = GL20.glUniform2fv(uniform.location, this to vec2Buffer)
//    infix fun Vec3.to(uniform: String) = GL20.glUniform3fv(uniform.location, this to vec3Buffer)
//    infix fun Vec4.to(uniform: String) = GL20.glUniform4fv(uniform.location, this to vec4Buffer)
//
//    infix fun Mat2.to(uniform: String) = GL20.glUniformMatrix2fv(uniform.location, false, this to mat2Buffer)
//    infix fun Mat3.to(uniform: String) = GL20.glUniformMatrix3fv(uniform.location, false, this to mat3Buffer)
//    infix fun Mat4.to(uniform: String) = GL20.glUniformMatrix4fv(uniform.location, false, this to mat4Buffer)
}

object ProgramBase {

    lateinit var gl:GL3

    var name = 0

    var String.location
        get() = gl.glGetUniformLocation(name, this)
        set(value) = gl.glBindAttribLocation(name, value, this)

    // read only because no program is used
    val String.blockIndex
        get() = gl.glGetUniformBlockIndex(name, this)

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        gl.glUseProgram(0)
    }

    infix fun Int.blockBinding(uniformBlockBinding: Int) = gl.glUniformBlockBinding(name, this, uniformBlockBinding)
    infix fun Int.getBlockIndex(uniformBlockName: String) = gl.glGetUniformBlockIndex(this, uniformBlockName)

    fun link() = gl.glLinkProgram(name)

    operator fun plusAssign(shader: Int) = gl.glAttachShader(name, shader)
    infix fun attach(shader: Int) = gl.glAttachShader(name, shader)
}

inline fun GL3.usingProgram(program: Int = 0, block: ProgramA.(GL3) -> Unit) {
    ProgramA.gl = this
    ProgramA.name = program //glUse
    ProgramA.block(this)
    glUseProgram(0)
}

//inline fun GL3.withProgram(program: Int = 0, block: ProgramB.() -> Unit) {
//    ProgramB.gl = this
//    ProgramB.name = program
//    ProgramB.block()
//}

object ProgramA {

    lateinit var gl: GL3
    var name = 0
        set(value) {
            gl.glUseProgram(value)
            field = value
        }

    val String.location: Int
        get() = gl.glGetUniformLocation(name, this)

    var Int.int: Int
        get() = 0
        set(value) = gl.glUniform1i(this, value)
    var Int.float: Float
        get() = 0f
        set(value) = gl.glUniform1f(this, value)
    var Int.vec2: Vec2
        get() = Vec2()
        set(value) = gl.glUniform2f(this, value.x, value.y)
    var Int.vec3: Vec3
        get() = Vec3()
        set(value) = gl.glUniform3f(this, value.x, value.y, value.z)
    var Int.vec4: Vec4
        get() = Vec4()
        set(value) = gl.glUniform4f(this, value.x, value.y, value.z, value.w)
    var Int.mat4: Mat4
        get() = Mat4()
        set(value) = gl.glUniformMatrix4fv(this, 1, false, value to mat4Buffer)
    var Int.mat3: Mat3
        get() = Mat3()
        set(value) = gl.glUniformMatrix3fv(this, 1, false, value to mat4Buffer)


    fun GL3.link() = glLinkProgram(name)

    infix fun Vec4.to(location: Int) = gl.glUniform4fv(location, 1, this to mat4Buffer)
    infix fun Mat4.to(location: Int) = gl.glUniformMatrix4fv(location, 1, false, this to mat4Buffer)
}

object ProgramB {

    lateinit var gl: GL3
    var name = 0

    val String.location
        get() = gl.glGetUniformLocation(name, this)
    val String.blockIndex
        get() = gl.glGetUniformBlockIndex(name, this)

    inline fun use(block: ProgramA.(GL3) -> Unit) {
        ProgramA.gl = gl
        ProgramA.name = name
        ProgramA.block(gl)
        gl.glUseProgram(0)
    }

    infix fun Int.blockBinding(uniformBlockBinding: Int) = gl.glUniformBlockBinding(name, this, uniformBlockBinding)
}


fun GL3.glUseProgram() = glUseProgram(0)
fun GL3.glDeletePrograms(vararg programs: Int) = programs.forEach { glDeleteProgram(it) }