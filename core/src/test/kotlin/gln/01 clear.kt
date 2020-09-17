package gln

import glm_.f
import glm_.i
import glm_.vec2.Vec2i
import gln.ClearBufferMask.Companion.COLOR_BUFFER_BIT
import org.lwjgl.system.APIUtil
import org.lwjgl.system.Library
import org.lwjgl.system.windows.User32
import uno.glfw.GlfwWindow
import uno.glfw.glfw

fun main() {

    val USER32 = Library.loadNative(User32::class.java, "org.lwjgl", "user32")

    val GetWindowLong = APIUtil.apiGetFunctionAddress(USER32, "GetWindowLong")
    println(GetWindowLong)

    glfw.init("3.3")
    Clear().run()
}

private class Clear : GlfwWindow(1280, 720, "[GLN] clear") {

    init {
        init()
        installDefaultCallbacks()
    }

    fun run() = loop {

        val sec = glfw.time.i

        gl.clearColor(
                (sec % 3 == 0).f,
                (sec % 3 == 1).f,
                (sec % 3 == 2).f, 0f)

        gl.clear(COLOR_BUFFER_BIT)
    }

    override fun onWindowResized(newSize: Vec2i) = gl.viewport(newSize)
}