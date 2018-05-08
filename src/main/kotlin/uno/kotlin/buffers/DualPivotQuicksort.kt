package uno.kotlin.buffers

import glm_.b
import glm_.buffer.intBufferBig
import glm_.set
import uno.buffer.intBufferBig
import java.nio.ByteBuffer
import java.nio.IntBuffer

object DualPivotQuicksort {

    /*
     * Tuning parameters.
     */

    /** The maximum number of runs in merge sort.   */
    val MAX_RUN_COUNT = 67

    /** The maximum length of run in merge sort.    */
    val MAX_RUN_LENGTH = 33

    /** If the length of an array to be sorted is less than this constant, Quicksort is used in preference to merge sort.   */
    val QUICKSORT_THRESHOLD = 286

    /** If the length of an array to be sorted is less than this constant, insertion sort is used in preference to Quicksort.   */
    val INSERTION_SORT_THRESHOLD = 47

    /** If the length of a byte array to be sorted is greater than this constant, counting sort is used in preference to insertion sort.    */
    val COUNTING_SORT_THRESHOLD_FOR_BYTE = 29

    /** If the length of a short or char array to be sorted is greater than this constant, counting sort is used in preference to Quicksort.    */
    val COUNTING_SORT_THRESHOLD_FOR_SHORT_OR_CHAR = 3200

    /*
     * Sorting methods for seven primitive types.
     */

    /** Sorts the specified range of the array using the given workspace array slice if possible for merging
     *
     * @param a the array to be sorted
     * @param left the index of the first element, inclusive, to be sorted
     * @param right the index of the last element, inclusive, to be sorted
     * @param work a workspace array (slice)
     * @param workBase origin of usable space in work array
     * @param workLen usable size of work array
     */
    fun sort(a: IntBuffer, left: Int, right: Int, work: IntBuffer?, workBase: Int, workLen: Int) {
        var a = a
        var right = right
        var work = work
        var workBase = workBase
        // Use Quicksort on small arrays
        if (right - left < QUICKSORT_THRESHOLD) {
            sort(a, left, right, true)
            return
        }

        // Index run[i] is the start of i-th run (ascending or descending sequence).
        val run = IntArray(MAX_RUN_COUNT + 1)
        var count = 0; run[0] = left

        // Check if the array is nearly sorted
        var k = left
        while (k < right) {
            if (a[k] < a[k + 1]) { // ascending
                while (++k <= right && a[k - 1] <= a[k]);
            } else if (a[k] > a[k + 1]) { // descending
                while (++k <= right && a[k - 1] >= a[k]);
                var lo = run[count] - 1
                var hi = k
                while (++lo < --hi) {
                    val t = a[lo]; a[lo] = a[hi]; a[hi] = t
                }
            } else { // equal
                var m = MAX_RUN_LENGTH
                while (++k <= right && a[k - 1] == a[k]) {
                    if (--m == 0) {
                        sort(a, left, right, true)
                        return
                    }
                }
            }

            // The array is not highly structured, use Quicksort instead of merge sort.
            if (++count == MAX_RUN_COUNT) {
                sort(a, left, right, true)
                return
            }
            run[count] = k
        }

        // Check special cases
        // Implementation note: variable "right" is increased by 1.
        if (run[count] == right++) // The last run contains one element
            run[++count] = right
        else if (count == 1) return // The array is already sorted

        // Determine alternation base for merge
        var odd = 0
        var n = 1
        while ((n shl 1) < count) {
            n = n shl 1
            odd = odd xor 1
        }

        // Use or create temporary array b for merging
        var b: IntBuffer    // temp array; alternates with a
        // array offsets from 'left'
        var ao = 0
        var bo = 0
        val blen = right - left // space needed for b
        if (work == null || workLen < blen || workBase + blen > work.capacity) {
            work = intBufferBig(blen)
            workBase = 0
        }
        if (odd == 0) {
            for (i in 0 until blen) work[workBase] = a[left]
            b = a
            bo = 0
            a = work
            ao = workBase - left
        } else {
            b = work
            ao = 0
            bo = workBase - left
        }

        // Merging
        var last = 0
        while (count > 1) {
            last = 0
            k = last + 2
            while (k <= count) {
                val hi = run[k]
                val mi = run[k - 1]
                var i = run[k - 2]
                var p = i
                var q = mi
                while (i < hi) {
                    if (q >= hi || p < mi && a[p + ao] <= a[q + ao]) b[i + bo] = a[p++ + ao]
                    else b[i + bo] = a[q++ + ao]
                    ++i
                }
                run[++last] = hi
                k += 2
            }
            if ((count and 1) != 0) {
                var i = right
                val lo = run[count - 1]
                while (--i >= lo)
                    b[i + bo] = a[i + ao]
                run[++last] = right
            }
            val t = a; a = b; b = t
            val o = ao; ao = bo; bo = o
        }
        count = last
    }

