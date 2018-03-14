package uno.kotlin.buffers

import glm_.*
import org.lwjgl.system.MemoryUtil.*
import uno.buffer.intBufferOf
import uno.kotlin.plusAssign
import java.nio.IntBuffer




inline fun IntBuffer.single(): Int {
    return when (capacity) {
        0 -> throw NoSuchElementException("Array is empty.")
        1 -> this[0]
        else -> throw IllegalArgumentException("Array has more than one element.")
    }
}

inline fun IntBuffer.single(predicate: (Int) -> Boolean): Int {
    var single: Int? = null
    var found = false
    for (element in this)
        if (predicate(element)) {
            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
            single = element
            found = true
        }
    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
    return single as Int
}

inline fun IntBuffer.singleOrNull() = if (capacity == 1) this[0] else null
inline fun IntBuffer.singleOrNull(predicate: (Int) -> Boolean): Int? {
    var single: Int? = null
    var found = false
    for (element in this)
        if (predicate(element)) {
            if (found) return null
            single = element
            found = true
        }
    if (!found) return null
    return single
}



inline fun IntBuffer.dropLast(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return take((capacity - n).coerceAtLeast(0))
}

inline fun IntBuffer.dropLastWhile(predicate: (Int) -> Boolean): List<Int> {
    for (index in lastIndex downTo 0)
        if (!predicate(this[index]))
            return take(index + 1)
    return emptyList()
}

inline fun IntBuffer.dropWhile(predicate: (Int) -> Boolean): List<Int> {
    var yielding = false
    val list = ArrayList<Int>()
    for (item in this)
        if (yielding) list += item
        else if (!predicate(item)) {
            list += item
            yielding = true
        }
    return list
}

inline fun IntBuffer.filter(predicate: (Int) -> Boolean) = filterTo(ArrayList(), predicate)
inline fun IntBuffer.filterIndexed(predicate: (index: Int, Int) -> Boolean) = filterIndexedTo(ArrayList(), predicate)
inline fun <C : MutableCollection<in Int>> IntBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Int) -> Boolean): C {
    forEachIndexed { index, element ->
        if (predicate(index, element)) destination += element
    }
    return destination
}

inline fun IntBuffer.filterNot(predicate: (Int) -> Boolean) = filterNotTo(ArrayList<Int>(), predicate)
inline fun <C : MutableCollection<in Int>> IntBuffer.filterNotTo(destination: C, predicate: (Int) -> Boolean): C {
    for (element in this) if (!predicate(element)) destination += element
    return destination
}

inline fun <C : MutableCollection<in Int>> IntBuffer.filterTo(destination: C, predicate: (Int) -> Boolean): C {
    for (element in this) if (predicate(element)) destination += element
    return destination
}

inline fun IntBuffer.slice(indices: IntRange): List<Int> {
    if (indices.isEmpty()) return listOf()
    return copyOfRange(indices.start, indices.endInclusive + 1).asList()
}

inline fun IntBuffer.slice(indices: Iterable<Int>): List<Int> {
    val size = indices.collectionSizeOrDefault(10)
    if (size == 0) return emptyList()
    val list = ArrayList<Int>(size)
    for (index in indices) list += get(index)
    return list
}

inline fun IntBuffer.sliceArray(indices: Collection<Int>): IntArray {
    val result = IntArray(indices.size)
    var targetIndex = 0
    for (sourceIndex in indices)
        result[targetIndex++] = this[sourceIndex]
    return result
}

inline fun IntBuffer.sliceArray(indices: IntRange): IntArray {
    if (indices.isEmpty()) return IntArray(0)
    return IntArray(indices.last - indices.first + 1, { this[it] })
}

inline fun IntBuffer.take(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    if (n >= capacity) return toList()
    if (n == 1) return listOf(this[0])
    var count = 0
    val list = ArrayList<Int>(n)
    for (item in this) {
        if (count++ == n) break
        list += item
    }
    return list
}

inline fun IntBuffer.reversedBuffer(): IntBuffer {
    if (isEmpty()) return this
    val result = IntArray(capacity)
    val lastIndex = lastIndex
    for (i in 0..lastIndex)
        result[lastIndex - i] = this[i]
    return intBufferOf(*result)
}

