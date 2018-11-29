package uno.buffer

import glm_.BYTES
import kool.Buffer
import kool.FloatBuffer
import kool.IntBuffer
import glm_.i
import glm_.set
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4b
import glm_.vec4.Vec4i
import gln.glf.Vertex
import gln.glf.glf
import org.lwjgl.system.MemoryUtil
import java.nio.*


fun bufferOf(vertices: Collection<*>): ByteBuffer {
    val res: ByteBuffer
    when (vertices.elementAt(0)) {
        is Vec2 -> {
            res = Buffer(Vec2.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.size)
        }
        is Vec3 -> {
            res = Buffer(Vec3.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.size)
        }
        is Vec4 -> {
            res = Buffer(Vec4.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec4).to(res, i * Vec4.size)
        }
        is Vertex.pos2_tc2 -> {
            res = Buffer(glf.pos2_tc2.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos2_tc2
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.t.to(res, i * glf.pos2_tc2.stride + Vec2.size)
            }
        }
        is Vertex.pos3_col4ub -> {
            res = Buffer(glf.pos3_col4ub.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos3_col4ub
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.c.to(res, i * glf.pos2_tc2.stride + Vec3.size)
            }
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(vararg vertices: Any): ByteBuffer {
    val res: ByteBuffer
    when (vertices.elementAt(0)) {
        is Float -> {
            res = Buffer(Float.BYTES * vertices.size)
            for (i in 0 until vertices.size)
                res.putFloat(i * Float.BYTES, (vertices[i] as Float))
        }
        is Int -> {
            res = Buffer(Int.BYTES * vertices.size)
            for (i in 0 until vertices.size)
                res.putInt(i * Int.BYTES, (vertices[i] as Int))
        }
        is Vec2 -> {
            res = Buffer(Vec2.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec2).to(res, i * Vec2.size)
        }
        is Vec3 -> {
            res = Buffer(Vec3.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec3).to(res, i * Vec3.size)
        }
        is Vec4 -> {
            res = Buffer(Vec4.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec4).to(res, i * Vec4.size)
        }
        is Vec4b -> {
            res = Buffer(Vec4b.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec4b).to(res, i * Vec4b.size)
        }
        is Vertex.pos2_tc2 -> {
            res = Buffer(glf.pos2_tc2.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos2_tc2
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.t.to(res, i * glf.pos2_tc2.stride + Vec2.size)
            }
        }
        is Vertex.pos3_col4ub -> {
            res = Buffer(glf.pos3_col4ub.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos3_col4ub
                v.p.to(res, i * glf.pos3_col4ub.stride)
                v.c.to(res, i * glf.pos3_col4ub.stride + Vec3.size)
            }
        }
        is Vertex.pos3_nor3_col4 -> {
            res = Buffer(glf.pos3_nor3_col4.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos3_nor3_col4
                v.p.to(res, i * glf.pos3_nor3_col4.stride)
                v.n.to(res, i * glf.pos3_nor3_col4.stride + Vec3.size)
                v.c.to(res, i * glf.pos3_nor3_col4.stride + Vec3.size * 2)
            }
        }
        else -> throw Error()
    }
    return res
}

fun floatBufferOf(vertices: Collection<*>): FloatBuffer {
    val res: FloatBuffer
    when (vertices.elementAt(0)) {
        is Float -> {
            res = FloatBuffer(vertices.size)
            for (i in 0 until vertices.size)
                res.put(i, (vertices.elementAt(i) as Float))
        }
        is Vec2 -> {
            res = FloatBuffer(Vec2.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.length)
        }
        is Vec3 -> {
            res = FloatBuffer(Vec3.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.length)
        }
        is Vec4 -> {
            res = FloatBuffer(Vec4.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec4).to(res, i * Vec4.length)
        }
        else -> throw Error()
    }
    return res
}

fun intBufferOf(vertices: Collection<*>): IntBuffer {
    val res: IntBuffer
    when (vertices.elementAt(0)) {
        is Int -> {
            res = IntBuffer(vertices.size)
            for (i in 0 until vertices.size)
                res.put(i, (vertices.elementAt(i) as Int))
        }
        is Vec2i -> {
            res = IntBuffer(Vec2i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2i).to(res, i * Vec2i.length)
        }
        is Vec3i -> {
            res = IntBuffer(Vec3i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3i).to(res, i * Vec3i.length)
        }
        is Vec4i -> {
            res = IntBuffer(Vec4i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec4i).to(res, i * Vec4i.length)
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(charSequence: CharSequence): ByteBuffer {
    val buffer = Buffer(charSequence.length)
    for (i in charSequence.indices)
        buffer[i] = charSequence[i].i
    return buffer
}