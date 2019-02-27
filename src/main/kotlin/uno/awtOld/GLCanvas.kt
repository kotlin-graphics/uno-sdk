package uno.awtOld

import org.lwjgl.system.jawt.JAWT
import org.lwjgl.system.jawt.JAWTFunctions
import java.awt.AWTException
import java.awt.Canvas

/**
 * Interface for platform-specific implementations of {@link AWTGLCanvas}.
 *
 * @author Kai Burjack
 */
abstract class GLCanvas {

    protected val awt: JAWT = org.lwjgl.system.jawt.JAWT.calloc().apply {
        version(JAWTFunctions.JAWT_VERSION_1_4)
        if (!JAWTFunctions.JAWT_GetAWT(this))
            throw AssertionError("GetAWT failed")
    }

    @Throws(AWTException::class)
    abstract fun createContext(canvas: Canvas, data: GLData, effective: GLData): WglContext

    open fun deleteContext(context: WglContext): Boolean = false

    abstract fun makeCurrent(context: WglContext): Boolean

    abstract fun isCurrent(context: WglContext): Boolean

    abstract fun swapBuffers(): Boolean

    abstract fun delayBeforeSwapNV(seconds: Float): Boolean

    @Throws(AWTException::class)
    open fun lock() {
    }

    @Throws(AWTException::class)
    open fun unlock() {
    }

    @Throws(AWTException::class)
    inline fun <R> lock(block: () -> R): R =
            try {
                lock()
                block()
            } finally {
                unlock()
            }
}