package tests

import gln.glClearColor
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.Platform
import uno.gl.GlWindow
import uno.glfw.*

class Clipboard: StringSpec() {

    val MODIFIER = when (Platform.get()) {
        Platform.MACOSX -> GLFW.GLFW_MOD_SUPER
        else -> GLFW.GLFW_MOD_CONTROL
    }

    fun errorCB(error: glfw.Error, description: String) = System.err.println("Error: $description")

    fun key_callback(window: GlfwWindow, key: Key, scancode: Int, action: InputAction, mods: Int) {

        if (action != InputAction.Press)
            return

        when (key) {
            Key.ESCAPE -> window.shouldClose = true
            Key.V ->
                if (mods == MODIFIER) {
                    val string = glfw.clipboardString
                    if (string != null)
                        println("Clipboard contains \"$string\"")
                    else
                        println("Clipboard does not contain a string")
                }
            Key.C ->
                if (mods == MODIFIER) {
                    val string = "Hello GLFW World!"
                    glfw.clipboardString = string
                    println("Setting clipboard to \"$string\"")
                }
            else -> {}
        }
    }

    init {
        "clipboard" {

            glfw.errorCB = ::errorCB

            glfw.init()

            val glfwWindow = GlfwWindow(200, "Clipboard Test")
            val window = GlWindow(glfwWindow)

            window.makeCurrent()
//    gladLoadGL(glfwGetProcAddress)
            GL.createCapabilities()
            glfw.swapInterval = VSync.ON

            window.keyCB = ::key_callback

            GL11.glClearColor(0.5f, 0.5f, 0.5f, 0f)

            var i = 0
            val string = "Hello GLFW World!"

            while (!window.shouldClose) {

                glClearColor()

                when(i++) {
                    0 -> glfw.clipboardString = string
                    1 -> glfw.clipboardString shouldBe string
                    2 -> window.shouldClose = true
                }

                window.swapBuffers()
                glfw.waitEvents()
            }

            glfw.terminate()
        }
    }
}


