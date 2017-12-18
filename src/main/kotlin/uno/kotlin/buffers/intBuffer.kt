package uno.kotlin.buffers

import glm_.*
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.*
import uno.buffer.destroy
import uno.buffer.intBufferBig
import uno.buffer.intBufferOf
import java.nio.IntBuffer

inline operator fun IntBuffer.component1() = get(0)
inline operator fun IntBuffer.component2() = get(1)
inline operator fun IntBuffer.component3() = get(2)
inline operator fun IntBuffer.component4() = get(3)
inline operator fun IntBuffer.component5() = get(4)
inline operator fun IntBuffer.contains(element: Int) = indexOf(element) >= 0
inline fun IntBuffer.elementAt(index: Int) = get(index)
inline fun IntBuffer.elementAtOrElse(index: Int, defaultValue: (Int) -> Int) = if (index >= 0 && index <= lastIndex) get(index) else defaultValue(index)
inline fun IntBuffer.elementAtOrNull(index: Int) = getOrNull(index)
inline fun IntBuffer.find(predicate: (Int) -> Boolean) = firstOrNull(predicate)
inline fun IntBuffer.findLast(predicate: (Int) -> Boolean) = lastOrNull(predicate)
inline fun IntBuffer.first(): Int {
    if (isEmpty()) throw NoSuchElementException("Array is empty.")
    return this[0]
}

inline fun IntBuffer.first(predicate: (Int) -> Boolean): Int {
    for (element in this) if (predicate(element)) return element
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

inline fun IntBuffer.firstOrNull() = if (isEmpty()) null else this[0]
inline fun IntBuffer.firstOrNull(predicate: (Int) -> Boolean): Int? {
    for (element in this) if (predicate(element)) return element
    return null
}

inline fun IntBuffer.getOrElse(index: Int, defaultValue: (Int) -> Int) = if (index >= 0 && index <= lastIndex) get(index) else defaultValue(index)
inline fun IntBuffer.getOrNull(index: Int) = if (index >= 0 && index <= lastIndex) get(index) else null
inline fun IntBuffer.indexOf(element: Int): Int {
    for (index in indices) {
        if (element == this[index]) {
            return index
        }
    }
    return -1
}

inline fun IntBuffer.indexOfFirst(predicate: (Int) -> Boolean): Int {
    for (index in indices) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}

inline fun IntBuffer.indexOfLast(predicate: (Int) -> Boolean): Int {
    for (index in indices.reversed()) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}

inline fun IntBuffer.last(): Int {
    if (isEmpty())
        throw NoSuchElementException("Array is empty.")
    return this[lastIndex]
}

inline fun IntBuffer.last(predicate: (Int) -> Boolean): Int {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    throw NoSuchElementException("Array contains no element matching the predicate.")
}

inline fun IntBuffer.lastIndexOf(element: Int): Int {
    for (index in indices.reversed()) {
        if (element == this[index]) {
            return index
        }
    }
    return -1
}

inline fun IntBuffer.lastOrNull() = if (isEmpty()) null else this[capacity - 1]
inline fun IntBuffer.lastOrNull(predicate: (Int) -> Boolean): Int? {
    for (index in this.indices.reversed()) {
        val element = this[index]
        if (predicate(element)) return element
    }
    return null
}

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
    for (element in this) {
        if (predicate(element)) {
            if (found) throw IllegalArgumentException("Array contains more than one matching element.")
            single = element
            found = true
        }
    }
    if (!found) throw NoSuchElementException("Array contains no element matching the predicate.")
    return single as Int
}

inline fun IntBuffer.singleOrNull() = if (capacity == 1) this[0] else null
inline fun IntBuffer.singleOrNull(predicate: (Int) -> Boolean): Int? {
    var single: Int? = null
    var found = false
    for (element in this) {
        if (predicate(element)) {
            if (found) return null
            single = element
            found = true
        }
    }
    if (!found) return null
    return single
}

inline fun IntBuffer.drop(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return takeLast((capacity - n).coerceAtLeast(0))
}

inline fun IntBuffer.dropLast(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    return take((capacity - n).coerceAtLeast(0))
}

inline fun IntBuffer.dropLastWhile(predicate: (Int) -> Boolean): List<Int> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return take(index + 1)
        }
    }
    return emptyList()
}

