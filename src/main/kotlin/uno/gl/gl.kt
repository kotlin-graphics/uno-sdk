package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL3
import uno.buffer.doubleBufferBig
import uno.buffer.floatBufferBig
import uno.buffer.intBufferBig

/**
 * Created by elect on 05/03/17.
 */

/* -------------------------- GL_EXT_texture_sRGB -------------------------- */
//val GL_COMPRESSED_SRGB_S3TC_DXT1_EXT = 0x8C4C
//val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT = 0x8C4D
//val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT = 0x8C4E
//val GL_COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT = 0x8C4F
//
///* --------------------- GL_ATI_texture_compression_3dc -------------------- */
//val GL_COMPRESSED_LUMINANCE_ALPHA_3DC_ATI = 0x8837
//
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


val floatBuffer = floatBufferBig(1)
val intBuffer = intBufferBig(1)

val vec2Buffer = floatBufferBig(2)
val vec3Buffer = floatBufferBig(3)
val vec4Buffer = floatBufferBig(4)

val vec2iBuffer = intBufferBig(2)
val vec4iBuffer = intBufferBig(4)

val mat2Buffer = floatBufferBig(2 * 2)
val mat2x3Buffer = floatBufferBig(2 * 3)
val mat2x4Buffer = floatBufferBig(2 * 4)
val mat3x2Buffer = floatBufferBig(3 * 2)
val mat3Buffer = floatBufferBig(3 * 3)
val mat3x4Buffer = floatBufferBig(3 * 4)
val mat4x2Buffer = floatBufferBig(4 * 2)
val mat4x3Buffer = floatBufferBig(4 * 3)
val mat4Buffer = floatBufferBig(4 * 4)

val mat2dBuffer = doubleBufferBig(2 * 2)
val mat2x3dBuffer = doubleBufferBig(2 * 3)
val mat2x4dBuffer = doubleBufferBig(2 * 4)
val mat3x2dBuffer = doubleBufferBig(3 * 2)
val mat3dBuffer = doubleBufferBig(3 * 3)
val mat3x4dBuffer = doubleBufferBig(3 * 4)
val mat4x2dBuffer = doubleBufferBig(4 * 2)
val mat4x3dBuffer = doubleBufferBig(4 * 3)
val mat4dBuffer = doubleBufferBig(4 * 4)