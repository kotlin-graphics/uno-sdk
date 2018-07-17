package uno.buffer


import glm_.BYTES
import glm_.buffer.*
import org.lwjgl.PointerBuffer
import org.lwjgl.system.MemoryUtil
import uno.kotlin.Quadruple
import uno.kotlin.Quintuple
import java.nio.*

/**
 * Created by elect on 05/03/17.
 */


fun pointerBufferBig(capacity: Int): PointerBuffer = MemoryUtil.memCallocPointer(capacity)
fun pointerBufferBig(capacity: IntBuffer): PointerBuffer = MemoryUtil.memCallocPointer(capacity[0])
fun pointerBufferBig(capacity: IntArray): PointerBuffer = MemoryUtil.memCallocPointer(capacity[0])

/** for opengl  */
inline fun <reified T : Enum<T>> intBufferBig() = intBufferBig(enumValues<T>().size)
/** glGenTextures(textureName[Tex.Color]) */
operator fun <E : Enum<E>, T> Array<T>.get(enum: Enum<E>): T = get(enum.ordinal)

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

fun free(vararg buffers: Buffer) {
    for (i in 0 until buffers.size)
        MemoryUtil.memFree(buffers[i])
}

fun <R> ByteBuffer.use(block: (ByteBuffer) -> R) = block(this).also { free() }
fun <R> ShortBuffer.use(block: (ShortBuffer) -> R) = block(this).also { free() }
fun <R> IntBuffer.use(block: (IntBuffer) -> R) = block(this).also { free() }
fun <R> LongBuffer.use(block: (LongBuffer) -> R) = block(this).also { free() }
fun <R> FloatBuffer.use(block: (FloatBuffer) -> R) = block(this).also { free() }
fun <R> DoubleBuffer.use(block: (DoubleBuffer) -> R) = block(this).also { free() }
fun <R> CharBuffer.use(block: (CharBuffer) -> R) = block(this).also { free() }
fun <R> PointerBuffer.use(block: (PointerBuffer) -> R) = block(this).also { free() }
//
//@Suppress("UNCHECKED_CAST")
//fun withBuffer(list: List<*>, block: ByteBuffer.() -> Unit) {
//    when (list.elementAt(0)) {
//        is Byte -> bufferBig(list.size).apply {
//            val l = list as List<Byte>
//            for (i in l.indices)
//                put(i, l[i])
//        }
//        is Short -> bufferBig(list.size * Short.BYTES).apply {
//            val l = list as List<Short>
//            for (i in l.indices) putShort(i * Short.BYTES, l[i])
//        }
//        is Int -> bufferBig(list.size * Int.BYTES).apply {
//            val l = list as List<Int>
//            for (i in l.indices) putInt(i * Int.BYTES, l[i])
//        }
//        is Long -> bufferBig(list.size * Long.BYTES).apply {
//            val l = list as List<Long>
//            for (i in l.indices) putLong(i * Long.BYTES, l[i])
//        }
//        is Float -> bufferBig(list.size * Float.BYTES).apply {
//            val l = list as List<Float>
//            for (i in l.indices) putFloat(i * Float.BYTES, l[i])
//        }
//        is Double -> bufferBig(list.size * Double.BYTES).apply {
//            val l = list as List<Double>
//            for (i in l.indices) putDouble(i * Double.BYTES, l[i])
//        }
//        else -> throw Error("unsupported type")
//    }.run {
//        block()
//        free()
//    }
//}