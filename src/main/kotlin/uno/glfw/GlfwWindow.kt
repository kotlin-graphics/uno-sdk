package uno.glfw

import glm_.BYTES
import glm_.bool
import glm_.f
import glm_.i
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import gln.debug.GlDebugSeverity
import gln.debug.GlDebugSource
import gln.debug.GlDebugType
import kool.stak
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL43C
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Callback
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import org.lwjgl.vulkan.VkInstance
import uno.kotlin.getOrfirst
import java.nio.ByteBuffer
import java.nio.FloatBuffer

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

    constructor(windowSize: Vec2i,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) : this(windowSize.x, windowSize.y, title, monitor, position, installCallbacks)

    constructor(x: Int,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) : this(x, x, title, monitor, position, installCallbacks)

    constructor(width: Int, height: Int,
                title: String,
                monitor: GlfwMonitor = NULL,
                position: Vec2i = Vec2i(Int.MIN_VALUE),
                installCallbacks: Boolean = true) : this(glfwCreateWindow(width, height, title, monitor, NULL)) {

        this.title = title

        if (position != Vec2i(Int.MIN_VALUE))
            glfwSetWindowPos(handle, position.x, position.y)

        if (installCallbacks) {
            glfwSetCharCallback(handle, nCharCallback)
            glfwSetCursorPosCallback(handle, nCursorPosCallback)
            glfwSetFramebufferSizeCallback(handle, nFramebufferSizeCallback)
            glfwSetKeyCallback(handle, nKeyCallback)
            glfwSetMouseButtonCallback(handle, nMouseButtonCallback)
            glfwSetScrollCallback(handle, nScrollCallback)
            glfwSetWindowCloseCallback(handle, nWindowCloseCallback)
            glfwSetWindowContentScaleCallback(handle, nWindowContentScaleCallback)
            cursorPosCallback = defaultCursorPosCallback
            framebufferSizeCallback = defaultFramebufferSizeCallback
            keyCallback = defaultKeyCallback
            mouseButtonCallback = defaultMouseButtonCallback
            scrollCallback = defaultScrollCallback
            windowCloseCallback = defaultWindowCloseCallback
            windowContentScaleCallback = defaultWindowContentScaleCallback
        }
    }

    init {
        if (handle == NULL) {
            glfw.terminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
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
        get() = glfwWindowShouldClose(handle)
        set(value) = glfwSetWindowShouldClose(handle, value)

    var title: String = ""
        set(value) {
            glfwSetWindowTitle(handle, value)
            field = value
        }

    fun setSizeLimit(width: IntRange, height: IntRange) = glfwSetWindowSizeLimits(handle, width.start, height.start, width.endInclusive, height.endInclusive)

    var pos = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetWindowPos(handle, x, y)
            field(memGetInt(x), memGetInt(y))
        }
        set(value) = glfwSetWindowPos(handle, value.x, value.y)

    var size = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetWindowSize(handle, x, y)
            field(memGetInt(x), memGetInt(y))
        }
        set(value) = glfwSetWindowSize(handle, value.x, value.y)

    fun sizeLimit(minWidth: Int, minHeight: Int, maxWidth: Int, maxHeight: Int) = glfwSetWindowSizeLimits(handle, minWidth, minHeight, maxWidth, maxHeight)

    val aspect: Float
        get() = size.aspect
