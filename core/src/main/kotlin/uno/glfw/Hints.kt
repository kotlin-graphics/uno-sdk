package uno.glfw

import glm_.i
import org.lwjgl.glfw.GLFW.*

class Hints {

    // Window related hints

    val window = Window()

    fun window(block: Window.() -> Unit) = window.block()

    class Window {

        var resizable = true
            set(value) {
                glfwWindowHint(GLFW_RESIZABLE, value.i)
                field = value
            }

        var visible = true
            set(value) {
                glfwWindowHint(GLFW_VISIBLE, value.i)
                field = value
            }

        var decorated = true
            set(value) {
                glfwWindowHint(GLFW_DECORATED, value.i)
                field = value
            }

        var focused = true
            set(value) {
                glfwWindowHint(GLFW_FOCUSED, value.i)
                field = value
            }

        var autoIconify = true
            set(value) {
                glfwWindowHint(GLFW_AUTO_ICONIFY, value.i)
                field = value
            }

        var floating = false
            set(value) {
                glfwWindowHint(GLFW_FLOATING, value.i)
                field = value
            }

        var maximized = false
            set(value) {
                glfwWindowHint(GLFW_MAXIMIZED, value.i)
                field = value
            }

        var centerCursor = true
            set(value) {
                glfwWindowHint(GLFW_CENTER_CURSOR, value.i)
                field = value
            }

        var transparentFramebuffer = false
            set(value) {
                glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, value.i)
                field = value
            }

        var focusOnShow = true
            set(value) {
                glfwWindowHint(GLFW_FOCUS_ON_SHOW, value.i)
                field = value
            }

        var scaleToMonitor = false
            set(value) {
                glfwWindowHint(GLFW_SCALE_TO_MONITOR, value.i)
                field = value
            }

