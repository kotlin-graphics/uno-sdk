package uno.glfw

import glm_.bool
import glm_.i
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import gln.L
import kool.*
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.memGetAddress
import org.lwjgl.system.MemoryUtil.memUTF8
import org.lwjgl.system.Pointer
import uno.kotlin.*
import java.util.*
import java.util.function.BooleanSupplier
import java.util.function.Consumer
open class GlfwWindow(var handle: Long) {

    @Throws(RuntimeException::class)
    constructor(windowSize: Vec2i,
                title: String = "Glfw Window",
                monitor: GlfwMonitor = GlfwMonitor.NULL,
                share: GlfwWindow? = null,
                position: Vec2i? = null) : this(windowSize.x, windowSize.y, title, monitor, share, position)

    @Throws(RuntimeException::class)
    constructor(x: Int,
                title: String = "Glfw Window",
                monitor: GlfwMonitor = GlfwMonitor.NULL,
                share: GlfwWindow? = null,
                position: Vec2i? = null) : this(x, x, title, monitor, share, position)

    @Throws(RuntimeException::class)
    constructor(width: Int, height: Int,
                title: String = "Glfw Window",
                monitor: GlfwMonitor = GlfwMonitor.NULL,
                share: GlfwWindow? = null,
                position: Vec2i? = null) : this(glfwCreateWindow(width, height, title, monitor.handle, share?.handle ?: MemoryUtil.NULL)) {

        this.title = title

        position?.let { pos = it }
    }

    // --- [ glfwCreateWindow ] ---

