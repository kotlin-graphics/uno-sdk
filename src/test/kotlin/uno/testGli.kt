package uno

import io.kotlintest.specs.StringSpec
import org.lwjgl.opengl.GL11.glGenTextures
import org.lwjgl.opengl.GL12.GL_TEXTURE_BASE_LEVEL
import org.lwjgl.opengl.GL12.GL_TEXTURE_MAX_LEVEL
import org.lwjgl.opengl.GL33.GL_TEXTURE_SWIZZLE_RGBA
import uno.buffer.intBufferBig
import uno.gln.*

class testGli : StringSpec() {

    init {

        fun createTexture(filename: String): Int {

            val texture = gli.load(filename)
            if(texture.empty())
                return 0

            gli.gl.profile = gli.gl.Profile.GL33
            val format = gli.gl.translate(texture.format, texture.swizzles)
            val target = gli.gl.translate(texture.target)
            assert(texture.format.isCompressed && target == gli.gl.Target._2D)

            val textureName = intBufferBig(1)
            glGenTextures(textureName)
            glBindTexture(target, textureName)
            glTexParameteri(target, GL_TEXTURE_BASE_LEVEL, 0)
            glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, texture.levels() - 1)
            glTexParameteriv(target, GL_TEXTURE_SWIZZLE_RGBA, format.swizzles)
            glTexStorage2D(target, texture.levels(), format.internal, texture.extent())
            for(level in 0 until texture.levels()) {
                glCompressedTexSubImage2D(
                        target, level, 0, 0, texture.extent(level),
                        format.internal, texture.data(0, 0, level))
            }
            return textureName[0]
        }
    }
}