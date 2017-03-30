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