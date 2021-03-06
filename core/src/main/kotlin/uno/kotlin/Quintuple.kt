package uno.kotlin

import java.io.Serializable

/**
 * Created by GBarbieri on 06.03.2017.
 */

/**
 * Represents a group of five values
 *
 * There is no meaning attached to values in this class, it can be used for any purpose.
 * Quintuple exhibits value semantics, i.e. two quintuple are equal if all four components are equal.
 * An example of decomposing it into values:
 *
 * @param A type of the first value.
 * @param B type of the second value.
 * @param C type of the third value.
 * @param D type of the fourth value.
 * @param E type of the fifth value.
 * @property first First value.
 * @property second Second value.
 * @property third Third value.
 * @property fourth Fourth value.
 * @property fifth Fifth value.
 */
public data class Quintuple<out A, out B, out C, out D, out E>(
        public val first: A,
        public val second: B,
        public val third: C,
        public val fourth: D,
        public val fifth: E
) : Serializable {

    /**
     * Returns string representation of the [Quintuple] including its [first], [second], [third], [fourth] and [fifth] values.
     */
    public override fun toString(): String = "($first, $second, $third, $fourth, $fifth)"
}

/**
 * Converts this quintuple into a list.
 */
public fun <T> Quintuple<T, T, T, T, T>.toList(): List<T> = listOf(first, second, third, fourth, fifth)
