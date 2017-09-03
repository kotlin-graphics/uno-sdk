package uno.buffer

import glm_.BYTES
import glm_.b
import glm_.set
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4ub
import org.lwjgl.system.MemoryUtil
import uno.glf.Vertex
import uno.glf.glf
import uno.kotlin.Quadruple
import uno.kotlin.Quintuple
import java.nio.*

/**
 * Created by elect on 05/03/17.
 */


fun FloatArray.toFloatBuf(): FloatBuffer {
    val res = MemoryUtil.memAllocFloat(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun FloatArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Float.BYTES)
    for (i in 0 until size) res.putFloat(i * Float.BYTES, this[i])
    return res
}

fun DoubleArray.toDoubleBuf(): DoubleBuffer {
    val res = MemoryUtil.memAllocDouble(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun DoubleArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Double.BYTES)
    for (i in 0 until size) res.putDouble(i * Double.BYTES, this[i])
    return res
}

fun ByteArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun ShortArray.toShortBuf(): ShortBuffer {
    val res = MemoryUtil.memAllocShort(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun ShortArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Short.BYTES)
    for (i in 0 until size) res.putShort(i * Short.BYTES, this[i])
    return res
}

fun IntArray.toIntBuf(): IntBuffer {
    val res = MemoryUtil.memAllocInt(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun IntArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Int.BYTES)
    for (i in 0 until size) res.putInt(i * Int.BYTES, this[i])
    return res
}

fun LongArray.toLongBuffer(): LongBuffer {
    val res = MemoryUtil.memAllocLong(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun LongArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Long.BYTES)
    for (i in 0 until size) res.putLong(i * Long.BYTES, this[i])
    return res
}

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
        Vec2 -> {
            res = bufferBig(Vec2.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec2>).elementAt(i).to(res, i * Vec2.size)
        }
        Vec3 -> {
            res = bufferBig(Vec3.size * vertices.size)
            for (i in 0 until vertices.size) (vertices as Collection<Vec3>).elementAt(i).to(res, i * Vec3.size)
        }
        Vec4 -> {
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


fun floatBufferBig(capacity: Int): FloatBuffer = MemoryUtil.memAllocFloat(capacity)
fun doubleBufferBig(capacity: Int): DoubleBuffer = MemoryUtil.memAllocDouble(capacity)

fun bufferBig(capacity: Int): ByteBuffer = MemoryUtil.memAlloc(capacity)
fun shortBufferBig(capacity: Int): ShortBuffer = MemoryUtil.memAllocShort(capacity)
fun intBufferBig(capacity: Int): IntBuffer = MemoryUtil.memAllocInt(capacity)
fun longBufferBig(capacity: Int): LongBuffer = MemoryUtil.memAllocLong(capacity)


// i.e: clear color buffer
fun FloatBuffer.put(vararg floats: Float): FloatBuffer {
    for (i in 0 until floats.size)
        put(i, floats[i])
    return this
}


fun buffersBig(sizeA: Int, sizeB: Int) = Pair(bufferBig(sizeA), bufferBig(sizeB))
fun buffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(bufferBig(sizeA), bufferBig(sizeB), bufferBig(sizeC))
fun buffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(bufferBig(sizeA), bufferBig(sizeB), bufferBig(sizeC), bufferBig(sizeD))
fun buffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(bufferBig(sizeA), bufferBig(sizeB), bufferBig(sizeC), bufferBig(sizeD), bufferBig(sizeE))

fun shortBuffersBig(sizeA: Int, sizeB: Int) = Pair(shortBufferBig(sizeA), shortBufferBig(sizeB))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC), shortBufferBig(sizeD))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC), shortBufferBig(sizeD), shortBufferBig(sizeE))

fun intBuffersBig(sizeA: Int, sizeB: Int) = Pair(intBufferBig(sizeA), intBufferBig(sizeB))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC), intBufferBig(sizeD))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC), intBufferBig(sizeD), intBufferBig(sizeE))

fun longBuffersBig(sizeA: Int, sizeB: Int) = Pair(longBufferBig(sizeA), longBufferBig(sizeB))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC), longBufferBig(sizeD))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC), longBufferBig(sizeD), longBufferBig(sizeE))

fun floatBuffersBig(sizeA: Int, sizeB: Int) = Pair(floatBufferBig(sizeA), floatBufferBig(sizeB))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC), floatBufferBig(sizeD))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC), floatBufferBig(sizeD), floatBufferBig(sizeE))

fun doubleBuffersBig(sizeA: Int, sizeB: Int) = Pair(doubleBufferBig(sizeA), doubleBufferBig(sizeB))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC), doubleBufferBig(sizeD))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC), doubleBufferBig(sizeD), doubleBufferBig(sizeE))


fun ByteBuffer.destroy() = MemoryUtil.memFree(this) // TODO rename?
fun ShortBuffer.destroy() = MemoryUtil.memFree(this)
fun IntBuffer.destroy() = MemoryUtil.memFree(this)
fun LongBuffer.destroy() = MemoryUtil.memFree(this)
fun FloatBuffer.destroy() = MemoryUtil.memFree(this)
fun DoubleBuffer.destroy() = MemoryUtil.memFree(this)
fun CharBuffer.destroy() = MemoryUtil.memFree(this)

fun destroyBuf(vararg buffers: Buffer) {
    for (i in 0 until buffers.size)
        MemoryUtil.memFree(buffers[i])
}

fun main(args: Array<String>) {

    println()

    for (r in 0..99) {

        val bb = bufferBig(1_000_000_000)
        for (i in 0 until bb.capacity())
            bb[i] = i.b

        println("run $r")

        bb.destroy()
    }

    println()
}