inline fun <R : Comparable<R>> IntBuffer.sortBy(crossinline selector: (Int) -> R?) {
    if (capacity > 1) sortWith(compareBy(selector))
}

inline fun <R : Comparable<R>> IntBuffer.sortByDescending(crossinline selector: (Int) -> R?) {
    if (capacity > 1) sortWith(compareByDescending(selector))
}

inline fun IntBuffer.sortDescending() {
    if (capacity > 1) {
        sort()
        reverse()
    }
}

inline fun IntBuffer.sorted() = toTypedArray().apply { sort() }.asList()
inline fun IntBuffer.sortedArray(): IntArray {
    if (isEmpty()) return IntArray(0)
    return copyOf().apply { sort() }.toIntArray()
}

inline fun IntBuffer.sortedArrayDescending(): IntArray {
    if (isEmpty()) return IntArray(0)
    return copyOf().apply { sortDescending() }.toIntArray()
}

inline fun <R : Comparable<R>> IntBuffer.sortedBy(crossinline selector: (Int) -> R?) = sortedWith(compareBy(selector))
inline fun <R : Comparable<R>> IntBuffer.sortedByDescending(crossinline selector: (Int) -> R?) = sortedWith(compareByDescending(selector))
inline fun IntBuffer.sortedDescending() = copyOf().apply { sort() }.reversed()
inline fun IntBuffer.sortedWith(comparator: Comparator<Int>) = toTypedArray().apply { sortWith(comparator) }.asList()
// no contentDeepEquals, contentDeepHashCode, contentDeepToString
inline infix fun IntBuffer.contentEquals(other: IntBuffer?): Boolean {
    if (this === other) return true
    if (other === null) return false
    val length = capacity
    if (other.capacity != length) return false
    for (i in 0 until length)
        if (this[i] != other[i])
            return false
    return true
}

inline fun IntBuffer.contentHashCode(): Int {
    var result = 1
    for (element in this) result = 31 * result + element
    return result
}

inline fun IntBuffer.contentToString(): String {
    var iMax = capacity - 1
    if (iMax == -1) return "[]"
    val builder = StringBuilder()
    builder += '['
    var i = 0
    while (true) {
        builder += this[i]
        if (i == iMax) return builder.append(']').toString()
        builder += ", "
        i++
    }
}



inline fun IntBuffer.toBooleanArray() = BooleanArray(capacity, { this[it].bool })
inline fun IntBuffer.toByteArray() = ByteArray(capacity, { this[it].toByte() })
inline fun IntBuffer.toCharArray() = CharArray(capacity, { this[it].toChar() })
inline fun IntBuffer.toDoubleArray() = DoubleArray(capacity, { this[it].toDouble() })
inline fun IntBuffer.toFloatArray() = FloatArray(capacity, { this[it].toFloat() })


inline fun IntBuffer.toLongArray() = LongArray(capacity, { this[it].toLong() })
inline fun IntBuffer.toShortArray() = ShortArray(capacity, { this[it].toShort() })
// TODO unsigned?

inline fun <K, V> IntBuffer.associate(transform: (Int) -> Pair<K, V>): Map<K, V> {
    val capacity = maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateTo(LinkedHashMap(capacity), transform)
}

inline fun <K> IntBuffer.associateBy(keySelector: (Int) -> K): Map<K, Int> {
    val capacity = maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateByTo(LinkedHashMap(capacity), keySelector)
}

inline fun <K, V> IntBuffer.associateBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, V> {
    val capacity = maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateByTo(LinkedHashMap(capacity), keySelector, valueTransform)
}

inline fun <K, M : MutableMap<in K, in Int>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K): M {
    for (element in this) destination[keySelector(element)] = element
    return destination
}

inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
    for (element in this) destination[keySelector(element)] = valueTransform(element)
    return destination
}

inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateTo(destination: M, transform: (Int) -> Pair<K, V>): M {
    for (element in this) destination += transform(element)
    return destination
}

