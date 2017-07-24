package uno.kotlin

import java.awt.event.KeyEvent
import java.io.File

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

val Char.isPrintable: Boolean get() = with(Character.UnicodeBlock.of(this)) {
    (!Character.isISOControl(this@isPrintable)) &&
            this@isPrintable != KeyEvent.CHAR_UNDEFINED &&
            this != null &&
            this != Character.UnicodeBlock.SPECIALS
}

fun main(args: Array<String>) {
    println('2'.isPrintable)
}