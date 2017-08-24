package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL3
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.system.MemoryStack
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


val fBuf get() = MemoryStack.stackGet().callocFloat(1)
val v2Buf get() = MemoryStack.stackGet().callocFloat(2)
val v3Buf get() = MemoryStack.stackGet().callocFloat(3)
val v4Buf get() = MemoryStack.stackGet().callocFloat(4)

val iBuf get() = MemoryStack.stackGet().callocInt(1)
val v2iBuf get() = MemoryStack.stackGet().callocInt(2)
val v3iBuf get() = MemoryStack.stackGet().callocInt(3)
val v4iBuf get() = MemoryStack.stackGet().callocInt(4)

val m2Buf get() = MemoryStack.stackGet().callocFloat(4)
val m23Buf get() = MemoryStack.stackGet().callocFloat(6)
val m24Buf get() = MemoryStack.stackGet().callocFloat(8)
val m32Buf get() = MemoryStack.stackGet().callocFloat(6)
val m3Buf get() = MemoryStack.stackGet().callocFloat(9)
val m34Buf get() = MemoryStack.stackGet().callocFloat(12)
val m42Buf get() = MemoryStack.stackGet().callocFloat(8)
val m43Buf get() = MemoryStack.stackGet().callocFloat(12)
val m4Buf get() = MemoryStack.stackGet().callocFloat(16)

val m2dBuf get() = MemoryStack.stackGet().callocDouble(4)
val m23dBuf get() = MemoryStack.stackGet().callocDouble(6)
val m24dBuf get() = MemoryStack.stackGet().callocDouble(8)
val m32dBuf get() = MemoryStack.stackGet().callocDouble(6)
val m3dBuf get() = MemoryStack.stackGet().callocDouble(9)
val m34dBuf get() = MemoryStack.stackGet().callocDouble(12)
val m42dBuf get() = MemoryStack.stackGet().callocDouble(8)
val m43dBuf get() = MemoryStack.stackGet().callocDouble(12)
val m4dBuf get() = MemoryStack.stackGet().callocDouble(4)