inline fun IntBuffer.dropWhile(predicate: (Int) -> Boolean): List<Int> {
    var yielding = false
    val list = ArrayList<Int>()
    for (item in this)
        if (yielding)
            list.add(item)
        else if (!predicate(item)) {
            list.add(item)
            yielding = true
        }
    return list
}

inline fun IntBuffer.filter(predicate: (Int) -> Boolean) = filterTo(ArrayList<Int>(), predicate)
inline fun IntBuffer.filterIndexed(predicate: (index: Int, Int) -> Boolean) = filterIndexedTo(ArrayList<Int>(), predicate)
inline fun <C : MutableCollection<in Int>> IntBuffer.filterIndexedTo(destination: C, predicate: (index: Int, Int) -> Boolean): C {
    forEachIndexed { index, element ->
        if (predicate(index, element)) destination.add(element)
    }
    return destination
}

inline fun IntBuffer.filterNot(predicate: (Int) -> Boolean) = filterNotTo(ArrayList<Int>(), predicate)
inline fun <C : MutableCollection<in Int>> IntBuffer.filterNotTo(destination: C, predicate: (Int) -> Boolean): C {
    for (element in this) if (!predicate(element)) destination.add(element)
    return destination
}

inline fun <C : MutableCollection<in Int>> IntBuffer.filterTo(destination: C, predicate: (Int) -> Boolean): C {
    for (element in this) if (predicate(element)) destination.add(element)
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
    for (index in indices) {
        list.add(get(index))
    }
    return list
}

inline fun IntBuffer.sliceArray(indices: Collection<Int>): IntArray {
    val result = IntArray(indices.size)
    var targetIndex = 0
    for (sourceIndex in indices) {
        result[targetIndex++] = this[sourceIndex]
    }
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
        if (count++ == n)
            break
        list.add(item)
    }
    return list
}

inline fun IntBuffer.takeLast(n: Int): List<Int> {
    require(n >= 0) { "Requested element count $n is less than zero." }
    if (n == 0) return emptyList()
    val capacity = capacity
    if (n >= capacity) return toList()
    if (n == 1) return listOf(this[capacity - 1])
    val list = ArrayList<Int>(n)
    for (index in capacity - n..capacity - 1)
        list.add(this[index])
    return list
}

inline fun IntBuffer.takeLastWhile(predicate: (Int) -> Boolean): List<Int> {
    for (index in lastIndex downTo 0) {
        if (!predicate(this[index])) {
            return drop(index + 1)
        }
    }
    return toList()
}

inline fun IntBuffer.takeWhile(predicate: (Int) -> Boolean): List<Int> {
    val list = ArrayList<Int>()
    for (item in this) {
        if (!predicate(item))
            break
        list.add(item)
    }
    return list
}

