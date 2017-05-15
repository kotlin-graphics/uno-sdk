package uno.gln

import com.jogamp.opengl.GL2GL3.GL_TEXTURE_1D
import gli.Texture2d
import gli.TextureCube
import gli.gl
import glm.vec1.Vec1i
import glm.vec2.Vec2i
import glm.vec3.Vec3i
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_TEXTURE_2D
import org.lwjgl.opengl.GL12
import org.lwjgl.opengl.GL12.GL_TEXTURE_3D
import org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X
import org.lwjgl.system.MemoryUtil
import java.nio.ByteBuffer

/**
 * Created by elect on 13/05/17.
 */


fun glTexImage1D(internalFormat:Int, width: Int, format: Int, type: Int) =
        GL11.glTexImage1D(GL_TEXTURE_2D, 0, internalFormat, width, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, internalFormat:Int, width: Int, format: Int, type: Int) =
        GL11.glTexImage1D(target, 0, internalFormat, width, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, internalFormat:Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage1D(target, 0, internalFormat, width, 0, format, type, pixels)

fun glTexImage1D(target:Int, level:Int, internalFormat:Int, width: Int, format: Int, type: Int) =
        GL11.glTexImage1D(target, level, internalFormat, width, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, level:Int, internalFormat:Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage1D(target, level, internalFormat, width, 0, format, type, pixels)


fun glTexImage1D(internalFormat:Int, size: Vec1i, format: Int, type: Int) =
        GL11.glTexImage1D(GL_TEXTURE_2D, 0, internalFormat, size.x, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, internalFormat:Int, size: Vec1i, format: Int, type: Int) =
        GL11.glTexImage1D(target, 0, internalFormat, size.x, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, internalFormat:Int, size: Vec1i, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage1D(target, 0, internalFormat, size.x, 0, format, type, pixels)

fun glTexImage1D(target:Int, level:Int, internalFormat:Int, size: Vec1i, format: Int, type: Int) =
        GL11.glTexImage1D(target, level, internalFormat, size.x, 0, format, type, MemoryUtil.NULL)

fun glTexImage1D(target:Int, level:Int, internalFormat:Int, size: Vec1i, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage1D(target, level, internalFormat, size.x, 0, format, type, pixels)


fun glTexImage1D(format: gl.Format, texture: gli.Texture) = glTexImage1D(0, format, texture)
fun glTexImage1D(level: Int, format: gl.Format, texture: gli.Texture) =
        GL11.glTexImage1D(
                GL_TEXTURE_1D,
                level,
                format.internal.i,
                texture.extent().x,
                0,
                format.external.i, format.type.i,
                texture.data())

fun glTexImage1D(format: gl.Format, texture: gli.Texture1d) = glTexImage1D(0, format, texture)
fun glTexImage1D(level: Int, format: gl.Format, texture: gli.Texture1d) =
        GL11.glTexImage1D(
                GL_TEXTURE_1D,
                level,
                format.internal.i,
                texture.extent().x,
                0,
                format.external.i, format.type.i,
                texture.data())

// ---------------------------------------------------------------------------------------------------------------------

fun glTexImage2D(internalFormat:Int, width: Int, height:Int, format: Int, type: Int) =
        GL11.glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, internalFormat:Int, width: Int, height:Int, format: Int, type: Int) =
        GL11.glTexImage2D(target, 0, internalFormat, width, height, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, internalFormat:Int, width: Int, height:Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage2D(target, 0, internalFormat, width, height, 0, format, type, pixels)

fun glTexImage2D(target:Int, level:Int, internalFormat:Int, width: Int, height:Int, format: Int, type: Int) =
        GL11.glTexImage2D(target, level, internalFormat, width, height, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, level:Int, internalFormat:Int, width: Int, height:Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage2D(target, level, internalFormat, width, height, 0, format, type, pixels)


fun glTexImage2D(internalFormat:Int, size: Vec2i, format: Int, type: Int) =
        GL11.glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, size.x, size.y, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, internalFormat:Int,size: Vec2i, format: Int, type: Int) =
        GL11.glTexImage2D(target, 0, internalFormat, size.x, size.y, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, internalFormat:Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage2D(target, 0, internalFormat, size.x, size.y, 0, format, type, pixels)

fun glTexImage2D(target:Int, level:Int, internalFormat:Int, size: Vec2i, format: Int, type: Int) =
        GL11.glTexImage2D(target, level, internalFormat, size.x, size.y, 0, format, type, MemoryUtil.NULL)

fun glTexImage2D(target:Int, level:Int, internalFormat:Int, size: Vec2i, format: Int, type: Int, pixels: ByteBuffer) =
        GL11.glTexImage2D(target, level, internalFormat, size.x, size.y, 0, format, type, pixels)


fun glTexImage2D(texture: gli.Texture) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImage2D(0, format, texture)
}

fun glTexImage2D(format: gl.Format, texture: gli.Texture) = glTexImage2D(0, format, texture)
fun glTexImage2D(level: Int, format: gl.Format, texture: gli.Texture) =
        GL11.glTexImage2D(
                GL_TEXTURE_2D,
                level,
                format.internal.i,
                texture.extent().x, texture.extent().y,
                0,
                format.external.i, format.type.i,
                texture.data())


fun glTexImage2D(texture: gli.Texture2d) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImage2D(0, format, texture)
}

fun glTexImage2D(format: gl.Format, texture: gli.Texture2d) = glTexImage2D(0, format, texture)
fun glTexImage2D(level: Int, format: gl.Format, texture: Texture2d) =
        GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D,
                level,
                format.internal.i,
                texture[level].extent().x, texture[level].extent().y,
                0,
                format.external.i, format.type.i,
                texture[level].data())

// ---------------------------------------------------------------------------------------------------------------------

fun glTexImage3D(internalFormat:Int, width: Int, height:Int, depth: Int, format: Int, type: Int) =
        GL12.glTexImage3D(GL_TEXTURE_3D, 0, internalFormat, width, height, depth, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, internalFormat:Int, width: Int, height:Int, depth: Int, format: Int, type: Int) =
        GL12.glTexImage3D(target, 0, internalFormat, width, height, depth, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, internalFormat:Int, width: Int, height:Int, depth: Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL12.glTexImage3D(target, 0, internalFormat, width, height, depth, 0, format, type, pixels)

fun glTexImage3D(target:Int, level:Int, internalFormat:Int, width: Int, height:Int, depth: Int, format: Int, type: Int) =
        GL12.glTexImage3D(target, level, internalFormat, width, height, depth, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, level:Int, internalFormat:Int, width: Int, height:Int, depth: Int, format: Int, type: Int, pixels: ByteBuffer) =
        GL12.glTexImage3D(target, level, internalFormat, width, height, depth, 0, format, type, pixels)


fun glTexImage3D(internalFormat:Int, size: Vec3i, format: Int, type: Int) =
        GL12.glTexImage3D(GL_TEXTURE_3D, 0, internalFormat, size.x, size.y, size.z, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, internalFormat:Int,size: Vec3i, format: Int, type: Int) =
        GL12.glTexImage3D(target, 0, internalFormat, size.x, size.y, size.z, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, internalFormat:Int, size: Vec3i, format: Int, type: Int, pixels: ByteBuffer) =
        GL12.glTexImage3D(target, 0, internalFormat, size.x, size.y, size.z, 0, format, type, pixels)

fun glTexImage3D(target:Int, level:Int, internalFormat:Int, size: Vec3i, format: Int, type: Int) =
        GL12.glTexImage3D(target, level, internalFormat, size.x, size.y, size.z, 0, format, type, MemoryUtil.NULL)

fun glTexImage3D(target:Int, level:Int, internalFormat:Int, size: Vec3i, format: Int, type: Int, pixels: ByteBuffer) =
        GL12.glTexImage3D(target, level, internalFormat, size.x, size.y, size.z, 0, format, type, pixels)


fun glTexImage3D(texture: gli.Texture) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImage3D(0, format, texture)
}

fun glTexImage3D(format: gl.Format, texture: gli.Texture) = glTexImage3D(0, format, texture)
fun glTexImage3D(level: Int, format: gl.Format, texture: gli.Texture) =
        GL12.glTexImage3D(
                GL_TEXTURE_3D,
                level,
                format.internal.i,
                texture.extent().x, texture.extent().y, texture.extent().z,
                0,
                format.external.i, format.type.i,
                texture.data())


fun glTexImage3D(texture: gli.Texture2d) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImage3D(0, format, texture)
}

fun glTexImage3D(format: gl.Format, texture: gli.Texture2d) = glTexImage3D(0, format, texture)
fun glTexImage3D(level: Int, format: gl.Format, texture: Texture2d) =
        GL12.glTexImage3D(
                GL12.GL_TEXTURE_3D,
                level,
                format.internal.i,
                texture[level].extent().x, texture[level].extent().y, texture[level].extent().z,
                0,
                format.external.i, format.type.i,
                texture[level].data())

// ---------------------------------------------------------------------------------------------------------------------


fun glTexImageCube(face: Int, texture: gli.Texture) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImageCube(face, 0, format, texture)
}

fun glTexImageCube(face: Int, format: gl.Format, texture: gli.Texture) = glTexImageCube(face, 0, format, texture)
fun glTexImageCube(face: Int, level: Int, format: gl.Format, texture: gli.Texture) =
        GL11.glTexImage2D(
                GL_TEXTURE_CUBE_MAP_POSITIVE_X + face,
                level,
                format.internal.i,
                texture.extent().x, texture.extent().y,
                0,
                format.external.i, format.type.i,
                texture.data(0, face, level))


fun glTexImageCube(face: Int, texture: gli.TextureCube) {
    val format = gli.gl.translate(texture.format, texture.swizzles)
    return glTexImageCube(face, 0, format, texture)
}

fun glTexImageCube(face: Int, format: gl.Format, texture: TextureCube) = glTexImageCube(face, 0, format, texture)
fun glTexImageCube(face: Int, level: Int, format: gl.Format, texture: TextureCube) =
        GL11.glTexImage2D(
                GL_TEXTURE_CUBE_MAP_POSITIVE_X + face,
                level,
                format.internal.i,
                texture.extent().x, texture.extent().y,
                0,
                format.external.i, format.type.i,
                texture[face].data())