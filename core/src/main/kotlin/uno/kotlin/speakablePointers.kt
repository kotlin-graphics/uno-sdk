package uno.kotlin

import glm_.has
import kool.Ptr
import kool.toPtr
import kotlin.random.Random


val Ptr<*>.speakable: String
    get() = formatUidDigit(adr)

private val evenLetters = "bcdfghlmnprstwx"

private val oddLetters = "aeiou"
private fun formatUidDigit(n: ULong, level: Int = 0): String {
    if (n != 0uL) {
        val letters = if (level has 1) oddLetters else evenLetters
        val base = letters.length.toULong()
        val s = formatUidDigit(n / base, level + 1)
        return s + letters[(n % base).toInt()]
    }
    return ""
}

fun main() {

    for (i in 0..99) {
        val k = Random.nextLong(1000000, 2000000).toPtr<Nothing>()
        println("$k, ${k.speakable}")
    }
}
