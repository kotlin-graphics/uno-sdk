package uno.kotlin

import glm_.has
import kool.Ptr
import kool.toPtr
import kotlin.random.Random

// TODO remove when kool is updated
val Ptr<*>.speakable: String
    get() = formatUidDigit(adr)

private val evenLetters = "bcdfghlmnprstwx"

private val oddLetters = "aeiou"
private fun formatUidDigit(n: ULong, level: Int = 0): String = when {
    n != 0uL -> {
        val letters = if (level has 1) oddLetters else evenLetters
        val base = letters.length.toULong()
        val s = formatUidDigit(n / base, level + 1)
        s + letters[(n % base).toInt()]
    }

    else -> ""
}

fun main() {

    for (i in 0..99) {
        val k = Random.nextLong(1000000, 2000000).toPtr<Nothing>()
        println("$k, ${k.speakable}")
    }
}
