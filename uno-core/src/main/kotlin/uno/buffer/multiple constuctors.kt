package uno.buffer


// TODO remove whole /buffer in favor of kool

import kool.*
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryUtil
import uno.kotlin.Quadruple
import uno.kotlin.Quintuple
import java.nio.*

/**
 * Created by elect on 05/03/17.
 */


//fun PointerBuffer(capacity: Int): PointerBuffer = MemoryUtil.memCallocPointer(capacity)
//fun PointerBuffer(capacity: IntBuffer): PointerBuffer = MemoryUtil.memCallocPointer(capacity[0])
//fun PointerBuffer(capacity: IntArray): PointerBuffer = MemoryUtil.memCallocPointer(capacity[0])

// i.e: clear color buffer
fun FloatBuffer.put(vararg floats: Float): FloatBuffer {
    for (i in 0 until floats.size)
        put(pos + i, floats[i])
    return this
}

fun ByteBuffers(sizeA: Int, sizeB: Int) = Pair(Buffer(sizeA), Buffer(sizeB))
fun ByteBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(Buffer(sizeA), Buffer(sizeB), Buffer(sizeC))
fun ByteBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(Buffer(sizeA), Buffer(sizeB), Buffer(sizeC), Buffer(sizeD))
fun ByteBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(Buffer(sizeA), Buffer(sizeB), Buffer(sizeC), Buffer(sizeD), Buffer(sizeE))

fun ShortBuffers(sizeA: Int, sizeB: Int) = Pair(ShortBuffer(sizeA), ShortBuffer(sizeB))
fun ShortBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(ShortBuffer(sizeA), ShortBuffer(sizeB), ShortBuffer(sizeC))
fun ShortBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(ShortBuffer(sizeA), ShortBuffer(sizeB), ShortBuffer(sizeC), ShortBuffer(sizeD))
fun ShortBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(ShortBuffer(sizeA), ShortBuffer(sizeB), ShortBuffer(sizeC), ShortBuffer(sizeD), ShortBuffer(sizeE))

fun IntBuffers(sizeA: Int, sizeB: Int) = Pair(IntBuffer(sizeA), IntBuffer(sizeB))
fun IntBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(IntBuffer(sizeA), IntBuffer(sizeB), IntBuffer(sizeC))
fun IntBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(IntBuffer(sizeA), IntBuffer(sizeB), IntBuffer(sizeC), IntBuffer(sizeD))
fun IntBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(IntBuffer(sizeA), IntBuffer(sizeB), IntBuffer(sizeC), IntBuffer(sizeD), IntBuffer(sizeE))

fun LongBuffers(sizeA: Int, sizeB: Int) = Pair(LongBuffer(sizeA), LongBuffer(sizeB))
fun LongBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(LongBuffer(sizeA), LongBuffer(sizeB), LongBuffer(sizeC))
fun LongBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(LongBuffer(sizeA), LongBuffer(sizeB), LongBuffer(sizeC), LongBuffer(sizeD))
fun LongBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(LongBuffer(sizeA), LongBuffer(sizeB), LongBuffer(sizeC), LongBuffer(sizeD), LongBuffer(sizeE))

fun FloatBuffers(sizeA: Int, sizeB: Int) = Pair(FloatBuffer(sizeA), FloatBuffer(sizeB))
fun FloatBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(FloatBuffer(sizeA), FloatBuffer(sizeB), FloatBuffer(sizeC))
fun FloatBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(FloatBuffer(sizeA), FloatBuffer(sizeB), FloatBuffer(sizeC), FloatBuffer(sizeD))
fun FloatBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(FloatBuffer(sizeA), FloatBuffer(sizeB), FloatBuffer(sizeC), FloatBuffer(sizeD), FloatBuffer(sizeE))

fun DoubleBuffers(sizeA: Int, sizeB: Int) = Pair(DoubleBuffer(sizeA), DoubleBuffer(sizeB))
fun DoubleBuffers(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(DoubleBuffer(sizeA), DoubleBuffer(sizeB), DoubleBuffer(sizeC))
fun DoubleBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(DoubleBuffer(sizeA), DoubleBuffer(sizeB), DoubleBuffer(sizeC), DoubleBuffer(sizeD))
fun DoubleBuffers(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(DoubleBuffer(sizeA), DoubleBuffer(sizeB), DoubleBuffer(sizeC), DoubleBuffer(sizeD), DoubleBuffer(sizeE))

fun memFree(vararg buffers: Buffer) {
    for (i in 0 until buffers.size)
        MemoryUtil.memFree(buffers[i])
}

//@Suppress("UNCHECKED_CAST")
//fun withBuffer(list: List<*>, block: ByteBuffer.() -> Unit) {
//    when (list.elementAt(0)) {
//        is Byte -> Buffer(list.size).apply {
//            val l = list as List<Byte>
//            for (i in l.indices)
//                put(i, l[i])
//        }
//        is Short -> Buffer(list.size * Short.BYTES).apply {
//            val l = list as List<Short>
//            for (i in l.indices) putShort(i * Short.BYTES, l[i])
//        }
//        is Int -> Buffer(list.size * Int.BYTES).apply {
//            val l = list as List<Int>
//            for (i in l.indices) putInt(i * Int.BYTES, l[i])
//        }
//        is Long -> Buffer(list.size * Long.BYTES).apply {
//            val l = list as List<Long>
//            for (i in l.indices) putLong(i * Long.BYTES, l[i])
//        }
//        is Float -> Buffer(list.size * Float.BYTES).apply {
//            val l = list as List<Float>
//            for (i in l.indices) putFloat(i * Float.BYTES, l[i])
//        }
//        is Double -> Buffer(list.size * Double.BYTES).apply {
//            val l = list as List<Double>
//            for (i in l.indices) putDouble(i * Double.BYTES, l[i])
//        }
//        else -> throw Error("unsupported type")
//    }.run {
//        block()
//        free()
//    }
//}