        var mousePassthrough = false
            set(value) {
                glfwWindowHint(GLFW_MOUSE_PASSTHROUGH, value.i)
                field = value
            }
    }

    // Framebuffer related hints

    val framebuffer = Framebuffer()

    fun framebuffer(block: Framebuffer.() -> Unit) = framebuffer.block()
    class Framebuffer {

        var redBits = 8
            set(value) {
                glfwWindowHint(GLFW_RED_BITS, value)
                field = value
            }

        var greenBits = 8
            set(value) {
                glfwWindowHint(GLFW_GREEN_BITS, value)
                field = value
            }

        var blueBits = 8
            set(value) {
                glfwWindowHint(GLFW_BLUE_BITS, value)
                field = value
            }

        var alphaBits = 8
            set(value) {
                glfwWindowHint(GLFW_ALPHA_BITS, value)
                field = value
            }

        var depthBits = 24
            set(value) {
                glfwWindowHint(GLFW_DEPTH_BITS, value)
                field = value
            }

        var stencilBits = 8
            set(value) {
                glfwWindowHint(GLFW_STENCIL_BITS, value)
                field = value
            }

        var accumRedBits = 0
            set(value) {
                glfwWindowHint(GLFW_ACCUM_RED_BITS, value)
                field = value
            }

        var accumGreenBits = 0
            set(value) {
                glfwWindowHint(GLFW_ACCUM_GREEN_BITS, value)
                field = value
            }

        var accumBlueBits = 0
            set(value) {
                glfwWindowHint(GLFW_ACCUM_BLUE_BITS, value)
                field = value
            }

        var accumAlphaBits = 0
            set(value) {
                glfwWindowHint(GLFW_ACCUM_ALPHA_BITS, value)
                field = value
            }

        var auxBuffers = 0
            set(value) {
                glfwWindowHint(GLFW_AUX_BUFFERS, value)
                field = value
            }

        var stereo = false
            set(value) {
                glfwWindowHint(GLFW_STEREO, value.i)
                field = value
            }

        var samples = 0
            set(value) {
                glfwWindowHint(GLFW_SAMPLES, value)
                field = value
            }

        var srgb = false
            set(value) {
                glfwWindowHint(GLFW_SRGB_CAPABLE, value.i)
                field = value
            }

        var doubleBuffer = true
            set(value) {
                glfwWindowHint(GLFW_DOUBLEBUFFER, value.i)
                field = value
            }
    }

    // Monitor related hints

    val monitor = Monitor()

    fun monitor(block: Monitor.() -> Unit) = monitor.block()
    class Monitor {
        var refreshRate = GLFW_DONT_CARE
            set(value) {
                glfwWindowHint(GLFW_REFRESH_RATE, value)
                field = value
            }
    }

    // Context related hints
    val context = Context()

    fun context(block: Context.() -> Unit) = context.block()

    class Context {

        enum class ClientApi { Gl, GlEs, None }

        var clientApi = ClientApi.Gl
            set(value) {
                glfwWindowHint(GLFW_CLIENT_API, when (value) {
                    ClientApi.Gl -> GLFW_OPENGL_API
                    ClientApi.GlEs -> GLFW_OPENGL_ES_API
                    ClientApi.None -> GLFW_NO_API
                })
                field = value
            }

        enum class CreationApi { Native, Egl, OsMesa }

        var creationApi = CreationApi.Native
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_CREATION_API, when (value) {
                    CreationApi.Native -> GLFW_NATIVE_CONTEXT_API
                    CreationApi.Egl -> GLFW_EGL_CONTEXT_API
                    CreationApi.OsMesa -> GLFW_OSMESA_CONTEXT_API
                })
                field = value
            }

        var version = "1.0"
            set(value) {
                check(value.length == 3 && value[0].isDigit() && value[1] == '.' && value[2].isDigit())
                glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, Integer.parseInt(value[0].toString()))
                glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, Integer.parseInt(value[2].toString()))
                field = value
            }

        var major = 1
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, value)
                field = value
            }

        var minor = 0
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, value)
                field = value
            }

        var forwardComp = false
            set(value) {
                glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, value.i)
                field = value
            }

        var debug = false
            set(value) {
                glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, value.i)
                field = value
            }

        enum class Profile { Any, Compat, Core }

        var profile = Profile.Any
            set(value) {
                glfwWindowHint(GLFW_OPENGL_PROFILE, when (value) {
                    Profile.Core -> GLFW_OPENGL_CORE_PROFILE
                    Profile.Compat -> GLFW_OPENGL_COMPAT_PROFILE
                    Profile.Any -> GLFW_OPENGL_ANY_PROFILE
                })
                field = value
            }

        enum class Robustness { NoResetNotification, LoseContextOnReset, None }

        var robustness = Robustness.None
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_ROBUSTNESS, when (value) {
                    Robustness.None -> GLFW_NO_ROBUSTNESS
                    Robustness.NoResetNotification -> GLFW_NO_RESET_NOTIFICATION
                    Robustness.LoseContextOnReset -> GLFW_LOSE_CONTEXT_ON_RESET
                })
                field = value
            }

        enum class ReleaseBehaviour { Any, Flush, None }

        var releaseBehaviour = ReleaseBehaviour.Any
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_RELEASE_BEHAVIOR, when (value) {
                    ReleaseBehaviour.Any -> GLFW_ANY_RELEASE_BEHAVIOR
                    ReleaseBehaviour.Flush -> GLFW_RELEASE_BEHAVIOR_FLUSH
                    ReleaseBehaviour.None -> GLFW_RELEASE_BEHAVIOR_NONE
                })
                field = value
            }

        var noError = false
            set(value) {
                glfwWindowHint(GLFW_CONTEXT_NO_ERROR, value.i)
                field = value
            }
    }

    // macOS specific window hints
    val cocoa = Cocoa()

    fun cocoa(block: Cocoa.() -> Unit) = cocoa.block()

    class Cocoa {

        var retinaFramebuffer = true
            set(value) {
                glfwWindowHint(GLFW_COCOA_RETINA_FRAMEBUFFER, value.i)
                field = value
            }

        var frameName = ""
            set(value) {
                glfwWindowHintString(GLFW_COCOA_FRAME_NAME, value)
                field = value
            }

        var graphicsSwitching = false
            set(value) {
                glfwWindowHint(GLFW_COCOA_GRAPHICS_SWITCHING, value.i)
                field = value
            }
    }

    // X11 specific window hints
    val x11 = X11()

    fun x11(block: X11.() -> Unit) = x11.block()

    class X11 {

        var className = ""
            set(value) {
                glfwWindowHintString(GLFW_X11_CLASS_NAME, value)
                field = value
            }

        var instanceName = ""
            set(value) {
                glfwWindowHintString(GLFW_X11_INSTANCE_NAME, value)
                field = value
            }
    }
}
