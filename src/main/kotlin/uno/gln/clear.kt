package uno.gln

import glm.f
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30

/**
 * Created by GBarbieri on 21.04.2017.
 */

inline fun clear(block: Clear.() -> Unit) = Clear.block()

object Clear {

    fun color() = color(0f, 0f, 0f, 1f)
    fun color(f: Float) = color(f, f, f, f)
    fun color(r: Float, g: Float, b: Float, a: Float) =
            GL30.glClearBufferfv(GL11.GL_COLOR, 0, mat4Buffer.put(0, r).put(1, g).put(2, b).put(3, a))

    fun color(n: Number) = color(n, n, n, n)
    fun color(r: Number, g: Number, b: Number, a: Number) =
            GL30.glClearBufferfv(GL11.GL_COLOR, 0, mat4Buffer.put(0, r.f).put(1, g.f).put(2, b.f).put(3, a.f))

    fun depth() = depth(1f)
    fun depth(depth: Float) = GL30.glClearBufferfv(GL11.GL_DEPTH, 0, floatBuffer.put(0, depth))
    fun depth(depth: Number) = depth(depth.f)

    // TODO stencil
}