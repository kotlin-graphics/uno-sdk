package uno.glfw

import glm_.vec2.Vec2i
import org.lwjgl.glfw.GLFWVidMode


inline val GLFWVidMode.width: Int
    get() = GLFWVidMode.nwidth(address()) // TODO adr
inline val GLFWVidMode.height: Int
    get() = GLFWVidMode.nheight(address())
inline val GLFWVidMode.size: Vec2i
    get() = Vec2i(width, height)
inline val GLFWVidMode.redBits: Int
    get() = GLFWVidMode.nredBits(address())
inline val GLFWVidMode.greenBits: Int
    get() = GLFWVidMode.ngreenBits(address())
inline val GLFWVidMode.blueBits: Int
    get() = GLFWVidMode.nblueBits(address())
inline val GLFWVidMode.refreshRate: Int
    get() = GLFWVidMode.nrefreshRate(address())


inline class HWND(val L: Long)