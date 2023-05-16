package uno.glfw

import glm_.bool
import kool.*
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil.*
import uno.kotlin.*
import kotlin.contracts.ExperimentalContracts

/**
 * Created by elect on 22/04/17.
 */

object glfw {

    // --- [ glfwInit ] ---

    /** Short version of:
     *  glfw.init()
     *  glfw.windowHint {
     *      context.version = "3.2"
     *      windowHint.profile = "core"
     *  }
     *  + default error callback
     */
    @Throws(RuntimeException::class)
    fun init(version: String, profile: Hints.Context.Profile = Hints.Context.Profile.Core, installDefaultErrorCB: Boolean = true) {
        init(installDefaultErrorCB)
        hints {
            context.version = version
            val v = version[0].parseInt() * 10 + version[1].parseInt()
            if (v >= 32) // The concept of a core profile does not exist prior to OpenGL 3.2
                context.profile = profile
        }
    }

    @Throws(RuntimeException::class)
    fun init(errorCallback: GLFWErrorCallbackT) {
        this.errorCB = errorCallback
        init()
    }

    @Throws(RuntimeException::class)
    fun init(installDefaultErrorCB: Boolean) {
        if (installDefaultErrorCB)
            errorCB = defaultErrorCB
        init()
    }

    @Throws(RuntimeException::class)
    fun init() {

        if (!glfwInit())
            throw RuntimeException("Unable to initialize GLFW")

        // This window hint is required to use OpenGL 3.1+ on macOS
        if (org.lwjgl.system.Platform.get() == org.lwjgl.system.Platform.MACOSX)
            hints.context.forwardComp = true
    }

    // --- [ glfwTerminate ] ---

    fun terminate() {
        glfwTerminate()
        errorCB = null // ~ free the current cb
    }

    // --- [ glfwInitHint ] ---

    val initHint = InitHint()
    fun initHint(block: InitHint.() -> Unit) = initHint.block()

    // --- [ glfwGetVersion ] ---

    object version {

        val major: Int get() = GLFW_VERSION_MAJOR
        val minor: Int get() = GLFW_VERSION_MAJOR
        val revision: Int get() = GLFW_VERSION_REVISION

        // --- [ glfwGetVersionString ] ---
        override fun toString(): String = readVec3i { x, y, z -> nglfwGetVersion(x, y, z) }.run { "$x.$y.$z" }
    }

    // --- [ glfwGetError ] ---

    enum class Error(val i: Int) {
        None(GLFW_NO_ERROR),
        NotInitialized(GLFW_NOT_INITIALIZED),
        NoCurrentContext(GLFW_NO_CURRENT_CONTEXT),
        InvalidEnum(GLFW_INVALID_ENUM),
        InvalidValue(GLFW_INVALID_VALUE),
        OutOfMemory(GLFW_OUT_OF_MEMORY),
        ApiUnavailable(GLFW_API_UNAVAILABLE),
        VersionUnavailable(GLFW_VERSION_UNAVAILABLE),
        PlatformError(GLFW_PLATFORM_ERROR),
        FormatUnavailable(GLFW_FORMAT_UNAVAILABLE),
        NoWindowContext(GLFW_NO_WINDOW_CONTEXT),
        CursorUnavailable(GLFW_CURSOR_UNAVAILABLE),
        FeatureUnavailable(GLFW_FEATURE_UNAVAILABLE),
        FeatureUnimplemented(GLFW_FEATURE_UNIMPLEMENTED),
        PlatformUnavailable(GLFW_PLATFORM_UNAVAILABLE);

        companion object {
            infix fun of(i: Int) = values().first { it.i == i }
        }
    }

    val error: Error
        get() = stack {
            val ppDescription = it.ptrLong()
            val code = nglfwGetError(ppDescription.adr.toLong())
            errorDescription = when {
                code != GLFW_NO_ERROR -> memUTF8(ppDescription[0])
                else -> ""
            }
            Error of code
        }

    var errorDescription = ""

    // --- [ glfwSetErrorCallback ] ---