inline fun IntBuffer.reverse(): Unit {
    val midPoint = (capacity / 2) - 1
    if (midPoint < 0) return
    var reverseIndex = lastIndex
    for (index in 0..midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}

inline fun IntBuffer.reversed(): List<Int> {
    if (isEmpty()) return emptyList()
    val list = toMutableList()
    list.reverse()
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

inline fun <R : Comparable<R>> IntBuffer.sortBy(crossinline selector: (Int) -> R?): Unit {
    if (capacity > 1) sortWith(compareBy(selector))
}

inline fun <R : Comparable<R>> IntBuffer.sortByDescending(crossinline selector: (Int) -> R?): Unit {
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
    return this.copyOf().apply { sort() }.toIntArray()
}

inline fun IntBuffer.sortedArrayDescending(): IntArray {
    if (isEmpty()) return IntArray(0)
    return this.copyOf().apply { sortDescending() }.toIntArray()
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
    for (i in 0 until length) {
        if (this[i] != other[i]) return false
    }
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
    builder.append('[')
    var i = 0
    while (true) {
        builder.append(this[i])
        if (i == iMax) return builder.append(']').toString()
        builder.append(", ")
        i++
    }
}

inline val IntBuffer.indices get() = IntRange(0, lastIndex)
inline fun IntBuffer.isEmpty() = capacity == 0
inline fun IntBuffer.isNotEmpty() = !isEmpty()
inline val IntBuffer.lastIndex get() = capacity - 1
inline fun IntBuffer.toBooleanArray() = BooleanArray(capacity, { this[it].bool })
inline fun IntBuffer.toByteArray() = ByteArray(capacity, { this[it].toByte() })
inline fun IntBuffer.toCharArray() = CharArray(capacity, { this[it].toChar() })
inline fun IntBuffer.toDoubleArray() = DoubleArray(capacity, { this[it].toDouble() })
inline fun IntBuffer.toFloatArray() = FloatArray(capacity, { this[it].toFloat() })
inline fun IntBuffer.toIntArray() = IntArray(capacity, { this[it] })
inline fun IntBuffer.toLongArray() = LongArray(capacity, { this[it].toLong() })
inline fun IntBuffer.toShortArray() = ShortArray(capacity, { this[it].toShort() })
// TODO unsigned?

inline fun <K, V> IntBuffer.associate(transform: (Int) -> Pair<K, V>): Map<K, V> {
    val capacity = Maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateTo(LinkedHashMap<K, V>(capacity), transform)
}

inline fun <K> IntBuffer.associateBy(keySelector: (Int) -> K): Map<K, Int> {
    val capacity = Maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateByTo(LinkedHashMap<K, Int>(capacity), keySelector)
}

inline fun <K, V> IntBuffer.associateBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, V> {
    val capacity = Maps.mapCapacity(capacity).coerceAtLeast(16)
    return associateByTo(LinkedHashMap<K, V>(capacity), keySelector, valueTransform)
}

inline fun <K, M : MutableMap<in K, in Int>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K): M {
    for (element in this) destination.put(keySelector(element), element)
    return destination
}

inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
    for (element in this) destination.put(keySelector(element), valueTransform(element))
    return destination
}

inline fun <K, V, M : MutableMap<in K, in V>> IntBuffer.associateTo(destination: M, transform: (Int) -> Pair<K, V>): M {
    for (element in this) destination += transform(element)
    return destination
}

inline fun <C : MutableCollection<in Int>> IntBuffer.toCollection(destination: C): C {
    for (item in this) destination.add(item)
    return destination
}

inline fun IntBuffer.toHashSet() = toCollection(HashSet<Int>(Maps.mapCapacity(capacity)))
inline fun IntBuffer.toList() = when (capacity) {
    0 -> emptyList()
    1 -> listOf(this[0])
    else -> toMutableList()
}

inline fun IntBuffer.toMutableList(): MutableList<Int> {
    val list = ArrayList<Int>(capacity)
    for (item in this) list.add(item)
    return list
}

inline fun IntBuffer.toSet() = when (size) {
    0 -> emptySet()
    1 -> setOf(this[0])
    else -> toCollection(LinkedHashSet<Int>(Maps.mapCapacity(size)))
}

inline fun IntBuffer.toSortedSet() = toCollection(sortedSetOf())
inline fun <R> IntBuffer.flatMap(transform: (Int) -> Iterable<R>) = flatMapTo(ArrayList<R>(), transform)
inline fun <R, C : MutableCollection<in R>> IntBuffer.flatMapTo(destination: C, transform: (Int) -> Iterable<R>): C {
    for (element in this) {
        val list = transform(element)
        destination.addAll(list)
    }
    return destination
}

inline fun <K> IntBuffer.groupBy(keySelector: (Int) -> K): Map<K, List<Int>> = groupByTo(LinkedHashMap<K, MutableList<Int>>(), keySelector)
inline fun <K, V> IntBuffer.groupBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, List<V>> =
        groupByTo(LinkedHashMap<K, MutableList<V>>(), keySelector, valueTransform)
inline fun <K, M : MutableMap<in K, MutableList<Int>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList<Int>() }
        list.add(element)
    }
    return destination
}

inline fun <K, V, M : MutableMap<in K, MutableList<V>>> IntBuffer.groupByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
    for (element in this) {
        val key = keySelector(element)
        val list = destination.getOrPut(key) { ArrayList<V>() }
        list.add(valueTransform(element))
    }
    return destination
}

inline fun <R> IntBuffer.map(transform: (Int) -> R): List<R> = mapTo(ArrayList<R>(capacity), transform)
inline fun <R> IntBuffer.mapIndexed(transform: (index: Int, Int) -> R): List<R> = mapIndexedTo(ArrayList<R>(capacity), transform)
inline fun <R : Any> IntBuffer.mapIndexedNotNull(transform: (index: Int, Int) -> R?): List<R> = mapIndexedNotNullTo(ArrayList<R>(), transform)
inline fun <R : Any, C : MutableCollection<in R>> IntBuffer.mapIndexedNotNullTo(destination: C, transform: (index: Int, Int) -> R?): C {
    forEachIndexed { index, element -> transform(index, element)?.let { destination.add(it) } }
    return destination
}

