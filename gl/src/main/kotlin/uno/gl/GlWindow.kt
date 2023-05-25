package uno.gl

import gln.cap.Caps
import gln.misc.GlDebugSeverity
import gln.misc.GlDebugSource
import gln.misc.GlDebugType
import gln.misc.glDebugCallback
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL43C
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Callback
import org.lwjgl.system.MemoryUtil
import uno.glfw.GlfwWindow
import uno.glfw.glfw

open class GlWindow(val glfwWindow: GlfwWindow,
                    profile: Caps.Profile = Caps.Profile.COMPATIBILITY,
                    forwardCompatible: Boolean = true): GlfwWindow(glfwWindow.handle) {

    /**
     * Spasi: "the ideal option for modern applications is: compatibility context + forwardCompatible. A compatibility context
     * does not do extra validations that may cost performance and with `forwardCompatible == true` you don't risk using
     * legacy functionality by mistake.
     * LWJGL will not try to load deprecated functions, so calling them will crash but the context will actually expose them"
     */
    init {
        makeCurrent()
        // This line is critical for LWJGL's interoperation with GLFW's OpenGL context,
        // or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread, creates the GLCapabilities instance and
        // makes the OpenGL bindings available for use.
//        GL.createCapabilities() // useless, it's in Caps instantiation
    }
    val caps = Caps(profile, forwardCompatible)

    var debugProc: Callback? = null

    fun init(show: Boolean = true) {
        glfwWindow.show(show)
        if (glfw.hints.context.debug) {
            debugProc = GLUtil.setupDebugMessageCallback()
            // turn off notifications only
            GL43C.nglDebugMessageControl(GlDebugSource.DONT_CARE.i, GlDebugType.DONT_CARE.i, GlDebugSeverity.NOTIFICATION.i, 0, MemoryUtil.NULL, false)
        }
    }

    // --- [ glfwMakeContextCurrent ] ---
    fun makeCurrent(current: Boolean = true) = GLFW.glfwMakeContextCurrent(if (current) handle else MemoryUtil.NULL)

    /** for Java */
    override fun destroy() {
        super.destroy()
        debugProc?.free()
        glDebugCallback?.free()
        GL.destroy()
    }

    inline fun withinContext(block: () -> Unit) {
        makeCurrent()
        GL.setCapabilities(caps.gl)
        block()
        makeCurrent(false)
    }

    /** for Java */
    fun withinContext(runnable: Runnable) = withinContext { runnable.run() }
}