//        set(value) = glfwSetWindowAspectRatio(handle, (value * 1_000).i, 1_000)

    var aspectRatio = Vec2i()
        get() = field(size.x, size.y)
        set(value) = glfwSetWindowAspectRatio(handle, value.x, value.y)

    val framebufferSize = Vec2i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 2)
            val y = x + Int.BYTES
            nglfwGetFramebufferSize(handle, x, y)
            field(memGetInt(x), memGetInt(y))
        }

    val frameSize = Vec4i()
        get() = stak {
            val x = it.nmalloc(Int.BYTES * 4)
            val y = x + Int.BYTES
            val z = y + Int.BYTES
            val w = z + Int.BYTES
            nglfwGetWindowFrameSize(handle, x, y, z, w)
            field(memGetInt(x), memGetInt(y), memGetInt(z), memGetInt(w))
        }

    val contentScale = Vec2()
        get() = stak {
            val x = it.nmalloc(Float.BYTES * 2)
            val y = x + Float.BYTES
            nglfwGetWindowContentScale(handle, x, y)
            field(memGetFloat(x), memGetFloat(y))
        }

    var opacity: Float
        get() = glfwGetWindowOpacity(handle)
        set(value) = glfwSetWindowOpacity(handle, value)

    var stickyKeys: Boolean
        get() = glfwGetInputMode(handle, GLFW_STICKY_KEYS).bool
        set(value) = glfwSetInputMode(handle, GLFW_STICKY_KEYS, value.i)

    var lockKeyMods: Boolean
        get() = glfwGetInputMode(handle, GLFW_LOCK_KEY_MODS).bool
        set(value) = glfwSetInputMode(handle, GLFW_LOCK_KEY_MODS, value.i)

    fun defaultHints() = glfwDefaultWindowHints()

    fun iconify() = glfwIconifyWindow(handle)
    fun restore() = glfwRestoreWindow(handle)
    fun maximize() = glfwMaximizeWindow(handle)
    fun show(show: Boolean = true) = if (show) glfwShowWindow(handle) else glfwHideWindow(handle)
    fun hide() = glfwHideWindow(handle)
    fun focus() = glfwFocusWindow(handle)

    data class Monitor(val monitor: Long, val xPos: Int, val yPos: Int, val width: Int, val height: Int, val refreshRate: Int = GLFW_DONT_CARE)

    var monitor: Monitor
        get() {
            val monitor = glfwGetWindowMonitor(handle)
            return Monitor(monitor, pos.x, pos.y, size.x, size.y)
        }
        set(value) = glfwSetWindowMonitor(handle, value.monitor, value.xPos, value.yPos, value.width, value.height, value.refreshRate)

    val isFocused: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_FOCUSED).bool
    val isIconified: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_ICONIFIED).bool
    val isMaximized: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_MAXIMIZED).bool
    val isVisible: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_VISIBLE).bool
    val isHovered: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_HOVERED).bool

    var resizable: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_RESIZABLE).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_RESIZABLE, value.i)
    var decorated: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_DECORATED).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_DECORATED, value.i)
    var floating: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_FLOATING).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_FLOATING, value.i)
    var autoIconified: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_AUTO_ICONIFY).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_AUTO_ICONIFY, value.i)

    fun makeContextCurrent() = glfwMakeContextCurrent(handle)

    /** Free the window callbacks and destroy the window and reset its handle back to NULL */
    fun destroy() {
        Callbacks.glfwFreeCallbacks(handle)
        debugProc?.free()
        glfwDestroyWindow(handle)
        handle = NULL
    }

    var cursorPos = Vec2d()
        get() = stak {
            val x = it.nmalloc(Double.BYTES * 2)
            val y = x + Double.BYTES
            nglfwGetCursorPos(handle, x, y)
            field(memGetDouble(x), memGetDouble(y))
        }
        set(value) = glfwSetCursorPos(handle, value.x, value.y)


    // ------------------- Callbacks -------------------

    val defaultKey = "0 - default"

    var charCallback: CharCallbackT? = null
        get() = charCallbacks.getOrfirst(defaultKey)
        set(value) {
            charCallbacks[defaultKey] = value
            field = value
        }

    val charCallbacks = sortedMapOf<String, CharCallbackT>()
    val nCharCallback = GLFWCharCallbackI { _, codePoint -> charCallbacks.values.forEach { it(codePoint) } }


    var cursorPosCallback: CursorPosCallbackT? = null
        get() = cursorPosCallbacks.getOrfirst(defaultKey)
        set(value) {
            cursorPosCallbacks[defaultKey] = value
            field = value
        }

    val cursorPosCallbacks = sortedMapOf<String, CursorPosCallbackT>()
    val nCursorPosCallback = GLFWCursorPosCallbackI { _, xPos, yPos -> cursorPosCallbacks.values.forEach { it(Vec2(xPos, yPos)) } }


    var framebufferSizeCallback: FramebufferSizeCallbackT? = null
        get() = framebufferSizeCallbacks.getOrfirst(defaultKey)
        set(value) {
            framebufferSizeCallbacks[defaultKey] = value
            field = value
        }
    val framebufferSizeCallbacks = sortedMapOf<String, FramebufferSizeCallbackT>()
    val nFramebufferSizeCallback = GLFWFramebufferSizeCallbackI { _, width, height -> framebufferSizeCallbacks.values.forEach { it(Vec2i(width, height)) } }


    var keyCallback: KeyCallbackT? = null
        get() = keyCallbacks.getOrfirst(defaultKey)
        set(value) {
            keyCallbacks[defaultKey] = value
            field = value
        }
    val keyCallbacks = sortedMapOf<String, KeyCallbackT>()
    val nKeyCallback = GLFWKeyCallbackI { _, key, scanCode, action, mods -> keyCallbacks.values.forEach { it(key, scanCode, action, mods) } }


    var mouseButtonCallback: MouseButtonCallbackT? = null
        get() = mouseButtonCallbacks.getOrfirst(defaultKey)
        set(value) {
            mouseButtonCallbacks[defaultKey] = value
            field = value
        }
    val mouseButtonCallbacks = sortedMapOf<String, MouseButtonCallbackT>()
    val nMouseButtonCallback = GLFWMouseButtonCallbackI { _, button, action, mods -> mouseButtonCallbacks.values.forEach { it(button, action, mods) } }


    var scrollCallback: ScrollCallbackT? = null
        get() = scrollCallbacks.getOrfirst(defaultKey)
        set(value) {
            scrollCallbacks[defaultKey] = value
            field = value
        }
    val scrollCallbacks = sortedMapOf<String, ScrollCallbackT>()
    val nScrollCallback = GLFWScrollCallbackI { _, xOffset, yOffset -> scrollCallbacks.values.forEach { it(Vec2d(xOffset, yOffset)) } }


    var windowCloseCallback: WindowCloseCallbackT? = null
        get() = windowCloseCallbacks.getOrfirst(defaultKey)
        set(value) {
            windowCloseCallbacks[defaultKey] = value
            field = value
        }
    val windowCloseCallbacks = sortedMapOf<String, WindowCloseCallbackT>()
    val nWindowCloseCallback = GLFWWindowCloseCallbackI { windowCloseCallbacks.values.forEach { it() } }


    var windowContentScaleCallback: WindowContentScaleCallbackT? = null
        get() = windowContentScaleCallbacks.getOrfirst(defaultKey)
        set(value) {
            windowContentScaleCallbacks[defaultKey] = value
            field = value
        }
    val windowContentScaleCallbacks = sortedMapOf<String, WindowContentScaleCallbackT>()
    val nWindowContentScaleCallback = GLFWWindowContentScaleCallbackI { _, xScale, yScale -> windowContentScaleCallbacks.values.forEach { it(Vec2(xScale, yScale)) } }


    val defaultKeyCallback: KeyCallbackT = { key, scanCode, action, mods -> onKey(key, scanCode, action, mods) }
    val defaultMouseButtonCallback: MouseButtonCallbackT = { button, action, mods -> onMouse(button, action, mods) }
    val defaultCursorPosCallback: CursorPosCallbackT = { pos -> onMouseMoved(pos) }
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
    open fun onKey(key: Int, scanCode: Int, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> onKeyPressed(key, mods)
            GLFW_RELEASE -> onKeyReleased(key, mods)
        }
    }

    open fun onKeyPressed(key: Int, mods: Int) {}
    open fun onKeyReleased(key: Int, mods: Int) {}

    // Mouse handling
    open fun onMouse(button: Int, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> onMousePressed(button, mods)
            GLFW_RELEASE -> onMouseReleased(button, mods)
        }
    }

    open fun onMousePressed(button: Int, mods: Int) {}
    open fun onMouseReleased(button: Int, mods: Int) {}
    open fun onMouseMoved(newPos: Vec2) {}
    open fun onMouseScrolled(delta: Float) {}

    open fun onWindowContentScaled(newScale: Vec2) {}

    var cursorStatus: CursorStatus
        get() = when (glfwGetInputMode(handle, GLFW_CURSOR)) {
            GLFW_CURSOR_NORMAL -> CursorStatus.Normal
            GLFW_CURSOR_HIDDEN -> CursorStatus.Hidden
            GLFW_CURSOR_DISABLED -> CursorStatus.Disabled
            else -> throw Error()
        }
        set(value) = glfwSetInputMode(handle, GLFW_CURSOR, when (value) {
            CursorStatus.Normal -> GLFW_CURSOR_NORMAL
            CursorStatus.Hidden -> GLFW_CURSOR_HIDDEN
            CursorStatus.Disabled -> GLFW_CURSOR_DISABLED
        })
    var cursor: GlfwCursor
        get() = NULL
        set(value) = glfwSetCursor(handle, value)

    enum class CursorStatus { Normal, Hidden, Disabled }

    fun pressed(key: Int) = glfwGetKey(handle, key) == GLFW_PRESS
    fun released(key: Int) = glfwGetKey(handle, key) == GLFW_RELEASE

    fun mouseButton(button: Int) = glfwGetMouseButton(handle, button)

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
                    glfwSwapBuffers(handle)
            }
        }
    }

