package uno

import io.kotlintest.specs.StringSpec
import org.lwjgl.opengl.GL11.*
import uno.glfw.GlfwWindow
import uno.glfw.glfw
import uno.glfw.size


class HelloWorld : StringSpec() {

    init {

        "Hello World" {

            // init
            glfw.init("3.3")

            // The window handle
            GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example").apply {

                pos = (glfw.videoMode.size - size) / 2

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