inline fun <R, C : MutableCollection<in R>> IntBuffer.mapIndexedTo(destination: C, transform: (index: Int, Int) -> R): C {
    var index = 0
    for (item in this) destination.add(transform(index++, item))
    return destination
}

inline fun <R : Any> IntBuffer.mapNotNull(transform: (Int) -> R?): List<R> = mapNotNullTo(ArrayList<R>(), transform)
inline fun <R : Any, C : MutableCollection<in R>> IntBuffer.mapNotNullTo(destination: C, transform: (Int) -> R?): C {
    forEach { element -> transform(element)?.let { destination.add(it) } }
    return destination
}

inline fun <R, C : MutableCollection<in R>> IntBuffer.mapTo(destination: C, transform: (Int) -> R): C {
    for (item in this) destination.add(transform(item))
    return destination
}

inline fun IntBuffer.withIndex(): Iterable<IndexedValue<Int>> = IndexingIterable { iterator() }
inline fun IntBuffer.distinct(): List<Int> = toMutableSet().toList()
inline fun <K> IntBuffer.distinctBy(selector: (Int) -> K): List<Int> {
    val set = HashSet<K>()
    val list = ArrayList<Int>()
    for (e in this) {
        val key = selector(e)
        if (set.add(key)) list.add(e)
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

inline fun IntBuffer.toMutableSet(): MutableSet<Int> {
    val set = LinkedHashSet<Int>(Maps.mapCapacity(capacity))
    for (item in this) set.add(item)
    return set
}

infix fun IntBuffer.union(other: Iterable<Int>): Set<Int> {
    val set = toMutableSet()
    set.addAll(other)
    return set
}

inline fun IntBuffer.all(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}

inline fun IntBuffer.any() = !isEmpty()
inline fun IntBuffer.any(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

inline fun IntBuffer.count() = capacity
inline fun IntBuffer.count(predicate: (Int) -> Boolean): Int {
    var count = 0
    for (element in this) if (predicate(element)) count++
    return count
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

inline fun IntBuffer.forEach(action: (Int) -> Unit): Unit {
    for (element in this) action(element)
}

inline fun IntBuffer.forEachIndexed(action: (index: Int, Int) -> Unit): Unit {
    var index = 0
    for (item in this) action(index++, item)
}

inline fun IntBuffer.max(): Int? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (max < e) max = e
    }
    return max
}

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

inline fun IntBuffer.maxWith(comparator: Comparator<in Int>): Int? {
    if (isEmpty()) return null
    var max = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (comparator.compare(max, e) < 0) max = e
    }
    return max
}

inline fun IntBuffer.min(): Int? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (min > e) min = e
    }
    return min
}

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

inline fun IntBuffer.minWith(comparator: Comparator<in Int>): Int? {
    if (isEmpty()) return null
    var min = this[0]
    for (i in 1..lastIndex) {
        val e = this[i]
        if (comparator.compare(min, e) > 0) min = e
    }
    return min
}