//    infix fun createSurface(instance: VkInstance) = glfw.createWindowSurface(handle, instance)

    fun swapBuffers() = glfwSwapBuffers(handle)
    fun present() = swapBuffers()

    fun requestAttention() = glfwRequestWindowAttention(handle)

    val hwnd: HWND
        get() = GLFWNativeWin32.glfwGetWin32Window(handle)

//    enum class Key(val i: Int) {
//        // Printable keys.
//        SPACE(GLFW_KEY_SPACE),
//        APOSTROPHE(GLFW_KEY_APOSTROPHE),
//        COMMA(GLFW_KEY_COMMA),
//        MINUS(GLFW_KEY_MINUS),
//        PERIOD(GLFW_KEY_PERIOD),
//        SLASH(GLFW_KEY_SLASH),
//        `0`(GLFW_KEY_0),
//        `1`(GLFW_KEY_1),
//        `2`(GLFW_KEY_2),
//        `3`(GLFW_KEY_3),
//        `4`(GLFW_KEY_4),
//        `5`(GLFW_KEY_5),
//        `6`(GLFW_KEY_6),
//        `7`(GLFW_KEY_7),
//        `8`(GLFW_KEY_8),
//        `9`(GLFW_KEY_9),
//        SEMICOLON(GLFW_KEY_SEMICOLON),
//        EQUAL(GLFW_KEY_EQUAL),
//        A(GLFW_KEY_A),
//        B(GLFW_KEY_B),
//        C(GLFW_KEY_C),
//        D(GLFW_KEY_D),
//        E(GLFW_KEY_E),
//        F(GLFW_KEY_F),
//        G(GLFW_KEY_G),
//        H(GLFW_KEY_H),
//        I(GLFW_KEY_I),
//        J(GLFW_KEY_J),
//        K(GLFW_KEY_K),
//        L(GLFW_KEY_L),
//        M(GLFW_KEY_M),
//        N(GLFW_KEY_N),
//        O(GLFW_KEY_O),
//        P(GLFW_KEY_P),
//        Q(GLFW_KEY_Q),
//        R(GLFW_KEY_R),
//        S(GLFW_KEY_S),
//        T(GLFW_KEY_T),
//        U(GLFW_KEY_U),
//        V(GLFW_KEY_V),
//        W(GLFW_KEY_W),
//        X(GLFW_KEY_X),
//        Y(GLFW_KEY_Y),
//        Z(GLFW_KEY_Z),
//        LEFT_BRACKET(GLFW_KEY_LEFT_BRACKET),
//        BACKSLASH(GLFW_KEY_BACKSLASH),
//        RIGHT_BRACKET(GLFW_KEY_RIGHT_BRACKET),
//        GRAVE_ACCENT(GLFW_KEY_GRAVE_ACCENT),
//        WORLD_1(GLFW_KEY_WORLD_1),
//        WORLD_2(GLFW_KEY_WORLD_2),
//        // Function keys.
//        ESCAPE(GLFW_KEY_ESCAPE),
//        ENTER(GLFW_KEY_ENTER),
//        TAB(GLFW_KEY_TAB),
//        BACKSPACE(GLFW_KEY_BACKSPACE),
//        INSERT(GLFW_KEY_INSERT),
//        DELETE(GLFW_KEY_DELETE),
//        RIGHT(GLFW_KEY_RIGHT),
//        LEFT(GLFW_KEY_LEFT),
//        DOWN(GLFW_KEY_DOWN),
//        UP(GLFW_KEY_UP),
//        PAGE_UP(GLFW_KEY_PAGE_UP),
//        PAGE_DOWN(GLFW_KEY_PAGE_DOWN),
//        HOME(GLFW_KEY_HOME),
//        END(GLFW_KEY_END),
//        CAPS_LOCK(GLFW_KEY_CAPS_LOCK),
//        SCROLL_LOCK(GLFW_KEY_SCROLL_LOCK),
//        NUM_LOCK(GLFW_KEY_NUM_LOCK),
//        PRINT_SCREEN(GLFW_KEY_PRINT_SCREEN),
//        PAUSE(GLFW_KEY_PAUSE),
//        F1(GLFW_KEY_F1),
//        F2(GLFW_KEY_F2),
//        F3(GLFW_KEY_F3),
//        F4(GLFW_KEY_F4),
//        F5(GLFW_KEY_F5),
//        F6(GLFW_KEY_F6),
//        F7(GLFW_KEY_F7),
//        F8(GLFW_KEY_F8),
//        F9(GLFW_KEY_F9),
//        F10(GLFW_KEY_F10),
//        F11(GLFW_KEY_F11),
//        F12(GLFW_KEY_F12),
//        F13(GLFW_KEY_F13),
//        F14(GLFW_KEY_F14),
//        F15(GLFW_KEY_F15),
//        F16(GLFW_KEY_F16),
//        F17(GLFW_KEY_F17),
//        F18(GLFW_KEY_F18),
//        F19(GLFW_KEY_F19),
//        F20(GLFW_KEY_F20),
//        F21(GLFW_KEY_F21),
//        F22(GLFW_KEY_F22),
//        F23(GLFW_KEY_F23),
//        F24(GLFW_KEY_F24),
//        F25(GLFW_KEY_F25),
//        KP_0(GLFW_KEY_KP_0),
//        KP_1(GLFW_KEY_KP_1),
//        KP_2(GLFW_KEY_KP_2),
//        KP_3(GLFW_KEY_KP_3),
//        KP_4(GLFW_KEY_KP_4),
//        KP_5(GLFW_KEY_KP_5),
//        KP_6(GLFW_KEY_KP_6),
//        KP_7(GLFW_KEY_KP_7),
//        KP_8(GLFW_KEY_KP_8),
//        KP_9(GLFW_KEY_KP_9),
//        KP_DECIMAL(GLFW_KEY_KP_DECIMAL),
//        KP_DIVIDE(GLFW_KEY_KP_DIVIDE),
//        KP_MULTIPLY(GLFW_KEY_KP_MULTIPLY),
//        KP_SUBTRACT(GLFW_KEY_KP_SUBTRACT),
//        KP_ADD(GLFW_KEY_KP_ADD),
//        KP_ENTER(GLFW_KEY_KP_ENTER),
//        KP_EQUAL(GLFW_KEY_KP_EQUAL),
//        LEFT_SHIFT(GLFW_KEY_LEFT_SHIFT),
//        LEFT_CONTROL(GLFW_KEY_LEFT_CONTROL),
//        LEFT_ALT(GLFW_KEY_LEFT_ALT),
//        LEFT_SUPER(GLFW_KEY_LEFT_SUPER),
//        RIGHT_SHIFT(GLFW_KEY_RIGHT_SHIFT),
//        RIGHT_CONTROL(GLFW_KEY_RIGHT_CONTROL),
//        RIGHT_ALT(GLFW_KEY_RIGHT_ALT),
//        RIGHT_SUPER(GLFW_KEY_RIGHT_SUPER),
//        MENU(GLFW_KEY_MENU),
//        LAST(GLFW_KEY_MENU);
//
//        val isPressed: Boolean
//            get() = glfwGetKey(handle, i) == GLFW_PRESS
//    }
}