    init {
        if (handle == MemoryUtil.NULL) {
            glfw.terminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
    }

    // --- [ glfwDestroyWindow ] ---

    /** Free the window callbacks and destroy the window and reset its handle back to NULL */
    open fun destroy() {
        freeAllCBs()
        glfwDestroyWindow(handle)
//        handle = Ptr.NULL
    }

    /** [JVM] */
    fun freeAllCBs() = Callbacks.glfwFreeCallbacks(handle)

    // --- [ glfwWindowShouldClose ] ---
    // --- [ glfwSetWindowShouldClose ] ---

    var shouldClose: Boolean
        get() = glfwWindowShouldClose(handle)
        set(value) = glfwSetWindowShouldClose(handle, value)

    val isOpen: Boolean
        get() = !shouldClose

    val isNotOpen: Boolean
        get() = !isOpen

    // --- [ glfwSetWindowTitle ] ---
    var title: String = ""
        set(value) {
            glfwSetWindowTitle(handle, value)
            field = value
        }

    // --- [ glfwSetWindowIcon ] ---
    fun setIcon(images: GLFWImage.Buffer?) = nglfwSetWindowIcon(handle, images?.rem ?: 0, images?.adr?.L ?: MemoryUtil.NULL)

    // --- [ glfwGetWindowPos ] ---
    // --- [ glfwSetWindowPos ] ---

    var pos: Vec2i
        get() = readVec2i { x, y -> nglfwGetWindowPos(handle, x, y) }
        set(value) = glfwSetWindowPos(handle, value.x, value.y)

    // --- [ glfwGetWindowSize ] ---
    // --- [ glfwSetWindowSize ] ---

    var size: Vec2i
        get() = readVec2i { x, y -> nglfwGetWindowSize(handle, x, y) }
        set(value) = glfwSetWindowSize(handle, value.x, value.y)

    // --- [ glfwSetWindowSizeLimits ] ---

    fun setSizeLimit(width: IntRange, height: IntRange) = setSizeLimit(width.first, width.last, height.first, height.last)
    fun setSizeLimit(minWidth: Int, minHeight: Int, maxWidth: Int, maxHeight: Int) = glfwSetWindowSizeLimits(handle, minWidth, minHeight, maxWidth, maxHeight)

    // --- [ glfwSetWindowAspectRatio ] ---
    fun setAspectRatio(ratio: Vec2i) = setAspectRatio(ratio.x, ratio.y)
    fun setAspectRatio(numer: Int, denom: Int) = glfwSetWindowAspectRatio(handle, numer, denom)

    /** [JVM] */
    val aspect: Float
        get() = size.aspect

    // --- [ glfwGetFramebufferSize ] ---

    val framebufferSize: Vec2i
        get() = readVec2i { x, y -> nglfwGetFramebufferSize(handle, x, y) }

    // --- [ glfwGetWindowFrameSize ] ---

    val frameSize: Vec4i
        get() = readVec4i { x, y, z, w -> nglfwGetWindowFrameSize(handle, x, y, z, w) }

    // --- [ glfwGetWindowContentScale ] ---

    val contentScale: Vec2
        get() = readVec2 { x, y -> nglfwGetWindowContentScale(handle, x, y) }

    // --- [ glfwGetWindowOpacity ] ---
    // --- [ glfwSetWindowOpacity ] ---

    var opacity: Float
        get() = glfwGetWindowOpacity(handle)
        set(value) = glfwSetWindowOpacity(handle, value)

    // --- [ glfwIconifyWindow ] ---
    fun iconify(iconify: Boolean = true) = if (iconify) glfwIconifyWindow(handle) else restore()

    // --- [ glfwRestoreWindow ] ---
    fun restore() = glfwRestoreWindow(handle)

    // --- [ glfwMaximizeWindow ] ---
    fun maximize() = glfwMaximizeWindow(handle)

    // --- [ glfwShowWindow ] ---
    fun show(show: Boolean = true) = if (show) glfwShowWindow(handle) else hide()

    // --- [ glfwHideWindow ] ---
    fun hide() = glfwHideWindow(handle)

    // --- [ glfwFocusWindow ] ---
    fun focus() = glfwFocusWindow(handle)

    // --- [ glfwRequestWindowAttention ] ---
    fun requestAttention() = glfwRequestWindowAttention(handle)

    // --- [ glfwGetWindowMonitor ] ---
    val monitor: GlfwMonitor
        get() = GlfwMonitor(glfwGetWindowMonitor(handle))

    // --- [ glfwSetWindowMonitor ] ---
    fun setMonitor(monitor: GlfwMonitor, pos: Vec2i, size: Vec2i, refreshRate: Int) = setMonitor(monitor, pos.x, pos.y, size.x, size.y, refreshRate)
    fun setMonitor(monitor: GlfwMonitor, posX: Int, posY: Int, width: Int, height: Int, refreshRate: Int) =
            glfwSetWindowMonitor(handle, monitor.handle, posX, posY, width, height, refreshRate)

    // --- [ glfwGetWindowAttrib ] ---
    // --- [ glfwSetWindowAttrib ] ---

    // Window related attributes
    val focused: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_FOCUSED).bool
    val iconified: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_ICONIFIED).bool
    val maximized: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_MAXIMIZED).bool
    val hovered: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_HOVERED).bool
    val visible: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_VISIBLE).bool
    var resizable: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_RESIZABLE).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_RESIZABLE, value.i)
    var decorated: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_DECORATED).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_DECORATED, value.i)
    var autoIconified: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_AUTO_ICONIFY).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_AUTO_ICONIFY, value.i)
    var floating: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_FLOATING).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_FLOATING, value.i)
    val transparentFramebuffer: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_TRANSPARENT_FRAMEBUFFER).bool
    var focusOnShow: Boolean
        get() = glfwGetWindowAttrib(handle, GLFW_FOCUS_ON_SHOW).bool
        set(value) = glfwSetWindowAttrib(handle, GLFW_FOCUS_ON_SHOW, value.i)

    // Context related attributes
    fun context(block: ContextAttributes.() -> Unit) = ContextAttributes().block()

    inner class ContextAttributes {

        val clientApi: Hints.Context.ClientApi
            get() = when (glfwGetWindowAttrib(handle, GLFW_CLIENT_API)) {
                GLFW_OPENGL_API -> Hints.Context.ClientApi.Gl
                GLFW_OPENGL_ES_API -> Hints.Context.ClientApi.GlEs
                else -> Hints.Context.ClientApi.None
            }

        val creationApi: Hints.Context.CreationApi
            get() = when (glfwGetWindowAttrib(handle, GLFW_CONTEXT_CREATION_API)) {
                GLFW_NATIVE_CONTEXT_API -> Hints.Context.CreationApi.Native
                GLFW_EGL_CONTEXT_API -> Hints.Context.CreationApi.Egl
                else -> Hints.Context.CreationApi.OsMesa
            }

        val version: String
            get() = "$major.$minor"

        val major: Int
            get() = glfwGetWindowAttrib(handle, GLFW_CONTEXT_VERSION_MAJOR)

        val minor: Int
            get() = glfwGetWindowAttrib(handle, GLFW_CONTEXT_VERSION_MINOR)

        val forwardComp: Boolean
            get() = glfwGetWindowAttrib(handle, GLFW_OPENGL_FORWARD_COMPAT).bool

        val debug: Boolean
            get() = glfwGetWindowAttrib(handle, GLFW_OPENGL_DEBUG_CONTEXT).bool

        val profile: Hints.Context.Profile
            get() = when (glfwGetWindowAttrib(handle, GLFW_OPENGL_PROFILE)) {
                GLFW_OPENGL_CORE_PROFILE -> Hints.Context.Profile.Core
                GLFW_OPENGL_COMPAT_PROFILE -> Hints.Context.Profile.Compat
                else -> Hints.Context.Profile.Any
            }

        val robustness: Hints.Context.Robustness
            get() = when (glfwGetWindowAttrib(handle, GLFW_CONTEXT_ROBUSTNESS)) {
                GLFW_NO_ROBUSTNESS -> Hints.Context.Robustness.None
                GLFW_NO_RESET_NOTIFICATION -> Hints.Context.Robustness.NoResetNotification
                else -> Hints.Context.Robustness.LoseContextOnReset
            }

        val releaseBehaviour: Hints.Context.ReleaseBehaviour
            get() = when (glfwGetWindowAttrib(handle, GLFW_CONTEXT_RELEASE_BEHAVIOR)) {
                GLFW_ANY_RELEASE_BEHAVIOR -> Hints.Context.ReleaseBehaviour.Any
                GLFW_RELEASE_BEHAVIOR_FLUSH -> Hints.Context.ReleaseBehaviour.Flush
                else -> Hints.Context.ReleaseBehaviour.None
            }

        val noError: Boolean
            get() = glfwGetWindowAttrib(handle, GLFW_CONTEXT_NO_ERROR).bool
    }

    // --- [ glfwSetWindowUserPointer ] ---
    // --- [ glfwGetWindowUserPointer ] ---

    var userPointer: Ptr<*>
        get() = glfwGetWindowUserPointer(handle).toPtr<Nothing>()
        set(value) = glfwSetWindowUserPointer(handle, value.adr.L)

    // --- [ glfwSetWindowPosCallback ] ---
    var posCB: WindowPosCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowPosCallbackI { wnd, x, y -> it(GlfwWindow(wnd), Vec2i(x, y)) } }
            glfwSetWindowPosCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowSizeCallback ] ---
    var sizeCB: WindowSizeCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowSizeCallbackI { wnd, x, y -> it(GlfwWindow(wnd), Vec2i(x, y)) } }
            glfwSetWindowSizeCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowCloseCallback ] ---

    var closeCB: WindowCloseCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowCloseCallbackI { wnd -> it(GlfwWindow(wnd)) } }
            glfwSetWindowCloseCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowRefreshCallback ] ---

    var refreshCB: WindowRefreshCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowRefreshCallbackI { wnd -> it(GlfwWindow(wnd)) } }
            glfwSetWindowRefreshCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowFocusCallback ] ---

    var focusCB: WindowFocusCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowFocusCallbackI { wnd, focused -> it(GlfwWindow(wnd), focused) } }
            glfwSetWindowFocusCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowIconifyCallback ] ---

    var iconifyCB: WindowIconifyCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowIconifyCallbackI { wnd, iconify -> it(GlfwWindow(wnd), iconify) } }
            glfwSetWindowIconifyCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowMaximizeCallback ] ---

    var maximizeCB: WindowMaximizeCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowMaximizeCallbackI { wnd, maximized -> it(GlfwWindow(wnd), maximized) } }
            glfwSetWindowMaximizeCallback(handle, cb)?.free()
        }

    // --- [ glfwSetFramebufferSizeCallback ] ---

    var framebufferSizeCB: FramebufferSizeCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWFramebufferSizeCallbackI { wnd, width, height -> it(GlfwWindow(wnd), Vec2i(width, height)) } }
            glfwSetFramebufferSizeCallback(handle, cb)?.free()
        }

    // --- [ glfwSetWindowContentScaleCallback ] ---

    var contentScaleCB: WindowContentScaleCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWWindowContentScaleCallbackI { wnd, xScale, yScale -> it(GlfwWindow(wnd), Vec2(xScale, yScale)) } }
            glfwSetWindowContentScaleCallback(handle, cb)?.free()
        }

    // --- [ glfwGetInputMode ] ---
    // --- [ glfwSetInputMode ] ---

    enum class CursorMode(val i: Int) {
        Normal(GLFW_CURSOR_NORMAL),
        Hidden(GLFW_CURSOR_HIDDEN),
        Disabled(GLFW_CURSOR_DISABLED),
        Captured(GLFW_CURSOR_CAPTURED);

        companion object {
            infix fun of(i: Int) = values().first { it.i == i }
        }
    }

    var cursorMode: CursorMode
        get() = CursorMode of glfwGetInputMode(handle, GLFW_CURSOR)
        set(value) = glfwSetInputMode(handle, GLFW_CURSOR, value.i)

    var stickyKeys: Boolean
        get() = glfwGetInputMode(handle, GLFW_STICKY_KEYS).bool
        set(value) = glfwSetInputMode(handle, GLFW_STICKY_KEYS, value.i)

    var stickyMouseButtons: Boolean
        get() = glfwGetInputMode(handle, GLFW_STICKY_MOUSE_BUTTONS).bool
        set(value) = glfwSetInputMode(handle, GLFW_STICKY_MOUSE_BUTTONS, value.i)

    var lockKeyMods: Boolean
        get() = glfwGetInputMode(handle, GLFW_LOCK_KEY_MODS).bool
        set(value) = glfwSetInputMode(handle, GLFW_LOCK_KEY_MODS, value.i)

    var rawMouseMotion: Boolean
        get() = glfwGetInputMode(handle, GLFW_RAW_MOUSE_MOTION).bool
        set(value) = glfwSetInputMode(handle, GLFW_RAW_MOUSE_MOTION, value.i)

    // --- [ glfwRawMouseMotionSupported ] ---
    val rawMouseMotionSupported: Boolean
        get() = glfwRawMouseMotionSupported()

    // --- [ glfwGetKeyName ] ---
    // --- [ glfwGetKeyScancode ] ---
    // -> Key

    // --- [ glfwGetKey ] ---
    infix fun isPressed(key: Key): Boolean = glfwGetKey(handle, key.i) == GLFW_PRESS
    infix fun isReleased(key: Key): Boolean = glfwGetKey(handle, key.i) == GLFW_RELEASE

    // --- [ glfwGetMouseButton ] ---
    infix fun isPressed(button: MouseButton): Boolean = glfwGetMouseButton(handle, button.i) == GLFW_PRESS
    infix fun isRelease(button: MouseButton): Boolean = glfwGetMouseButton(handle, button.i) == GLFW_RELEASE

    // --- [ glfwGetCursorPos ] ---
    // --- [ glfwSetCursorPos ] ---
    var cursorPos: Vec2d
        get() = readVec2d { x, y -> nglfwGetCursorPos(handle, x, y) }
        set(value) = glfwSetCursorPos(handle, value.x, value.y)


    // --- [ glfwSetCursor ] ---
    var cursor: GlfwCursor
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) = glfwSetCursor(handle, value.handle)

    // --- [ glfwSetKeyCallback ] ---
    var keyCB: KeyCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWKeyCallbackI { wnd, key, scanCode, action, mods -> it(GlfwWindow(wnd), Key of key, scanCode, InputAction of action, mods) } }
            glfwSetKeyCallback(handle, cb)?.free()
        }

    // --- [ glfwSetCharCallback ] ---
    var charCB: CharCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWCharCallbackI { wnd, codePoint -> it(GlfwWindow(wnd), codePoint) } }
            glfwSetCharCallback(handle, cb)?.free()
        }

    // --- [ glfwSetCharModsCallback ] ---
    var charModsCB: CharModsCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWCharModsCallbackI { wnd, codePoint, mods -> it(GlfwWindow(wnd), codePoint, mods) } }
            glfwSetCharModsCallback(handle, cb)?.free()
        }

    // --- [ glfwSetMouseButtonCallback ] ---
    var mouseButtonCB: MouseButtonCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWMouseButtonCallbackI { wnd, button, action, mods -> it(GlfwWindow(wnd), button, action, mods) } }
            glfwSetMouseButtonCallback(handle, cb)?.free()
        }

    // --- [ glfwSetCursorPosCallback ] ---

    var cursorPosCB: CursorPosCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWCursorPosCallbackI { wnd, xPos, yPos -> it(GlfwWindow(wnd), Vec2d(xPos, yPos)) } }
            glfwSetCursorPosCallback(handle, cb)?.free()
        }

    // --- [ glfwSetCursorEnterCallback ] ---
    var cursorEnterCB: CursorEnterCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWCursorEnterCallbackI { wnd, entered -> it(GlfwWindow(wnd), entered) } }
            glfwSetCursorEnterCallback(handle, cb)?.free()
        }

    // --- [ glfwSetScrollCallback ] ---
    var scrollCB: ScrollCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWScrollCallbackI { wnd, xOffset, yOffset -> it(GlfwWindow(wnd), Vec2d(xOffset, yOffset)) } }
            glfwSetScrollCallback(handle, cb)?.free()
        }

    // --- [ glfwSetDropCallback ] ---
    var dropCB: DropCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let {
                GLFWDropCallbackI { wnd, count, ppNames ->
                    it(GlfwWindow(wnd), Array(count) {
                        memUTF8(memGetAddress(ppNames + it * Pointer.POINTER_SIZE))
                    })
                }
            }
            glfwSetDropCallback(handle, cb)?.free()
        }

    // --- [ glfwGetClipboardString ] ---
    // --- [ glfwSetClipboardString ] ---
