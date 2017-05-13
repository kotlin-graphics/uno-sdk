package uno.gln

import glm.vec2.Vec2i
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.GL_RENDERBUFFER

/**
 * Created by elect on 13/05/17.
 */

fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
fun glRenderbufferStorage(target:Int, internalFormat: Int, size: Vec2i) = GL30.glRenderbufferStorage(target, internalFormat, size.x, size.y)