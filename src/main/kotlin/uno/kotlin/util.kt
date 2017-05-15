package uno.kotlin

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

val String.uri  get() = url.toURI()!!
val String.url  get() = ClassLoader.getSystemResource(this)!!
val String.stream  get() = ClassLoader.getSystemResourceAsStream(this)!!