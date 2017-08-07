package uno.gln.jogl

import com.jogamp.opengl.GL2ES2
import com.jogamp.opengl.GL3
import glm_.set
import uno.buffer.byteBufferOf
import uno.buffer.destroy
import uno.gl.intBuffer


fun GL3.glShaderSource(shader: Int, source: String) {
    val lines = arrayOf(source)
    intBuffer[0] = lines[0].length
    glShaderSource(shader, 1, lines, intBuffer)
}

fun GL3.glGetShader(shader: Int, pname: Int): Int {
    glGetShaderiv(shader, pname, intBuffer)
    return intBuffer[0]
}

fun GL3.glGetShaderInfoLog(shader: Int): String {

    val infoLogLength = glGetShader(shader, GL2ES2.GL_INFO_LOG_LENGTH)

    val bufferInfoLog = byteBufferOf(infoLogLength)
    glGetShaderInfoLog(shader, infoLogLength, null, bufferInfoLog)

    val bytes = ByteArray(infoLogLength)
    bufferInfoLog.get(bytes).destroy()

    return String(bytes)
}