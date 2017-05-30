package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL
import com.jogamp.opengl.GL.*
import com.jogamp.opengl.GL3
import com.jogamp.opengl.GLException
import glm.vec2.Vec2
import glm.vec2.Vec2i
import glm.vec4.Vec4
import glm.vec4.Vec4i
import org.lwjgl.opengl.GL11.glGetFloatv
import org.lwjgl.opengl.GL11.glGetIntegerv
import uno.gln.vec2Buffer
import uno.gln.vec2iBuffer
import uno.gln.vec4Buffer
import uno.gln.vec4iBuffer

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
    glGetFloatv(pname, vec2Buffer)
    return Vec2(vec2Buffer)
}

fun glGetVec2i(pname: Int): Vec2i {
    glGetIntegerv(pname, vec2iBuffer)
    return Vec2i(vec2iBuffer)
}

fun glGetVec4(pname: Int): Vec4 {
    glGetFloatv(pname, vec4Buffer)
    return Vec4(vec4Buffer)
}

fun glGetVec4i(pname: Int): Vec4i {
    glGetIntegerv(pname, vec4iBuffer)
    return Vec4i(vec4iBuffer)
}