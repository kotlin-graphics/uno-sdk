package uno.gln.jogl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL3
import glm_.set
import uno.buffer.bufferOf
import uno.buffer.destroy
import uno.gl.iBuf


fun GL3.glShaderSource(shader: Int, source: String) {
    val lines = arrayOf(source)
    iBuf[0] = lines[0].length
    glShaderSource(shader, 1, lines, iBuf)
}

fun GL3.glGetShader(shader: Int, pname: Int): Int {
    glGetShaderiv(shader, pname, iBuf)
    return iBuf[0]
}

fun GL3.glGetShaderInfoLog(shader: Int): String {

    val infoLogLength = glGetShader(shader, GL2ES2.GL_INFO_LOG_LENGTH)

    val bufferInfoLog = bufferOf(infoLogLength)
    glGetShaderInfoLog(shader, infoLogLength, null, bufferInfoLog)

    val bytes = ByteArray(infoLogLength)
    bufferInfoLog.get(bytes).destroy()

    return String(bytes)
}