    /**
     * Sorts the specified range of the array by Dual-Pivot Quicksort.
     *
     * @param a the array to be sorted
     * @param left the index of the first element, inclusive, to be sorted
     * @param right the index of the last element, inclusive, to be sorted
     * @param leftmost indicates if this part is the leftmost in the range
     */
    private fun sort(a: IntBuffer, left: Int, right: Int, leftmost: Boolean) {

        val length = right - left + 1
        var left = left
        var right = right

        // Use insertion sort on tiny arrays
        if (length < INSERTION_SORT_THRESHOLD) {
            if (leftmost) {
                /* Traditional (without sentinel) insertion sort, optimized for server VM, is used in case of
                    the leftmost part.  */
                var i = left
                var j = i
                while (i < right) {
                    val ai = a[i + 1]
                    while (ai < a[j]) {
                        a[j + 1] = a[j]
                        if (j-- == left) break
                    }
                    a[j + 1] = ai
                    j = ++i
                }
            } else {
                // Skip the longest ascending sequence.
                do {
                    if (left >= right) return
                } while (a[++left] >= a[left - 1])

                /* Every element from adjoining part plays the role of sentinel, therefore this allows us to avoid the
                 * left range check on each iteration. Moreover, we use the more optimized algorithm, so called pair
                 * insertion sort, which is faster (in the context of Quicksort) than traditional implementation of
                 * insertion sort.  */
                var k = left
                while (++left <= right) {
                    var a1 = a[k]
                    var a2 = a[left]
                    if (a1 < a2) {
                        a2 = a1; a1 = a[left]
                    }
                    while (a1 < a[--k])
                        a[k + 2] = a[k]
                    a[++k + 1] = a1
                    while (a2 < a[--k])
                        a[k + 1] = a[k]
                    a[k + 1] = a2
                    k = ++left
                }
                val last = a[right]

                while (last < a[--right])
                    a[right + 1] = a[right]
                a[right + 1] = last
            }
            return
        }

        // Inexpensive approximation of length / 7
        val seventh = (length shr 3) + (length shr 6) + 1

        /*  Sort five evenly spaced elements around (and including) the center element in the range. These elements will
            be used for pivot selection as described below. The choice for spacing these elements was empirically
            determined to work well on a wide variety of inputs.    */
        val e3 = (left + right) ushr 1 // The midpoint
        val e2 = e3 - seventh
        val e1 = e2 - seventh
        val e4 = e3 + seventh
        val e5 = e4 + seventh

        // Sort these elements using insertion sort
        if (a[e2] < a[e1]) {
            val t = a[e2]; a[e2] = a[e1]; a[e1] = t; }

        if (a[e3] < a[e2]) {
            val t = a[e3]; a[e3] = a[e2]; a[e2] = t
            if (t < a[e1]) {
                a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3]) {
            val t = a[e4]; a[e4] = a[e3]; a[e3] = t
            if (t < a[e2]) {
                a[e3] = a[e2]; a[e2] = t
                if (t < a[e1]) {
                    a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4]) {
            val t = a[e5]; a[e5] = a[e4]; a[e4] = t
            if (t < a[e3]) {
                a[e4] = a[e3]; a[e3] = t
                if (t < a[e2]) {
                    a[e3] = a[e2]; a[e2] = t
                    if (t < a[e1]) {
                        a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }

        // Pointers
        var less = left  // The index of the first element of center part
        var great = right // The index before the first element of right part

        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
            /*  Use the second and fourth of the five sorted elements as pivots. These values are inexpensive
                approximations of the first and second terciles of the array. Note that pivot1 <= pivot2.   */
            val pivot1 = a[e2]
            val pivot2 = a[e4]

            /*  The first and the last elements to be sorted are moved to the locations formerly occupied by the pivots.
                When partitioning is complete, the pivots are swapped back into their final positions, and excluded
                from subsequent sorting.    */
            a[e2] = a[left]
            a[e4] = a[right]

            // Skip elements, which are less or greater than pivot values.
            while (a[++less] < pivot1);
            while (a[--great] > pivot2);

            /* Partitioning:
             *
             *   left part           center part                   right part
             * +--------------------------------------------------------------+
             * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
             * +--------------------------------------------------------------+
             *               ^                          ^       ^
             *               |                          |       |
             *              less                        k     great
             *
             * Invariants:
             *
             *              all in (left, less)   < pivot1
             *    pivot1 <= all in [less, k)     <= pivot2
             *              all in (great, right) > pivot2
             *
             * Pointer k is the first index of ?-part.  */
            var k = less - 1
            outer@ while (++k <= great) {
                val ak = a[k]
                if (ak < pivot1) { // Move a[k] to left part
                    a[k] = a[less]
                    //  Here and below we use "a[i] = b; i++;" instead of "a[i++] = b;" due to performance issue.
                    a[less] = ak
                    ++less
                } else if (ak > pivot2) { // Move a[k] to right part
                    while (a[great] > pivot2) if (great-- == k) break@outer
                    if (a[great] < pivot1) { // a[great] <= pivot2
                        a[k] = a[less]
                        a[less] = a[great]
                        ++less
                    } else a[k] = a[great]  // pivot1 <= a[great] <= pivot2
                    /*
                     * Here and below we use "a[i] = b; i--;" instead
                     * of "a[i--] = b;" due to performance issue.
                     */
                    a[great] = ak
                    --great
                }
            }

            // Swap pivots into their final positions
            a[left] = a[less - 1]; a[less - 1] = pivot1
            a[right] = a[great + 1]; a[great + 1] = pivot2

            // Sort left and right parts recursively, excluding known pivots
            sort(a, left, less - 2, leftmost)
            sort(a, great + 2, right, false)

            // If center part is too large (comprises > 4/7 of the array), swap internal pivot values to ends.
            if (less < e1 && e5 < great) {
                // Skip elements, which are equal to pivot values.
                while (a[less] == pivot1) ++less

                while (a[great] == pivot2) --great

                /*
                 * Partitioning:
                 *
                 *   left part         center part                  right part
                 * +----------------------------------------------------------+
                 * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                 * +----------------------------------------------------------+
                 *              ^                        ^       ^
                 *              |                        |       |
                 *             less                      k     great
                 *
                 * Invariants:
                 *
                 *              all in (*,  less) == pivot1
                 *     pivot1 < all in [less,  k)  < pivot2
                 *              all in (great, *) == pivot2
                 *
                 * Pointer k is the first index of ?-part.
                 */
                k = less - 1
                outer@ while (++k <= great) {
                    val ak = a[k]
                    if (ak == pivot1) { // Move a[k] to left part
                        a[k] = a[less]
                        a[less] = ak
                        ++less
                    } else if (ak == pivot2) { // Move a[k] to right part
                        while (a[great] == pivot2) if (great-- == k) break@outer
                        if (a[great] == pivot1) { // a[great] < pivot2
                            a[k] = a[less]
                            /*  Even though a[great] equals to pivot1, the assignment a[less] = pivot1 may be incorrect,
                                if a[great] and pivot1 are floating-point zeros of different signs. Therefore in float
                                and double sorting methods we have to use more accurate assignment a[less] = a[great].  */
                            a[less] = pivot1
                            ++less
                        } else a[k] = a[great]  // pivot1 < a[great] < pivot2
                        a[great] = ak
                        --great
                    }
                }
            }

            // Sort center part recursively
            sort(a, less, great, false)

        } else { // Partitioning with one pivot
            //  Use the third of the five sorted elements as pivot. This value is inexpensive approximation of the median.
            val pivot = a[e3]

            /* Partitioning degenerates to the traditional 3-way
             * (or "Dutch National Flag") schema:
             *
             *   left part    center part              right part
             * +-------------------------------------------------+
             * |  < pivot  |   == pivot   |     ?    |  > pivot  |
             * +-------------------------------------------------+
             *              ^              ^        ^
             *              |              |        |
             *             less            k      great
             *
             * Invariants:
             *
             *   all in (left, less)   < pivot
             *   all in [less, k)     == pivot
             *   all in (great, right) > pivot
             *
             * Pointer k is the first index of ?-part.  */
            var k = less
            while (k <= great) {
                if (a[k] == pivot) {
                    ++k
                    continue
                }
                val ak = a[k]
                if (ak < pivot) { // Move a[k] to left part
                    a[k] = a[less]
                    a[less] = ak
                    ++less
                } else { // a[k] > pivot - Move a[k] to right part
                    while (a[great] > pivot) --great
                    if (a[great] < pivot) { // a[great] <= pivot
                        a[k] = a[less]
                        a[less] = a[great]
                        ++less
                    } else // a[great] == pivot
                    /*  Even though a[great] equals to pivot, the assignment a[k] = pivot may be incorrect, if a[great]
                        and pivot are floating-point zeros of different signs. Therefore in float and double sorting
                        methods we have to use more accurate assignment a[k] = a[great]. */
                        a[k] = pivot

                    a[great] = ak
                    --great
                }
                ++k
            }

            // Sort left and right parts recursively. All elements from center part are equal and, therefore, already sorted.
            sort(a, left, less - 1, leftmost)
            sort(a, great + 1, right, false)
        }
    }

    /** The number of distinct byte values. */
    val NUM_BYTE_VALUES = 1 shl 8

    /**
     * Sorts the specified range of the array.
     *
     * @param a the array to be sorted
     * @param left the index of the first element, inclusive, to be sorted
     * @param right the index of the last element, inclusive, to be sorted
     */
    fun sort(a: ByteBuffer, left: Int, right: Int) {
        // Use counting sort on large arrays
        if (right - left > COUNTING_SORT_THRESHOLD_FOR_BYTE) {
            val count = IntArray(NUM_BYTE_VALUES)

            for (i in left - 1..right)
                count[a[i] - Byte.MIN_VALUE]++
            var i = NUM_BYTE_VALUES
            var k = right + 1
            while (k > left) {
                while (count[--i] == 0);
                val value = (i + Byte.MIN_VALUE).b
                var s = count[i]

                do a[--k] = value
                while (--s > 0)
            }
        } else { // Use insertion sort on small arrays
            var i = left
            var j = i
            while(i < right) {
                val ai = a [i + 1]
                while (ai < a[j]) {
                    a[j + 1] = a[j]
                    if (j-- == left)
                        break
                }
                a[j + 1] = ai
                j = ++i
            }
        }
    }
}