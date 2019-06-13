package uno.glfw

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
fun GlfwWindow.init(show: Boolean = true) {
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