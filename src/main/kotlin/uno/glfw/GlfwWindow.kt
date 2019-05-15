package uno.glfw

import glm_.BYTES
import glm_.bool
import glm_.f
import glm_.i
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import gln.cap.Caps
import gln.misc.GlDebugSeverity
import gln.misc.GlDebugSource
import gln.misc.GlDebugType
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL43C
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Callback
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import uno.kotlin.getOrfirst
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.util.function.BooleanSupplier
import java.util.function.Consumer

/**
 * Created by GBarbieri on 24.04.2017.
 */


/*  TODO
    icon
    glfwGetJoystickHats, GLFW_JOYSTICK_HAT_BUTTONS
    glfwSetJoystickUserPointer
    glfwSetMonitorUserPointer
    glfwSetWindowMaximizeCallback
    glfwGetKeyScancode
    glfwGetWindowContentScale, glfwGetMonitorContentScale and glfwSetWindowContentScaleCallback
    glfwGetGamepadState function, GLFW_GAMEPAD_* and GLFWgamepadstate
    glfwGetJoystickGUID
    glfwGetGamepadName
    glfwJoystickIsGamepad
    glfwUpdateGamepadMappings
 */
open class GlfwWindow(var handle: GlfwWindowHandle) {

    @Throws(RuntimeException::class)
    constructor(windowSize: Vec2i,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) : this(windowSize.x, windowSize.y, title, monitor, position, installCallbacks)

    @Throws(RuntimeException::class)
    constructor(x: Int,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) : this(x, x, title, monitor, position, installCallbacks)

    @Throws(RuntimeException::class)
    constructor(width: Int, height: Int,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) :
            this(GlfwWindowHandle(glfwCreateWindow(width, height, title, monitor, NULL))) {

        this.title = title

        if (position != Vec2i(Int.MIN_VALUE))
            glfwSetWindowPos(handle.L, position.x, position.y)

        if (installCallbacks) {
            installNativeCallbacks() // TODO default too?
        }
    }

    init {
        if (handle.L == NULL) {
            glfw.terminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
    }

    fun installNativeCallbacks() {
        glfwSetCharCallback(handle.L, nCharCallback)
        glfwSetCursorPosCallback(handle.L, nCursorPosCallback)
        glfwSetCursorEnterCallback(handle.L, nCursorEnterCallback)
        glfwSetFramebufferSizeCallback(handle.L, nFramebufferSizeCallback)
        glfwSetKeyCallback(handle.L, nKeyCallback)
        glfwSetMouseButtonCallback(handle.L, nMouseButtonCallback)
        glfwSetScrollCallback(handle.L, nScrollCallback)
        glfwSetWindowCloseCallback(handle.L, nWindowCloseCallback)
        glfwSetWindowContentScaleCallback(handle.L, nWindowContentScaleCallback)
    }

    fun installDefaultCallbacks() {
        cursorPosCallback = defaultCursorPosCallback
        cursorEnterCallback = defaultCursorEnterCallback
        framebufferSizeCallback = defaultFramebufferSizeCallback
        keyCallback = defaultKeyCallback
        mouseButtonCallback = defaultMouseButtonCallback
        scrollCallback = defaultScrollCallback
        windowCloseCallback = defaultWindowCloseCallback
        windowContentScaleCallback = defaultWindowContentScaleCallback
    }

    lateinit var caps: Caps

    /**
     * Spasi: "the ideal option for modern applications is: compatibility context + forwardCompatible. A compatibility context
     * does not do extra validations that may cost performance and with `forwardCompatible == true` you don't risk using
     * legacy functionality by mistake.
     * LWJGL will not try to load deprecated functions, so calling them will crash but the context will actually expose them"
     */
    fun createCapabilities(profile: Caps.Profile = Caps.Profile.COMPATIBILITY, forwardCompatible: Boolean = true) {
        caps = Caps(profile, forwardCompatible)
    }

    var debugProc: Callback? = null

    fun init(show: Boolean = true) {
        makeContextCurrent()
        /*  This line is critical for LWJGL's interoperation with GLFW's OpenGL context,
            or any context that is managed externally.
            LWJGL detects the context that is current in the current thread, creates the GLCapabilities instance and
            makes the OpenGL bindings available for use. */
        GL.createCapabilities()
        show(show)
        if (windowHint.debug) {
            debugProc = GLUtil.setupDebugMessageCallback()
            // turn off notifications only
            GL43C.nglDebugMessageControl(GlDebugSource.DONT_CARE.i, GlDebugType.DONT_CARE.i, GlDebugSeverity.NOTIFICATION.i, 0, NULL, false)
        }
    }

    val isOpen: Boolean
        get() = !shouldClose

    var shouldClose: Boolean
        get() = glfwWindowShouldClose(handle.L)
        set(value) = glfwSetWindowShouldClose(handle.L, value)

    var title: String = ""
        set(value) {
            glfwSetWindowTitle(handle.L, value)
            field = value
        }

    fun setSizeLimit(width: IntRange, height: IntRange) = glfwSetWindowSizeLimits(handle.L, width.start, height.start, width.endInclusive, height.endInclusive)

    var pos = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetWindowPos(handle.L, x, y)
            field(memGetInt(x), memGetInt(y))
        }
        set(value) = glfwSetWindowPos(handle.L, value.x, value.y)

