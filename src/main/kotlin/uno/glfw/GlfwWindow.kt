package uno.glfw

import glm_.BYTES
import glm_.bool
import glm_.buffer.adr
import glm_.buffer.bufferBig
import glm_.buffer.free
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec4.Vec4
import glm_.vec4.Vec4i
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.memGetDouble
import org.lwjgl.system.MemoryUtil.memGetInt
import org.lwjgl.vulkan.VkInstance
import vkk.appBuffer
import java.nio.ByteBuffer
import java.nio.FloatBuffer

/**
 * Created by GBarbieri on 24.04.2017.
 */

typealias FramebufferSizeCallbackT = (Vec2i) -> Unit

typealias CursorPosCallbackT = (Vec2d) -> Unit
typealias ScrollCallbackT = (Vec2d) -> Unit
typealias MouseButtonCallbackT = (Int, Int, Int) -> Unit
typealias KeyCallbackT = (Int, Int, Int, Int) -> Unit
typealias CharCallbackT = (Int) -> Unit

class GlfwWindow(val handle: Long) {

    private val buff = bufferBig(Vec4.size)
    private val adr = buff.adr

    constructor(windowSize: Vec2i, title: String) : this(windowSize.x, windowSize.y, title)
    constructor(x: Int, title: String) : this(x, x, title)
    constructor(width: Int, height: Int, title: String) : this(glfwCreateWindow(width, height, title, 0L, 0L)) {
        this.title = title
    }