//    Parameters
//    [in]	window	Deprecated. Any valid window or NULL.
    // -> glfw object
    var clipboardString: String?
        get() = glfwGetClipboardString(handle)
        set(value) {
            value?.let { glfwSetClipboardString(handle, it) }
        }

    // --- [ glfwMakeContextCurrent ] ---
    fun makeCurrent(current: Boolean = true) = glfwMakeContextCurrent(if (current) handle else MemoryUtil.NULL)

    // --- [ glfwSwapBuffers ] ---

    fun swapBuffers() = glfwSwapBuffers(handle)

    /** [JVM] alias */
    fun present() = swapBuffers()

    inline fun inContext(block: () -> Unit) {
        makeCurrent()
        block()
        makeCurrent(false)
    }

    /** for Java */
    open fun inContext(runnable: Runnable) {
        makeCurrent()
        runnable.run()
        makeCurrent(false)
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
            glfw.pollEvents()
            stack {
                block(it)
                if (autoSwap)
                    swapBuffers()
            }
        }
    }

    fun loop(block: Consumer<MemoryStack>) = loop({ isOpen }, block)

    /**
     *  The `stack` passed to `block` will be automatically a stack frame in place
     *  (i.e. it has been pushed exactly once, without popping).
     *  So you can do any allocation on that frame without pushing/popping further
     *  It's the user choice to pass it down the stacktrace to avoid TLS
     */
    fun loop(condition: BooleanSupplier, block: Consumer<MemoryStack>) {
        while (condition.asBoolean) {
            glfw.pollEvents()
            stack {
                block.accept(it)
                if (autoSwap)
                    glfwSwapBuffers(handle)
            }
        }
    }

//    infix fun createSurface(instance: VkInstance) = glfw.createWindowSurface(handle, instance)


    val hwnd: HWND
        get() = HWND(GLFWNativeWin32.glfwGetWin32Window(handle))

    val isCurrent: Boolean
        get() = glfw.currentContext == this

    companion object {
        infix fun fromWin32Window(hwnd: HWND): GlfwWindow = glfw attachWin32Window hwnd

        @JvmStatic
        infix fun from(handle: Long): GlfwWindow = GlfwWindow(handle)
    }
}