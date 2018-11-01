package uno.awt

import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.Platform
import java.awt.AWTException
import java.awt.Canvas

/**
 * An AWT [Canvas] that supports to be drawn on using OpenGL.
 *
 * @author Kai Burjack
 */
abstract class AWTGLCanvas

@JvmOverloads
constructor(private val data: GLData = GLData()) : Canvas() {

    private var glCanvas: GLCanvas = when (Platform.get()) {
        Platform.WINDOWS -> Win32GLCanvas()
        Platform.LINUX -> LinuxGLCanvas()
        else -> throw AssertionError("Platform-specific GLCanvas class not found")
    }

    var context: WglContext = 0
    protected val effective = GLData()

    fun init() {

        try {
            context = glCanvas.createContext(this, data, effective)
            glCanvas.makeCurrent(context)
            initGL()
        } catch (e: AWTException) {
            throw RuntimeException("Exception while creating the OpenGL context", e)
        }
    }

    fun render() {
        try {
            glCanvas.lock() // <- MUST lock on Linux
        } catch (e: AWTException) {
            throw RuntimeException("Failed to lock Canvas", e)
        }
        try {
            paintGL()
        } finally {
            try {
                glCanvas.unlock() // <- MUST unlock on Linux
            } catch (e: AWTException) {
                throw RuntimeException("Failed to unlock Canvas", e)
            }
        }
    }

    fun end() {
        glCanvas.makeCurrent(NULL)
    }

    fun renderOld() {
        var created = false
        if (context == NULL) {
            try {
                context = glCanvas.createContext(this, data, effective)
                created = true
            } catch (e: AWTException) {
                throw RuntimeException("Exception while creating the OpenGL context", e)
            }

        }
        try {
            glCanvas.lock() // <- MUST lock on Linux
        } catch (e: AWTException) {
            throw RuntimeException("Failed to lock Canvas", e)
        }

        glCanvas.makeCurrent(context)
        try {
            if (created)
                initGL()
            paintGL()
        } finally {
            glCanvas.makeCurrent(NULL)
            try {
                glCanvas.unlock() // <- MUST unlock on Linux
            } catch (e: AWTException) {
                throw RuntimeException("Failed to unlock Canvas", e)
            }
        }
    }

    /** Will be called once after the OpenGL has been created. */
    abstract fun initGL()

    /** Will be called whenever the [Canvas] needs to paint itself. */
    abstract fun paintGL()

    fun swapBuffers() = glCanvas.swapBuffers()

    companion object {
        private const val serialVersionUID = 1L
    }
}
