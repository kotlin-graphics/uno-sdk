package uno.buffer

import glm_.BYTES
import glm_.b
import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec3.Vec3i
import glm_.vec4.Vec4
import glm_.vec4.Vec4b
import glm_.vec4.Vec4i
import glm_.vec4.Vec4ub
import gln.glf.Vertex
import gln.glf.glf
import kool.*
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer


fun bufferOf(vertices: Collection<*>): ByteBuffer {
    val res: ByteBuffer
    when (vertices.elementAt(0)) {
        is Vec2 -> {
            res = Buffer(Vec2.size * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.size)
        }
        is Vec3 -> {
            res = Buffer(Vec3.size * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.size)
        }
        is Vec4 -> {
            res = Buffer(Vec4.size * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec4).to(res, i * Vec4.size)
        }
        is Vertex.pos2_tc2 -> {
            res = Buffer(glf.pos2_tc2.stride * vertices.size)
            for (i in vertices.indices) {
                val v = vertices.elementAt(i) as Vertex.pos2_tc2
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.t.to(res, i * glf.pos2_tc2.stride + Vec2.size)
            }
        }
        is Vertex.pos3_col4ub -> {
            res = Buffer(glf.pos3_col4ub.stride * vertices.size)
            for (i in vertices.indices) {
                val v = vertices.elementAt(i) as Vertex.pos3_col4ub
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.c.to(res, i * glf.pos2_tc2.stride + Vec3.size)
            }
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(vararg elements: Any): ByteBuffer {
    val size = elements.sumBy {
        when (it) {
            is Float, Int, Vec4b -> Float.BYTES
            is Vec2 -> Vec2.size
            is Vec3 -> Vec3.size
            is Vec4 -> Vec4.size
            is Vertex.pos2_tc2 -> Vec2.size * 2
            is Vertex.pos3_col4ub -> Vec3.size + Vec4ub.size
            is Vertex.pos3_nor3_col4 -> Vec3.size * 2 + Vec4.size
            else -> throw Exception("Invalid")
        }
    }
    val res = Buffer(size)
    var offset = 0
    for (e in elements)
        when (e) {
            is Float -> {
                res.putFloat(offset, e)
                offset += Float.BYTES
            }
            is Int -> {
                res.putInt(offset, e)
                offset += Int.BYTES
            }
            is Vec4b -> {
                e.to(res, offset)
                offset += Vec4b.size
            }
            is Vec2 -> {
                e.to(res, offset)
                offset += Vec2.size
            }
            is Vec3 -> {
                e.to(res, offset)
                offset += Vec3.size
            }
            is Vec4 -> {
                e.to(res, offset)
                offset += Vec4.size
            }
//            is Vertex.pos2_tc2 -> {
//                e.to(res, offset)
//                offset += Vec4b.size
//            }
//            is Vertex.pos3_col4ub -> Vec3.size + Vec4ub.size
//            is Vertex.pos3_nor3_col4 -> Vec3.size * 2 + Vec4.size
            else -> throw Exception("Invalid")
        }
    return res
}

fun floatBufferOf(vertices: Collection<*>): FloatBuffer {
    val res: FloatBuffer
    when (vertices.elementAt(0)) {
        is Float -> res = FloatBuffer(vertices.size) { vertices.elementAt(it) as Float }
        is Vec2 -> {
            res = FloatBuffer(Vec2.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.length)
        }
        is Vec3 -> {
            res = FloatBuffer(Vec3.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.length)
        }
        is Vec4 -> {
            res = FloatBuffer(Vec4.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec4).to(res, i * Vec4.length)
        }
        else -> throw Error()
    }
    return res
}

fun intBufferOf(vertices: Collection<*>): IntBuffer {
    val res: IntBuffer
    when (vertices.elementAt(0)) {
        is Int -> res = IntBuffer(vertices.size) { vertices.elementAt(it) as Int }
        is Vec2i -> {
            res = IntBuffer(Vec2i.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec2i).to(res, i * Vec2i.length)
        }
        is Vec3i -> {
            res = IntBuffer(Vec3i.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec3i).to(res, i * Vec3i.length)
        }
        is Vec4i -> {
            res = IntBuffer(Vec4i.length * vertices.size)
            for (i in vertices.indices)
                (vertices.elementAt(i) as Vec4i).to(res, i * Vec4i.length)
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(charSequence: CharSequence) = ByteBuffer(charSequence.length) { charSequence[it].b }