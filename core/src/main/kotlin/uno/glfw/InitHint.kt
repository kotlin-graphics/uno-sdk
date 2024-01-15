package uno.glfw

import glm_.i
import org.lwjgl.glfw.GLFW.*

class InitHint {

    var joystickHatButtons = true
        set(value) {
            glfwInitHint(GLFW_JOYSTICK_HAT_BUTTONS, value.i)
            field = value
        }

    var cocoaChdirResources = true
        set(value) {
            glfwInitHint(GLFW_COCOA_CHDIR_RESOURCES, value.i)
            field = value
        }

    var cocoaMenubar = true
        set(value) {
            glfwInitHint(GLFW_COCOA_MENUBAR, value.i)
            field = value
        }

    var platform: Int
        get() = throw Exception("No getting allowed")
        set(value) {
            glfwInitHint(GLFW_PLATFORM, value)
        }

    val wayland = Wayland()
    fun wayland(block: Wayland.() -> Unit) = wayland.block()

    class Wayland {
        var libDecor: Int
            get() = throw Exception("No getting allowed")
            set(value) {
                glfwInitHint(GLFW_WAYLAND_LIBDECOR, value)
            }
    }

}