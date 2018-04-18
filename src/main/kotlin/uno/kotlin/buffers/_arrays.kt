package uno.kotlin.buffers

import glm_.L
import glm_.set
import glm_.size
import org.lwjgl.system.MemoryUtil.*
import java.nio.*

/** Returns 1st *element* from the collection. */
inline operator fun ByteBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun ShortBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun IntBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun LongBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun FloatBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun DoubleBuffer.component1() = get(0)

/** Returns 1st *element* from the collection. */
inline operator fun CharBuffer.component1() = get(0)


/** Returns 2nd *element* from the collection. */
inline operator fun ByteBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun ShortBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun IntBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun LongBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun FloatBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun DoubleBuffer.component2() = get(1)

/** Returns 2nd *element* from the collection. */
inline operator fun CharBuffer.component2() = get(1)


/** Returns 3rd *element* from the collection. */
inline operator fun ByteBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun ShortBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun IntBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun LongBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun FloatBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun DoubleBuffer.component3() = get(2)

/** Returns 3rd *element* from the collection. */
inline operator fun CharBuffer.component3() = get(2)


/** Returns 4th *element* from the collection. */
inline operator fun ByteBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun ShortBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun IntBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun LongBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun FloatBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun DoubleBuffer.component4() = get(3)

/** Returns 4th *element* from the collection. */
inline operator fun CharBuffer.component4() = get(3)


/** Returns 5th *element* from the collection. */
inline operator fun ByteBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun ShortBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun IntBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun LongBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun FloatBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun DoubleBuffer.component5() = get(4)

/** Returns 5th *element* from the collection. */
inline operator fun CharBuffer.component5() = get(4)


/** Returns `true` if [element] is found in the buffer. */
operator fun ByteBuffer.contains(element: Byte) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun ShortBuffer.contains(element: Short) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun IntBuffer.contains(element: Int) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun LongBuffer.contains(element: Long) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun FloatBuffer.contains(element: Float) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun DoubleBuffer.contains(element: Double) = indexOf(element) >= 0

/** Returns `true` if [element] is found in the buffer. */
operator fun CharBuffer.contains(element: Char) = indexOf(element) >= 0


/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun ByteBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun ShortBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun IntBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun LongBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun FloatBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun DoubleBuffer.elementAt(index: Int) = get(index)

/** Returns an element at the given [index] or throws an [IndexOutOfBoundsException] if the [index] is out of bounds of this buffer. */
inline fun CharBuffer.elementAt(index: Int) = get(index)


/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun ByteBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Byte) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun ShortBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Short) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun IntBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Int) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun LongBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Long) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun FloatBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Float) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun DoubleBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Double) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun CharBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Char) = if (index in 0..lastIndex) get(index) else defaultValue(index)


/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun ByteBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun ShortBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun IntBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun LongBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun FloatBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun DoubleBuffer.elementAtOrNull(index: Int) = getOrNull(index)

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
inline fun CharBuffer.elementAtOrNull(index: Int) = getOrNull(index)


