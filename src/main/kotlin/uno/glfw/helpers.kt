package uno.glfw

import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i


typealias GLFWErrorCallbackT = (String, String) -> Unit

inline class GlfwMonitor(val L: Long)

inline class GlfwWindowHandle(val L: Long)

inline class GlfwCursor(val L: Long)


typealias CharCallbackT = (codePoint: Int) -> Unit
typealias CursorPosCallbackT = (pos: Vec2) -> Unit
typealias FramebufferSizeCallbackT = (size: Vec2i) -> Unit
typealias KeyCallbackT = (key: Int, scanCode: Int, action: Int, mods: Int) -> Unit
typealias MouseButtonCallbackT = (button: Int, action: Int, mods: Int) -> Unit
typealias ScrollCallbackT = (scroll: Vec2d) -> Unit
typealias WindowCloseCallbackT = () -> Unit
typealias WindowContentScaleCallbackT = (Vec2) -> Unit


enum class VSync { AdaptiveHalfRate, Adaptive, OFF, ON;

    val i = ordinal - 2
}