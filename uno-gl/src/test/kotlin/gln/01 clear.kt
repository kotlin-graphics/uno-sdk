package gln

import glm_.f
import glm_.vec2.Vec2i
import gln.ClearBufferMask.Companion.COLOR_BUFFER_BIT
import uno.glfw.GlfwWindow

fun main() {
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