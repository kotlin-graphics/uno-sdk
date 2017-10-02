package uno

import gli_.gl
import gli_.gli
import gln.texture.glCompressedTexSubImage2D
import gln.texture.glTexStorage2D
import gln.texture.initTexture2d
import io.kotlintest.specs.StringSpec
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL12.GL_TEXTURE_BASE_LEVEL
import org.lwjgl.opengl.GL12.GL_TEXTURE_MAX_LEVEL
import org.lwjgl.opengl.GL13.glCompressedTexSubImage2D
import org.lwjgl.opengl.GL33.GL_TEXTURE_SWIZZLE_RGBA
import org.lwjgl.opengl.GL42.glTexStorage2D
import uno.buffer.intBufferBig

class testGli : StringSpec() {

    init {

        fun createTexture(filename: String): Int {

            val texture = gli.load(filename)
            if(texture.empty())
                return 0

            gli.gl.profile = gl.Profile.GL33
            val format = gli.gl.translate(texture.format, texture.swizzles)
            val target = gli.gl.translate(texture.target)
            assert(texture.format.isCompressed && target == gl.Target._2D)

            val textureName = intBufferBig(1)
            glGenTextures(textureName)
            glBindTexture(target.i, textureName[0])
            glTexParameteri(target.i, GL_TEXTURE_BASE_LEVEL, 0)
            glTexParameteri(target.i, GL_TEXTURE_MAX_LEVEL, texture.levels() - 1)
            val swizzles = intBufferBig(4)
            format.swizzles to swizzles
            glTexParameteriv(target.i, GL_TEXTURE_SWIZZLE_RGBA, swizzles)
            var extent = texture.extent()
            glTexStorage2D(target.i, texture.levels(), format.internal.i, extent.x, extent.y)
            for(level in 0 until texture.levels()) {
                extent = texture.extent(level)
                glCompressedTexSubImage2D(
                        target.i, level, 0, 0, extent.x, extent.y,
                        format.internal.i, texture.data(0, 0, level))
            }
            return textureName[0]
        }
    }

    fun createTexture(filename: String): Int {

        val texture = gli.load(filename)
        if(texture.empty())
            return 0

        gli.gl.profile = gl.Profile.GL33
        val (target, format) = gli.gl.translate(texture)
        assert(texture.format.isCompressed && target == gl.Target._2D)

        return initTexture2d {
            levels = 0 until texture.levels()
            swizzles = format.swizzles
            storage(target, texture.levels(), format.internal, texture.extent())
        }
    }
}