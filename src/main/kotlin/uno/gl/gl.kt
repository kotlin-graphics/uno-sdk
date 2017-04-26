package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL
import com.jogamp.opengl.GL.*
import com.jogamp.opengl.GL3
import com.jogamp.opengl.GLException
import glm.vec2.Vec2
import org.lwjgl.opengl.GL11.glGetFloatv
import uno.buffer.byteBufferBig
import uno.buffer.floatBufferBig
import uno.buffer.intBufferBig
import uno.buffer.longBufferBig

/**
 * Created by elect on 05/03/17.
 */

/* -------------------------- GL_EXT_texture_sRGB -------------------------- */
val GL_COMPRESSED_SRGB_S3TC_DXT1_EXT = 0x8C4C
val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT = 0x8C4D
val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT = 0x8C4E
val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT = 0x8C4F

/* --------------------- GL_ATI_texture_compression_3dc -------------------- */
val GL_COMPRESSED_LUMINANCE_ALPHA_3DC_ATI = 0x8837

/* -------------------- GL_3DFX_texture_compression_FXT1 ------------------- */
val GL_COMPRESSED_RGB_FXT1_3DFX = 0x86B0
val GL_COMPRESSED_RGBA_FXT1_3DFX = 0x86B1

/* ------------------- GL_OES_compressed_paletted_texture ------------------ */
val GL_PALETTE4_RGB8_OES = 0x8B90
val GL_PALETTE4_RGBA8_OES = 0x8B91
val GL_PALETTE4_R5_G6_B5_OES = 0x8B92
val GL_PALETTE4_RGBA4_OES = 0x8B93
val GL_PALETTE4_RGB5_A1_OES = 0x8B94
val GL_PALETTE8_RGB8_OES = 0x8B95
val GL_PALETTE8_RGBA8_OES = 0x8B96
val GL_PALETTE8_R5_G6_B5_OES = 0x8B97
val GL_PALETTE8_RGBA4_OES = 0x8B98
val GL_PALETTE8_RGB5_A1_OES = 0x8B99


inline infix fun GLWindow.gl3(crossinline inject: GL3.() -> Unit) {
    invoke(false) { glAutoDrawable ->
        glAutoDrawable.gl.gL3.inject()
        false
    }
}

fun checkError(gl: GL, location: String) {

    val error = gl.glGetError()
    if (error != GL_NO_ERROR) {
        val errorString: String
        when (error) {
            GL_INVALID_ENUM -> errorString = "GL_INVALID_ENUM"
            GL_INVALID_VALUE -> errorString = "GL_INVALID_VALUE"
            GL_INVALID_OPERATION -> errorString = "GL_INVALID_OPERATION"
            GL_INVALID_FRAMEBUFFER_OPERATION -> errorString = "GL_INVALID_FRAMEBUFFER_OPERATION"
            GL_OUT_OF_MEMORY -> errorString = "GL_OUT_OF_MEMORY"
            else -> errorString = "UNKNOWN"
        }
        throw GLException("OpenGL Error($errorString): $location")
    }
}

fun glGetVec2(pname: Int): Vec2 {
    glGetFloatv(pname, vec2)
    return Vec2(vec2)
}

private val byte = byteBufferBig(1)
private val int = intBufferBig(1)
private val long = longBufferBig(1)
private val float = floatBufferBig(1)
private val vec2 = floatBufferBig(2)