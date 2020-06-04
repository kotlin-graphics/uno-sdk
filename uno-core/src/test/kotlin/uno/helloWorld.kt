package uno

import io.kotest.core.spec.style.StringSpec
import org.lwjgl.opengl.GL11.*
import uno.glfw.GlfwWindow
import uno.glfw.glfw

val isNotCI: Boolean
    get() = System.getenv("GITHUB_ACTIONS") != "true" && System.getenv("TRAVIS") != "true"

class HelloWorld : StringSpec() {

    init {

        if (isNotCI)
            "Hello World" {

                // init
                glfw.init("3.3")

                // The window handle
                GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example").apply {

                    pos = (glfw.primaryMonitor.videoMode.size - size) / 2

                    init()

                    // Set the clear color
                    glClearColor(1f, 0f, 0f, 0f)

                    loop({ glfw.time < 1 }) {
                        glClear(GL_COLOR_BUFFER_BIT)
                    }
                }
            }
    }
}