    var size = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetWindowSize(handle.L, x, y)
            field(memGetInt(x), memGetInt(y))
        }
        set(value) = glfwSetWindowSize(handle.L, value.x, value.y)

    fun sizeLimit(minWidth: Int, minHeight: Int, maxWidth: Int, maxHeight: Int) = glfwSetWindowSizeLimits(handle.L, minWidth, minHeight, maxWidth, maxHeight)

    val aspect: Float
        get() = size.aspect
//        set(value) = glfwSetWindowAspectRatio(handle, (value * 1_000).i, 1_000)

    var aspectRatio = Vec2i()
        get() = field(size.x, size.y)
        set(value) = glfwSetWindowAspectRatio(handle.L, value.x, value.y)

    val framebufferSize = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetFramebufferSize(handle.L, x, y)
            field(memGetInt(x), memGetInt(y))
        }

    val frameSize = Vec4i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 4)
            val y = x + Int.BYTES
            val z = y + Int.BYTES
            val w = z + Int.BYTES
            nglfwGetWindowFrameSize(handle.L, x, y, z, w)
            field(memGetInt(x), memGetInt(y), memGetInt(z), memGetInt(w))
        }

    val contentScale = Vec2()
        get() = stak {
            val x = it.nmalloc(Float.BYTES * 2)
            val y = x + Float.BYTES
            nglfwGetWindowContentScale(handle.L, x, y)
            field(memGetFloat(x), memGetFloat(y))
        }

    var opacity: Float
        get() = glfwGetWindowOpacity(handle.L)
        set(value) = glfwSetWindowOpacity(handle.L, value)

    var stickyKeys: Boolean
        get() = glfwGetInputMode(handle.L, GLFW_STICKY_KEYS).bool
        set(value) = glfwSetInputMode(handle.L, GLFW_STICKY_KEYS, value.i)

    var lockKeyMods: Boolean
        get() = glfwGetInputMode(handle.L, GLFW_LOCK_KEY_MODS).bool
        set(value) = glfwSetInputMode(handle.L, GLFW_LOCK_KEY_MODS, value.i)

    fun defaultHints() = glfwDefaultWindowHints()

    fun iconify() = glfwIconifyWindow(handle.L)
    fun restore() = glfwRestoreWindow(handle.L)
    fun maximize() = glfwMaximizeWindow(handle.L)
    fun show(show: Boolean = true) = if (show) glfwShowWindow(handle.L) else glfwHideWindow(handle.L)
    fun hide() = glfwHideWindow(handle.L)
    fun focus() = glfwFocusWindow(handle.L)

    data class Monitor(val monitor: Long, val xPos: Int, val yPos: Int, val width: Int, val height: Int, val refreshRate: Int = GLFW_DONT_CARE)

    var monitor: Monitor
        get() {
            val monitor = glfwGetWindowMonitor(handle.L)
            return Monitor(monitor, pos.x, pos.y, size.x, size.y)
        }
        set(value) = glfwSetWindowMonitor(handle.L, value.monitor, value.xPos, value.yPos, value.width, value.height, value.refreshRate)

    val isFocused: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_FOCUSED).bool
    val isIconified: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_ICONIFIED).bool
    val isMaximized: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_MAXIMIZED).bool
    val isVisible: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_VISIBLE).bool
    val isHovered: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_HOVERED).bool

    var resizable: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_RESIZABLE).bool
        set(value) = glfwSetWindowAttrib(handle.L, GLFW_RESIZABLE, value.i)
    var decorated: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_DECORATED).bool
        set(value) = glfwSetWindowAttrib(handle.L, GLFW_DECORATED, value.i)
    var floating: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_FLOATING).bool
        set(value) = glfwSetWindowAttrib(handle.L, GLFW_FLOATING, value.i)
    var autoIconified: Boolean
        get() = glfwGetWindowAttrib(handle.L, GLFW_AUTO_ICONIFY).bool
        set(value) = glfwSetWindowAttrib(handle.L, GLFW_AUTO_ICONIFY, value.i)

    fun makeContextCurrent() = glfwMakeContextCurrent(handle.L)

    fun unmakeContextCurrent() = glfwMakeContextCurrent(NULL)

    inline fun inContext(block: () -> Unit) {
        glfwMakeContextCurrent(handle.L)
        GL.setCapabilities(caps.caps)
        block()
        glfwMakeContextCurrent(NULL)
    }

    /** for Java */
    fun inContext(runnable: Runnable) {
        glfwMakeContextCurrent(handle.L)
        GL.setCapabilities(caps.caps)
        runnable.run()
        glfwMakeContextCurrent(NULL)
    }

    /** Free the window callbacks and destroy the window and reset its handle back to NULL */
    fun destroy() {
        Callbacks.glfwFreeCallbacks(handle.L)
        debugProc?.free()
        glfwDestroyWindow(handle.L)
        handle = GlfwWindowHandle(NULL)
    }

    var cursorPos = Vec2d()
        get() = stak {
            val x = it.nmalloc(Double.BYTES * 2)
            val y = x + Double.BYTES
            nglfwGetCursorPos(handle.L, x, y)
            field(memGetDouble(x), memGetDouble(y))
        }
        set(value) = glfwSetCursorPos(handle.L, value.x, value.y)

