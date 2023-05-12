package uno

import io.kotest.core.spec.style.StringSpec
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil
import uno.glfw.GlWindow
import uno.glfw.GlfwWindow
import uno.glfw.Wut
import uno.glfw.glfw
import uno.kotlin.isNotCI

class HelloWorld : StringSpec() {

    init {

        if (isNotCI)
            "Hello World" {

                // init
                glfw.init("3.3")


                // Setup an error callback. The default implementation
                // will print the error message in System.err.
                GLFWErrorCallback.createPrint(System.err).set()

                println(GLFW.glfwCreateWindow(300, 300, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL))

                // The window handle
                val glfwWindow = GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example")
                GlWindow(glfwWindow).apply {

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

fun main() {
    // init
    glfw.init("3.3")


    // Setup an error callback. The default implementation
    // will print the error message in System.err.
    GLFWErrorCallback.createPrint(System.err).set()

    // The window handle
    val glfwWindow = GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example")
//    GlWindow(glfwWindow).apply {
//
//        pos = (glfw.primaryMonitor.videoMode.size - size) / 2
//
//        init()
//
//        // Set the clear color
//        glClearColor(1f, 0f, 0f, 0f)
//
//        loop({ glfw.time < 1 }) {
//            glClear(GL_COLOR_BUFFER_BIT)
//        }
//    }
}