    val defaultErrorCB: GLFWErrorCallbackT = { error, description ->
        var text = error.toString()
        if (description.isNotEmpty())
            text += "$text: $description"
        System.err.println(text)
    }

    var errorCB: GLFWErrorCallbackT? = null
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            field = value
            val cb = value?.let { GLFWErrorCallbackI { err, ppDesc -> value(Error of err, memUTF8(ppDesc)) } }
            glfwSetErrorCallback(cb)?.free()
        }

    // --- [ glfwGetPlatform ] ---

    enum class Platform(val i: Int) {
        Any(0x60000),
        Win32(0x60001),
        Cocoa(0x60002),
        Wayland(0x60003),
        X11(0x60004),
        Null(0x60005);

        // --- [ glfwPlatformSupported ] ---
        val supported: Boolean
            get() = glfwPlatformSupported(i)

        companion object {
            infix fun of(i: Int) = values().first { it.i == i }
        }
    }

    val platform: Platform
        get() = Platform of glfwGetPlatform()

    // --- [ glfwGetMonitors ] ---
    val monitors: GlfwMonitors
        get() = stack { s ->
            val pCount = s.ptrInt()
            val pMonitors = nglfwGetMonitors(pCount.adr.toLong())
            GlfwMonitors(LongArray(pCount[0]) { memGetAddress(pMonitors + it shl 3) })
        }

    // --- [ glfwGetPrimaryMonitor ] ---

    val primaryMonitor: GlfwMonitor
        get() = GlfwMonitor(glfwGetPrimaryMonitor())

    /** [JVM] */
    val primaryMonitorVideoMode: GlfwVidMode?
        get() = when (val pMode = nglfwGetVideoMode(primaryMonitor.handle)) {
            NULL -> null
            else -> GlfwVidMode(pMode.toPtr())
        }

    // --- [ glfwGetMonitorPos ] ---
    // --- [ glfwGetMonitorWorkarea ] ---
    // --- [ glfwGetMonitorPhysicalSize ] ---
    // --- [ glfwGetMonitorContentScale ] ---
    // --- [ glfwGetMonitorName ] ---
    // --- [ glfwSetMonitorUserPointer ] ---
    // --- [ glfwGetMonitorUserPointer ] ---
    // -> GlfwMonitor class

    // --- [ glfwSetMonitorCallback ] ---
    var monitorCallback: GlfwMonitorFun?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWMonitorCallbackI { monitor, event -> it(GlfwMonitor(monitor), event == GLFW_CONNECTED) } }
            glfwSetMonitorCallback(cb)?.free()
        }

    // --- [ glfwGetVideoModes ] ---
    // --- [ glfwGetVideoMode ] ---
    // --- [ glfwSetGamma ] ---
    // --- [ glfwGetGammaRamp ] ---
    // --- [ glfwSetGammaRamp ] ---
    // -> GlfwMonitor class

    // --- [ glfwDefaultWindowHints ] ---

    /** [JVM] alias for defaultWindowHints */
    fun resetWindowHints() = glfwDefaultWindowHints()
    fun defaultWindowHints() = glfwDefaultWindowHints()

    // --- [ glfwWindowHint ] ---
    // --- [ glfwWindowHintString ] ---

    val hints = Hints()
    fun hints(block: Hints.() -> Unit) = hints.block()

    // --- [ glfwCreateWindow ] ---
    // --- [ glfwDestroyWindow ] ---
    // --- [ glfwWindowShouldClose ] ---
    // --- [ glfwSetWindowShouldClose ] ---
    // --- [ glfwSetWindowTitle ] ---
    // --- [ glfwSetWindowIcon ] ---
    // --- [ glfwGetWindowPos ] ---
    // --- [ glfwSetWindowPos ] ---
    // --- [ glfwGetWindowSize ] ---
    // --- [ glfwSetWindowSizeLimits ] ---
    // --- [ glfwSetWindowAspectRatio ] ---
    // --- [ glfwSetWindowSize ] ---
    // --- [ glfwGetFramebufferSize ] ---
    // --- [ glfwGetWindowFrameSize ] ---
    // --- [ glfwGetWindowContentScale ] ---
    // --- [ glfwGetWindowOpacity ] ---
    // --- [ glfwSetWindowOpacity ] ---
    // --- [ glfwIconifyWindow ] ---
    // --- [ glfwRestoreWindow ] ---
    // --- [ glfwMaximizeWindow ] ---
    // --- [ glfwShowWindow ] ---
    // --- [ glfwHideWindow ] ---
    // --- [ glfwFocusWindow ] ---
    // --- [ glfwRequestWindowAttention ] ---
    // --- [ glfwGetWindowMonitor ] ---
    // --- [ glfwSetWindowMonitor ] ---
    // --- [ glfwGetWindowAttrib ] ---
    // --- [ glfwSetWindowAttrib ] ---
    // --- [ glfwSetWindowUserPointer ] ---
    // --- [ glfwGetWindowUserPointer ] ---
    // --- [ glfwSetWindowPosCallback ] ---
    // --- [ glfwSetWindowSizeCallback ] ---
    // --- [ glfwSetWindowCloseCallback ] ---
    // --- [ glfwSetWindowRefreshCallback ] ---
    // --- [ glfwSetWindowFocusCallback ] ---
    // --- [ glfwSetWindowIconifyCallback ] ---
    // --- [ glfwSetWindowMaximizeCallback ] ---
    // --- [ glfwSetFramebufferSizeCallback ] ---
    // --- [ glfwSetWindowContentScaleCallback ] ---
    // -> GlfwWindow

    // --- [ glfwPollEvents ] ---
    fun pollEvents() = glfwPollEvents()

    // --- [ glfwWaitEvents ] ---
    fun waitEvents() = glfwWaitEvents()

    // --- [ glfwWaitEventsTimeout ] ---
    fun waitEventsTimeout(timeout: Double) = glfwWaitEventsTimeout(timeout)

    // --- [ glfwPostEmptyEvent ] ---
    fun postEmptyEvent() = glfwPostEmptyEvent()

    // --- [ glfwGetInputMode ] ---
    // --- [ glfwSetInputMode ] ---
    // -> GlfwWindow

    // --- [ glfwRawMouseMotionSupported ] ---
    val rawMouseMotionSupported: Boolean
        get() = glfwRawMouseMotionSupported()

    // --- [ glfwGetKeyName ] ---
    // also on Key enum
    fun getKeyName(scancode: Int): String? = glfwGetKeyName(GLFW_KEY_UNKNOWN, scancode)

    // --- [ glfwGetKeyScancode ] ---
    // -> Key

    // --- [ glfwGetKey ] ---
    // --- [ glfwGetMouseButton ] ---
    // --- [ glfwGetCursorPos ] ---
    // --- [ glfwSetCursorPos ] ---
    // -> GlfwWindow

    // --- [ glfwCreateCursor ] ---
    // --- [ glfwDestroyCursor ] ---
    // -> GlfwCursor

    // --- [ glfwCreateStandardCursor ] ---

    enum class CursorShape(val i: Int) {
        Arrow(GLFW_ARROW_CURSOR),
        Ibeam(GLFW_IBEAM_CURSOR),
        Crosshair(GLFW_CROSSHAIR_CURSOR),
        PointingHand(GLFW_POINTING_HAND_CURSOR),
        ResizeEW(GLFW_RESIZE_EW_CURSOR),
        ResizeNS(GLFW_RESIZE_NS_CURSOR),
        ResizeNWSE(GLFW_RESIZE_NWSE_CURSOR),
        ResizeNESW(GLFW_RESIZE_NESW_CURSOR),
        ResizeAll(GLFW_RESIZE_ALL_CURSOR),
        NotAllowed(GLFW_NOT_ALLOWED_CURSOR)
    }

    // --- [ glfwCreateStandardCursor ] ---
    infix fun createStandardCursor(shape: CursorShape): GlfwCursor = GlfwCursor(glfwCreateStandardCursor(shape.i))

    // --- [ glfwDestroyCursor ] ---
    // -> GlfwCursor

    // --- [ glfwSetCursor ] ---
    // --- [ glfwSetKeyCallback ] ---
    // --- [ glfwSetCharCallback ] ---
    // --- [ glfwSetCharModsCallback ] ---
    // --- [ glfwSetMouseButtonCallback ] ---
    // --- [ glfwSetCursorPosCallback ] ---
    // --- [ glfwSetCursorEnterCallback ] ---
    // --- [ glfwSetScrollCallback ] ---
    // --- [ glfwSetDropCallback ] ---
    // -> GlfwWindow

    // --- [ glfwJoystickPresent ] ---
    // --- [ glfwGetJoystickAxes ] ---
    // --- [ glfwGetJoystickButtons ] ---
    // --- [ glfwGetJoystickHats ] ---
    // --- [ glfwGetJoystickName ] ---
    // --- [ glfwGetJoystickGUID ] ---
    // --- [ glfwSetJoystickUserPointer ] ---
    // --- [ glfwGetJoystickUserPointer ] ---
    // --- [ glfwJoystickIsGamepad ] ---
    // --- [ glfwSetJoystickCallback ] ---
    // -> Joystick

    // --- [ glfwUpdateGamepadMappings ] ---
    infix fun updateGamepadMappings(string: String): Boolean = stack.writeAsciiToAdr(string) { nglfwUpdateGamepadMappings(it.toLong()) }.bool

    // --- [ glfwGetGamepadName ] ---
    // --- [ glfwGetGamepadState ] ---
    // -> Joystick

    // --- [ glfwSetClipboardString ] ---
    // --- [ glfwGetClipboardString ] ---
