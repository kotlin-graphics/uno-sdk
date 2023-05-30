package uno

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.lwjgl.opengl.GL11.*
import uno.gl.GlWindow
import uno.glfw.GlfwWindow
import uno.glfw.glfw
import uno.kotlin.isNotCI

class HelloWorld : StringSpec() {

    init {

        if (isNotCI) {

            "Hello World" {

                // init
                glfw.init("3.3")

                // The window handle
                val glfwWindow = GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example")
                GlWindow(glfwWindow).apply {

                    pos = (glfw.primaryMonitor.videoMode.size - size) / 2

                    init()

                    // Set the clear color
                    glClearColor(1f, 0f, 0f, 0f)

                    val condition = { glfw.time < 0.1 }
                    loop(condition) {
                        glClear(GL_COLOR_BUFFER_BIT)
                    }
                }
            }

            "equality" {
                val a = GlfwWindow(1L)
                val b = GlfwWindow(1L)
                a shouldBe b
                val c = GlfwWindow(2L)
                a shouldNotBe c
            }
        }
    }
}