/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun ByteBuffer.find(predicate: (Byte) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun ShortBuffer.find(predicate: (Short) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun IntBuffer.find(predicate: (Int) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun LongBuffer.find(predicate: (Long) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun FloatBuffer.find(predicate: (Float) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun DoubleBuffer.find(predicate: (Double) -> Boolean) = firstOrNull(predicate)

/** Returns the first element matching the given [predicate], or `null` if no such element was found. */
inline fun CharBuffer.find(predicate: (Char) -> Boolean) = firstOrNull(predicate)


/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun ByteBuffer.findLast(predicate: (Byte) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun ShortBuffer.findLast(predicate: (Short) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun IntBuffer.findLast(predicate: (Int) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun LongBuffer.findLast(predicate: (Long) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun FloatBuffer.findLast(predicate: (Float) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun DoubleBuffer.findLast(predicate: (Double) -> Boolean) = lastOrNull(predicate)

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun CharBuffer.findLast(predicate: (Char) -> Boolean) = lastOrNull(predicate)


/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun ByteBuffer.first(): Byte {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun ShortBuffer.first(): Short {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun IntBuffer.first(): Int {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun LongBuffer.first(): Long {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun FloatBuffer.first(): Float {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun DoubleBuffer.first(): Double {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

/** Returns first element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun CharBuffer.first(): Char {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}


/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun ByteBuffer.first(predicate: (Byte) -> Boolean): Byte {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun ShortBuffer.first(predicate: (Short) -> Boolean): Short {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun IntBuffer.first(predicate: (Int) -> Boolean): Int {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun LongBuffer.first(predicate: (Long) -> Boolean): Long {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun FloatBuffer.first(predicate: (Float) -> Boolean): Float {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun DoubleBuffer.first(predicate: (Double) -> Boolean): Double {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the first element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun CharBuffer.first(predicate: (Char) -> Boolean): Char {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}


/** Returns the first element, or `null` if the buffer is empty. */
fun ByteBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun ShortBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun IntBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun LongBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun FloatBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun DoubleBuffer.firstOrNull() = if (isEmpty()) null else this[0]

/** Returns the first element, or `null` if the buffer is empty. */
fun CharBuffer.firstOrNull() = if (isEmpty()) null else this[0]


/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun ByteBuffer.firstOrNull(predicate: (Byte) -> Boolean): Byte? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun ShortBuffer.firstOrNull(predicate: (Short) -> Boolean): Short? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun IntBuffer.firstOrNull(predicate: (Int) -> Boolean): Int? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun LongBuffer.firstOrNull(predicate: (Long) -> Boolean): Long? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun FloatBuffer.firstOrNull(predicate: (Float) -> Boolean): Float? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun DoubleBuffer.firstOrNull(predicate: (Double) -> Boolean): Double? {
    for (element in this) if (predicate(element)) return element
    return null
}

/** Returns the first element matching the given [predicate], or `null` if element was not found. */
inline fun CharBuffer.firstOrNull(predicate: (Char) -> Boolean): Char? {
    for (element in this) if (predicate(element)) return element
    return null
}


/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun ByteBuffer.getOrElse(index: Int, defaultValue: (Int) -> Byte) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun ShortBuffer.getOrElse(index: Int, defaultValue: (Int) -> Short) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun IntBuffer.getOrElse(index: Int, defaultValue: (Int) -> Int) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun LongBuffer.getOrElse(index: Int, defaultValue: (Int) -> Long) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun FloatBuffer.getOrElse(index: Int, defaultValue: (Int) -> Float) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun DoubleBuffer.getOrElse(index: Int, defaultValue: (Int) -> Double) = if (index in 0..lastIndex) get(index) else defaultValue(index)

/** Returns an element at the given [index] or the result of calling the [defaultValue] function if the [index] is out of bounds of this buffer. */
inline fun CharBuffer.getOrElse(index: Int, defaultValue: (Int) -> Char) = if (index in 0..lastIndex) get(index) else defaultValue(index)


/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun ByteBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun ShortBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun IntBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun LongBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun FloatBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun DoubleBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null

/** Returns an element at the given [index] or `null` if the [index] is out of bounds of this buffer. */
fun CharBuffer.getOrNull(index: Int) = if (index in 0..lastIndex) get(index) else null


/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun ByteBuffer.indexOf(element: Byte): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun ShortBuffer.indexOf(element: Short): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun IntBuffer.indexOf(element: Int): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun LongBuffer.indexOf(element: Long): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun FloatBuffer.indexOf(element: Float): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun DoubleBuffer.indexOf(element: Double): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}

/** Returns first index of [element], or -1 if the buffer does not contain element. */
fun CharBuffer.indexOf(element: Char): Int {
    for (index in indices)
        if (element == this[index])
            return index
    return -1
}


/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun ByteBuffer.indexOfFirst(predicate: (Byte) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun ShortBuffer.indexOfFirst(predicate: (Short) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun IntBuffer.indexOfFirst(predicate: (Int) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun LongBuffer.indexOfFirst(predicate: (Long) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun FloatBuffer.indexOfFirst(predicate: (Float) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun DoubleBuffer.indexOfFirst(predicate: (Double) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the first element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun CharBuffer.indexOfFirst(predicate: (Char) -> Boolean): Int {
    for (index in indices)
        if (predicate(this[index]))
            return index
    return -1
}


/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun ByteBuffer.indexOfLast(predicate: (Byte) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun ShortBuffer.indexOfLast(predicate: (Short) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun IntBuffer.indexOfLast(predicate: (Int) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun LongBuffer.indexOfLast(predicate: (Long) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun FloatBuffer.indexOfLast(predicate: (Float) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun DoubleBuffer.indexOfLast(predicate: (Double) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}

/** Returns index of the last element matching the given [predicate], or -1 if the buffer does not contain such element. */
inline fun CharBuffer.indexOfLast(predicate: (Char) -> Boolean): Int {
    for (index in indices.reversed())
        if (predicate(this[index]))
            return index
    return -1
}


/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun ByteBuffer.last(): Byte {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun ShortBuffer.last(): Short {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun IntBuffer.last(): Int {
    if (isEmpty())
        throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun LongBuffer.last(): Long {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun FloatBuffer.last(): Float {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun DoubleBuffer.last(): Double {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

/** Returns the last element.
 *  @throws [NoSuchElementException] if the buffer is empty. */
fun CharBuffer.last(): Char {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}


/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun <T> Array<out T>.last(predicate: (T) -> Boolean): T {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun ByteBuffer.last(predicate: (Byte) -> Boolean): Byte {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun ShortBuffer.last(predicate: (Short) -> Boolean): Short {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun IntBuffer.last(predicate: (Int) -> Boolean): Int {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun LongBuffer.last(predicate: (Long) -> Boolean): Long {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun FloatBuffer.last(predicate: (Float) -> Boolean): Float {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun DoubleBuffer.last(predicate: (Double) -> Boolean): Double {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

/** Returns the last element matching the given [predicate].
 *  @throws [NoSuchElementException] if no such element is found. */
inline fun CharBuffer.last(predicate: (Char) -> Boolean): Char {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}


/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun ByteBuffer.lastIndexOf(element: Byte): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun ShortBuffer.lastIndexOf(element: Short): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun IntBuffer.lastIndexOf(element: Int): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun LongBuffer.lastIndexOf(element: Long): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun FloatBuffer.lastIndexOf(element: Float): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun DoubleBuffer.lastIndexOf(element: Double): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}

/** Returns last index of [element], or -1 if the buffer does not contain element. */
fun CharBuffer.lastIndexOf(element: Char): Int {
    for (index in indices.reversed())
        if (element == this[index])
            return index
    return -1
}


/** Returns the last element, or `null` if the buffer is empty. */
fun ByteBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun ShortBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun IntBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun LongBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun FloatBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun DoubleBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]

/** Returns the last element, or `null` if the buffer is empty. */
fun CharBuffer.lastOrNull() = if (isEmpty()) null else this[size - 1]


/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun ByteBuffer.lastOrNull(predicate: (Byte) -> Boolean): Byte? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun ShortBuffer.lastOrNull(predicate: (Short) -> Boolean): Short? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun IntBuffer.lastOrNull(predicate: (Int) -> Boolean): Int? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun LongBuffer.lastOrNull(predicate: (Long) -> Boolean): Long? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun FloatBuffer.lastOrNull(predicate: (Float) -> Boolean): Float? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun DoubleBuffer.lastOrNull(predicate: (Double) -> Boolean): Double? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

/** Returns the last element matching the given [predicate], or `null` if no such element was found. */
inline fun CharBuffer.lastOrNull(predicate: (Char) -> Boolean): Char? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun <T> Array<out T>.single(): T {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun ByteBuffer.single(): Byte {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun ShortBuffer.single(): Short {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun IntBuffer.single(): Int {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun LongBuffer.single(): Long {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun FloatBuffer.single(): Float {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun DoubleBuffer.single(): Double {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun DELETE.single(): Boolean {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element, or throws an exception if the buffer is empty or has more than one element.
// */
//fun CharBuffer.single(): Char {
//    return when (size) {
//        0 -> throw NoSuchElementException("Array is empty.")
//        1 -> this[0]
//        else -> throw IllegalArgumentException("Array has more than one element.")
//    }
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun <T> Array<out T>.single(predicate: (T) -> Boolean): T {
//    var single: T? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as T
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun ByteBuffer.single(predicate: (Byte) -> Boolean): Byte {
//    var single: Byte? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Byte
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun ShortBuffer.single(predicate: (Short) -> Boolean): Short {
//    var single: Short? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Short
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun IntBuffer.single(predicate: (Int) -> Boolean): Int {
//    var single: Int? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Int
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun LongBuffer.single(predicate: (Long) -> Boolean): Long {
//    var single: Long? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Long
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun FloatBuffer.single(predicate: (Float) -> Boolean): Float {
//    var single: Float? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Float
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun DoubleBuffer.single(predicate: (Double) -> Boolean): Double {
//    var single: Double? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Double
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun DELETE.single(predicate: (Boolean) -> Boolean): Boolean {
//    var single: Boolean? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Boolean
//}
//
///**
// * Returns the single element matching the given [predicate], or throws exception if there is no or more than one matching element.
// */
//inline fun CharBuffer.single(predicate: (Char) -> Boolean): Char {
//    var single: Char? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
//            single = element
//            found = true
//        }
//    }
//    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
//    @Suppress("UNCHECKED_CAST")
//    return single as Char
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun <T> Array<out T>.singleOrNull(): T? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun ByteBuffer.singleOrNull(): Byte? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun ShortBuffer.singleOrNull(): Short? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun IntBuffer.singleOrNull(): Int? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun LongBuffer.singleOrNull(): Long? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun FloatBuffer.singleOrNull(): Float? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun DoubleBuffer.singleOrNull(): Double? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun DELETE.singleOrNull(): Boolean? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns single element, or `null` if the buffer is empty or has more than one element.
// */
//fun CharBuffer.singleOrNull(): Char? {
//    return if (size == 1) this[0] else null
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun <T> Array<out T>.singleOrNull(predicate: (T) -> Boolean): T? {
//    var single: T? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun ByteBuffer.singleOrNull(predicate: (Byte) -> Boolean): Byte? {
//    var single: Byte? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun ShortBuffer.singleOrNull(predicate: (Short) -> Boolean): Short? {
//    var single: Short? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun IntBuffer.singleOrNull(predicate: (Int) -> Boolean): Int? {
//    var single: Int? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun LongBuffer.singleOrNull(predicate: (Long) -> Boolean): Long? {
//    var single: Long? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun FloatBuffer.singleOrNull(predicate: (Float) -> Boolean): Float? {
//    var single: Float? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun DoubleBuffer.singleOrNull(predicate: (Double) -> Boolean): Double? {
//    var single: Double? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun DELETE.singleOrNull(predicate: (Boolean) -> Boolean): Boolean? {
//    var single: Boolean? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}
//
///**
// * Returns the single element matching the given [predicate], or `null` if element was not found or more than one element was found.
// */
//inline fun CharBuffer.singleOrNull(predicate: (Char) -> Boolean): Char? {
//    var single: Char? = null
//    var found = false
//    for (element in this) {
//        if (predicate(element)) {
//            if (found) return null
//            single = element
//            found = true
//        }
//    }
//    if (!found) return null
//    return single
//}


/** Returns a list containing all elements except first [n] elements. */
fun ByteBuffer.drop(n: Int): List<Byte> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun ShortBuffer.drop(n: Int): List<Short> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun IntBuffer.drop(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun LongBuffer.drop(n: Int): List<Long> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun FloatBuffer.drop(n: Int): List<Float> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun DoubleBuffer.drop(n: Int): List<Double> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}

/** Returns a list containing all elements except first [n] elements. */
fun CharBuffer.drop(n: Int): List<Char> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((size - n).coerceAtLeast(0))
}


///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun <T> Array<out T>.dropLast(n: Int): List<T> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun ByteBuffer.dropLast(n: Int): List<Byte> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun ShortBuffer.dropLast(n: Int): List<Short> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun IntBuffer.dropLast(n: Int): List<Int> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun LongBuffer.dropLast(n: Int): List<Long> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun FloatBuffer.dropLast(n: Int): List<Float> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun DoubleBuffer.dropLast(n: Int): List<Double> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun DELETE.dropLast(n: Int): List<Boolean> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last [n] elements.
// */
//fun CharBuffer.dropLast(n: Int): List<Char> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    return take((size - n).coerceAtLeast(0))
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun <T> Array<out T>.dropLastWhile(predicate: (T) -> Boolean): List<T> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun ByteBuffer.dropLastWhile(predicate: (Byte) -> Boolean): List<Byte> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun ShortBuffer.dropLastWhile(predicate: (Short) -> Boolean): List<Short> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun IntBuffer.dropLastWhile(predicate: (Int) -> Boolean): List<Int> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun LongBuffer.dropLastWhile(predicate: (Long) -> Boolean): List<Long> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun FloatBuffer.dropLastWhile(predicate: (Float) -> Boolean): List<Float> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun DoubleBuffer.dropLastWhile(predicate: (Double) -> Boolean): List<Double> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun DELETE.dropLastWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except last elements that satisfy the given [predicate].
// */
//inline fun CharBuffer.dropLastWhile(predicate: (Char) -> Boolean): List<Char> {
//    for (index in lastIndex downTo 0) {
//        if (!predicate(this[index])) {
//            return take(index + 1)
//        }
//    }
//    return emptyList()
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun <T> Array<out T>.dropWhile(predicate: (T) -> Boolean): List<T> {
//    var yielding = false
//    val list = ArrayList<T>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun ByteBuffer.dropWhile(predicate: (Byte) -> Boolean): List<Byte> {
//    var yielding = false
//    val list = ArrayList<Byte>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun ShortBuffer.dropWhile(predicate: (Short) -> Boolean): List<Short> {
//    var yielding = false
//    val list = ArrayList<Short>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun IntBuffer.dropWhile(predicate: (Int) -> Boolean): List<Int> {
//    var yielding = false
//    val list = ArrayList<Int>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun LongBuffer.dropWhile(predicate: (Long) -> Boolean): List<Long> {
//    var yielding = false
//    val list = ArrayList<Long>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun FloatBuffer.dropWhile(predicate: (Float) -> Boolean): List<Float> {
//    var yielding = false
//    val list = ArrayList<Float>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun DoubleBuffer.dropWhile(predicate: (Double) -> Boolean): List<Double> {
//    var yielding = false
//    val list = ArrayList<Double>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun DELETE.dropWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
//    var yielding = false
//    val list = ArrayList<Boolean>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing all elements except first elements that satisfy the given [predicate].
// */
//inline fun CharBuffer.dropWhile(predicate: (Char) -> Boolean): List<Char> {
//    var yielding = false
//    val list = ArrayList<Char>()
//    for (item in this)
//        if (yielding)
//            list.add(item)
//        else if (!predicate(item)) {
//            list.add(item)
//            yielding = true
//        }
//    return list
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun <T> Array<out T>.filter(predicate: (T) -> Boolean): List<T> {
//    return filterTo(ArrayList<T>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun ByteBuffer.filter(predicate: (Byte) -> Boolean): List<Byte> {
//    return filterTo(ArrayList<Byte>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun ShortBuffer.filter(predicate: (Short) -> Boolean): List<Short> {
//    return filterTo(ArrayList<Short>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun IntBuffer.filter(predicate: (Int) -> Boolean): List<Int> {
//    return filterTo(ArrayList<Int>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun LongBuffer.filter(predicate: (Long) -> Boolean): List<Long> {
//    return filterTo(ArrayList<Long>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun FloatBuffer.filter(predicate: (Float) -> Boolean): List<Float> {
//    return filterTo(ArrayList<Float>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun DoubleBuffer.filter(predicate: (Double) -> Boolean): List<Double> {
//    return filterTo(ArrayList<Double>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun DELETE.filter(predicate: (Boolean) -> Boolean): List<Boolean> {
//    return filterTo(ArrayList<Boolean>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// */
//inline fun CharBuffer.filter(predicate: (Char) -> Boolean): List<Char> {
//    return filterTo(ArrayList<Char>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <T> Array<out T>.filterIndexed(predicate: (index: Int, T) -> Boolean): List<T> {
//    return filterIndexedTo(ArrayList<T>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun ByteBuffer.filterIndexed(predicate: (index: Int, Byte) -> Boolean): List<Byte> {
//    return filterIndexedTo(ArrayList<Byte>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun ShortBuffer.filterIndexed(predicate: (index: Int, Short) -> Boolean): List<Short> {
//    return filterIndexedTo(ArrayList<Short>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun IntBuffer.filterIndexed(predicate: (index: Int, Int) -> Boolean): List<Int> {
//    return filterIndexedTo(ArrayList<Int>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun LongBuffer.filterIndexed(predicate: (index: Int, Long) -> Boolean): List<Long> {
//    return filterIndexedTo(ArrayList<Long>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun FloatBuffer.filterIndexed(predicate: (index: Int, Float) -> Boolean): List<Float> {
//    return filterIndexedTo(ArrayList<Float>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun DoubleBuffer.filterIndexed(predicate: (index: Int, Double) -> Boolean): List<Double> {
//    return filterIndexedTo(ArrayList<Double>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun DELETE.filterIndexed(predicate: (index: Int, Boolean) -> Boolean): List<Boolean> {
//    return filterIndexedTo(ArrayList<Boolean>(), predicate)
//}
//
///**
// * Returns a list containing only elements matching the given [predicate].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun CharBuffer.filterIndexed(predicate: (index: Int, Char) -> Boolean): List<Char> {
//    return filterIndexedTo(ArrayList<Char>(), predicate)
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <T, C : MutableCollection<in T>> Array<out T>.filterIndexedTo(destination: C, predicate: (index: Int, T) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Byte>> ByteBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Byte) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Short>> ShortBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Short) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Int>> IntBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Int) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Long>> LongBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Long) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Float>> FloatBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Float) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Double>> DoubleBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Double) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Boolean>> DELETE.filterIndexedTo(destination: C, predicate: (index: Int, Boolean) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// * @param [predicate] function that takes the index of an element and the element itself
// * and returns the result of predicate evaluation on the element.
// */
//inline fun <C : MutableCollection<in Char>> CharBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Char) -> Boolean): C {
//    forEachIndexed { index, element ->
//        if (predicate(index, element)) destination.add(element)
//    }
//    return destination
//}
//
///**
// * Returns a list containing all elements that are instances of specified type parameter R.
// */
//inline fun <reified R> Array<*>.filterIsInstance(): List<@kotlin.internal.NoInfer R> {
//    return filterIsInstanceTo(ArrayList<R>())
//}
//
///**
// * Returns a list containing all elements that are instances of specified class.
// */
//fun <R> Array<*>.filterIsInstance(klass: Class<R>): List<R> {
//    return filterIsInstanceTo(ArrayList<R>(), klass)
//}
//
///**
// * Appends all elements that are instances of specified type parameter R to the given [destination].
// */
//inline fun <reified R, C : MutableCollection<in R>> Array<*>.filterIsInstanceTo(destination: C): C {
//    for (element in this) if (element is R) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements that are instances of specified class to the given [destination].
// */
//fun <C : MutableCollection<in R>, R> Array<*>.filterIsInstanceTo(destination: C, klass: Class<R>): C {
//    @Suppress("UNCHECKED_CAST")
//    for (element in this) if (klass.isInstance(element)) destination.add(element as R)
//    return destination
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun <T> Array<out T>.filterNot(predicate: (T) -> Boolean): List<T> {
//    return filterNotTo(ArrayList<T>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun ByteBuffer.filterNot(predicate: (Byte) -> Boolean): List<Byte> {
//    return filterNotTo(ArrayList<Byte>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun ShortBuffer.filterNot(predicate: (Short) -> Boolean): List<Short> {
//    return filterNotTo(ArrayList<Short>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun IntBuffer.filterNot(predicate: (Int) -> Boolean): List<Int> {
//    return filterNotTo(ArrayList<Int>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun LongBuffer.filterNot(predicate: (Long) -> Boolean): List<Long> {
//    return filterNotTo(ArrayList<Long>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun FloatBuffer.filterNot(predicate: (Float) -> Boolean): List<Float> {
//    return filterNotTo(ArrayList<Float>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun DoubleBuffer.filterNot(predicate: (Double) -> Boolean): List<Double> {
//    return filterNotTo(ArrayList<Double>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun DELETE.filterNot(predicate: (Boolean) -> Boolean): List<Boolean> {
//    return filterNotTo(ArrayList<Boolean>(), predicate)
//}
//
///**
// * Returns a list containing all elements not matching the given [predicate].
// */
//inline fun CharBuffer.filterNot(predicate: (Char) -> Boolean): List<Char> {
//    return filterNotTo(ArrayList<Char>(), predicate)
//}
//
///**
// * Returns a list containing all elements that are not `null`.
// */
//fun <T : Any> Array<out T?>.filterNotNull(): List<T> {
//    return filterNotNullTo(ArrayList<T>())
//}
//
///**
// * Appends all elements that are not `null` to the given [destination].
// */
//fun <C : MutableCollection<in T>, T : Any> Array<out T?>.filterNotNullTo(destination: C): C {
//    for (element in this) if (element != null) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <T, C : MutableCollection<in T>> Array<out T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Byte>> ByteBuffer.filterNotTo(destination: C, predicate: (Byte) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Short>> ShortBuffer.filterNotTo(destination: C, predicate: (Short) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Int>> IntBuffer.filterNotTo(destination: C, predicate: (Int) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Long>> LongBuffer.filterNotTo(destination: C, predicate: (Long) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Float>> FloatBuffer.filterNotTo(destination: C, predicate: (Float) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Double>> DoubleBuffer.filterNotTo(destination: C, predicate: (Double) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Boolean>> DELETE.filterNotTo(destination: C, predicate: (Boolean) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements not matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Char>> CharBuffer.filterNotTo(destination: C, predicate: (Char) -> Boolean): C {
//    for (element in this) if (!predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <T, C : MutableCollection<in T>> Array<out T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Byte>> ByteBuffer.filterTo(destination: C, predicate: (Byte) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Short>> ShortBuffer.filterTo(destination: C, predicate: (Short) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Int>> IntBuffer.filterTo(destination: C, predicate: (Int) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Long>> LongBuffer.filterTo(destination: C, predicate: (Long) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Float>> FloatBuffer.filterTo(destination: C, predicate: (Float) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Double>> DoubleBuffer.filterTo(destination: C, predicate: (Double) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Boolean>> DELETE.filterTo(destination: C, predicate: (Boolean) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Appends all elements matching the given [predicate] to the given [destination].
// */
//inline fun <C : MutableCollection<in Char>> CharBuffer.filterTo(destination: C, predicate: (Char) -> Boolean): C {
//    for (element in this) if (predicate(element)) destination.add(element)
//    return destination
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun <T> Array<out T>.slice(indices: IntRange): List<T> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun ByteBuffer.slice(indices: IntRange): List<Byte> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun ShortBuffer.slice(indices: IntRange): List<Short> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun IntBuffer.slice(indices: IntRange): List<Int> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun LongBuffer.slice(indices: IntRange): List<Long> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun FloatBuffer.slice(indices: IntRange): List<Float> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun DoubleBuffer.slice(indices: IntRange): List<Double> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun DELETE.slice(indices: IntRange): List<Boolean> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun CharBuffer.slice(indices: IntRange): List<Char> {
//    if (indices.isEmpty()) return listOf()
//    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun <T> Array<out T>.slice(indices: Iterable<Int>): List<T> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<T>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun ByteBuffer.slice(indices: Iterable<Int>): List<Byte> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Byte>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun ShortBuffer.slice(indices: Iterable<Int>): List<Short> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Short>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun IntBuffer.slice(indices: Iterable<Int>): List<Int> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Int>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun LongBuffer.slice(indices: Iterable<Int>): List<Long> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Long>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun FloatBuffer.slice(indices: Iterable<Int>): List<Float> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Float>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun DoubleBuffer.slice(indices: Iterable<Int>): List<Double> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Double>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun DELETE.slice(indices: Iterable<Int>): List<Boolean> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Boolean>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a list containing elements at specified [indices].
// */
//fun CharBuffer.slice(indices: Iterable<Int>): List<Char> {
//    val size = indices.collectionSizeOrDefault(10)
//    if (size == 0) return emptyList()
//    val list = ArrayList<Char>(size)
//    for (index in indices) {
//        list.add(get(index))
//    }
//    return list
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun <T> Array<T>.sliceArray(indices: Collection<Int>): Array<T> {
//    val result = bufferOfNulls(this, indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun ByteBuffer.sliceArray(indices: Collection<Int>): ByteBuffer {
//    val result = ByteBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun ShortBuffer.sliceArray(indices: Collection<Int>): ShortBuffer {
//    val result = ShortBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun IntBuffer.sliceArray(indices: Collection<Int>): IntBuffer {
//    val result = IntBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun LongBuffer.sliceArray(indices: Collection<Int>): LongBuffer {
//    val result = LongBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun FloatBuffer.sliceArray(indices: Collection<Int>): FloatBuffer {
//    val result = FloatBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun DoubleBuffer.sliceArray(indices: Collection<Int>): DoubleBuffer {
//    val result = DoubleBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun DELETE.sliceArray(indices: Collection<Int>): DELETE {
//    val result = DELETE(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a buffer containing elements of this buffer at specified [indices].
// */
//fun CharBuffer.sliceArray(indices: Collection<Int>): CharBuffer {
//    val result = CharBuffer(indices.size)
//    var targetIndex = 0
//    for (sourceIndex in indices) {
//        result[targetIndex++] = this[sourceIndex]
//    }
//    return result
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun <T> Array<T>.sliceArray(indices: IntRange): Array<T> {
//    if (indices.isEmpty()) return copyOfRange(0, 0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun ByteBuffer.sliceArray(indices: IntRange): ByteBuffer {
//    if (indices.isEmpty()) return ByteBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun ShortBuffer.sliceArray(indices: IntRange): ShortBuffer {
//    if (indices.isEmpty()) return ShortBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun IntBuffer.sliceArray(indices: IntRange): IntBuffer {
//    if (indices.isEmpty()) return IntBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun LongBuffer.sliceArray(indices: IntRange): LongBuffer {
//    if (indices.isEmpty()) return LongBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun FloatBuffer.sliceArray(indices: IntRange): FloatBuffer {
//    if (indices.isEmpty()) return FloatBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun DoubleBuffer.sliceArray(indices: IntRange): DoubleBuffer {
//    if (indices.isEmpty()) return DoubleBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun DELETE.sliceArray(indices: IntRange): DELETE {
//    if (indices.isEmpty()) return DELETE(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing elements at indices in the specified [indices] range.
// */
//fun CharBuffer.sliceArray(indices: IntRange): CharBuffer {
//    if (indices.isEmpty()) return CharBuffer(0)
//    return copyOfRange(indices.start, indices.endInclusive + 1)
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun <T> Array<out T>.take(n: Int): List<T> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<T>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun ByteBuffer.take(n: Int): List<Byte> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Byte>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun ShortBuffer.take(n: Int): List<Short> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Short>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun IntBuffer.take(n: Int): List<Int> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Int>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun LongBuffer.take(n: Int): List<Long> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Long>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun FloatBuffer.take(n: Int): List<Float> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Float>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun DoubleBuffer.take(n: Int): List<Double> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Double>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun DELETE.take(n: Int): List<Boolean> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Boolean>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//
///**
// * Returns a list containing first [n] elements.
// */
//fun CharBuffer.take(n: Int): List<Char> {
//    require(n >= 0) { "Requested element count $n is less than zero." }
//    if (n == 0) return emptyList()
//    if (n >= size) return toList()
//    if (n == 1) return listOf(this[0])
//    var count = 0
//    val list = ArrayList<Char>(n)
//    for (item in this) {
//        if (count++ == n)
//            break
//        list.add(item)
//    }
//    return list
//}
//


/** Returns a list containing last [n] elements. */
fun ByteBuffer.takeLast(n: Int): List<Byte> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Byte>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun ShortBuffer.takeLast(n: Int): List<Short> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Short>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun IntBuffer.takeLast(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Int>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun LongBuffer.takeLast(n: Int): List<Long> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Long>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun FloatBuffer.takeLast(n: Int): List<Float> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Float>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun DoubleBuffer.takeLast(n: Int): List<Double> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Double>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}

/** Returns a list containing last [n] elements. */
fun CharBuffer.takeLast(n: Int): List<Char> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val size = size
    if (n >= size) return toList()
    if (n == 1) return listOf(this[size - 1])
    val list = ArrayList<Char>(n)
    for (index in size - n until size)
        list += this[index]
    return list
}


/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun ByteBuffer.takeLastWhile(predicate: (Byte) -> Boolean): List<Byte> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}


/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun ShortBuffer.takeLastWhile(predicate: (Short) -> Boolean): List<Short> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}

/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun IntBuffer.takeLastWhile(predicate: (Int) -> Boolean): List<Int> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}

/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun LongBuffer.takeLastWhile(predicate: (Long) -> Boolean): List<Long> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}

/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun FloatBuffer.takeLastWhile(predicate: (Float) -> Boolean): List<Float> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}

/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun DoubleBuffer.takeLastWhile(predicate: (Double) -> Boolean): List<Double> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}

/** Returns a list containing last elements satisfying the given [predicate]. */
inline fun CharBuffer.takeLastWhile(predicate: (Char) -> Boolean): List<Char> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return drop(index + 1)
    return toList()
}


/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun ByteBuffer.takeWhile(predicate: (Byte) -> Boolean): List<Byte> {
    val list = ArrayList<Byte>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun ShortBuffer.takeWhile(predicate: (Short) -> Boolean): List<Short> {
    val list = ArrayList<Short>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun IntBuffer.takeWhile(predicate: (Int) -> Boolean): List<Int> {
    val list = ArrayList<Int>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun LongBuffer.takeWhile(predicate: (Long) -> Boolean): List<Long> {
    val list = ArrayList<Long>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun FloatBuffer.takeWhile(predicate: (Float) -> Boolean): List<Float> {
    val list = ArrayList<Float>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun DoubleBuffer.takeWhile(predicate: (Double) -> Boolean): List<Double> {
    val list = ArrayList<Double>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}

/** Returns a list containing first elements satisfying the given [predicate]. */
inline fun CharBuffer.takeWhile(predicate: (Char) -> Boolean): List<Char> {
    val list = ArrayList<Char>()
    for (item in this) {
        if (!predicate(item)) break
        list += item
    }
    return list
}


/** Reverses elements in the buffer in-place. */
fun ByteBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun ShortBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun IntBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun LongBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun FloatBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun DoubleBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

/** Reverses elements in the buffer in-place. */
fun CharBuffer.reverse() {
    val midPoint = size / 2 - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}


/** Returns a list with elements in reversed order. */
fun ByteBuffer.reversed(): List<Byte> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun ShortBuffer.reversed(): List<Short> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun IntBuffer.reversed(): List<Int> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun LongBuffer.reversed(): List<Long> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun FloatBuffer.reversed(): List<Float> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun DoubleBuffer.reversed(): List<Double> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}

/** Returns a list with elements in reversed order. */
fun CharBuffer.reversed(): List<Char> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
    return list
}


///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun <T> Array<T>.reversedArray(): Array<T> {
//    if (isEmpty()) return this
//    val result = bufferOfNulls(this, size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun ByteBuffer.reversedArray(): ByteBuffer {
//    if (isEmpty()) return this
//    val result = ByteBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun ShortBuffer.reversedArray(): ShortBuffer {
//    if (isEmpty()) return this
//    val result = ShortBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun IntBuffer.reversedArray(): IntBuffer {
//    if (isEmpty()) return this
//    val result = IntBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun LongBuffer.reversedArray(): LongBuffer {
//    if (isEmpty()) return this
//    val result = LongBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun FloatBuffer.reversedArray(): FloatBuffer {
//    if (isEmpty()) return this
//    val result = FloatBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun DoubleBuffer.reversedArray(): DoubleBuffer {
//    if (isEmpty()) return this
//    val result = DoubleBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun DELETE.reversedArray(): DELETE {
//    if (isEmpty()) return this
//    val result = DELETE(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Returns a buffer with elements of this buffer in reversed order.
// */
//fun CharBuffer.reversedArray(): CharBuffer {
//    if (isEmpty()) return this
//    val result = CharBuffer(size)
//    val lastIndex = lastIndex
//    for (i in 0..lastIndex)
//        result[lastIndex - i] = this[i]
//    return result
//}
//
///**
// * Sorts elements in the buffer in-place according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <T, R : Comparable<R>> Array<out T>.sortBy(crossinline selector: (T) -> R?): Unit {
//    if (size > 1) sortWith(compareBy(selector))
//}
//
///**
// * Sorts elements in the buffer in-place descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <T, R : Comparable<R>> Array<out T>.sortByDescending(crossinline selector: (T) -> R?): Unit {
//    if (size > 1) sortWith(compareByDescending(selector))
//}
//


///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun ByteBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun ShortBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun IntBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun LongBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun FloatBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun DoubleBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Sorts elements in the buffer in-place descending according to their natural sort order.
// */
//fun CharBuffer.sortDescending(): Unit {
//    if (size > 1) {
//        sort()
//        reverse()
//    }
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun <T : Comparable<T>> Array<out T>.sorted(): List<T> {
//    return sortedArray().asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun ByteBuffer.sorted(): List<Byte> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun ShortBuffer.sorted(): List<Short> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun IntBuffer.sorted(): List<Int> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun LongBuffer.sorted(): List<Long> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun FloatBuffer.sorted(): List<Float> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun DoubleBuffer.sorted(): List<Double> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to their natural sort order.
// */
//fun CharBuffer.sorted(): List<Char> {
//    return toTypedArray().apply { sort() }.asList()
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun <T : Comparable<T>> Array<T>.sortedArray(): Array<T> {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun ByteBuffer.sortedArray(): ByteBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun ShortBuffer.sortedArray(): ShortBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun IntBuffer.sortedArray(): IntBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun LongBuffer.sortedArray(): LongBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun FloatBuffer.sortedArray(): FloatBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun DoubleBuffer.sortedArray(): DoubleBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according to their natural sort order.
// */
//fun CharBuffer.sortedArray(): CharBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sort() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun <T : Comparable<T>> Array<T>.sortedArrayDescending(): Array<T> {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortWith(reverseOrder()) }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun ByteBuffer.sortedArrayDescending(): ByteBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun ShortBuffer.sortedArrayDescending(): ShortBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun IntBuffer.sortedArrayDescending(): IntBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun LongBuffer.sortedArrayDescending(): LongBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun FloatBuffer.sortedArrayDescending(): FloatBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun DoubleBuffer.sortedArrayDescending(): DoubleBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted descending according to their natural sort order.
// */
//fun CharBuffer.sortedArrayDescending(): CharBuffer {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortDescending() }
//}
//
///**
// * Returns a buffer with all elements of this buffer sorted according the specified [comparator].
// */
//fun <T> Array<out T>.sortedArrayWith(comparator: Comparator<in T>): Array<out T> {
//    if (isEmpty()) return this
//    return this.copyOf().apply { sortWith(comparator) }
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <T, R : Comparable<R>> Array<out T>.sortedBy(crossinline selector: (T) -> R?): List<T> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> ByteBuffer.sortedBy(crossinline selector: (Byte) -> R?): List<Byte> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> ShortBuffer.sortedBy(crossinline selector: (Short) -> R?): List<Short> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> IntBuffer.sortedBy(crossinline selector: (Int) -> R?): List<Int> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> LongBuffer.sortedBy(crossinline selector: (Long) -> R?): List<Long> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> FloatBuffer.sortedBy(crossinline selector: (Float) -> R?): List<Float> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> DoubleBuffer.sortedBy(crossinline selector: (Double) -> R?): List<Double> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> DELETE.sortedBy(crossinline selector: (Boolean) -> R?): List<Boolean> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> CharBuffer.sortedBy(crossinline selector: (Char) -> R?): List<Char> {
//    return sortedWith(compareBy(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <T, R : Comparable<R>> Array<out T>.sortedByDescending(crossinline selector: (T) -> R?): List<T> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> ByteBuffer.sortedByDescending(crossinline selector: (Byte) -> R?): List<Byte> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> ShortBuffer.sortedByDescending(crossinline selector: (Short) -> R?): List<Short> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> IntBuffer.sortedByDescending(crossinline selector: (Int) -> R?): List<Int> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> LongBuffer.sortedByDescending(crossinline selector: (Long) -> R?): List<Long> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> FloatBuffer.sortedByDescending(crossinline selector: (Float) -> R?): List<Float> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> DoubleBuffer.sortedByDescending(crossinline selector: (Double) -> R?): List<Double> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> DELETE.sortedByDescending(crossinline selector: (Boolean) -> R?): List<Boolean> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to natural sort order of the value returned by specified [selector] function.
// */
//inline fun <R : Comparable<R>> CharBuffer.sortedByDescending(crossinline selector: (Char) -> R?): List<Char> {
//    return sortedWith(compareByDescending(selector))
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun <T : Comparable<T>> Array<out T>.sortedDescending(): List<T> {
//    return sortedWith(reverseOrder())
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun ByteBuffer.sortedDescending(): List<Byte> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun ShortBuffer.sortedDescending(): List<Short> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun IntBuffer.sortedDescending(): List<Int> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun LongBuffer.sortedDescending(): List<Long> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun FloatBuffer.sortedDescending(): List<Float> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun DoubleBuffer.sortedDescending(): List<Double> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted descending according to their natural sort order.
// */
//fun CharBuffer.sortedDescending(): List<Char> {
//    return copyOf().apply { sort() }.reversed()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun <T> Array<out T>.sortedWith(comparator: Comparator<in T>): List<T> {
//    return sortedArrayWith(comparator).asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun ByteBuffer.sortedWith(comparator: Comparator<in Byte>): List<Byte> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun ShortBuffer.sortedWith(comparator: Comparator<in Short>): List<Short> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun IntBuffer.sortedWith(comparator: Comparator<in Int>): List<Int> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun LongBuffer.sortedWith(comparator: Comparator<in Long>): List<Long> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun FloatBuffer.sortedWith(comparator: Comparator<in Float>): List<Float> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun DoubleBuffer.sortedWith(comparator: Comparator<in Double>): List<Double> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun DELETE.sortedWith(comparator: Comparator<in Boolean>): List<Boolean> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}
//
///**
// * Returns a list of all elements sorted according to the specified [comparator].
// */
//fun CharBuffer.sortedWith(comparator: Comparator<in Char>): List<Char> {
//    return toTypedArray().apply { sortWith(comparator) }.asList()
//}


/** Returns a [List] that wraps the original buffer. */
fun ByteBuffer.asList(): List<Byte> {
    return object : AbstractList<Byte>(), RandomAccess {
        override val size get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Byte): Boolean = contains(element)
        override fun get(index: Int): Byte = get(index)
        override fun indexOf(element: Byte): Int = indexOf(element)
        override fun lastIndexOf(element: Byte): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun ShortBuffer.asList(): List<Short> {
    return object : AbstractList<Short>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Short): Boolean = contains(element)
        override fun get(index: Int): Short = get(index)
        override fun indexOf(element: Short): Int = indexOf(element)
        override fun lastIndexOf(element: Short): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun IntBuffer.asList(): List<Int> {
    return object : AbstractList<Int>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Int): Boolean = contains(element)
        override fun get(index: Int): Int = get(index)
        override fun indexOf(element: Int): Int = indexOf(element)
        override fun lastIndexOf(element: Int): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun LongBuffer.asList(): List<Long> {
    return object : AbstractList<Long>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Long): Boolean = contains(element)
        override fun get(index: Int): Long = get(index)
        override fun indexOf(element: Long): Int = indexOf(element)
        override fun lastIndexOf(element: Long): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun FloatBuffer.asList(): List<Float> {
    return object : AbstractList<Float>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Float): Boolean = contains(element)
        override fun get(index: Int): Float = get(index)
        override fun indexOf(element: Float): Int = indexOf(element)
        override fun lastIndexOf(element: Float): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun DoubleBuffer.asList(): List<Double> {
    return object : AbstractList<Double>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Double): Boolean = contains(element)
        override fun get(index: Int): Double = get(index)
        override fun indexOf(element: Double): Int = indexOf(element)
        override fun lastIndexOf(element: Double): Int = lastIndexOf(element)
    }
}

/** Returns a [List] that wraps the original buffer. */
fun CharBuffer.asList(): List<Char> {
    return object : AbstractList<Char>(), RandomAccess {
        override val size: Int get() = this@asList.size
        override fun isEmpty(): Boolean = isEmpty()
        override fun contains(element: Char): Boolean = contains(element)
        override fun get(index: Int): Char = get(index)
        override fun indexOf(element: Char): Int = indexOf(element)
        override fun lastIndexOf(element: Char): Int = lastIndexOf(element)
    }
}


///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted according to the specified [comparator], otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted according to the specified [comparator].
// */
//fun <T> Array<out T>.binarySearch(element: T, comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element, comparator)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun <T> Array<out T>.binarySearch(element: T, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun ByteBuffer.binarySearch(element: Byte, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun ShortBuffer.binarySearch(element: Short, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun IntBuffer.binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun LongBuffer.binarySearch(element: Long, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun FloatBuffer.binarySearch(element: Float, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun DoubleBuffer.binarySearch(element: Double, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Searches the buffer or the range of the buffer for the provided [element] using the binary search algorithm.
// * The buffer is expected to be sorted, otherwise the result is undefined.
// *
// * If the buffer contains multiple elements equal to the specified [element], there is no guarantee which one will be found.
// *
// * @return the index of the element, if it is contained in the buffer within the specified range;
// * otherwise, the inverted insertion point `(-insertion point - 1)`.
// * The insertion point is defined as the index at which the element should be inserted,
// * so that the buffer (or the specified subrange of buffer) still remains sorted.
// */
//fun CharBuffer.binarySearch(element: Char, fromIndex: Int = 0, toIndex: Int = size): Int {
//    return java.util.Arrays.binarySearch(this, fromIndex, toIndex, element)
//}
//
///**
// * Returns `true` if the two specified buffers are *deeply* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// *
// * If two corresponding elements are nested buffers, they are also compared deeply.
// * If any of buffers contains itself on any nesting level the behavior is undefined.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun <T> Array<out T>.contentDeepEquals(other: Array<out T>): Boolean {
//    return java.util.Arrays.deepEquals(this, other)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// * Nested buffers are treated as lists too.
// *
// * If any of buffers contains itself on any nesting level the behavior is undefined.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun <T> Array<out T>.contentDeepHashCode(): Int {
//    return java.util.Arrays.deepHashCode(this)
//}
//
///**
// * Returns a string representation of the contents of this buffer as if it is a [List].
// * Nested buffers are treated as lists too.
// *
// * If any of buffers contains itself on any nesting level that reference
// * is rendered as `"[...]"` to prevent recursion.
// *
// * @sample samples.collections.Arrays.ContentOperations.contentDeepToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun <T> Array<out T>.contentDeepToString(): String {
//    return java.util.Arrays.deepToString(this)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun <T> Array<out T>.contentEquals(other: Array<out T>): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun ByteBuffer.contentEquals(other: ByteBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun ShortBuffer.contentEquals(other: ShortBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun IntBuffer.contentEquals(other: IntBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun LongBuffer.contentEquals(other: LongBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun FloatBuffer.contentEquals(other: FloatBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun DoubleBuffer.contentEquals(other: DoubleBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun DELETE.contentEquals(other: DELETE): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns `true` if the two specified buffers are *structurally* equal to one another,
// * i.e. contain the same number of the same elements in the same order.
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline infix fun CharBuffer.contentEquals(other: CharBuffer): Boolean {
//    return java.util.Arrays.equals(this, other)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun <T> Array<out T>.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun ByteBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun ShortBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun IntBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun LongBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun FloatBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun DoubleBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun DELETE.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a hash code based on the contents of this buffer as if it is [List].
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun CharBuffer.contentHashCode(): Int {
//    return java.util.Arrays.hashCode(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun <T> Array<out T>.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun ByteBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun ShortBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun IntBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun LongBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun FloatBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun DoubleBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun DELETE.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns a string representation of the contents of the specified buffer as if it is [List].
// *
// * @sample samples.collections.Arrays.ContentOperations.contentToString
// */
//@SinceKotlin("1.1")
//@kotlin.internal.InlineOnly
//inline fun CharBuffer.contentToString(): String {
//    return java.util.Arrays.toString(this)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun <T> Array<T>.copyOf(): Array<T> {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun ByteBuffer.copyOf(): ByteBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun ShortBuffer.copyOf(): ShortBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun IntBuffer.copyOf(): IntBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun LongBuffer.copyOf(): LongBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun FloatBuffer.copyOf(): FloatBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun DoubleBuffer.copyOf(): DoubleBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun DELETE.copyOf(): DELETE {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun CharBuffer.copyOf(): CharBuffer {
//    return java.util.Arrays.copyOf(this, size)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun ByteBuffer.copyOf(newSize: Int): ByteBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun ShortBuffer.copyOf(newSize: Int): ShortBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun IntBuffer.copyOf(newSize: Int): IntBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun LongBuffer.copyOf(newSize: Int): LongBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun FloatBuffer.copyOf(newSize: Int): FloatBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun DoubleBuffer.copyOf(newSize: Int): DoubleBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun DELETE.copyOf(newSize: Int): DELETE {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun CharBuffer.copyOf(newSize: Int): CharBuffer {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of the original buffer, resized to the given [newSize].
// */
//@kotlin.internal.InlineOnly
//inline fun <T> Array<T>.copyOf(newSize: Int): Array<T?> {
//    return java.util.Arrays.copyOf(this, newSize)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun <T> Array<T>.copyOfRange(fromIndex: Int, toIndex: Int): Array<T> {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun ByteBuffer.copyOfRange(fromIndex: Int, toIndex: Int): ByteBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun ShortBuffer.copyOfRange(fromIndex: Int, toIndex: Int): ShortBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun IntBuffer.copyOfRange(fromIndex: Int, toIndex: Int): IntBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun LongBuffer.copyOfRange(fromIndex: Int, toIndex: Int): LongBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun FloatBuffer.copyOfRange(fromIndex: Int, toIndex: Int): FloatBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun DoubleBuffer.copyOfRange(fromIndex: Int, toIndex: Int): DoubleBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun DELETE.copyOfRange(fromIndex: Int, toIndex: Int): DELETE {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}
//
///**
// * Returns new buffer which is a copy of range of original buffer.
// */
//@kotlin.internal.InlineOnly
//inline fun CharBuffer.copyOfRange(fromIndex: Int, toIndex: Int): CharBuffer {
//    return java.util.Arrays.copyOfRange(this, fromIndex, toIndex)
//}


/** Fills original buffer with the provided value. */
fun ByteBuffer.fill(element: Byte, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun ShortBuffer.fill(element: Short, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun IntBuffer.fill(element: Int, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun LongBuffer.fill(element: Long, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun FloatBuffer.fill(element: Float, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun DoubleBuffer.fill(element: Double, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}

/** Fills original buffer with the provided value. */
fun CharBuffer.fill(element: Char, fromIndex: Int = 0, toIndex: Int = capacity) {
    for (i in fromIndex until toIndex) put(i, element)
}


/** Returns the range of valid indices for the buffer. */
val ByteBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val ShortBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val IntBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val LongBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val FloatBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val DoubleBuffer.indices get() = IntRange(0, lastIndex)

/** Returns the range of valid indices for the buffer. */
val CharBuffer.indices get() = IntRange(0, lastIndex)


/** Returns `true` if the buffer is empty. */
inline fun ByteBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun ShortBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun IntBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun LongBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun FloatBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun DoubleBuffer.isEmpty() = capacity == 0

/** Returns `true` if the buffer is empty. */
inline fun CharBuffer.isEmpty() = capacity == 0


/** Returns `true` if the buffer is not empty. */
inline fun ByteBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun ShortBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun IntBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun LongBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun FloatBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun DoubleBuffer.isNotEmpty() = !isEmpty()

/** Returns `true` if the buffer is not empty. */
inline fun CharBuffer.isNotEmpty() = !isEmpty()


/** Returns the last valid index for the buffer. */
val ByteBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val ShortBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val IntBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val LongBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val FloatBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val DoubleBuffer.lastIndex get() = capacity - 1

/** Returns the last valid index for the buffer. */
val CharBuffer.lastIndex get() = capacity - 1


/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun ByteBuffer.plus(element: Byte): ByteBuffer {
    val dst = memAlloc(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun ShortBuffer.plus(element: Short): ShortBuffer {
    val dst = memAllocShort(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun IntBuffer.plus(element: Int): IntBuffer {
    val dst = memAllocInt(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun LongBuffer.plus(element: Long): LongBuffer {
    val dst = memAllocLong(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun FloatBuffer.plus(element: Float): FloatBuffer {
    val dst = memAllocFloat(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun DoubleBuffer.plus(element: Double): DoubleBuffer {
    val dst = memAllocDouble(capacity + 1)
    memCopy(this, dst)
    return dst.put(capacity, element)
}

/** Returns a buffer containing all elements of the original buffer and then the given [element]. */
operator fun CharBuffer.plus(element: Char): CharBuffer {
    TODO()
}


/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun ByteBuffer.plus(elements: Collection<Byte>): ByteBuffer {
    val dst = memAlloc(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun ShortBuffer.plus(elements: Collection<Short>): ShortBuffer {
    val dst = memAllocShort(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun IntBuffer.plus(elements: Collection<Int>): IntBuffer {
    val dst = memAllocInt(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun LongBuffer.plus(elements: Collection<Long>): LongBuffer {
    val dst = memAllocLong(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun FloatBuffer.plus(elements: Collection<Float>): FloatBuffer {
    val dst = memAllocFloat(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun DoubleBuffer.plus(elements: Collection<Double>): DoubleBuffer {
    val dst = memAllocDouble(capacity + elements.size)
    memCopy(this, dst)
    for (i in elements.indices) dst[capacity + i] = elements.elementAt(i)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] collection. */
operator fun CharBuffer.plus(elements: Collection<Char>): CharBuffer {
    TODO()
}


/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun ByteBuffer.plus(elements: ByteBuffer): ByteBuffer {
    val dst = memAlloc(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun ShortBuffer.plus(elements: ShortBuffer): ShortBuffer {
    val dst = memAllocShort(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun IntBuffer.plus(elements: IntBuffer): IntBuffer {
    val dst = memAllocInt(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun LongBuffer.plus(elements: LongBuffer): LongBuffer {
    val dst = memAllocLong(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun FloatBuffer.plus(elements: FloatBuffer): FloatBuffer {
    val dst = memAllocFloat(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun DoubleBuffer.plus(elements: DoubleBuffer): DoubleBuffer {
    val dst = memAllocDouble(capacity + elements.size)
    memCopy(this, dst)
    memCopy(memAddress(elements), memAddress(dst, capacity), elements.remaining.L)
    return dst
}

/** Returns a buffer containing all elements of the original buffer and then all elements of the given [elements] buffer. */
operator fun CharBuffer.plus(elements: CharBuffer): CharBuffer {
    TODO()
}


///**
// * Returns a buffer containing all elements of the original buffer and then the given [element].
// */
//@kotlin.internal.InlineOnly
//inline fun <T> Array<T>.plusElement(element: T): Array<T> {
//    return plus(element)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun IntBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun LongBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun ByteBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun ShortBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun DoubleBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun FloatBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place.
// */
//fun CharBuffer.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts the buffer in-place according to the natural order of its elements.
// */
//@kotlin.internal.InlineOnly
//inline fun <T : Comparable<T>> Array<out T>.sort(): Unit {
//    @Suppress("UNCHECKED_CAST")
//    (this as Array<Any?>).sort()
//}
//
///**
// * Sorts the buffer in-place according to the natural order of its elements.
// *
// * @throws ClassCastException if any element of the buffer is not [Comparable].
// */
//fun <T> Array<out T>.sort(): Unit {
//    if (size > 1) java.util.Arrays.sort(this)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun <T> Array<out T>.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun ByteBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun ShortBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun IntBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun LongBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun FloatBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun DoubleBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts a range in the buffer in-place.
// */
//fun CharBuffer.sort(fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex)
//}
//
///**
// * Sorts the buffer in-place according to the order specified by the given [comparator].
// */
//fun <T> Array<out T>.sortWith(comparator: Comparator<in T>): Unit {
//    if (size > 1) java.util.Arrays.sort(this, comparator)
//}
//
///**
// * Sorts a range in the buffer in-place with the given [comparator].
// */
//fun <T> Array<out T>.sortWith(comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = size): Unit {
//    java.util.Arrays.sort(this, fromIndex, toIndex, comparator)
//}
//


/** Returns a buffer of Byte containing all of the elements of this generic buffer. */
fun ByteBuffer.toByteArray() = ByteArray(size) { get(it) }

/** Returns a buffer of Char containing all of the elements of this generic buffer. */
fun CharBuffer.toCharArray() = CharArray(size) { get(it) }

/** Returns a buffer of Double containing all of the elements of this generic buffer. */
fun DoubleBuffer.toDoubleArray() = DoubleArray(size) { get(it) }

/** Returns a buffer of Float containing all of the elements of this generic buffer. */
fun FloatBuffer.toFloatArray() = FloatArray(size) { get(it) }

/** Returns a buffer of Int containing all of the elements of this generic buffer. */
fun IntBuffer.toIntArray() = IntArray(size) { get(it) }

/** Returns a buffer of Long containing all of the elements of this generic buffer. */
fun LongBuffer.toLongArray() = LongArray(size) { get(it) }

/** Returns a buffer of Short containing all of the elements of this generic buffer. */
fun ShortBuffer.toShortArray() = ShortArray(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun ByteBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun ShortBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun IntBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun LongBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun FloatBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun DoubleBuffer.toTypedArray() = Array(size) { get(it) }

/** Returns a *typed* object buffer containing all of the elements of this primitive buffer. */
fun CharBuffer.toTypedArray() = Array(size) { get(it) }

//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <T, K, V> Array<out T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> ByteBuffer.associate(transform: (Byte) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> ShortBuffer.associate(transform: (Short) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> IntBuffer.associate(transform: (Int) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> LongBuffer.associate(transform: (Long) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> FloatBuffer.associate(transform: (Float) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> DoubleBuffer.associate(transform: (Double) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> DELETE.associate(transform: (Boolean) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing key-value pairs provided by [transform] function
// * applied to elements of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> CharBuffer.associate(transform: (Char) -> Pair<K, V>): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateTo(LinkedHashMap<K, V>(capacity), transform)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <T, K> Array<out T>.associateBy(keySelector: (T) -> K): Map<K, T> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, T>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> ByteBuffer.associateBy(keySelector: (Byte) -> K): Map<K, Byte> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Byte>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> ShortBuffer.associateBy(keySelector: (Short) -> K): Map<K, Short> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Short>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> IntBuffer.associateBy(keySelector: (Int) -> K): Map<K, Int> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Int>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> LongBuffer.associateBy(keySelector: (Long) -> K): Map<K, Long> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Long>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> FloatBuffer.associateBy(keySelector: (Float) -> K): Map<K, Float> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Float>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> DoubleBuffer.associateBy(keySelector: (Double) -> K): Map<K, Double> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Double>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> DELETE.associateBy(keySelector: (Boolean) -> K): Map<K, Boolean> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Boolean>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the elements from the given buffer indexed by the key
// * returned from [keySelector] function applied to each element.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K> CharBuffer.associateBy(keySelector: (Char) -> K): Map<K, Char> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, Char>(capacity), keySelector)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <T, K, V> Array<out T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> ByteBuffer.associateBy(keySelector: (Byte) -> K, valueTransform: (Byte) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> ShortBuffer.associateBy(keySelector: (Short) -> K, valueTransform: (Short) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> IntBuffer.associateBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> LongBuffer.associateBy(keySelector: (Long) -> K, valueTransform: (Long) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> FloatBuffer.associateBy(keySelector: (Float) -> K, valueTransform: (Float) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> DoubleBuffer.associateBy(keySelector: (Double) -> K, valueTransform: (Double) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> DELETE.associateBy(keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Returns a [Map] containing the values provided by [valueTransform] and indexed by [keySelector] functions applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// *
// * The returned map preserves the entry iteration order of the original buffer.
// */
//inline fun <K, V> CharBuffer.associateBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, V> {
//    val capacity = mapCapacity(size).coerceAtLeast(16)
//    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <T, K, M : MutableMap<in K, in T>> Array<out T>.associateByTo(destination: M, keySelector: (T) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Byte>> ByteBuffer.associateByTo(destination: M, keySelector: (Byte) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Short>> ShortBuffer.associateByTo(destination: M, keySelector: (Short) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Int>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Long>> LongBuffer.associateByTo(destination: M, keySelector: (Long) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Float>> FloatBuffer.associateByTo(destination: M, keySelector: (Float) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Double>> DoubleBuffer.associateByTo(destination: M, keySelector: (Double) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Boolean>> DELETE.associateByTo(destination: M, keySelector: (Boolean) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function applied to each element of the given buffer
// * and value is the element itself.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, M : MutableMap<in K, in Char>> CharBuffer.associateByTo(destination: M, keySelector: (Char) -> K): M {
//    for (element in this) {
//        destination.put(keySelector(element), element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <T, K, V, M : MutableMap<in K, in V>> Array<out T>.associateByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> ByteBuffer.associateByTo(destination: M, keySelector: (Byte) -> K, valueTransform: (Byte) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> ShortBuffer.associateByTo(destination: M, keySelector: (Short) -> K, valueTransform: (Short) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> LongBuffer.associateByTo(destination: M, keySelector: (Long) -> K, valueTransform: (Long) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> FloatBuffer.associateByTo(destination: M, keySelector: (Float) -> K, valueTransform: (Float) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> DoubleBuffer.associateByTo(destination: M, keySelector: (Double) -> K, valueTransform: (Double) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> DELETE.associateByTo(destination: M, keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs,
// * where key is provided by the [keySelector] function and
// * and value is provided by the [valueTransform] function applied to elements of the given buffer.
// *
// * If any two elements would have the same key returned by [keySelector] the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> CharBuffer.associateByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
//    for (element in this) {
//        destination.put(keySelector(element), valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <T, K, V, M : MutableMap<in K, in V>> Array<out T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> ByteBuffer.associateTo(destination: M, transform: (Byte) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> ShortBuffer.associateTo(destination: M, transform: (Short) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateTo(destination: M, transform: (Int) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> LongBuffer.associateTo(destination: M, transform: (Long) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> FloatBuffer.associateTo(destination: M, transform: (Float) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> DoubleBuffer.associateTo(destination: M, transform: (Double) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> DELETE.associateTo(destination: M, transform: (Boolean) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}
//
///**
// * Populates and returns the [destination] mutable map with key-value pairs
// * provided by [transform] function applied to each element of the given buffer.
// *
// * If any of two pairs would have the same key the last one gets added to the map.
// */
//inline fun <K, V, M : MutableMap<in K, in V>> CharBuffer.associateTo(destination: M, transform: (Char) -> Pair<K, V>): M {
//    for (element in this) {
//        destination += transform(element)
//    }
//    return destination
//}


/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Byte>> ByteBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Short>> ShortBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Int>> IntBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Long>> LongBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Float>> FloatBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Double>> DoubleBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}

/** Appends all elements to the given [destination] collection. */
fun <C : MutableCollection<in Char>> CharBuffer.toCollection(destination: C): C {
    for (item in this) destination += item
    return destination
}


/** Returns a [HashSet] of all elements. */
fun <T> Array<out T>.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun ByteBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun ShortBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun IntBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun LongBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun FloatBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun DoubleBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))

/** Returns a [HashSet] of all elements. */
fun CharBuffer.toHashSet() = toCollection(HashSet(maps.mapCapacity(size)))


/** Returns a [List] containing all elements. */
fun ByteBuffer.toList(): List<Byte> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun ShortBuffer.toList(): List<Short> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun IntBuffer.toList(): List<Int> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun LongBuffer.toList(): List<Long> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun FloatBuffer.toList(): List<Float> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun DoubleBuffer.toList(): List<Double> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}

/** Returns a [List] containing all elements. */
fun CharBuffer.toList(): List<Char> {
    return when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> toMutableList()
    }
}


/** Returns a [MutableList] filled with all elements of this buffer. */
fun ByteBuffer.toMutableList(): MutableList<Byte> {
    val list = ArrayList<Byte>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun ShortBuffer.toMutableList(): MutableList<Short> {
    val list = ArrayList<Short>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun IntBuffer.toMutableList(): MutableList<Int> {
    val list = ArrayList<Int>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun LongBuffer.toMutableList(): MutableList<Long> {
    val list = ArrayList<Long>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun FloatBuffer.toMutableList(): MutableList<Float> {
    val list = ArrayList<Float>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun DoubleBuffer.toMutableList(): MutableList<Double> {
    val list = ArrayList<Double>(size)
    for (item in this) list += item
    return list
}

/** Returns a [MutableList] filled with all elements of this buffer. */
fun CharBuffer.toMutableList(): MutableList<Char> {
    val list = ArrayList<Char>(size)
    for (item in this) list += item
    return list
}


/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun ByteBuffer.toSet(): Set<Byte> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun ShortBuffer.toSet(): Set<Short> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun IntBuffer.toSet(): Set<Int> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun LongBuffer.toSet(): Set<Long> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun FloatBuffer.toSet(): Set<Float> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun DoubleBuffer.toSet(): Set<Double> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}

/** Returns a [Set] of all elements.
 *  The returned set preserves the element iteration order of the original buffer. */
fun CharBuffer.toSet(): Set<Char> {
    return when (size) {
        0 -> emptySet()
        1 -> setOf(this[0])
        else -> toCollection(LinkedHashSet(maps.mapCapacity(size)))
    }
}


/** Returns a [SortedSet] of all elements. */
fun ByteBuffer.toSortedSet(): java.util.SortedSet<Byte> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun ShortBuffer.toSortedSet(): java.util.SortedSet<Short> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun IntBuffer.toSortedSet(): java.util.SortedSet<Int> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun LongBuffer.toSortedSet(): java.util.SortedSet<Long> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun FloatBuffer.toSortedSet(): java.util.SortedSet<Float> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun DoubleBuffer.toSortedSet(): java.util.SortedSet<Double> = toCollection(sortedSetOf())

/** Returns a [SortedSet] of all elements. */
fun CharBuffer.toSortedSet(): java.util.SortedSet<Char> = toCollection(sortedSetOf())


//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <T, R> Array<out T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> ByteBuffer.flatMap(transform: (Byte) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> ShortBuffer.flatMap(transform: (Short) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> IntBuffer.flatMap(transform: (Int) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> LongBuffer.flatMap(transform: (Long) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> FloatBuffer.flatMap(transform: (Float) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> DoubleBuffer.flatMap(transform: (Double) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> DELETE.flatMap(transform: (Boolean) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Returns a single list of all elements yielded from results of [transform] function being invoked on each element of original buffer.
// */
//inline fun <R> CharBuffer.flatMap(transform: (Char) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> ByteBuffer.flatMapTo(destination: C, transform: (Byte) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> ShortBuffer.flatMapTo(destination: C, transform: (Short) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> IntBuffer.flatMapTo(destination: C, transform: (Int) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> LongBuffer.flatMapTo(destination: C, transform: (Long) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> FloatBuffer.flatMapTo(destination: C, transform: (Float) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> DoubleBuffer.flatMapTo(destination: C, transform: (Double) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> DELETE.flatMapTo(destination: C, transform: (Boolean) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Appends all elements yielded from results of [transform] function being invoked on each element of original buffer, to the given [destination].
// */
//inline fun <R, C : MutableCollection<in R>> CharBuffer.flatMapTo(destination: C, transform: (Char) -> Iterable<R>): C {
//    for (element in this) {
//        val list = transform(element)
//        destination.addAll(list)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <T, K> Array<out T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
//    return groupByTo(LinkedHashMap<K, MutableList<T>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> ByteBuffer.groupBy(keySelector: (Byte) -> K): Map<K, List<Byte>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Byte>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> ShortBuffer.groupBy(keySelector: (Short) -> K): Map<K, List<Short>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Short>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> IntBuffer.groupBy(keySelector: (Int) -> K): Map<K, List<Int>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Int>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> LongBuffer.groupBy(keySelector: (Long) -> K): Map<K, List<Long>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Long>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> FloatBuffer.groupBy(keySelector: (Float) -> K): Map<K, List<Float>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Float>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> DoubleBuffer.groupBy(keySelector: (Double) -> K): Map<K, List<Double>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Double>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> DELETE.groupBy(keySelector: (Boolean) -> K): Map<K, List<Boolean>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Boolean>>(), keySelector)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and returns a map where each group key is associated with a list of corresponding elements.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K> CharBuffer.groupBy(keySelector: (Char) -> K): Map<K, List<Char>> {
//    return groupByTo(LinkedHashMap<K, MutableList<Char>>(), keySelector)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <T, K, V> Array<out T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> ByteBuffer.groupBy(keySelector: (Byte) -> K, valueTransform: (Byte) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> ShortBuffer.groupBy(keySelector: (Short) -> K, valueTransform: (Short) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> IntBuffer.groupBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> LongBuffer.groupBy(keySelector: (Long) -> K, valueTransform: (Long) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> FloatBuffer.groupBy(keySelector: (Float) -> K, valueTransform: (Float) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> DoubleBuffer.groupBy(keySelector: (Double) -> K, valueTransform: (Double) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> DELETE.groupBy(keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and returns a map where each group key is associated with a list of corresponding values.
// *
// * The returned map preserves the entry iteration order of the keys produced from the original buffer.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V> CharBuffer.groupBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, List<V>> {
//    return groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Array<out T>.groupByTo(destination: M, keySelector: (T) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<T>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Byte>>> ByteBuffer.groupByTo(destination: M, keySelector: (Byte) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Byte>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Short>>> ShortBuffer.groupByTo(destination: M, keySelector: (Short) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Short>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Int>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Int>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Long>>> LongBuffer.groupByTo(destination: M, keySelector: (Long) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Long>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Float>>> FloatBuffer.groupByTo(destination: M, keySelector: (Float) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Float>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Double>>> DoubleBuffer.groupByTo(destination: M, keySelector: (Double) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Double>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Boolean>>> DELETE.groupByTo(destination: M, keySelector: (Boolean) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Boolean>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups elements of the original buffer by the key returned by the given [keySelector] function
// * applied to each element and puts to the [destination] map each group key associated with a list of corresponding elements.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupBy
// */
//inline fun <K, M : MutableMap<in K, MutableList<Char>>> CharBuffer.groupByTo(destination: M, keySelector: (Char) -> K): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<Char>() }
//        list.add(element)
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Array<out T>.groupByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> ByteBuffer.groupByTo(destination: M, keySelector: (Byte) -> K, valueTransform: (Byte) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> ShortBuffer.groupByTo(destination: M, keySelector: (Short) -> K, valueTransform: (Short) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> LongBuffer.groupByTo(destination: M, keySelector: (Long) -> K, valueTransform: (Long) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> FloatBuffer.groupByTo(destination: M, keySelector: (Float) -> K, valueTransform: (Float) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> DoubleBuffer.groupByTo(destination: M, keySelector: (Double) -> K, valueTransform: (Double) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> DELETE.groupByTo(destination: M, keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Groups values returned by the [valueTransform] function applied to each element of the original buffer
// * by the key returned by the given [keySelector] function applied to the element
// * and puts to the [destination] map each group key associated with a list of corresponding values.
// *
// * @return The [destination] map.
// *
// * @sample samples.collections.Collections.Transformations.groupByKeysAndValues
// */
//inline fun <K, V, M : MutableMap<in K, MutableList<V>>> CharBuffer.groupByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
//    for (element in this) {
//        val key = keySelector(element)
//        val list = destination.getOrPut(key) { ArrayList<V>() }
//        list.add(valueTransform(element))
//    }
//    return destination
//}
//
///**
// * Creates a [Grouping] source from a buffer to be used later with one of group-and-fold operations
// * using the specified [keySelector] function to extract a key from each element.
// *
// * @sample samples.collections.Collections.Transformations.groupingByEachCount
// */
//@SinceKotlin("1.1")
//inline fun <T, K> Array<out T>.groupingBy(crossinline keySelector: (T) -> K): Grouping<T, K> {
//    return object : Grouping<T, K> {
//        override fun sourceIterator(): Iterator<T> = this@groupingBy.iterator()
//        override fun keyOf(element: T): K = keySelector(element)
//    }
//}


/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> ByteBuffer.map(transform: (Byte) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> ShortBuffer.map(transform: (Short) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> IntBuffer.map(transform: (Int) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> LongBuffer.map(transform: (Long) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> FloatBuffer.map(transform: (Float) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> DoubleBuffer.map(transform: (Double) -> R): List<R> = mapTo(ArrayList(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element in the original buffer. */
inline fun <R> CharBuffer.map(transform: (Char) -> R): List<R> = mapTo(ArrayList(size), transform)


/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> ByteBuffer.mapIndexed(transform: (index: Int, Byte) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> ShortBuffer.mapIndexed(transform: (index: Int, Short) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> IntBuffer.mapIndexed(transform: (index: Int, Int) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> LongBuffer.mapIndexed(transform: (index: Int, Long) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> FloatBuffer.mapIndexed(transform: (index: Int, Float) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> DoubleBuffer.mapIndexed(transform: (index: Int, Double) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)

/** Returns a list containing the results of applying the given [transform] function to each element and its index in the original buffer.
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R> CharBuffer.mapIndexed(transform: (index: Int, Char) -> R): List<R> = mapIndexedTo(ArrayList<R>(size), transform)


/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <T, R, C : MutableCollection<in R>> Array<out T>.mapIndexedTo(destination: C, transform: (index: Int, T) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> ByteBuffer.mapIndexedTo(destination: C, transform: (index: Int, Byte) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> ShortBuffer.mapIndexedTo(destination: C, transform: (index: Int, Short) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> IntBuffer.mapIndexedTo(destination: C, transform: (index: Int, Int) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> LongBuffer.mapIndexedTo(destination: C, transform: (index: Int, Long) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> FloatBuffer.mapIndexedTo(destination: C, transform: (index: Int, Float) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> DoubleBuffer.mapIndexedTo(destination: C, transform: (index: Int, Double) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}

/** Applies the given [transform] function to each element and its index in the original buffer and appends the results to the given [destination].
 *  @param [transform] function that takes the index of an element and the element itself and returns the result of the transform applied to the element. */
inline fun <R, C : MutableCollection<in R>> CharBuffer.mapIndexedTo(destination: C, transform: (index: Int, Char) -> R): C {
    var index = 0
    for (item in this) destination += transform(index++, item)
    return destination
}


/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> ByteBuffer.mapTo(destination: C, transform: (Byte) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> ShortBuffer.mapTo(destination: C, transform: (Short) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> IntBuffer.mapTo(destination: C, transform: (Int) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> LongBuffer.mapTo(destination: C, transform: (Long) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> FloatBuffer.mapTo(destination: C, transform: (Float) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> DoubleBuffer.mapTo(destination: C, transform: (Double) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}

/** Applies the given [transform] function to each element of the original buffer and appends the results to the given [destination]. */
inline fun <R, C : MutableCollection<in R>> CharBuffer.mapTo(destination: C, transform: (Char) -> R): C {
    for (item in this) destination += transform(item)
    return destination
}


///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun <T> Array<out T>.withIndex(): Iterable<IndexedValue<T>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun ByteBuffer.withIndex(): Iterable<IndexedValue<Byte>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun ShortBuffer.withIndex(): Iterable<IndexedValue<Short>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun IntBuffer.withIndex(): Iterable<IndexedValue<Int>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun LongBuffer.withIndex(): Iterable<IndexedValue<Long>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun FloatBuffer.withIndex(): Iterable<IndexedValue<Float>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun DoubleBuffer.withIndex(): Iterable<IndexedValue<Double>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun DELETE.withIndex(): Iterable<IndexedValue<Boolean>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a lazy [Iterable] of [IndexedValue] for each element of the original buffer.
// */
//fun CharBuffer.withIndex(): Iterable<IndexedValue<Char>> {
//    return IndexingIterable { iterator() }
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun <T> Array<out T>.distinct(): List<T> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun ByteBuffer.distinct(): List<Byte> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun ShortBuffer.distinct(): List<Short> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun IntBuffer.distinct(): List<Int> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun LongBuffer.distinct(): List<Long> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun FloatBuffer.distinct(): List<Float> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun DoubleBuffer.distinct(): List<Double> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun DELETE.distinct(): List<Boolean> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only distinct elements from the given buffer.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//fun CharBuffer.distinct(): List<Char> {
//    return this.toMutableSet().toList()
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <T, K> Array<out T>.distinctBy(selector: (T) -> K): List<T> {
//    val set = HashSet<K>()
//    val list = ArrayList<T>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> ByteBuffer.distinctBy(selector: (Byte) -> K): List<Byte> {
//    val set = HashSet<K>()
//    val list = ArrayList<Byte>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> ShortBuffer.distinctBy(selector: (Short) -> K): List<Short> {
//    val set = HashSet<K>()
//    val list = ArrayList<Short>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> IntBuffer.distinctBy(selector: (Int) -> K): List<Int> {
//    val set = HashSet<K>()
//    val list = ArrayList<Int>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> LongBuffer.distinctBy(selector: (Long) -> K): List<Long> {
//    val set = HashSet<K>()
//    val list = ArrayList<Long>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> FloatBuffer.distinctBy(selector: (Float) -> K): List<Float> {
//    val set = HashSet<K>()
//    val list = ArrayList<Float>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> DoubleBuffer.distinctBy(selector: (Double) -> K): List<Double> {
//    val set = HashSet<K>()
//    val list = ArrayList<Double>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> DELETE.distinctBy(selector: (Boolean) -> K): List<Boolean> {
//    val set = HashSet<K>()
//    val list = ArrayList<Boolean>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a list containing only elements from the given buffer
// * having distinct keys returned by the given [selector] function.
// *
// * The elements in the resulting list are in the same order as they were in the source buffer.
// */
//inline fun <K> CharBuffer.distinctBy(selector: (Char) -> K): List<Char> {
//    val set = HashSet<K>()
//    val list = ArrayList<Char>()
//    for (e in this) {
//        val key = selector(e)
//        if (set.add(key))
//            list.add(e)
//    }
//    return list
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun <T> Array<out T>.intersect(other: Iterable<T>): Set<T> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun ByteBuffer.intersect(other: Iterable<Byte>): Set<Byte> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun ShortBuffer.intersect(other: Iterable<Short>): Set<Short> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun IntBuffer.intersect(other: Iterable<Int>): Set<Int> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun LongBuffer.intersect(other: Iterable<Long>): Set<Long> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun FloatBuffer.intersect(other: Iterable<Float>): Set<Float> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun DoubleBuffer.intersect(other: Iterable<Double>): Set<Double> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun DELETE.intersect(other: Iterable<Boolean>): Set<Boolean> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by both this set and the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun CharBuffer.intersect(other: Iterable<Char>): Set<Char> {
//    val set = this.toMutableSet()
//    set.retainAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun <T> Array<out T>.subtract(other: Iterable<T>): Set<T> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun ByteBuffer.subtract(other: Iterable<Byte>): Set<Byte> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun ShortBuffer.subtract(other: Iterable<Short>): Set<Short> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun IntBuffer.subtract(other: Iterable<Int>): Set<Int> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun LongBuffer.subtract(other: Iterable<Long>): Set<Long> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun FloatBuffer.subtract(other: Iterable<Float>): Set<Float> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun DoubleBuffer.subtract(other: Iterable<Double>): Set<Double> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun DELETE.subtract(other: Iterable<Boolean>): Set<Boolean> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}
//
///**
// * Returns a set containing all elements that are contained by this buffer and not contained by the specified collection.
// *
// * The returned set preserves the element iteration order of the original buffer.
// */
//infix fun CharBuffer.subtract(other: Iterable<Char>): Set<Char> {
//    val set = this.toMutableSet()
//    set.removeAll(other)
//    return set
//}


/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun ByteBuffer.toMutableSet(): MutableSet<Byte> {
    val set = LinkedHashSet<Byte>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun ShortBuffer.toMutableSet(): MutableSet<Short> {
    val set = LinkedHashSet<Short>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun IntBuffer.toMutableSet(): MutableSet<Int> {
    val set = LinkedHashSet<Int>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun LongBuffer.toMutableSet(): MutableSet<Long> {
    val set = LinkedHashSet<Long>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun FloatBuffer.toMutableSet(): MutableSet<Float> {
    val set = LinkedHashSet<Float>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun DoubleBuffer.toMutableSet(): MutableSet<Double> {
    val set = LinkedHashSet<Double>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/** Returns a mutable set containing all distinct elements from the given buffer.
 *  The returned set preserves the element iteration order of the original buffer. */
fun CharBuffer.toMutableSet(): MutableSet<Char> {
    val set = LinkedHashSet<Char>(maps.mapCapacity(size))
    for (item in this) set += item
    return set
}

/**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun <T> Array<out T>.union(other: Iterable<T>): Set<T> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun ByteBuffer.union(other: Iterable<Byte>): Set<Byte> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun ShortBuffer.union(other: Iterable<Short>): Set<Short> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun IntBuffer.union(other: Iterable<Int>): Set<Int> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun LongBuffer.union(other: Iterable<Long>): Set<Long> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun FloatBuffer.union(other: Iterable<Float>): Set<Float> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun DoubleBuffer.union(other: Iterable<Double>): Set<Double> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun DELETE.union(other: Iterable<Boolean>): Set<Boolean> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}
//
///**
// * Returns a set containing all distinct elements from both collections.
// *
// * The returned set preserves the element iteration order of the original buffer.
// * Those elements of the [other] collection that are unique are iterated in the end
// * in the order of the [other] collection.
// */
//infix fun CharBuffer.union(other: Iterable<Char>): Set<Char> {
//    val set = this.toMutableSet()
//    set.addAll(other)
//    return set
//}


/** Returns `true` if all elements match the given [predicate]. */
inline fun ByteBuffer.all(predicate: (Byte) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun ShortBuffer.all(predicate: (Short) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun IntBuffer.all(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun LongBuffer.all(predicate: (Long) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun FloatBuffer.all(predicate: (Float) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun DoubleBuffer.all(predicate: (Double) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

/** Returns `true` if all elements match the given [predicate]. */
inline fun CharBuffer.all(predicate: (Char) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}


/** Returns `true` if buffer has at least one element. */
fun ByteBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun ShortBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun IntBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun LongBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun FloatBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun DoubleBuffer.any() = !isEmpty()

/** Returns `true` if buffer has at least one element. */
fun CharBuffer.any() = !isEmpty()


/** Returns `true` if at least one element matches the given [predicate]. */
inline fun ByteBuffer.any(predicate: (Byte) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun ShortBuffer.any(predicate: (Short) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun IntBuffer.any(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun LongBuffer.any(predicate: (Long) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun FloatBuffer.any(predicate: (Float) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun DoubleBuffer.any(predicate: (Double) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/** Returns `true` if at least one element matches the given [predicate]. */
inline fun CharBuffer.any(predicate: (Char) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}


/** Returns the number of elements in this buffer. */
inline fun ByteBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun ShortBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun IntBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun LongBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun FloatBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun DoubleBuffer.count() = capacity

/** Returns the number of elements in this buffer. */
inline fun CharBuffer.count() = capacity


/** Returns the number of elements matching the given [predicate]. */
inline fun ByteBuffer.count(predicate: (Byte) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun ShortBuffer.count(predicate: (Short) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun IntBuffer.count(predicate: (Int) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun LongBuffer.count(predicate: (Long) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun FloatBuffer.count(predicate: (Float) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun DoubleBuffer.count(predicate: (Double) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}

/** Returns the number of elements matching the given [predicate]. */
inline fun CharBuffer.count(predicate: (Char) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
}


///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <T, R> Array<out T>.fold(initial: R, operation: (acc: R, T) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> ByteBuffer.fold(initial: R, operation: (acc: R, Byte) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> ShortBuffer.fold(initial: R, operation: (acc: R, Short) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> IntBuffer.fold(initial: R, operation: (acc: R, Int) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> LongBuffer.fold(initial: R, operation: (acc: R, Long) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> FloatBuffer.fold(initial: R, operation: (acc: R, Float) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> DoubleBuffer.fold(initial: R, operation: (acc: R, Double) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> DELETE.fold(initial: R, operation: (acc: R, Boolean) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <R> CharBuffer.fold(initial: R, operation: (acc: R, Char) -> R): R {
//    var accumulator = initial
//    for (element in this) accumulator = operation(accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <T, R> Array<out T>.foldIndexed(initial: R, operation: (index: Int, acc: R, T) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> ByteBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Byte) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> ShortBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Short) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> IntBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Int) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> LongBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Long) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> FloatBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Float) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> DoubleBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Double) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> DELETE.foldIndexed(initial: R, operation: (index: Int, acc: R, Boolean) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself, and calculates the next accumulator value.
// */
//inline fun <R> CharBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Char) -> R): R {
//    var index = 0
//    var accumulator = initial
//    for (element in this) accumulator = operation(index++, accumulator, element)
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <T, R> Array<out T>.foldRight(initial: R, operation: (T, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> ByteBuffer.foldRight(initial: R, operation: (Byte, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> ShortBuffer.foldRight(initial: R, operation: (Short, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> IntBuffer.foldRight(initial: R, operation: (Int, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> LongBuffer.foldRight(initial: R, operation: (Long, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> FloatBuffer.foldRight(initial: R, operation: (Float, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> DoubleBuffer.foldRight(initial: R, operation: (Double, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> DELETE.foldRight(initial: R, operation: (Boolean, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <R> CharBuffer.foldRight(initial: R, operation: (Char, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <T, R> Array<out T>.foldRightIndexed(initial: R, operation: (index: Int, T, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> ByteBuffer.foldRightIndexed(initial: R, operation: (index: Int, Byte, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> ShortBuffer.foldRightIndexed(initial: R, operation: (index: Int, Short, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> IntBuffer.foldRightIndexed(initial: R, operation: (index: Int, Int, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> LongBuffer.foldRightIndexed(initial: R, operation: (index: Int, Long, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> FloatBuffer.foldRightIndexed(initial: R, operation: (index: Int, Float, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> DoubleBuffer.foldRightIndexed(initial: R, operation: (index: Int, Double, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> DELETE.foldRightIndexed(initial: R, operation: (index: Int, Boolean, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with [initial] value and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <R> CharBuffer.foldRightIndexed(initial: R, operation: (index: Int, Char, acc: R) -> R): R {
//    var index = lastIndex
//    var accumulator = initial
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}


/** Performs the given [action] on each element. */
inline fun ByteBuffer.forEach(action: (Byte) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun ShortBuffer.forEach(action: (Short) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun IntBuffer.forEach(action: (Int) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun LongBuffer.forEach(action: (Long) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun FloatBuffer.forEach(action: (Float) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun DoubleBuffer.forEach(action: (Double) -> Unit) {
    for (element in this) action(element)
}

/** Performs the given [action] on each element. */
inline fun CharBuffer.forEach(action: (Char) -> Unit) {
    for (element in this) action(element)
}


/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun <T> Array<out T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun ByteBuffer.forEachIndexed(action: (index: Int, Byte) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun ShortBuffer.forEachIndexed(action: (index: Int, Short) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun IntBuffer.forEachIndexed(action: (index: Int, Int) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun LongBuffer.forEachIndexed(action: (index: Int, Long) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun FloatBuffer.forEachIndexed(action: (index: Int, Float) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun DoubleBuffer.forEachIndexed(action: (index: Int, Double) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

/** Performs the given [action] on each element, providing sequential index with the element.
 *  @param [action] function that takes the index of an element and the element itself and performs the desired action on the element. */
inline fun CharBuffer.forEachIndexed(action: (index: Int, Char) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}


/** Returns the largest element or `null` if there are no elements. */
fun ByteBuffer.max(): Byte? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. */
fun ShortBuffer.max(): Short? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. */
fun IntBuffer.max(): Int? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. */
fun LongBuffer.max(): Long? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. If any of elements is `NaN` returns `NaN`. */
fun FloatBuffer.max(): Float? {
    if (isEmpty()) return null
    var max = this[0]
    if (max.isNaN()) return max
    for (i in 1..lastIndex) {
        val e = this[i]
        if (e.isNaN()) return e
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. If any of elements is `NaN` returns `NaN`. */
fun DoubleBuffer.max(): Double? {
    if (isEmpty()) return null
    var max = this[0]
    if (max.isNaN()) return max
    for (i in 1..lastIndex) {
        val e = this[i]
        if (e.isNaN()) return e
        if (max < e) max = e
    }
    return max
}

/** Returns the largest element or `null` if there are no elements. */
fun CharBuffer.max(): Char? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}


/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> ByteBuffer.maxBy(selector: (Byte) -> R): Byte? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> ShortBuffer.maxBy(selector: (Short) -> R): Short? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> IntBuffer.maxBy(selector: (Int) -> R): Int? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> LongBuffer.maxBy(selector: (Long) -> R): Long? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> FloatBuffer.maxBy(selector: (Float) -> R): Float? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> DoubleBuffer.maxBy(selector: (Double) -> R): Double? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

/** Returns the first element yielding the largest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> CharBuffer.maxBy(selector: (Char) -> R): Char? {
    if (isEmpty()) return null
    var maxElem = this[0]
    var maxValue = selector(maxElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (maxValue < v) {
            maxElem = e
            maxValue = v
        }
    }
    return maxElem
}

///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun <T> Array<out T>.maxWith(comparator: Comparator<in T>): T? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun ByteBuffer.maxWith(comparator: Comparator<in Byte>): Byte? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun ShortBuffer.maxWith(comparator: Comparator<in Short>): Short? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun IntBuffer.maxWith(comparator: Comparator<in Int>): Int? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun LongBuffer.maxWith(comparator: Comparator<in Long>): Long? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun FloatBuffer.maxWith(comparator: Comparator<in Float>): Float? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun DoubleBuffer.maxWith(comparator: Comparator<in Double>): Double? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun DELETE.maxWith(comparator: Comparator<in Boolean>): Boolean? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}
//
///**
// * Returns the first element having the largest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun CharBuffer.maxWith(comparator: Comparator<in Char>): Char? {
//    if (isEmpty()) return null
//    var max = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(max, e) < 0) max = e
//    }
//    return max
//}


/** Returns the smallest element or `null` if there are no elements. */
fun ByteBuffer.min(): Byte? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. */
fun ShortBuffer.min(): Short? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. */
fun IntBuffer.min(): Int? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. */
fun LongBuffer.min(): Long? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. If any of elements is `NaN` returns `NaN`. */
fun FloatBuffer.min(): Float? {
    if (isEmpty()) return null
    var min = this[0]
    if (min.isNaN()) return min
    for (i in 1..lastIndex) {
        val e = this[i]
        if (e.isNaN()) return e
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. If any of elements is `NaN` returns `NaN`. */
fun DoubleBuffer.min(): Double? {
    if (isEmpty()) return null
    var min = this[0]
    if (min.isNaN()) return min
    for (i in 1..lastIndex) {
        val e = this[i]
        if (e.isNaN()) return e
        if (min > e) min = e
    }
    return min
}

/** Returns the smallest element or `null` if there are no elements. */
fun CharBuffer.min(): Char? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}


/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> ByteBuffer.minBy(selector: (Byte) -> R): Byte? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> ShortBuffer.minBy(selector: (Short) -> R): Short? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> IntBuffer.minBy(selector: (Int) -> R): Int? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> LongBuffer.minBy(selector: (Long) -> R): Long? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> FloatBuffer.minBy(selector: (Float) -> R): Float? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> DoubleBuffer.minBy(selector: (Double) -> R): Double? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}

/** Returns the first element yielding the smallest value of the given function or `null` if there are no elements. */
inline fun <R : Comparable<R>> CharBuffer.minBy(selector: (Char) -> R): Char? {
    if (isEmpty()) return null
    var minElem = this[0]
    var minValue = selector(minElem)
    for (i in 1..lastIndex) {
        val e = this[i]
        val v = selector(e)
        if (minValue > v) {
            minElem = e
            minValue = v
        }
    }
    return minElem
}


///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun <T> Array<out T>.minWith(comparator: Comparator<in T>): T? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun ByteBuffer.minWith(comparator: Comparator<in Byte>): Byte? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun ShortBuffer.minWith(comparator: Comparator<in Short>): Short? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun IntBuffer.minWith(comparator: Comparator<in Int>): Int? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun LongBuffer.minWith(comparator: Comparator<in Long>): Long? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun FloatBuffer.minWith(comparator: Comparator<in Float>): Float? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun DoubleBuffer.minWith(comparator: Comparator<in Double>): Double? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun DELETE.minWith(comparator: Comparator<in Boolean>): Boolean? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}
//
///**
// * Returns the first element having the smallest value according to the provided [comparator] or `null` if there are no elements.
// */
//fun CharBuffer.minWith(comparator: Comparator<in Char>): Char? {
//    if (isEmpty()) return null
//    var min = this[0]
//    for (i in 1..lastIndex) {
//        val e = this[i]
//        if (comparator.compare(min, e) > 0) min = e
//    }
//    return min
//}


/** Returns `true` if the buffer has no elements. */
fun ByteBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun ShortBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun IntBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun LongBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun FloatBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun DoubleBuffer.none() = isEmpty()

/** Returns `true` if the buffer has no elements. */
fun CharBuffer.none() = isEmpty()


/** Returns `true` if no elements match the given [predicate]. */
inline fun ByteBuffer.none(predicate: (Byte) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun ShortBuffer.none(predicate: (Short) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun IntBuffer.none(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun LongBuffer.none(predicate: (Long) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun FloatBuffer.none(predicate: (Float) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun DoubleBuffer.none(predicate: (Double) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

/** Returns `true` if no elements match the given [predicate]. */
inline fun CharBuffer.none(predicate: (Char) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
}

///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun <S, T : S> Array<out T>.reduce(operation: (acc: S, T) -> S): S {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator: S = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun ByteBuffer.reduce(operation: (acc: Byte, Byte) -> Byte): Byte {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun ShortBuffer.reduce(operation: (acc: Short, Short) -> Short): Short {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun IntBuffer.reduce(operation: (acc: Int, Int) -> Int): Int {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun LongBuffer.reduce(operation: (acc: Long, Long) -> Long): Long {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun FloatBuffer.reduce(operation: (acc: Float, Float) -> Float): Float {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun DoubleBuffer.reduce(operation: (acc: Double, Double) -> Double): Double {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun DELETE.reduce(operation: (acc: Boolean, Boolean) -> Boolean): Boolean {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right to current accumulator value and each element.
// */
//inline fun CharBuffer.reduce(operation: (acc: Char, Char) -> Char): Char {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun <S, T : S> Array<out T>.reduceIndexed(operation: (index: Int, acc: S, T) -> S): S {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator: S = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun ByteBuffer.reduceIndexed(operation: (index: Int, acc: Byte, Byte) -> Byte): Byte {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun ShortBuffer.reduceIndexed(operation: (index: Int, acc: Short, Short) -> Short): Short {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun IntBuffer.reduceIndexed(operation: (index: Int, acc: Int, Int) -> Int): Int {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun LongBuffer.reduceIndexed(operation: (index: Int, acc: Long, Long) -> Long): Long {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun FloatBuffer.reduceIndexed(operation: (index: Int, acc: Float, Float) -> Float): Float {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun DoubleBuffer.reduceIndexed(operation: (index: Int, acc: Double, Double) -> Double): Double {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun DELETE.reduceIndexed(operation: (index: Int, acc: Boolean, Boolean) -> Boolean): Boolean {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with the first element and applying [operation] from left to right
// * to current accumulator value and each element with its index in the original buffer.
// * @param [operation] function that takes the index of an element, current accumulator value
// * and the element itself and calculates the next accumulator value.
// */
//inline fun CharBuffer.reduceIndexed(operation: (index: Int, acc: Char, Char) -> Char): Char {
//    if (isEmpty())
//        throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = this[0]
//    for (index in 1..lastIndex) {
//        accumulator = operation(index, accumulator, this[index])
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun <S, T : S> Array<out T>.reduceRight(operation: (T, acc: S) -> S): S {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator: S = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun ByteBuffer.reduceRight(operation: (Byte, acc: Byte) -> Byte): Byte {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun ShortBuffer.reduceRight(operation: (Short, acc: Short) -> Short): Short {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun IntBuffer.reduceRight(operation: (Int, acc: Int) -> Int): Int {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun LongBuffer.reduceRight(operation: (Long, acc: Long) -> Long): Long {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun FloatBuffer.reduceRight(operation: (Float, acc: Float) -> Float): Float {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun DoubleBuffer.reduceRight(operation: (Double, acc: Double) -> Double): Double {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun DELETE.reduceRight(operation: (Boolean, acc: Boolean) -> Boolean): Boolean {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left to each element and current accumulator value.
// */
//inline fun CharBuffer.reduceRight(operation: (Char, acc: Char) -> Char): Char {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(get(index--), accumulator)
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun <S, T : S> Array<out T>.reduceRightIndexed(operation: (index: Int, T, acc: S) -> S): S {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator: S = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun ByteBuffer.reduceRightIndexed(operation: (index: Int, Byte, acc: Byte) -> Byte): Byte {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun ShortBuffer.reduceRightIndexed(operation: (index: Int, Short, acc: Short) -> Short): Short {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun IntBuffer.reduceRightIndexed(operation: (index: Int, Int, acc: Int) -> Int): Int {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun LongBuffer.reduceRightIndexed(operation: (index: Int, Long, acc: Long) -> Long): Long {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun FloatBuffer.reduceRightIndexed(operation: (index: Int, Float, acc: Float) -> Float): Float {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun DoubleBuffer.reduceRightIndexed(operation: (index: Int, Double, acc: Double) -> Double): Double {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun DELETE.reduceRightIndexed(operation: (index: Int, Boolean, acc: Boolean) -> Boolean): Boolean {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}
//
///**
// * Accumulates value starting with last element and applying [operation] from right to left
// * to each element with its index in the original buffer and current accumulator value.
// * @param [operation] function that takes the index of an element, the element itself
// * and current accumulator value, and calculates the next accumulator value.
// */
//inline fun CharBuffer.reduceRightIndexed(operation: (index: Int, Char, acc: Char) -> Char): Char {
//    var index = lastIndex
//    if (index < 0) throw UnsupportedOperationException("Empty buffer can't be reduced.")
//    var accumulator = get(index--)
//    while (index >= 0) {
//        accumulator = operation(index, get(index), accumulator)
//        --index
//    }
//    return accumulator
//}


/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun ByteBuffer.sumBy(selector: (Byte) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun ShortBuffer.sumBy(selector: (Short) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun IntBuffer.sumBy(selector: (Int) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun LongBuffer.sumBy(selector: (Long) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun FloatBuffer.sumBy(selector: (Float) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun DoubleBuffer.sumBy(selector: (Double) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}

/** Returns the sum of all values produced by [selector] function applied to each element in the buffer. */
inline fun CharBuffer.sumBy(selector: (Char) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
}


///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun <T> Array<out T>.sumByDouble(selector: (T) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun ByteBuffer.sumByDouble(selector: (Byte) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun ShortBuffer.sumByDouble(selector: (Short) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun IntBuffer.sumByDouble(selector: (Int) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun LongBuffer.sumByDouble(selector: (Long) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun FloatBuffer.sumByDouble(selector: (Float) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun DoubleBuffer.sumByDouble(selector: (Double) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun DELETE.sumByDouble(selector: (Boolean) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns the sum of all values produced by [selector] function applied to each element in the buffer.
// */
//inline fun CharBuffer.sumByDouble(selector: (Char) -> Double): Double {
//    var sum: Double = 0.0
//    for (element in this) {
//        sum += selector(element)
//    }
//    return sum
//}
//
///**
// * Returns an original collection containing all the non-`null` elements, throwing an [IllegalArgumentException] if there are any `null` elements.
// */
//fun <T : Any> Array<T?>.requireNoNulls(): Array<T> {
//    for (element in this) {
//        if (element == null) {
//            throw IllegalArgumentException("null element found in $this.")
//        }
//    }
//    @Suppress("UNCHECKED_CAST")
//    return this as Array<T>
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun <T> Array<out T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
//    val first = ArrayList<T>()
//    val second = ArrayList<T>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun ByteBuffer.partition(predicate: (Byte) -> Boolean): Pair<List<Byte>, List<Byte>> {
//    val first = ArrayList<Byte>()
//    val second = ArrayList<Byte>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun ShortBuffer.partition(predicate: (Short) -> Boolean): Pair<List<Short>, List<Short>> {
//    val first = ArrayList<Short>()
//    val second = ArrayList<Short>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun IntBuffer.partition(predicate: (Int) -> Boolean): Pair<List<Int>, List<Int>> {
//    val first = ArrayList<Int>()
//    val second = ArrayList<Int>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun LongBuffer.partition(predicate: (Long) -> Boolean): Pair<List<Long>, List<Long>> {
//    val first = ArrayList<Long>()
//    val second = ArrayList<Long>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun FloatBuffer.partition(predicate: (Float) -> Boolean): Pair<List<Float>, List<Float>> {
//    val first = ArrayList<Float>()
//    val second = ArrayList<Float>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun DoubleBuffer.partition(predicate: (Double) -> Boolean): Pair<List<Double>, List<Double>> {
//    val first = ArrayList<Double>()
//    val second = ArrayList<Double>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun DELETE.partition(predicate: (Boolean) -> Boolean): Pair<List<Boolean>, List<Boolean>> {
//    val first = ArrayList<Boolean>()
//    val second = ArrayList<Boolean>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Splits the original buffer into pair of lists,
// * where *first* list contains elements for which [predicate] yielded `true`,
// * while *second* list contains elements for which [predicate] yielded `false`.
// */
//inline fun CharBuffer.partition(predicate: (Char) -> Boolean): Pair<List<Char>, List<Char>> {
//    val first = ArrayList<Char>()
//    val second = ArrayList<Char>()
//    for (element in this) {
//        if (predicate(element)) {
//            first.add(element)
//        } else {
//            second.add(element)
//        }
//    }
//    return Pair(first, second)
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <T, R> Array<out T>.zip(other: Array<out R>): List<Pair<T, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> ByteBuffer.zip(other: Array<out R>): List<Pair<Byte, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> ShortBuffer.zip(other: Array<out R>): List<Pair<Short, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> IntBuffer.zip(other: Array<out R>): List<Pair<Int, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> LongBuffer.zip(other: Array<out R>): List<Pair<Long, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> FloatBuffer.zip(other: Array<out R>): List<Pair<Float, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> DoubleBuffer.zip(other: Array<out R>): List<Pair<Double, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> DELETE.zip(other: Array<out R>): List<Pair<Boolean, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> CharBuffer.zip(other: Array<out R>): List<Pair<Char, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <T, R, V> Array<out T>.zip(other: Array<out R>, transform: (a: T, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> ByteBuffer.zip(other: Array<out R>, transform: (a: Byte, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> ShortBuffer.zip(other: Array<out R>, transform: (a: Short, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> IntBuffer.zip(other: Array<out R>, transform: (a: Int, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> LongBuffer.zip(other: Array<out R>, transform: (a: Long, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> FloatBuffer.zip(other: Array<out R>, transform: (a: Float, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> DoubleBuffer.zip(other: Array<out R>, transform: (a: Double, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> DELETE.zip(other: Array<out R>, transform: (a: Boolean, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> CharBuffer.zip(other: Array<out R>, transform: (a: Char, b: R) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <T, R> Array<out T>.zip(other: Iterable<R>): List<Pair<T, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> ByteBuffer.zip(other: Iterable<R>): List<Pair<Byte, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> ShortBuffer.zip(other: Iterable<R>): List<Pair<Short, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> IntBuffer.zip(other: Iterable<R>): List<Pair<Int, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> LongBuffer.zip(other: Iterable<R>): List<Pair<Long, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> FloatBuffer.zip(other: Iterable<R>): List<Pair<Float, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> DoubleBuffer.zip(other: Iterable<R>): List<Pair<Double, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> DELETE.zip(other: Iterable<R>): List<Pair<Boolean, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun <R> CharBuffer.zip(other: Iterable<R>): List<Pair<Char, R>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <T, R, V> Array<out T>.zip(other: Iterable<R>, transform: (a: T, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> ByteBuffer.zip(other: Iterable<R>, transform: (a: Byte, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> ShortBuffer.zip(other: Iterable<R>, transform: (a: Short, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> IntBuffer.zip(other: Iterable<R>, transform: (a: Int, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> LongBuffer.zip(other: Iterable<R>, transform: (a: Long, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> FloatBuffer.zip(other: Iterable<R>, transform: (a: Float, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> DoubleBuffer.zip(other: Iterable<R>, transform: (a: Double, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> DELETE.zip(other: Iterable<R>, transform: (a: Boolean, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <R, V> CharBuffer.zip(other: Iterable<R>, transform: (a: Char, b: R) -> V): List<V> {
//    val bufferSize = size
//    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), bufferSize))
//    var i = 0
//    for (element in other) {
//        if (i >= bufferSize) break
//        list.add(transform(this[i++], element))
//    }
//    return list
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun ByteBuffer.zip(other: ByteBuffer): List<Pair<Byte, Byte>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun ShortBuffer.zip(other: ShortBuffer): List<Pair<Short, Short>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun IntBuffer.zip(other: IntBuffer): List<Pair<Int, Int>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun LongBuffer.zip(other: LongBuffer): List<Pair<Long, Long>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun FloatBuffer.zip(other: FloatBuffer): List<Pair<Float, Float>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun DoubleBuffer.zip(other: DoubleBuffer): List<Pair<Double, Double>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun DELETE.zip(other: DELETE): List<Pair<Boolean, Boolean>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of pairs built from elements of both collections with same indexes. List has length of shortest collection.
// */
//infix fun CharBuffer.zip(other: CharBuffer): List<Pair<Char, Char>> {
//    return zip(other) { t1, t2 -> t1 to t2 }
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> ByteBuffer.zip(other: ByteBuffer, transform: (a: Byte, b: Byte) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> ShortBuffer.zip(other: ShortBuffer, transform: (a: Short, b: Short) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> IntBuffer.zip(other: IntBuffer, transform: (a: Int, b: Int) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> LongBuffer.zip(other: LongBuffer, transform: (a: Long, b: Long) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> FloatBuffer.zip(other: FloatBuffer, transform: (a: Float, b: Float) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> DoubleBuffer.zip(other: DoubleBuffer, transform: (a: Double, b: Double) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> DELETE.zip(other: DELETE, transform: (a: Boolean, b: Boolean) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Returns a list of values built from elements of both collections with same indexes using provided [transform]. List has length of shortest collection.
// */
//inline fun <V> CharBuffer.zip(other: CharBuffer, transform: (a: Char, b: Char) -> V): List<V> {
//    val size = minOf(size, other.size)
//    val list = ArrayList<V>(size)
//    for (i in 0 until size) {
//        list.add(transform(this[i], other[i]))
//    }
//    return list
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <T, A : Appendable> Array<out T>.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            buffer.appendElement(element, transform)
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> ByteBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Byte) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> ShortBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Short) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> IntBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> LongBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Long) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> FloatBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Float) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> DoubleBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Double) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> DELETE.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Boolean) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element.toString())
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Appends the string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <A : Appendable> CharBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Char) -> CharSequence)? = null): A {
//    buffer.append(prefix)
//    var count = 0
//    for (element in this) {
//        if (++count > 1) buffer.append(separator)
//        if (limit < 0 || count <= limit) {
//            if (transform != null)
//                buffer.append(transform(element))
//            else
//                buffer.append(element)
//        } else break
//    }
//    if (limit >= 0 && count > limit) buffer.append(truncated)
//    buffer.append(postfix)
//    return buffer
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun <T> Array<out T>.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((T) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun ByteBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Byte) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun ShortBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Short) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun IntBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun LongBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Long) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun FloatBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Float) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun DoubleBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Double) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun DELETE.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Boolean) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}
//
///**
// * Creates a string from all the elements separated using [separator] and using the given [prefix] and [postfix] if supplied.
// *
// * If the collection could be huge, you can specify a non-negative value of [limit], in which case only the first [limit]
// * elements will be appended, followed by the [truncated] string (which defaults to "...").
// */
//fun CharBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", transform: ((Char) -> CharSequence)? = null): String {
//    return joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()
//}


/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun ByteBuffer.asIterable(): Iterable<Byte> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun ShortBuffer.asIterable(): Iterable<Short> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun IntBuffer.asIterable(): Iterable<Int> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun LongBuffer.asIterable(): Iterable<Long> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun FloatBuffer.asIterable(): Iterable<Float> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun DoubleBuffer.asIterable(): Iterable<Double> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}

/** Creates an [Iterable] instance that wraps the original buffer returning its elements when being iterated. */
fun CharBuffer.asIterable(): Iterable<Char> {
    if (isEmpty()) return emptyList()
    return Iterable { iterator() }
}


/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun ByteBuffer.asSequence(): Sequence<Byte> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun ShortBuffer.asSequence(): Sequence<Short> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun IntBuffer.asSequence(): Sequence<Int> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun LongBuffer.asSequence(): Sequence<Long> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun FloatBuffer.asSequence(): Sequence<Float> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun DoubleBuffer.asSequence(): Sequence<Double> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}

/** Creates a [Sequence] instance that wraps the original buffer returning its elements when being iterated.
 *  @sample samples.collections.Sequences.Building.sequenceFromArray */
fun CharBuffer.asSequence(): Sequence<Char> {
    if (isEmpty()) return emptySequence()
    return Sequence { iterator() }
}


/** Returns an average value of elements in the buffer. */
fun ByteBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns an average value of elements in the buffer. */
fun ShortBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns an average value of elements in the buffer. */
fun IntBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns an average value of elements in the buffer. */
fun LongBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns an average value of elements in the buffer. */
fun FloatBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns an average value of elements in the buffer. */
fun DoubleBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

/** Returns the sum of all elements in the buffer. */
fun ByteBuffer.sum(): Int {
    var sum = 0
    for (element in this) sum += element
    return sum
}

/** Returns the sum of all elements in the buffer. */
fun ShortBuffer.sum(): Int {
    var sum = 0
    for (element in this) sum += element
    return sum
}

/** Returns the sum of all elements in the buffer. */
fun IntBuffer.sum(): Int {
    var sum = 0
    for (element in this) sum += element
    return sum
}

/** Returns the sum of all elements in the buffer. */
fun LongBuffer.sum(): Long {
    var sum = 0L
    for (element in this) sum += element
    return sum
}

/** Returns the sum of all elements in the buffer. */
fun FloatBuffer.sum(): Float {
    var sum = 0f
    for (element in this) sum += element
    return sum
}

/** Returns the sum of all elements in the buffer. */
fun DoubleBuffer.sum(): Double {
    var sum = 0.0
    for (element in this) sum += element
    return sum
}