    init {
        if (handle == MemoryUtil.NULL) {
            glfw.terminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
    }

    val isOpen get() = !shouldClose

    var shouldClose
        get() = glfwWindowShouldClose(handle)
        set(value) = glfwSetWindowShouldClose(handle, value)

    var title = ""
        set(value) = glfwSetWindowTitle(handle, value)

    fun setSizeLimit(width: IntRange, height: IntRange) = glfwSetWindowSizeLimits(handle, width.start, height.start, width.endInclusive, height.endInclusive)

    // TODO icon

    var pos = Vec2i()
        get() {
            nglfwGetWindowPos(handle, adr, adr + Int.BYTES)
            return field(memGetInt(adr), memGetInt(adr + Int.BYTES))
        }
        set(value) = glfwSetWindowPos(handle, value.x, value.y)

    var size = Vec2i()
        get() {
            nglfwGetWindowSize(handle, adr, adr + Int.BYTES)
            return field(memGetInt(adr), memGetInt(adr + Int.BYTES))
        }
        set(value) = glfwSetWindowSize(handle, value.x, value.y)

    val aspect get() = size.aspect
//        set(value) = glfwSetWindowAspectRatio(handle, (value * 1_000).i, 1_000)

    var aspectRatio = Vec2i()
        get() = field(size.x, size.y)
        set(value) = glfwSetWindowAspectRatio(handle, value.x, value.y)

    val framebufferSize = Vec2i()
        get() {
            nglfwGetFramebufferSize(handle, adr, adr + Int.BYTES)
            return field(memGetInt(adr), memGetInt(adr + Int.BYTES))
        }

    val frameSize = Vec4i()
        get() {
            nglfwGetWindowFrameSize(handle, adr, adr + Int.BYTES, adr + Int.BYTES * 2, adr + Int.BYTES * 3)
            return field(memGetInt(adr), memGetInt(adr + Int.BYTES), memGetInt(adr + Int.BYTES * 2), memGetInt(adr + Int.BYTES * 3))
        }

    fun iconify() = glfwIconifyWindow(handle)
    fun restore() = glfwRestoreWindow(handle)
    fun maximize() = glfwMaximizeWindow(handle)
    fun show() = glfwShowWindow(handle)
    fun hide() = glfwHideWindow(handle)
    fun focus() = glfwFocusWindow(handle)

    data class Monitor(val monitor: Long, val xPos: Int, val yPos: Int, val width: Int, val height: Int, val refreshRate: Int = GLFW_DONT_CARE)

    var monitor: Monitor
        get() {
            val monitor = glfwGetWindowMonitor(handle)
            return Monitor(monitor, pos.x, pos.y, size.x, size.y)
        }
        set(value) = glfwSetWindowMonitor(handle, value.monitor, value.xPos, value.yPos, value.width, value.height, value.refreshRate)

    val focused get() = glfwGetWindowAttrib(handle, GLFW_FOCUSED).bool
    val iconified get() = glfwGetWindowAttrib(handle, GLFW_ICONIFIED).bool
    val maximized get() = glfwGetWindowAttrib(handle, GLFW_MAXIMIZED).bool
    val visible get() = glfwGetWindowAttrib(handle, GLFW_VISIBLE).bool
    val resizable get() = glfwGetWindowAttrib(handle, GLFW_RESIZABLE).bool
    val decorated get() = glfwGetWindowAttrib(handle, GLFW_DECORATED).bool
    val floating get() = glfwGetWindowAttrib(handle, GLFW_FLOATING).bool

    fun makeContextCurrent() = glfwMakeContextCurrent(handle)

    fun destroy() {
        buff.free()

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(handle)
        glfwDestroyWindow(handle)
    }

    fun swapBuffers() = glfwSwapBuffers(handle)

    /*fun setFramebufferSizeCallback(callbackFunc: (Vec2i) -> Unit) {
        val callback = GLFWFramebufferSizeCallbackI { _, width, height -> callbackFunc(Vec2i(width, height)) }
        setFramebufferSizeCallback(callback)
    }

    fun setFramebufferSizeCallback(callbackFunc: (Int, Int) -> Unit) {
        val callback = GLFWFramebufferSizeCallbackI { _, width, height -> callbackFunc(width, height) }
        setFramebufferSizeCallback(callback)
    }

    var framebufferSizeCB: ((Vec2i) -> Unit)? = null
        set(value) {
            setFramebufferSizeCallback(value?.let { GLFWFramebufferSizeCallbackI { _, width, height -> it(Vec2i(width, height)) } })
            field = value
        }

    fun setFramebufferSizeCallback(callback: GLFWFramebufferSizeCallbackI?) {
        glfwSetFramebufferSizeCallback(handle, callback)?.free()
    }
    */

    var framebufferSizeCallback: FramebufferSizeCallbackT? = null
        set(value) {
            glfwSetFramebufferSizeCallback(handle, value?.let { GLFWFramebufferSizeCallbackI { _, width, height -> it(Vec2i(width, height)) } })?.free()
            field = value
        }


    var cursorPos = Vec2d()
        get() {
            nglfwGetCursorPos(handle, adr, adr + Double.BYTES)
            return field(memGetDouble(adr), memGetDouble(adr + Double.BYTES))
        }
        set(value) = glfwSetCursorPos(handle, value.x, value.y)


    /*fun setCursorPosCallback(callbackFunc: (Vec2d) -> Unit) {
        val callback = GLFWCursorPosCallbackI { _, xPos, yPos -> callbackFunc(Vec2d(xPos, yPos)) }
        setCursorPosCallback(callback)
    }

    fun setCursorPosCallback(callbackFunc: (Double, Double) -> Unit) {
        val callback = GLFWCursorPosCallbackI { _, xPos, yPos -> callbackFunc(xPos, yPos) }
        setCursorPosCallback(callback)
    }

    var cursorPosCB: ((Vec2d) -> Unit)? = null
        set(value) {
            setCursorPosCallback(value?.let { GLFWCursorPosCallbackI { _, xPos, yPos -> it(Vec2d(xPos, yPos)) } })
            field = value
        }

    fun setCursorPosCallback(callback: GLFWCursorPosCallbackI?) {
        glfwSetCursorPosCallback(handle, callback)?.free()
    }*/

    var cursorPosCallback: CursorPosCallbackT? = null
        set(value) {
            glfwSetCursorPosCallback(handle, value?.let { GLFWCursorPosCallbackI { _, xPos, yPos -> it(Vec2d(xPos, yPos)) } })?.free()
            field = value
        }


    /*fun setScrollCallback(callbackFunc: (Vec2d) -> Unit) {
        val callback = GLFWScrollCallbackI { _, xOffset, yOffset -> callbackFunc(Vec2d(xOffset, yOffset)) }
        setScrollCallback(callback)
    }

    fun setScrollCallback(callbackFunc: (Double, Double) -> Unit) {
        val callback = GLFWScrollCallbackI { _, xOffset, yOffset -> callbackFunc(xOffset, yOffset) }
        setScrollCallback(callback)
    }

    var scrollCB: ((Vec2d) -> Unit)? = null
        set(value) {
            setScrollCallback(value?.let { GLFWScrollCallbackI { _, xOffset, yOffset -> it(Vec2d(xOffset, yOffset)) } })
            field = value
        }

    fun setScrollCallback(callback: GLFWScrollCallbackI?) {
        glfwSetScrollCallback(handle, callback)?.free()
    }*/

    var scrollCallback: ScrollCallbackT? = null
        set(value) {
            glfwSetScrollCallback(handle, value?.let { GLFWScrollCallbackI { _, xOffset, yOffset -> it(Vec2d(xOffset, yOffset)) } })?.free()
            field = value
        }


    /*fun setMouseButtonCallback(callbackFunc: (Int, Int, Int) -> Unit) {
        val callback = GLFWMouseButtonCallbackI { _, button, action, mods -> callbackFunc(button, action, mods) }
        setMouseButtonCallback(callback)
    }

    var mouseButtonCB: ((Int, Int, Int) -> Unit)? = null
        set(value) {
            setMouseButtonCallback(value?.let { GLFWMouseButtonCallbackI { _, button, action, mods -> it(button, action, mods) } })
            field = value
        }

    fun setMouseButtonCallback(callback: GLFWMouseButtonCallbackI?) {
        glfwSetMouseButtonCallback(handle, callback)?.free()
    }*/

    var mouseButtonCallback: MouseButtonCallbackT? = null
        set(value) {
            glfwSetMouseButtonCallback(handle, value?.let { GLFWMouseButtonCallbackI { _, button, action, mods -> it(button, action, mods) } })?.free()
            field = value
        }


    /*fun setKeyCallback(callbackFunc: (Int, Int, Int, Int) -> Unit) {
        val callback = GLFWKeyCallbackI { _, key, scancode, action, mods -> callbackFunc(key, scancode, action, mods) }
        setKeyCallback(callback)
    }

    var keyCB: ((Int, Int, Int, Int) -> Unit)? = null
        set(value) {
            setKeyCallback(value?.let { GLFWKeyCallbackI { _, key, scancode, action, mods -> it(key, scancode, action, mods) } })
            field = value
        }

    fun setKeyCallback(callback: GLFWKeyCallbackI?) {
        glfwSetKeyCallback(handle, callback)?.free()
    }*/

    var keyCallback: KeyCallbackT? = null
        set(value) {
            glfwSetKeyCallback(handle, value?.let { GLFWKeyCallbackI { _, key, scancode, action, mods -> it(key, scancode, action, mods) } })?.free()
            field = value
        }


    /*fun setCharCallback(callbackFunc: (Int) -> Unit) {
        val callback = GLFWCharCallbackI { _, codepoint -> callbackFunc(codepoint) }
        setCharCallback(callback)
    }

    var charCB: ((Int) -> Unit)? = null
        set(value) {
            setCharCallback(value?.let { GLFWCharCallbackI { _, codepoint -> it(codepoint) } })
            field = value
        }

    fun setCharCallback(callback: GLFWCharCallbackI?) {
        glfwSetCharCallback(handle, callback)?.free()
    }*/

    var charCallback: CharCallbackT? = null
        set(value) {
            glfwSetCharCallback(handle, value?.let { GLFWCharCallbackI { _, codepoint -> it(codepoint) } })?.free()
            field = value
        }


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

    fun getJoystickButtons(joystickId: Int): ByteBuffer? = glfwGetJoystickButtons(joystickId)
    fun getJoystickAxes(joystickId: Int): FloatBuffer? = glfwGetJoystickAxes(joystickId)

    inline fun loop(block: () -> Unit) {
        while (isOpen) {
            glfwPollEvents()
            block()
            appBuffer.reset()
        }
    }

    infix fun createSurface(instance: VkInstance) = glfw.createWindowSurface(handle, instance)
}