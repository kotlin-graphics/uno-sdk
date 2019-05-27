package uno.time

import glm_.glm

/**
 * Created by GBarbieri on 13.03.2017.
 */

/** Creates a timer with the given type.
 *  LOOP and SINGLE timers need an explicit duration. This represents the time in seconds through a loop, or the time in
 *  seconds until the timer expires.
 *  INFINITE timers ignore the duration.
 *  It is legal to create these statically. **/
class Timer(var type: Type, val secDuration: Float) {

    constructor(secDuration: Float) : this(Type.Infinite, 1f)

    private var hasUpdated = false
    var isPaused = false

    private var absPrevTime = 0f
    private var secAccumTime = 0f
    private val start = System.currentTimeMillis()

    init {
        if (type != Type.Infinite)
            assert(secDuration > 0f)
    }

    /** Resets the timer, as though the user just created the object with the original parameters.  */
    fun reset() {
        hasUpdated = false
        secAccumTime = 0f
    }

    /** Pauses/unpauses. Returns true if the timer is paused after the toggling.    */
    fun togglePause(): Boolean {
        isPaused = !isPaused
        return isPaused
    }

    /** Updates the time for the timer. Returns true if the timer has reached the end.
     *  Will only return true for SINGLE timers that have reached their duration.   */
    fun update(): Boolean {

        val absCurrTime = (System.currentTimeMillis() - start) / 1_000f
        if (!hasUpdated) {
            absPrevTime = absCurrTime
            hasUpdated = true
        }

        if (isPaused) {
            absPrevTime = absCurrTime
            return false
        }

        val deltaTime = absCurrTime - absPrevTime
        secAccumTime += deltaTime

        absPrevTime = absCurrTime
        if (type == Type.Single)
            return secAccumTime > secDuration

        return false
    }

    /** Subtracts secRewind from the current time and continues from there. */
    fun rewind(secRewind: Float) {
        secAccumTime -= secRewind
        if (secAccumTime < 0f)
            secAccumTime = 0f
    }

    /** Adds secRewind to the current time and continues from there.    */
    fun fastForward(secFF: Float) {
        secAccumTime += secFF
    }

    /** Returns a number [0, 1], representing progress through the duration. Only used for SINGLE and LOOP timers. */
    fun getAlpha() = when (type) {

        Type.Loop -> glm.mod(secAccumTime, secDuration) / secDuration

        Type.Single -> glm.clamp(secAccumTime / secDuration, 0f, 1f)

        else -> -1f //Garbage.
    }

    /** Returns a number [0, duration], representing the progress through the timer in seconds. Only for SINGLE and LOOP timers.    */
    fun getProgression() = when (type) {

        Type.Loop -> glm.mod(secAccumTime, secDuration)

        Type.Single -> glm.clamp(secAccumTime, 0f, secDuration)

        else -> -1f //Garbage.
    }

    /** Returns the time in seconds since the timer was started, excluding time for pausing.    */
    fun getTimeSinceStart() = secAccumTime

    /** Returns the timer's duration that was passed in.    */
    fun getDuration() = secDuration

    enum class Type {Loop, Single, Infinite, MAX }
}

fun main(args: Array<String>) {

    val timer = Timer(Timer.Type.Single, 1f)

}