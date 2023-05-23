package uno

import uno.glfw.GlfwWindow
import uno.glfw.glfw

fun main() {
    // init
    glfw.init("3.3")

    // The window handle
    val glfwWindow = GlfwWindow(1280, 720, "ImGui Lwjgl OpenGL3 example")
}