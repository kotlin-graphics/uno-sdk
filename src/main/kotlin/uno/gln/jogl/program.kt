package uno.gln.jogl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL3
import uno.buffer.bufferOf
import uno.buffer.destroy
import uno.gl.iBuf
import uno.glsl.Program

fun GL3.glGetProgram(program: Int, pname: Int): Int {
    glGetProgramiv(program, pname, iBuf)
    return iBuf[0]
}

fun GL3.glGetProgramInfoLog(program: Int): String {

    val infoLogLength = glGetProgram(program, GL2ES2.GL_INFO_LOG_LENGTH)

    val bufferInfoLog = bufferOf(infoLogLength)
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
//    infix fun Vec2.to(location: Int) = GL20.glUniform2fv(location, this to v2Buf)
//    infix fun Vec3.to(location: Int) = GL20.glUniform3fv(location, this to v3Buf)
//    infix fun Vec4.to(location: Int) = GL20.glUniform4fv(location, this to v4Buf)
//
//    infix fun Mat2.to(location: Int) = GL20.glUniformMatrix2fv(location, false, this to m2Buf)
//    infix fun Mat3.to(location: Int) = GL20.glUniformMatrix3fv(location, false, this to m3Buf)
//    infix fun Mat4.to(location: Int) = GL20.glUniformMatrix4fv(location, false, this to m4Buf)
//
//    infix fun Int.to(uniform: String) = GL20.glUniform1i(uniform.location, this)
//    infix fun Float.to(uniform: String) = GL20.glUniform1f(uniform.location, this)
//
//    infix fun Vec2.to(uniform: String) = GL20.glUniform2fv(uniform.location, this to v2Buf)
//    infix fun Vec3.to(uniform: String) = GL20.glUniform3fv(uniform.location, this to v3Buf)
//    infix fun Vec4.to(uniform: String) = GL20.glUniform4fv(uniform.location, this to v4Buf)
//
//    infix fun Mat2.to(uniform: String) = GL20.glUniformMatrix2fv(uniform.location, false, this to m2Buf)
//    infix fun Mat3.to(uniform: String) = GL20.glUniformMatrix3fv(uniform.location, false, this to m3Buf)
//    infix fun Mat4.to(uniform: String) = GL20.glUniformMatrix4fv(uniform.location, false, this to m4Buf)
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

fun GL3.glUseProgram() = glUseProgram(0)
fun GL3.glUseProgram(program: Program) = glUseProgram(program.name)
fun GL3.glDeleteProgram(program: Program) = glDeleteProgram(program.name)
fun GL3.glDeletePrograms(vararg programs: Int) = programs.forEach { glDeleteProgram(it) }