//    Parameters
//    [in]	window	Deprecated. Any valid window or NULL.
    // -> here
    var clipboardString: String?
        get() = glfwGetClipboardString(NULL)
        set(value) {
            value?.let { glfwSetClipboardString(NULL, it) }
        }

    // --- [ glfwGetTime ] ---
    // --- [ glfwSetTime ] ---
    var time: Seconds
        get() = glfwGetTime()
        set(value) = glfwSetTime(value)

    // --- [ glfwGetTimerValue ] ---
    val timerValue: ULong
        get() = glfwGetTimerValue().toULong()

    // --- [ glfwGetTimerFrequency ] ---
    val timerFrequency: Hz
        get() = glfwGetTimerFrequency().toULong()

    // --- [ glfwMakeContextCurrent ] ---
    // --- [ glfwGetCurrentContext ] ---
    // also -> GlfwWindow
    var currentContext: GlfwWindow
        get() = GlfwWindow(glfwGetCurrentContext())
        set(value) = glfwMakeContextCurrent(value.handle)

    // --- [ glfwSwapBuffers ] ---
    // -> GlfwWindow

    // --- [ glfwSwapInterval ] ---

    var swapInterval: VSync = VSync.ON
        set(value) {
            glfwSwapInterval(value.i)
            field = value
        }

    // --- [ glfwExtensionSupported ] ---
    fun extensionSupported(extension: String): Boolean = glfwExtensionSupported(extension)

    // --- [ glfwGetProcAddress ] ---
    fun getProcAddress(procName: String): GLFWglproc = glfwGetProcAddress(procName)


//    val resolution: Vec2i
//        get() = Vec2i(videoMode.width, videoMode.height)


    infix fun attachWin32Window(handle: HWND): GlfwWindow = GlfwWindow(GLFWNativeWin32.glfwAttachWin32Window(handle.L, NULL))

}

/** [JVM] */
// TODO switch to `operator` once K2 Kotlin compiler is released
@OptIn(ExperimentalContracts::class)
inline fun glfw(block: glfw.() -> Unit) {
    kotlin.contracts.contract { callsInPlace(block, kotlin.contracts.InvocationKind.EXACTLY_ONCE) }
    glfw.block()
}