inline fun IntBuffer.none() = isEmpty()
inline fun IntBuffer.none(predicate: (Int) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return false
    return true
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

inline fun IntBuffer.sumBy(selector: (Int) -> Int): Int {
    var sum = 0
    for (element in this) sum += selector(element)
    return sum
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
    for (element in this) {
        if (predicate(element)) first.add(element)
        else second.add(element)
    }
    return Pair(first, second)
}

infix fun <R> IntBuffer.zip(other: Iterable<R>): List<Pair<Int, R>> = zip(other) { t1, t2 -> t1 to t2 }
inline fun <R, V> IntBuffer.zip(other: Iterable<R>, transform: (a: Int, b: R) -> V): List<V> {
    val arraySize = capacity
    val list = ArrayList<V>(minOf(other.collectionSizeOrDefault(10), arraySize))
    var i = 0
    for (element in other) {
        if (i >= arraySize) break
        list.add(transform(this[i++], element))
    }
    return list
}

infix fun IntBuffer.zip(other: IntBuffer): List<Pair<Int, Int>> = zip(other) { t1, t2 -> t1 to t2 }
inline fun <V> IntBuffer.zip(other: IntBuffer, transform: (a: Int, b: Int) -> V): List<V> {
    val size = minOf(capacity, other.capacity)
    val list = ArrayList<V>(size)
    for (i in 0..size - 1) {
        list.add(transform(this[i], other[i]))
    }
    return list
}

fun <A : Appendable> IntBuffer.joinTo(buffer: A, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1,
                                      truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null): A {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) {
            if (transform != null) buffer.append(transform(element))
            else buffer.append(element.toString())
        } else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun IntBuffer.joinToString(separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1,
                                  truncated: CharSequence = "...", transform: ((Int) -> CharSequence)? = null) =
        joinTo(StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString()

inline fun IntBuffer.asIterable(): Iterable<Int> {
    if (isEmpty()) return emptyList()
    return Iterable { this.iterator() }
}

inline fun IntBuffer.asSequence(): Sequence<Int> {
    if (isEmpty()) return emptySequence()
    return Sequence { this.iterator() }
}

inline fun IntBuffer.average(): Double {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element
        count += 1
    }
    return if (count == 0) Double.NaN else sum / count
}

inline fun IntBuffer.sum(): Int {
    var sum = 0
    for (element in this) sum += element
    return sum
}

fun IntBuffer.asList(): List<Int> {
    return object : AbstractList<Int>(), RandomAccess {
        override val size: Int get() = this@asList.capacity
        override fun isEmpty(): Boolean = this@asList.isEmpty()
        override fun contains(element: Int): Boolean = this@asList.contains(element)
        override fun get(index: Int): Int = this@asList[index]
        override fun indexOf(element: Int): Int = this@asList.indexOf(element)
        override fun lastIndexOf(element: Int): Int = this@asList.lastIndexOf(element)
    }
}

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

operator fun IntBuffer.plus(element: Int): IntBuffer {
    val dst = memRealloc(this, capacity + 1)
    dst[capacity] = element
    return dst
}

operator fun IntBuffer.plus(elements: Collection<Int>): IntBuffer {
    val dst = memRealloc(this, capacity + elements.size)
    for (i in 0 until elements.size) dst[capacity + i] = elements.elementAt(i)
    return dst
}

operator fun IntBuffer.plus(elements: IntBuffer): IntBuffer {
    val dst = memRealloc(this, capacity + elements.capacity)
    for (i in 0 until elements.size) dst[capacity + i] = elements.elementAt(i)
    return dst
}

fun IntBuffer.sort() {
    if (capacity > 1) Arrays.sort(this)
}

fun IntBuffer.sortWith(comparator: Comparator<Int>) {
    if (capacity > 1) Arrays.sort(this, comparator)
}

fun IntBuffer.toTypedArray() = IntArray(capacity, { this[it] })

fun IntBuffer.binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = capacity) = Arrays.binarySearch(this, fromIndex, toIndex, element)

fun IntBuffer.fill(element: Int, fromIndex: Int = 0, toIndex: Int = capacity) = Arrays.fill(this, fromIndex, toIndex, element)

fun IntBuffer.sort(fromIndex: Int = 0, toIndex: Int = capacity) = Arrays.sort(this, fromIndex, toIndex)

fun IntBuffer.sortWith(comparator: Comparator<Int>, fromIndex: Int = 0, toIndex: Int = capacity) = Arrays.sort(this, fromIndex, toIndex, comparator)

object Arrays {

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

object Maps {
    private const val INT_MAX_POWER_OF_TWO: Int = Int.MAX_VALUE / 2 + 1
    fun mapCapacity(expectedSize: Int) = when {
        expectedSize < 3 -> expectedSize + 1
        expectedSize < INT_MAX_POWER_OF_TWO -> expectedSize + expectedSize / 3
        else -> Int.MAX_VALUE // any large value
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

operator fun IntBuffer.iterator() = IntBufferIterator(this)

class IntBufferIterator(private val intBuffer: IntBuffer) : Iterator<Int> {

    private var position = intBuffer.position()

    override fun next() = intBuffer[position++]
    override fun hasNext() = position < intBuffer.capacity
}

inline val IntBuffer.capacity get() = capacity()