//    var userPointer: Adr
//        get() = glfwGetWindowUserPointer(handle.L)
//        set(value) = glfwSetWindowUserPointer(handle.L, value)

    // ------------------- Callbacks -------------------

    val defaultKey = "0 - default"

    var charCallback: CharCallbackT?
        get() = charCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                charCallbacks[defaultKey] = value
            else
                charCallbacks -= defaultKey
        }

    val charCallbacks = sortedMapOf<String, CharCallbackT>()
    val nCharCallback = GLFWCharCallbackI { _, codePoint -> charCallbacks.values.forEach { it(codePoint) } }


    var cursorPosCallback: CursorPosCallbackT?
        get() = cursorPosCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                cursorPosCallbacks[defaultKey] = value
            else
                cursorPosCallbacks -= defaultKey
        }

    val cursorPosCallbacks = sortedMapOf<String, CursorPosCallbackT>()
    val nCursorPosCallback = GLFWCursorPosCallbackI { _, xPos, yPos -> cursorPosCallbacks.values.forEach { it(Vec2(xPos, yPos)) } }


    var cursorEnterCallback: CursorEnterCallbackT?
        get() = cursorEnterCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                cursorEnterCallbacks[defaultKey] = value
            else
                cursorEnterCallbacks -= defaultKey
        }

    val cursorEnterCallbacks = sortedMapOf<String, CursorEnterCallbackT>()
    val nCursorEnterCallback = GLFWCursorEnterCallbackI { _, entered -> cursorEnterCallbacks.values.forEach { it(entered) } }


    var framebufferSizeCallback: FramebufferSizeCallbackT?
        get() = framebufferSizeCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                framebufferSizeCallbacks[defaultKey] = value
            else
                framebufferSizeCallbacks -= defaultKey
        }
    val framebufferSizeCallbacks = sortedMapOf<String, FramebufferSizeCallbackT>()
    val nFramebufferSizeCallback = GLFWFramebufferSizeCallbackI { _, width, height -> framebufferSizeCallbacks.values.forEach { it(Vec2i(width, height)) } }


    var keyCallback: KeyCallbackT?
        get() = keyCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                keyCallbacks[defaultKey] = value
            else
                keyCallbacks -= defaultKey
        }
    val keyCallbacks = sortedMapOf<String, KeyCallbackT>()
    val nKeyCallback = GLFWKeyCallbackI { _, key, scanCode, action, mods -> keyCallbacks.values.forEach { it(key, scanCode, action, mods) } }


    var mouseButtonCallback: MouseButtonCallbackT?
        get() = mouseButtonCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                mouseButtonCallbacks[defaultKey] = value
            else
                mouseButtonCallbacks -= defaultKey
        }
    val mouseButtonCallbacks = sortedMapOf<String, MouseButtonCallbackT>()
    val nMouseButtonCallback = GLFWMouseButtonCallbackI { _, button, action, mods -> mouseButtonCallbacks.values.forEach { it(button, action, mods) } }


    var scrollCallback: ScrollCallbackT?
        get() = scrollCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                scrollCallbacks[defaultKey] = value
            else
                scrollCallbacks -= defaultKey
        }
    val scrollCallbacks = sortedMapOf<String, ScrollCallbackT>()
    val nScrollCallback = GLFWScrollCallbackI { _, xOffset, yOffset -> scrollCallbacks.values.forEach { it(Vec2d(xOffset, yOffset)) } }


    var windowCloseCallback: WindowCloseCallbackT?
        get() = windowCloseCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                windowCloseCallbacks[defaultKey] = value
            else
                windowCloseCallbacks -= defaultKey
        }
    val windowCloseCallbacks = sortedMapOf<String, WindowCloseCallbackT>()
    val nWindowCloseCallback = GLFWWindowCloseCallbackI { windowCloseCallbacks.values.forEach { it() } }


    var windowContentScaleCallback: WindowContentScaleCallbackT?
        get() = windowContentScaleCallbacks.getOrfirst(defaultKey)
        set(value) {
            if (value != null)
                windowContentScaleCallbacks[defaultKey] = value
            else
                windowContentScaleCallbacks -= defaultKey
        }
    val windowContentScaleCallbacks = sortedMapOf<String, WindowContentScaleCallbackT>()
    val nWindowContentScaleCallback = GLFWWindowContentScaleCallbackI { _, xScale, yScale -> windowContentScaleCallbacks.values.forEach { it(Vec2(xScale, yScale)) } }


    val defaultKeyCallback: KeyCallbackT = { key, scanCode, action, mods -> onKey(Key of key, scanCode, action, mods) }
    val defaultMouseButtonCallback: MouseButtonCallbackT = { button, action, mods -> onMouse(MouseButton of button, action, mods) }
    val defaultCursorPosCallback: CursorPosCallbackT = { pos -> onMouseMoved(pos) }
    val defaultCursorEnterCallback: CursorEnterCallbackT = { entered -> if(entered) onMouseEntered() else onMouseExited() }
    val defaultScrollCallback: ScrollCallbackT = { scroll -> onMouseScrolled(scroll.y.f) }
    val defaultWindowCloseCallback: WindowCloseCallbackT = ::onWindowClosed
    val defaultWindowContentScaleCallback: WindowContentScaleCallbackT = { newScale -> onWindowContentScaled(newScale) }
    val defaultFramebufferSizeCallback: FramebufferSizeCallbackT = { size -> onWindowResized(size) }

    //
    // Event handlers are called by the GLFW callback mechanism and should not be called directly
    //

    open fun onWindowResized(newSize: Vec2i) {}
    open fun onWindowClosed() {}

    // Keyboard handling
    open fun onKey(key: Key, scanCode: Int, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> onKeyPressed(key, mods)
            GLFW_RELEASE -> onKeyReleased(key, mods)
        }
    }

    open fun onKeyPressed(key: Key, mods: Int) {}
    open fun onKeyReleased(key: Key, mods: Int) {}

    // Mouse handling
    open fun onMouse(button: MouseButton, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> onMousePressed(button, mods)
            GLFW_RELEASE -> onMouseReleased(button, mods)
        }
    }

    open fun onMousePressed(button: MouseButton, mods: Int) {}
    open fun onMouseReleased(button: MouseButton, mods: Int) {}
    open fun onMouseMoved(newPos: Vec2) {}
    open fun onMouseEntered() {}
    open fun onMouseExited() {}
    open fun onMouseScrolled(delta: Float) {}

    open fun onWindowContentScaled(newScale: Vec2) {}

    var cursorStatus: CursorStatus
        get() = when (glfwGetInputMode(handle.L, GLFW_CURSOR)) {
            GLFW_CURSOR_NORMAL -> CursorStatus.Normal
            GLFW_CURSOR_HIDDEN -> CursorStatus.Hidden
            GLFW_CURSOR_DISABLED -> CursorStatus.Disabled
            else -> throw Error()
        }
        set(value) = glfwSetInputMode(handle.L, GLFW_CURSOR, when (value) {
            CursorStatus.Normal -> GLFW_CURSOR_NORMAL
            CursorStatus.Hidden -> GLFW_CURSOR_HIDDEN
            CursorStatus.Disabled -> GLFW_CURSOR_DISABLED
        })
    var cursor: GlfwCursor
        get() = throw Error("this is a setter-only property")
        set(value) = glfwSetCursor(handle.L, value.L)

    enum class CursorStatus { Normal, Hidden, Disabled }

    fun isPressed(key: Key) = glfwGetKey(handle.L, key.i) == GLFW_PRESS
    fun isReleased(key: Key) = glfwGetKey(handle.L, key.i) == GLFW_RELEASE

    fun isPressed(button: MouseButton) = glfwGetMouseButton(handle.L, button.i) == GLFW_PRESS
    fun isRelease(button: MouseButton) = glfwGetMouseButton(handle.L, button.i) == GLFW_RELEASE

    val joystick1Buttons: ByteBuffer?
        get() = getJoystickButtons(GLFW_JOYSTICK_1)
    val joystick2Buttons: ByteBuffer?
        get() = getJoystickButtons(GLFW_JOYSTICK_2)
    val joystick3Buttons: ByteBuffer?
        get() = getJoystickButtons(GLFW_JOYSTICK_3)

    fun getJoystickButtons(joystickId: Int): ByteBuffer? = stak {
        val count = it.nmalloc(Int.BYTES)
        val result = nglfwGetJoystickButtons(joystickId, count)
        memByteBufferSafe(result, memGetInt(count))
    }

    val joystick1Axes: FloatBuffer?
        get() = getJoystickAxes(GLFW_JOYSTICK_1)
    val joystick2Axes: FloatBuffer?
        get() = getJoystickAxes(GLFW_JOYSTICK_2)
    val joystick3Axes: FloatBuffer?
        get() = getJoystickAxes(GLFW_JOYSTICK_3)

    fun getJoystickAxes(joystickId: Int): FloatBuffer? = stak {
        val count = it.nmalloc(Int.BYTES)
        val result = nglfwGetJoystickAxes(joystickId, count)
        memFloatBufferSafe(result, memGetInt(count))
    }

    var autoSwap = true

    inline fun loop(block: (MemoryStack) -> Unit) = loop({ isOpen }, block)

    /**
     *  The `stack` passed to `block` will be automatically a stack frame in place
     *  (i.e. it has been pushed exactly once, without popping).
     *  So you can do any allocation on that frame without pushing/popping further
     *  It's the user choice to pass it down the stacktrace to avoid TLS
     */
    inline fun loop(condition: () -> Boolean, block: (MemoryStack) -> Unit) {
        while (condition()) {
            glfwPollEvents()
            stak {
                block(it)
                if (autoSwap)
                    glfwSwapBuffers(handle.L)
            }
        }
    }

    fun loop(block: Consumer<MemoryStack>) = loop(BooleanSupplier { isOpen }, block)

    /**
     *  The `stack` passed to `block` will be automatically a stack frame in place
     *  (i.e. it has been pushed exactly once, without popping).
     *  So you can do any allocation on that frame without pushing/popping further
     *  It's the user choice to pass it down the stacktrace to avoid TLS
     */
    fun loop(condition: BooleanSupplier, block: Consumer<MemoryStack>) {
        while (condition.asBoolean) {
            glfwPollEvents()
            stak {
                block.accept(it)
                if (autoSwap)
                    glfwSwapBuffers(handle.L)
            }
        }
    }

//    infix fun createSurface(instance: VkInstance) = glfw.createWindowSurface(handle, instance)

    fun swapBuffers() = glfwSwapBuffers(handle.L)
    fun present() = swapBuffers()

    fun requestAttention() = glfwRequestWindowAttention(handle.L)

    val hwnd: HWND
        get() = HWND(GLFWNativeWin32.glfwGetWin32Window(handle.L))

    val isCurrent: Boolean
        get() = glfw.currentContext == handle

    companion object {
        infix fun fromWin32Window(hwnd: HWND): GlfwWindow = GlfwWindow(glfw.attachWin32Window(hwnd))
        @JvmStatic
        fun from(handle: Long) = GlfwWindow(GlfwWindowHandle(handle))
    }
}