package uno.buffer

import glm_.BYTES
import glm_.buffer.bufferBig
import glm_.buffer.floatBufferBig
import glm_.buffer.intBufferBig
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


fun floatBufferOf(vararg floats: Float): FloatBuffer {
    val res = MemoryUtil.memAllocFloat(floats.size)
    for (i in 0 until floats.size) res.put(i, floats[i])
    return res
}

fun floatBufferOf(vararg numbers: Number): FloatBuffer {
    val res = MemoryUtil.memAllocFloat(numbers.size)
    for (i in 0 until numbers.size) res.put(i, numbers[i].toFloat())
    return res
}

fun doubleBufferOf(vararg doubles: Double): DoubleBuffer {
    val res = MemoryUtil.memAllocDouble(doubles.size)
    for (i in 0 until doubles.size) res.put(i, doubles[i])
    return res
}

fun doubleBufferOf(vararg numbers: Number): DoubleBuffer {
    val res = MemoryUtil.memAllocDouble(numbers.size)
    for (i in 0 until numbers.size) res.put(i, numbers[i].toDouble())
    return res
}

fun bufferOf(vararg bytes: Byte): ByteBuffer {
    val res = MemoryUtil.memAlloc(bytes.size)
    for (i in 0 until bytes.size) res.put(i, bytes[i])
    return res
}

fun bufferOf(vararg bytes: Number): ByteBuffer {
    val res = MemoryUtil.memAlloc(bytes.size)
    for (i in 0 until bytes.size) res.put(i, bytes[i].toByte())
    return res
}

fun bufferOf(vararg floats: Float): ByteBuffer {
    val res = MemoryUtil.memAlloc(floats.size * Float.BYTES)
    for (i in 0 until floats.size) res.putFloat(i * Float.BYTES, floats[i])
    return res
}

fun shortBufferOf(vararg shorts: Short): ShortBuffer {
    val res = MemoryUtil.memAllocShort(shorts.size)
    for (i in 0 until shorts.size) res.put(i, shorts[i])
    return res
}

fun shortBufferOf(vararg numbers: Number): ShortBuffer {
    val res = MemoryUtil.memAllocShort(numbers.size)
    for (i in 0 until numbers.size) res.put(i, numbers[i].toShort())
    return res
}

fun intBufferOf(vararg ints: Int): IntBuffer {
    val res = MemoryUtil.memAllocInt(ints.size)
    for (i in 0 until ints.size) res.put(i, ints[i])
    return res
}

fun intBufferOf(vararg numbers: Number): IntBuffer {
    val res = MemoryUtil.memAllocInt(numbers.size)
    for (i in 0 until numbers.size) res.put(i, numbers[i].toInt())
    return res
}

fun longBufferOf(vararg longs: Long): LongBuffer {
    val res = MemoryUtil.memAllocLong(longs.size)
    for (i in 0 until longs.size) res.put(i, longs[i])
    return res
}

fun longBufferOf(vararg numbers: Number): LongBuffer {
    val res = MemoryUtil.memAllocLong(numbers.size)
    for (i in 0 until numbers.size) res.put(i, numbers[i].toLong())
    return res
}

fun bufferOf(vertices: Collection<*>): ByteBuffer {
    val res: ByteBuffer
    when (vertices.elementAt(0)) {
        is Vec2 -> {
            res = bufferBig(Vec2.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.size)
        }
        is Vec3 -> {
            res = bufferBig(Vec3.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.size)
        }
        is Vec4 -> {
            res = bufferBig(Vec4.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec4).to(res, i * Vec4.size)
        }
        is Vertex.pos2_tc2 -> {
            res = bufferBig(glf.pos2_tc2.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos2_tc2
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.t.to(res, i * glf.pos2_tc2.stride + Vec2.size)
            }
        }
        is Vertex.pos3_col4ub -> {
            res = bufferBig(glf.pos3_col4ub.stride * vertices.size)
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
            res = bufferBig(Float.BYTES * vertices.size)
            for (i in 0 until vertices.size)
                res.putFloat(i * Float.BYTES, (vertices[i] as Float))
        }
        is Int -> {
            res = bufferBig(Int.BYTES * vertices.size)
            for (i in 0 until vertices.size)
                res.putInt(i * Int.BYTES, (vertices[i] as Int))
        }
        is Vec2 -> {
            res = bufferBig(Vec2.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec2).to(res, i * Vec2.size)
        }
        is Vec3 -> {
            res = bufferBig(Vec3.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec3).to(res, i * Vec3.size)
        }
        is Vec4 -> {
            res = bufferBig(Vec4.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec4).to(res, i * Vec4.size)
        }
        is Vec4b -> {
            res = bufferBig(Vec4b.size * vertices.size)
            for (i in 0 until vertices.size)
                (vertices[i] as Vec4b).to(res, i * Vec4b.size)
        }
        is Vertex.pos2_tc2 -> {
            res = bufferBig(glf.pos2_tc2.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos2_tc2
                v.p.to(res, i * glf.pos2_tc2.stride)
                v.t.to(res, i * glf.pos2_tc2.stride + Vec2.size)
            }
        }
        is Vertex.pos3_col4ub -> {
            res = bufferBig(glf.pos3_col4ub.stride * vertices.size)
            for (i in 0 until vertices.size) {
                val v = vertices.elementAt(i) as Vertex.pos3_col4ub
                v.p.to(res, i * glf.pos3_col4ub.stride)
                v.c.to(res, i * glf.pos3_col4ub.stride + Vec3.size)
            }
        }
        is Vertex.pos3_nor3_col4 -> {
            res = bufferBig(glf.pos3_nor3_col4.stride * vertices.size)
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
            res = floatBufferBig(vertices.size)
            for (i in 0 until vertices.size)
                res.put(i, (vertices.elementAt(i) as Float))
        }
        is Vec2 -> {
            res = floatBufferBig(Vec2.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2).to(res, i * Vec2.length)
        }
        is Vec3 -> {
            res = floatBufferBig(Vec3.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3).to(res, i * Vec3.length)
        }
        is Vec4 -> {
            res = floatBufferBig(Vec4.length * vertices.size)
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
            res = intBufferBig(vertices.size)
            for (i in 0 until vertices.size)
                res.put(i, (vertices.elementAt(i) as Int))
        }
        is Vec2i -> {
            res = intBufferBig(Vec2i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec2i).to(res, i * Vec2i.length)
        }
        is Vec3i -> {
            res = intBufferBig(Vec3i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec3i).to(res, i * Vec3i.length)
        }
        is Vec4i -> {
            res = intBufferBig(Vec4i.length * vertices.size)
            for (i in 0 until vertices.size)
                (vertices.elementAt(i) as Vec4i).to(res, i * Vec4i.length)
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(charSequence: CharSequence): ByteBuffer {
    val buffer = bufferBig(charSequence.length)
    for (i in charSequence.indices)
        buffer[i] = charSequence[i].i
    return buffer
}