inline fun <R> IntBuffer.flatMap(transform: (Int) -> Iterable<R>) = flatMapTo(ArrayList(), transform)
inline fun <R, C : MutableCollection<in R>> IntBuffer.flatMapTo(destination: C, transform: (Int) -> Iterable<R>): C {
    for (element in this) {
        val list = transform(element)
        destination += list
    }
    return destination
}

inline fun <K> IntBuffer.groupBy(keySelector: (Int) -> K): Map<K, List<Int>> = groupByTo(LinkedHashMap(), keySelector)
inline fun <K, V> IntBuffer.groupBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, List<V>> =
        groupByTo(LinkedHashMap(), keySelector, valueTransform)

inline fun <K, M : MutableMap<in K, MutableList<Int>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList() }
        list += element
    }
    return destination
}

inline fun <K, V, M : MutableMap<in K, MutableList<V>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList() }
        list += valueTransform(element)
    }
    return destination
}

inline fun <R : Any> IntBuffer.mapIndexedNotNull(transform: (index: Int, Int) -> R?): List<R> = mapIndexedNotNullTo(ArrayList(), transform)
inline fun <R : Any, C : MutableCollection<in R>> IntBuffer.mapIndexedNotNullTo(destination: C, transform: (index: Int, Int) -> R?): C {
    forEachIndexed { index, element -> transform(index, element)?.let { destination += it } }
    return destination
}


inline fun <R : Any> IntBuffer.mapNotNull(transform: (Int) -> R?): List<R> = mapNotNullTo(ArrayList<R>(), transform)
inline fun <R : Any, C : MutableCollection<in R>> IntBuffer.mapNotNullTo(destination: C, transform: (Int) -> R?): C {
    forEach { element -> transform(element)?.let { destination.add(it) } }
    return destination
}


inline fun IntBuffer.withIndex(): Iterable<IndexedValue<Int>> = IndexingIterable { iterator() }
inline fun IntBuffer.distinct(): List<Int> = toMutableSet().toList()
inline fun <K> IntBuffer.distinctBy(selector: (Int) -> K): List<Int> {
    val set = HashSet<K>()
    val list = ArrayList<Int>()
    for (e in this) {
        val key = selector(e)
        if (set.add(key)) list += e
    }
    return list
}

infix fun IntBuffer.intersect(other: Iterable<Int>): Set<Int> {
    val set = toMutableSet()
    set.retainAll(other)
    return set
}

infix fun IntBuffer.subtract(other: Iterable<Int>): Set<Int> {
    val set = toMutableSet()
    set.removeAll(other)
    return set
}


infix fun IntBuffer.union(other: Iterable<Int>): Set<Int> {
    val set = toMutableSet()
    set += other
    return set
}


inline fun <R> IntBuffer.fold(initial: R, operation: (acc: R, Int) -> R): R {
    var accumulator = initial
    for (element in this) accumulator = operation(accumulator, element)
    return accumulator
}

inline fun <R> IntBuffer.foldIndexed(initial: R, operation: (index: Int, acc: R, Int) -> R): R {
    var index = 0
    var accumulator = initial
    for (element in this) accumulator = operation(index++, accumulator, element)
    return accumulator
}

inline fun <R> IntBuffer.foldRight(initial: R, operation: (Int, acc: R) -> R): R {
    var index = lastIndex
    var accumulator = initial
    while (index >= 0) accumulator = operation(get(index--), accumulator)
    return accumulator
}

inline fun <R> IntBuffer.foldRightIndexed(initial: R, operation: (index: Int, Int, acc: R) -> R): R {
    var index = lastIndex
    var accumulator = initial
    while (index >= 0) {
        accumulator = operation(index, get(index), accumulator)
        --index
    }
    return accumulator
}


inline fun IntBuffer.maxWith(comparator: Comparator<in Int>): Int? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (comparator.compare(max, e) < 0) max = e
    }
    return max
}


inline fun IntBuffer.minWith(comparator: Comparator<in Int>): Int? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (comparator.compare(min, e) > 0) min = e
    }
    return min
}


inline fun IntBuffer.reduce(operation: (acc: Int, Int) -> Int): Int {
    if (isEmpty()) throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator = this[0]
    for (index in 1..lastIndex) accumulator = operation(accumulator, this[index])
    return accumulator
}

