package uno.glfw

import glm_.bool
import glm_.f
import glm_.i
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil
import uno.buffer.destroyBuf
import uno.buffer.doubleBufferBig
import uno.buffer.intBufferBig

/**
 * Created by GBarbieri on 24.04.2017.
 */

class GlfwWindow(val handle: Long) {

    constructor(windowSize: Vec2i, title: String) : this(windowSize.x, windowSize.y, title)
    constructor(x: Int, title: String) : this(x, x, title)
    constructor(width: Int, height: Int, title: String) : this(glfwCreateWindow(width, height, title, 0L, 0L)) {
        this.title = title
    }

    private val x = intBufferBig(1)
    private val y = intBufferBig(1)
    private val z = intBufferBig(1)
    private val w = intBufferBig(1)
    private val xD = doubleBufferBig(1)
    private val yD = doubleBufferBig(1)

    init {
        if (handle == MemoryUtil.NULL) {
            glfw.terminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
    }

    var close
        get() = glfwWindowShouldClose(handle)
        set(value) = glfwSetWindowShouldClose(handle, value)
    val open get() = !close

    var title = ""
        set(value) = glfwSetWindowTitle(handle, value)

    // TODO icon

    var pos = Vec2i()
        get() {
            glfwGetWindowPos(handle, x, y)
            return field.put(x[0], y[0])
        }
        set(value) = glfwSetWindowPos(handle, value.x, value.y)

    var size = Vec2i()
        get() {
            glfwGetWindowSize(handle, x, y)
            return field.put(x[0], y[0])
        }
        set(value) = glfwSetWindowSize(handle, value.x, value.y)

    fun sizeLimit(width: IntRange, height: IntRange) = glfwSetWindowSizeLimits(handle, width.start, height.start, width.endInclusive, height.endInclusive)

    fun aspect(numer: Int, denom: Int) = glfwSetWindowAspectRatio(handle, numer, denom)

    var aspect
        get() = size.x / size.y.f
        set(value) = glfwSetWindowAspectRatio(handle, (value * 1_000).i, 1_000)

    val framebufferSize = Vec2i()
        get() {
            glfwGetFramebufferSize(handle, x, y)
            return field.put(x[0], y[0])
        }

    val frameSize = Vec4i()
        get() {
            glfwGetWindowFrameSize(handle, x, y, z, w)
            return field.put(x[0], y[0], z[0], w[0])
        }

    fun iconify() = glfwIconifyWindow(handle)
    fun restore() = glfwRestoreWindow(handle)
    fun maximize() = glfwMaximizeWindow(handle)
    fun show() = glfwShowWindow(handle)
    fun hide() = glfwHideWindow(handle)
    fun focus() = glfwFocusWindow(handle)

    val monitor get() = glfwGetWindowMonitor(handle)
    fun monitor(monitor: Long, xPos: Int, yPos: Int, width: Int, height: Int) =
            monitor(monitor, xPos, yPos, width, height, GLFW_DONT_CARE)

    fun monitor(monitor: Long, xPos: Int, yPos: Int, width: Int, height: Int, refreshRate: Int) =
            glfwSetWindowMonitor(handle, monitor, xPos, yPos, width, height, refreshRate)

    val focused get() = glfwGetWindowAttrib(handle, GLFW_FOCUSED).bool
    val iconified get() = glfwGetWindowAttrib(handle, GLFW_ICONIFIED).bool
    val maximized get() = glfwGetWindowAttrib(handle, GLFW_MAXIMIZED).bool
    val visible get() = glfwGetWindowAttrib(handle, GLFW_VISIBLE).bool
    val resizable get() = glfwGetWindowAttrib(handle, GLFW_RESIZABLE).bool
    val decorated get() = glfwGetWindowAttrib(handle, GLFW_DECORATED).bool
    val floating get() = glfwGetWindowAttrib(handle, GLFW_FLOATING).bool


    fun makeContextCurrent() = glfwMakeContextCurrent(handle)

    fun destroy() {

        destroyBuf(x, y, z, w, xD, yD)

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(handle)
        glfwDestroyWindow(handle)
    }


    fun swapBuffers() = glfwSwapBuffers(handle)


    var framebufferSizeCallback: ((Vec2i) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetFramebufferSizeCallback(handle, null)?.free()
            else
                glfwSetFramebufferSizeCallback(handle, framebufferSizeListener_)?.free()
            field = value
        }
    private val framebufferSizeListener_ = GLFWFramebufferSizeCallbackI { _, width, height -> framebufferSizeCallback!!.invoke(Vec2i(width, height)) }

    var framebufferSizeCallBack: ((Int, Int) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetFramebufferSizeCallback(handle, null)?.free()
            else
                glfwSetFramebufferSizeCallback(handle, framebufferSizeListener)?.free()
            field = value
        }
    private val framebufferSizeListener = GLFWFramebufferSizeCallbackI { _, width, height -> framebufferSizeCallBack!!.invoke(width, height) }


    var cursorPos = Vec2d()
        get() {
            glfwGetCursorPos(handle, xD, yD)
            return field.put(xD[0], yD[0])
        }
        set(value) = glfwSetCursorPos(handle, value.x, value.y)


    var cursorPosCallback: ((Vec2d) -> Unit)? = null
    set(value) {
        if (value == null)
            glfwSetCursorPosCallback(handle, null)?.free()
        else
            glfwSetCursorPosCallback(handle, cursorPosListener)?.free()
        field = value
    }
    private val cursorPosListener = GLFWCursorPosCallbackI { _, xPos, yPos -> cursorPosCallback!!.invoke(Vec2d(xPos, yPos)) }

    var cursorPosCallBack: ((Double, Double) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetCursorPosCallback(handle, null)?.free()
            else
                glfwSetCursorPosCallback(handle, cursorPosListener_)?.free()
            field = value
        }
    private val cursorPosListener_ = GLFWCursorPosCallbackI { _, xPos, yPos -> cursorPosCallBack!!.invoke(xPos, yPos) }


    var scrollCallback: ((Vec2d) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetScrollCallback(handle, null)?.free()
            else
                glfwSetScrollCallback(handle, scrollListener)?.free()
            field = value
        }
    private val scrollListener = GLFWScrollCallbackI { _, xOffset, yOffset -> scrollCallback!!.invoke(Vec2d(xOffset, yOffset)) }

    var scrollCallBack: ((Double, Double) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetScrollCallback(handle, null)?.free()
            else
                glfwSetScrollCallback(handle, scrollListener_)?.free()
            field = value
        }
    private val scrollListener_ = GLFWScrollCallbackI { _, xOffset, yOffset -> scrollCallBack!!.invoke(xOffset, yOffset) }


    var mouseButtonCallback: ((Int, Int, Int) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetMouseButtonCallback(handle, null)?.free()
            else
                glfwSetMouseButtonCallback(handle, mouseButtonListener)?.free()
            field = value
        }
    private val mouseButtonListener = GLFWMouseButtonCallbackI { _, button, action, mods -> mouseButtonCallback!!.invoke(button, action, mods) }


    var keyCallback: ((Int, Int, Int, Int) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetKeyCallback(handle, null)?.free()
            else
                glfwSetKeyCallback(handle, keyListener)?.free()
            field = value
        }
    private val keyListener = GLFWKeyCallbackI { _, key, scancode, action, mods -> keyCallback!!.invoke(key, scancode, action, mods) }


    var charCallback: ((Int) -> Unit)? = null
        set(value) {
            if (value == null)
                glfwSetCharCallback(handle, null)?.free()
            else
                glfwSetCharCallback(handle, charListener)?.free()
            field = value
        }
    private val charListener = GLFWCharCallbackI { _, codepoint -> charCallback!!.invoke(codepoint) }


    var cursor: Cursor
        get() = when (glfwGetInputMode(handle, GLFW_CURSOR)) {
            GLFW_CURSOR_NORMAL -> Cursor.Normal
            GLFW_CURSOR_HIDDEN -> Cursor.Hidden
            GLFW_CURSOR_DISABLED -> Cursor.Disabled
            else -> throw Error()
        }
        set(value) = glfwSetInputMode(handle, GLFW_CURSOR, when (value) {
            Cursor.Normal -> GLFW_CURSOR_NORMAL
            Cursor.Hidden -> GLFW_CURSOR_HIDDEN
            Cursor.Disabled -> GLFW_CURSOR_DISABLED
            else -> throw Error()
        })

    enum class Cursor { Normal, Hidden, Disabled }


    fun pressed(key: Int) = glfwGetKey(handle, key) == GLFW_PRESS
    fun released(key: Int) = glfwGetKey(handle, key) == GLFW_PRESS

    fun mouseButton(button: Int) = glfwGetMouseButton(handle, button)


    inline fun loop(block: () -> Unit) {
        while (open) block()
    }
}