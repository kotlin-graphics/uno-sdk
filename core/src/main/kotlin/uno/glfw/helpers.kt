@file:OptIn(ExperimentalUnsignedTypes::class)

package uno.glfw

import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import gln.L
import kool.*
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.glfwDestroyCursor
import org.lwjgl.glfw.GLFWGammaRamp
import org.lwjgl.glfw.GLFWImage
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.*
import uno.kotlin.ptrUByte
import uno.kotlin.ptrUShort


typealias GLFWErrorCallbackT = (error: glfw.Error, desc: String) -> Unit

class GlfwVidMode(val size: Vec2i, val redBits: Int, val greenBits: Int, val blueBits: Int, val refreshRate: Int) {
    constructor(ptr: Ptr<Int>) : this(Vec2i(ptr), ptr[2], ptr[3], ptr[4], ptr[5])

    companion object {
        val Size = Vec2i.size + Int.BYTES * 4
    }
}

operator fun Ptr<GlfwVidMode>.get(index: Int) = GlfwVidMode((adr.L + index * GlfwVidMode.Size).toPtr())

class GlfwGammaRamp(val red: IntArray, val green: IntArray, val blue: IntArray) {

    infix fun toStack(stack: MemoryStack): Ptr<GlfwGammaRamp> {
        val r = stack.ptrUShort(red.size)
        val g = stack.ptrUShort(red.size)
        val b = stack.ptrUShort(red.size)
        for (i in red.indices) {
            r[i] = red[i].toUShort()
            g[i] = green[i].toUShort()
            b[i] = blue[i].toUShort()
        }
        val ptr = stack.nmalloc(GLFWGammaRamp.ALIGNOF, GLFWGammaRamp.SIZEOF)
        memPutAddress(ptr + GLFWGammaRamp.RED, r.adr.L)
        memPutAddress(ptr + GLFWGammaRamp.GREEN, g.adr.L)
        memPutAddress(ptr + GLFWGammaRamp.BLUE, b.adr.L)
        memPutInt(ptr + GLFWGammaRamp.SIZE, red.size)
        return ptr.toPtr()
    }
}

fun GlfwGammaRamp(ptr: Ptr<GlfwGammaRamp>): GlfwGammaRamp {
    val red = ptr.toPtr<UShort>()
    val green = red + 1
    val blue = red + 2
    val size = (red + 3).toPtr<Int>()[0]
    return GlfwGammaRamp(IntArray(size) { red[it].toInt() }, IntArray(size) { green[it].toInt() }, IntArray(size) { blue[it].toInt() })
}

@JvmInline
value class GlfwCursor(val handle: Long) {
    // --- [ glfwCreateCursor ] ---
    constructor(image: GlfwImage, hot: Int) : this(image, hot, hot)
    constructor(image: GlfwImage, xHot: Int, yHot: Int) : this(stack { GLFW.nglfwCreateCursor(image.toStack(it).adr.L, xHot, yHot) })

    // --- [ glfwDestroyCursor ] ---
    fun destroy() = glfwDestroyCursor(handle)

    val isValid: Boolean
        get() = handle != MemoryUtil.NULL
    val isNotValid: Boolean
        get() = !isValid

    companion object {
        val NULL = GlfwCursor(MemoryUtil.NULL)
    }
}

typealias WindowPosCB = (window: GlfwWindow, pos: Vec2i) -> Unit
typealias WindowSizeCB = (window: GlfwWindow, size: Vec2i) -> Unit
typealias WindowCloseCB = (window: GlfwWindow) -> Unit
typealias WindowRefreshCB = (window: GlfwWindow) -> Unit
typealias WindowFocusCB = (window: GlfwWindow, focused: Boolean) -> Unit
typealias WindowIconifyCB = (window: GlfwWindow, iconified: Boolean) -> Unit
typealias WindowMaximizeCB = (window: GlfwWindow, maximized: Boolean) -> Unit
typealias FramebufferSizeCB = (window: GlfwWindow, size: Vec2i) -> Unit
typealias WindowContentScaleCB = (window: GlfwWindow, scale: Vec2) -> Unit
typealias KeyCB = (window: GlfwWindow, key: Key, scanCode: Int, action: InputAction, mods: Int) -> Unit
typealias CharCB = (window: GlfwWindow, codePoint: Int) -> Unit
typealias CharModsCB = (window: GlfwWindow, codePoint: Int, mods: Int) -> Unit
typealias MouseButtonCB = (window: GlfwWindow, button: Int, action: Int, mods: Int) -> Unit
typealias CursorPosCB = (window: GlfwWindow, pos: Vec2d) -> Unit
typealias CursorEnterCB = (window: GlfwWindow, entered: Boolean) -> Unit
typealias ScrollCB = (window: GlfwWindow, scroll: Vec2d) -> Unit
typealias DropCB = (window: GlfwWindow, names: Array<String>) -> Unit

typealias JoystickCB = (joystick: Joystick, connected: Boolean) -> Unit

enum class VSync {
    AdaptiveHalfRate, Adaptive, OFF, ON;

    val i = ordinal - 2
}

typealias GLFWglproc = Long

class GlfwImage(val width: Int, val height: Int, val pixels: UByteArray) {
    constructor(size: Int, pixels: UByteArray) : this(size, size, pixels)

    infix fun toStack(stack: MemoryStack): Ptr<GlfwImage> {
        val ptr = stack.nmalloc(GLFWImage.ALIGNOF, GLFWImage.SIZEOF)
        memPutInt(ptr + GLFWImage.WIDTH, width)
        memPutInt(ptr + GLFWImage.HEIGHT, height)
        val pPixels = stack.ptrUByte(pixels.size)
        for (i in pixels.indices)
            pPixels[i] = pixels[i]
        memPutAddress(ptr + GLFWImage.PIXELS, pPixels.adr.L)
        return ptr.toPtr()
    }
}