inline fun IntBuffer.reduceIndexed(operation: (index: Int, acc: Int, Int) -> Int): Int {
    if (isEmpty()) throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator = this[0]
    for (index in 1..lastIndex) accumulator = operation(index, accumulator, this[index])
    return accumulator
}

inline fun IntBuffer.reduceRight(operation: (Int, acc: Int) -> Int): Int {
    var index = lastIndex
    if (index < 0) throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator = get(index--)
    while (index >= 0) accumulator = operation(get(index--), accumulator)
    return accumulator
}

inline fun IntBuffer.reduceRightIndexed(operation: (index: Int, Int, acc: Int) -> Int): Int {
    var index = lastIndex
    if (index < 0) throw UnsupportedOperationException("Empty array can't be reduced.")
    var accumulator = get(index--)
    while (index >= 0) {
        accumulator = operation(index, get(index), accumulator)
        --index
    }
    return accumulator
}

inline fun IntBuffer.sumByDouble(selector: (Int) -> Double): Double {
    var sum = 0.0
    for (element in this) sum += selector(element)
    return sum
}

// no requireNoNulls
inline fun IntBuffer.partition(predicate: (Int) -> Boolean): Pair<List<Int>, List<Int>> {
    val first = ArrayList<Int>()
    val second = ArrayList<Int>()
    for (element in this)
        if (predicate(element)) first += element
        else second += element
    return Pair(first, second)
}

infix fun <R> IntBuffer.zip(other: Iterable<R>): List<Pair<Int, R>> = zip(other) { t1, t2 -> t1 to t2 }
inline fun <R, V> IntBuffer.zip(other: Iterable<R>, transform: (a: Int, b: R) -> V): List<V> {
    val arraySize = capacity
    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), arraySize))
    var i = 0
    for (element in other) {
        if (i >= arraySize) break
        list += transform(this[i++], element)
    }
    return list
}

infix fun IntBuffer.zip(other: IntBuffer): List<Pair<Int, Int>> = zip(other) { t1, t2 -> t1 to t2 }
inline fun <V> IntBuffer.zip(other: IntBuffer, transform: (a: Int, b: Int) -> V): List<V> {
    val size = minOf(capacity, other.capacity)
    val list = ArrayList<V>(size)
    for (i in 0 until size)
        list += transform(this[i], other[i])
    return list
}

fun <A : Appendable> IntBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1,
                                      truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null): A {
    buffer += prefix
    var count = 0
    for (element in this) {
        if (++count > 1) buffer += separator
        if (limit < 0 || count <= limit) {
            if (transform != null) buffer += transform(element)
            else buffer += element.toString()
        } else break
    }
    if (limit in 0 until count) buffer += truncated
    buffer += postfix
    return buffer
}

fun IntBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1,
                           truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null) =
        joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()



inline fun IntBuffer.copyOf(): IntBuffer {
    val dst = memAllocInt(capacity)
    memCopy(memAddress(this), memAddress(dst), size.L)
    return dst
}

inline fun IntBuffer.copyOf(newSize: Int): IntBuffer {
    val dst = memAllocInt(newSize)
    memCopy(memAddress(this), memAddress(dst), newSize.L)
    return dst
}

/**
 * @param fromIndex the initial index of the range to be copied, inclusive
 * @param toIndex the final index of the range to be copied, exclusive.
 */
inline fun IntBuffer.copyOfRange(fromIndex: Int, toIndex: Int): IntBuffer {
    val count = toIndex - fromIndex
    val dst = memAllocInt(count)
    memCopy(memAddress(this), memAddress(dst), count.L * Int.BYTES)
    return dst
}

//operator fun IntBuffer.plus(element: Int): IntBuffer {
//    val dst = memRealloc(this, capacity + 1)!!
//    dst[capacity] = element
//    return dst
//}

//operator fun IntBuffer.plus(elements: Collection<Int>): IntBuffer {
//    val dst = memRealloc(this, capacity + elements.size)!!
//    for (i in 0 until elements.size) dst[capacity + i] = elements.elementAt(i)
//    return dst
//}

