package uno.kotlin

import glm_.i
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec3.Vec3i
import glm_.vec4.Vec4i
import kool.*
import java.awt.event.KeyEvent
import java.io.File
import java.nio.IntBuffer
import java.util.*
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

/**
 * Created by GBarbieri on 30.03.2017.
 */

infix fun <T> (() -> Any).shallThrow(exClass: Class<T>) {
    try {
        this()
    } catch (e: Throwable) {
        if (exClass.isInstance(e)) return
        else throw Error("Exception type different")
    }
    throw Error("No exception")
}

val String.uri get() = url.toURI()!!
val String.url get() = ClassLoader.getSystemResource(this)!!
val String.stream get() = ClassLoader.getSystemResourceAsStream(this)!!

val String.file get() = File(uri)

val Char.isPrintable: Boolean
    get() = with(Character.UnicodeBlock.of(this)) {
        (!Character.isISOControl(this@isPrintable)) &&
                this@isPrintable != KeyEvent.CHAR_UNDEFINED &&
                this != null &&
                this != Character.UnicodeBlock.SPECIALS
    }

fun Char.parseInt() = java.lang.Character.getNumericValue(this)

operator fun <K> HashSet<K>.plusAssign(element: K) {
    add(element)
}

operator fun <K> HashSet<K>.minusAssign(element: K) {
    remove(element)
}

infix operator fun Appendable.plusAssign(char: Char) {
    append(char)
}

infix operator fun Appendable.plusAssign(string: String) {
    append(string)
}

infix operator fun <T>StringBuilder.plusAssign(element: T) {
    append(element)
}

fun <T> treeSetOf() = TreeSet<T>()

fun <K, V> SortedMap<K, V>.getOrfirst(key: K): V? = get(key) ?: first
val <K, V>SortedMap<K, V>.first: V? get() = get(firstKey())

operator fun <R> KMutableProperty0<R>.setValue(host: Any?, property: KProperty<*>, value: R) = set(value)
operator fun <R> KMutableProperty0<R>.getValue(host: Any?, property: KProperty<*>): R = get()
operator fun <T> KMutableProperty0<T>.invoke(t: T): KMutableProperty0<T> {
    set(t)
    return this
}

operator fun IntBuffer.plusAssign(bool: Boolean) = plusAssign(bool.i)
operator fun IntBuffer.plusAssign(i: Int) {
    put(i)
}


//fun <T> MemoryStack.Ptr() = 0

const val NUL = '\u0000'

val isNotCI: Boolean
    get() = System.getenv("GITHUB_ACTIONS") != "true" && System.getenv("TRAVIS") != "true"

inline fun readVec2(block: (Long, Long) -> Unit): Vec2 = stack {
    val ptr = it.PtrFloat(Vec2.length)
    block(ptr.adr.toLong(), (ptr + 1).adr.toLong())
    Vec2(ptr)
}
inline fun readVec2d(block: (Long, Long) -> Unit): Vec2d = stack {
    val ptr = it.PtrDouble(Vec2d.length)
    block(ptr.adr.toLong(), (ptr + 1).adr.toLong())
    Vec2d(ptr)
}

inline fun readVec2i(block: (Long, Long) -> Unit): Vec2i = stack {
    val ptr = it.PtrInt(Vec2i.length)
    block(ptr.adr.toLong(), (ptr + 1).adr.toLong())
    Vec2i(ptr)
}

inline fun readVec3i(block: (Long, Long, Long) -> Unit): Vec3i = stack {
    val ptr = it.PtrInt(Vec3i.length)
    block(ptr.adr.toLong(), (ptr + 1).adr.toLong(), (ptr + 2).adr.toLong())
    Vec3i(ptr)
}

inline fun readVec4i(block: (Long, Long, Long, Long) -> Unit): Vec4i = stack {
    val ptr = it.PtrInt(Vec4i.length)
    block(ptr.adr.toLong(), (ptr + 1).adr.toLong(), (ptr + 2).adr.toLong(), (ptr + 3).adr.toLong())
    Vec4i(ptr)
}

@JvmInline
value class HWND(val L: Long)

typealias Seconds = Double
typealias Hz = ULong