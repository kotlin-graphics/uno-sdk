package uno.buffer

import glm_.BYTES
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import org.lwjgl.system.MemoryUtil
import uno.glf.Vertex
import uno.glf.glf
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

fun bufferOf(vararg vectors: Vec2): ByteBuffer {
    val res = MemoryUtil.memAlloc(vectors.size * Vec2.size)
    for (i in 0 until vectors.size) vectors[i].to(res, i * Vec2.size)
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
        Vec2::class -> {
            res = bufferBig(Vec2.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec2>).elementAt(i).to(res, i * Vec2.size)
        }
        Vec3::class -> {
            res = bufferBig(Vec3.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec3>).elementAt(i).to(res, i * Vec3.size)
        }
        Vec4::class -> {
            res = bufferBig(Vec4.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec4>).elementAt(i).to(res, i * Vec4.size)
        }
        Vertex.pos3_col4ub::class -> {
            res = bufferBig(glf.pos3_col4ub.stride * vertices.size)
            for (i in 0 until vertices.size)
                (vertices as Collection<Vertex.pos3_col4ub>).elementAt(i).to(res, i * glf.pos3_col4ub.stride)
        }
        else -> throw Error()
    }
    return res
}

fun bufferOf(vararg vertices: Vertex.pos3_col4ub): ByteBuffer {
    val res = bufferBig(glf.pos3_col4ub.stride * vertices.size)
    for (i in 0 until vertices.size)
        vertices[i].to(res, i * glf.pos3_col4ub.stride)
    return res
}

fun bufferOf(vertices: Array<*>): ByteBuffer {
    val res: ByteBuffer
    when (vertices.elementAt(0)) {
        Vec2 -> {
            res = bufferBig(Vec2.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Array<Vec2>)[i].to(res, i * Vec2.size)
        }
        Vec3 -> {
            res = bufferBig(Vec3.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Array<Vec3>)[i].to(res, i * Vec3.size)
        }
        Vec4 -> {
            res = bufferBig(Vec4.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Array<Vec4>)[i].to(res, i * Vec4.size)
        }
        Vertex.pos3_col4ub::class -> {
            res = bufferBig(glf.pos3_col4ub.stride * vertices.size)
            for (i in 0 until vertices.size)
                (vertices as Array<Vertex.pos3_col4ub>)[i].to(res, i * glf.pos3_col4ub.stride)
        }
        else -> throw Error()
    }
    return res
}

fun floatBufferOf(vertices: Collection<*>): FloatBuffer {
    val res: FloatBuffer
    when (vertices.elementAt(0)) {
        Vec2 -> {
            res = floatBufferBig(Vec2.length * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec2>).elementAt(i).to(res, i * Vec2.length)
        }
        Vec3 -> {
            res = floatBufferBig(Vec3.length * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec3>).elementAt(i).to(res, i * Vec3.length)
        }
        Vec4 -> {
            res = floatBufferBig(Vec4.length * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec4>).elementAt(i).to(res, i * Vec4.length)
        }
        else -> throw Error()
    }
    return res
}