//operator fun IntBuffer.plus(elements: IntBuffer): IntBuffer {
//    val dst = memRealloc(this, capacity + elements.capacity)!!
//    for (i in 0 until elements.size) dst[capacity + i] = elements.elementAt(i)
//    return dst
//}

fun IntBuffer.sort() {
    if (capacity > 1) _intArrays.sort(this)
}

fun IntBuffer.sortWith(comparator: Comparator<Int>) {
    if (capacity > 1) _intArrays.sort(this, comparator)
}


fun IntBuffer.binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = capacity) = _intArrays.binarySearch(this, fromIndex, toIndex, element)


fun IntBuffer.sort(fromIndex: Int = 0, toIndex: Int = capacity) = _intArrays.sort(this, fromIndex, toIndex)

fun IntBuffer.sortWith(comparator: Comparator<Int>, fromIndex: Int = 0, toIndex: Int = capacity) = _intArrays.sort(this, fromIndex, toIndex, comparator)

object _intArrays {

    fun sort(intBuffer: IntBuffer) = DualPivotQuicksort.sort(intBuffer, 0, intBuffer.capacity - 1, null, 0, 0)

    fun sort(a: IntBuffer, c: Comparator<Int>?) {
        if (c == null) sort(a)
        else TimSort.sort(a, 0, a.capacity, c, null, 0, 0)
    }

    fun binarySearch(a: IntBuffer, fromIndex: Int, toIndex: Int, key: Int): Int {
        rangeCheck(a.capacity, fromIndex, toIndex)
        return binarySearch0(a, fromIndex, toIndex, key)
    }

    private fun rangeCheck(arrayLength: Int, fromIndex: Int, toIndex: Int) {
        if (fromIndex > toIndex)
            throw IllegalArgumentException("fromIndex($fromIndex) > toIndex($toIndex)")
        if (fromIndex < 0)
            throw ArrayIndexOutOfBoundsException(fromIndex)
        if (toIndex > arrayLength)
            throw ArrayIndexOutOfBoundsException(toIndex)
    }

    private fun binarySearch0(a: IntBuffer, fromIndex: Int, toIndex: Int, key: Int): Int {
        var low = fromIndex
        var high = toIndex - 1

        while (low <= high) {
            val mid = (low + high) ushr 1
            val midVal = a[mid]

            if (midVal < key)
                low = mid + 1
            else if (midVal > key)
                high = mid - 1
            else
                return mid // key found
        }
        return -(low + 1)  // key not found.
    }

    fun fill(a: IntBuffer, fromIndex: Int, toIndex: Int, value: Int) {
        rangeCheck(a.capacity, fromIndex, toIndex)
        for (i in fromIndex until toIndex) a[i] = value
    }

    fun sort(a: IntBuffer, fromIndex: Int, toIndex: Int) {
        rangeCheck(a.capacity, fromIndex, toIndex)
        DualPivotQuicksort.sort(a, fromIndex, toIndex - 1, null, 0, 0)
    }

    fun sort(a: IntBuffer, fromIndex: Int, toIndex: Int, c: Comparator<Int>?) {
        if (c == null)
            sort(a, fromIndex, toIndex)
        else {
            rangeCheck(a.capacity, fromIndex, toIndex)
            TimSort.sort(a, fromIndex, toIndex, c, null, 0, 0)
        }
    }
}

/** Iterables   */
fun <T> Iterable<T>.collectionSizeOrDefault(default: Int) = if (this is Collection<*>) this.size else default

class IndexingIterable<out T>(private val iteratorFactory: () -> Iterator<T>) : Iterable<IndexedValue<T>> {
    override fun iterator(): Iterator<IndexedValue<T>> = IndexingIterator(iteratorFactory())
}

/** Iterators   */
class IndexingIterator<out T>(private val iterator: Iterator<T>) : Iterator<IndexedValue<T>> {
    private var index = 0
    final override fun hasNext(): Boolean = iterator.hasNext()
    final override fun next(): IndexedValue<T> = IndexedValue(index++, iterator.next())
}

