package uno.gl

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL3
import glm_.mat4x4.Mat4d
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.memAddress
import uno.buffer.bufferBig
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


val fBuf = floatBufferBig(1)
val v2Buf = floatBufferBig(2)
val v3Buf = floatBufferBig(3)
val v4Buf = floatBufferBig(4)

val iBuf = intBufferBig(1)
val v2iBuf = intBufferBig(2)
val v3iBuf = intBufferBig(3)
val v4iBuf = intBufferBig(4)

val m2Buf = floatBufferBig(4)
val m23Buf = floatBufferBig(6)
val m24Buf = floatBufferBig(8)
val m32Buf = floatBufferBig(6)
val m3Buf = floatBufferBig(9)
val m34Buf = floatBufferBig(12)
val m42Buf = floatBufferBig(8)
val m43Buf = floatBufferBig(12)
val m4Buf = floatBufferBig(16)

val m2dBuf = doubleBufferBig(4)
val m23dBuf = doubleBufferBig(6)
val m24dBuf = doubleBufferBig(8)
val m32dBuf = doubleBufferBig(6)
val m3dBuf = doubleBufferBig(9)
val m34dBuf = doubleBufferBig(12)
val m42dBuf = doubleBufferBig(8)
val m43dBuf = doubleBufferBig(12)
val m4dBuf = doubleBufferBig(4)


val fBufAd = memAddress(fBuf)
val v2BufAd = memAddress(v2Buf)
val v3BufAd = memAddress(v3Buf)
val v4BufAd = memAddress(v4Buf)

val iBufAd = memAddress(iBuf)
val v2iBufAd = memAddress(v2iBuf)
val v3iBufAd = memAddress(v3iBuf)
val v4iBufAd = memAddress(v4iBuf)

val m2BufAd = memAddress(m2Buf)
val m23BufAd = memAddress(m23Buf)
val m24BufAd = memAddress(m24Buf)
val m32BufAd = memAddress(m32Buf)
val m3BufAd = memAddress(m3Buf)
val m34BufAd = memAddress(m34Buf)
val m42BufAd = memAddress(m42Buf)
val m43BufAd = memAddress(m43Buf)
val m4BufAd = memAddress(m4Buf)

val m2dBufAd = memAddress(m2dBuf)
val m23dBufAd = memAddress(m23dBuf)
val m24dBufAd = memAddress(m24dBuf)
val m32dBufAd = memAddress(m32dBuf)
val m3dBufAd = memAddress(m3dBuf)
val m34dBufAd = memAddress(m34dBuf)
val m42dBufAd = memAddress(m42dBuf)
val m43dBufAd = memAddress(m43dBuf)
val m4dBufAd = memAddress(m4dBuf)


val buf = bufferBig(256)
val bufAd = memAddress(buf)


