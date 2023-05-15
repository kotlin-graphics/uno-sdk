@file:OptIn(ExperimentalUnsignedTypes::class, ExperimentalUnsignedTypes::class)

package tests

import glm_.*
import glm_.func.sin
import glm_.vec2.Vec2
import glm_.vec2.Vec2d
import glm_.vec2.Vec2i
import gln.*
import gln.draw.DrawMode
import gln.draw.glDrawArrays
import kool.floatBufferOf
import kool.free
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import uno.glfw.*
import java.awt.Color
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sqrt

const val CURSOR_FRAME_COUNT = 60

val vertexShaderText = """
    #version 110
    uniform mat4 MVP;
    attribute vec2 vPos;
    void main()
    {
        gl_Position = MVP * vec4(vPos, 0.0, 1.0);
    }""".trimIndent()

val fragmentShaderText = """
    #version 110
    void main()
    {
        gl_FragColor = vec4(1.0);
    }""".trimIndent()

var cursor = Vec2d()
var swapInterval = VSync.ON
var waitEvents = true
var animateCursor = false
var trackCursor = false
val standardCursors = Array(10) { GlfwCursor.NULL }
var trackingCursor: GlfwCursor = GlfwCursor.NULL

fun star(x: Int, y: Int, t: Float): Float {

    val c = 64 / 2f

    val i = 0.25f * (2f * glm.πf * t).sin + 0.75f
    val k = 64 * 0.046875f * i

    val dist = sqrt((x - c) * (x - c) + (y - c) * (y - c))

    val sAlpha = 1f - dist / c
    val xAlpha = if (x.f == c) c else k / abs(x - c)
    val yAlpha = if (y.f == c) c else k / abs(y - c)

    return 0f max (1f min (i * sAlpha * 0.2f + sAlpha * xAlpha * yAlpha))
}

fun createCursorFrame(t: Float): GlfwCursor {
    var i = 0
    val buffer = UByteArray(64 * 64 * 4)
    val image = GlfwImage(64, buffer)

    for (y in 0 until image.width)
        for (x in 0 until image.height) {
            buffer[i++] = 255.toUByte()
            buffer[i++] = 255.toUByte()
            buffer[i++] = 255.toUByte()
            buffer[i++] = (255 * star(x, y, t)).toUInt().toUByte()
        }

    return GlfwCursor(image, image.width / 2, image.height / 2)
}

fun createTrackingCursor(): GlfwCursor {
    var i = 0
    val buffer = UByteArray(32 * 32 * 4)
    val image = GlfwImage(32, buffer)

    for (y in 0 until image.width)
        for (x in 0 until image.height)
            if (x == 7 || y == 7) {
                buffer[i++] = 255.toUByte()
                buffer[i++] = 0.toUByte()
                buffer[i++] = 0.toUByte()
                buffer[i++] = 255.toUByte()
            } else {
                buffer[i++] = 0.toUByte()
                buffer[i++] = 0.toUByte()
                buffer[i++] = 0.toUByte()
                buffer[i++] = 0.toUByte()
            }
    return GlfwCursor(image, 7)
}

fun cursorPositionCB(window: GlfwWindow, pos: Vec2d) {
    println("%.3f: Cursor position: ${pos.x.f} ${pos.y.f} (%+f %+f)".format(glfw.time, pos.x - cursor.x, pos.y - cursor.y))

    cursor.x = pos.x
    cursor.y = pos.y
}

var pos = Vec2i()
var size = Vec2i()
fun keyCB(window: GlfwWindow, key: Key, scancode: Int, action: InputAction, mods: Int) {

    if (action != InputAction.Press)
        return

    when (key) {
        Key.A -> {
            animateCursor = !animateCursor
            if (!animateCursor)
                window.cursor = GlfwCursor.NULL
        }

        Key.ESCAPE -> {
            val mode = window.cursorMode
            if (mode != GlfwWindow.CursorMode.Disabled && mode != GlfwWindow.CursorMode.Captured)
                window.shouldClose = true

            /* FALLTHROUGH */
        }

        Key.N -> {
            window.cursorMode = GlfwWindow.CursorMode.Normal
            cursor = window.cursorPos
            println("(( cursor is normal ))")
        }

        Key.D -> {
            window.cursorMode = GlfwWindow.CursorMode.Disabled
            println("(( cursor is disabled ))")
        }

        Key.H -> {
            window.cursorMode = GlfwWindow.CursorMode.Hidden
            println("(( cursor is hidden ))")
        }

        Key.C -> {
            window.cursorMode = GlfwWindow.CursorMode.Captured
            println("(( cursor is captured ))")
        }

        Key.R -> {
            if (glfw.rawMouseMotionSupported) {
                if (window.rawMouseMotion) {
                    window.rawMouseMotion = false
                    println("(( raw input is disabled ))")
                } else {
                    window.rawMouseMotion = true
                    println("(( raw input is enabled ))")
                }
            }
        }

        Key.SPACE -> {
            swapInterval = if (swapInterval == VSync.ON) VSync.OFF else VSync.ON
            println("(( swap interval: $swapInterval ))")
            glfw.swapInterval = swapInterval
        }

        Key.W -> {
            waitEvents = !waitEvents
            println("(( ${if (waitEvents) "wait" else "poll"}ing for events ))")
        }

        Key.T -> {
            trackCursor = !trackCursor
            window.cursor = if (trackCursor) trackingCursor else GlfwCursor.NULL
        }

        Key.P -> {
            var pos = window.cursorPos

            println("Query before set: %f %f (%+f %+f)".format(pos.x, pos.y, pos.x - cursor.x, pos.y - cursor.y))
            cursor put pos
            window.cursorPos = cursor
            pos = window.cursorPos

            println("Query after set: %f %f (%+f %+f)".format(pos.x, pos.y, pos.x - cursor.x, pos.y - cursor.y))
            cursor put pos
        }

        Key.UP -> {
            window.cursorPos = Vec2d()
            cursor = window.cursorPos
        }

        Key.DOWN -> {
            val size = window.size
            window.cursorPos = Vec2d(size.x - 1, size.y - 1)
            cursor = window.cursorPos
        }

        Key.`0` -> window.cursor = GlfwCursor.NULL

        Key.`1`, Key.`2`, Key.`3`, Key.`4`, Key.`5`, Key.`6`, Key.`7`, Key.`8`, Key.`9` -> {
            var index = key.i - Key.`1`.i
            if (mods has GLFW.GLFW_MOD_SHIFT)
                index += 9

            if (index < standardCursors.size)
                window.cursor = standardCursors[index]
        }

        Key.F11, Key.ENTER -> {

            if (mods != GLFW.GLFW_MOD_ALT)
                return

            if (window.monitor.isValid)
                window.setMonitor(GlfwMonitor.NULL, pos, size, 0)
            else {
                val monitor = glfw.primaryMonitor
                val mode = monitor.videoMode
                pos = window.pos
                size = window.size
                window.setMonitor(monitor, Vec2i(), mode.size, mode.refreshRate)
            }

            cursor = window.cursorPos
        }
        else -> {}
    }
}

fun main() {
//    int i
//            GLFWwindow * window
    val starCursors = Array(CURSOR_FRAME_COUNT) { GlfwCursor.NULL }
    var currentFrame = GlfwCursor.NULL
//    GLuint vertex_buffer, vertex_shader, fragment_shader, program
//    GLint mvp_location, vpos_location

    glfw.init(installDefaultErrorCB = true)

    trackingCursor = createTrackingCursor()
    if (trackingCursor.isNotValid)
        glfw.terminate()

    for (i in 0 until CURSOR_FRAME_COUNT) {
        starCursors[i] = createCursorFrame(i / CURSOR_FRAME_COUNT.f)
        if (starCursors[i].isNotValid)
            glfw.terminate()
    }

    for (i in standardCursors.indices)
        standardCursors[i] = glfw.createStandardCursor(glfw.CursorShape.values()[i])

    glfw.hints.context {
        major = 2
        minor = 0
    }

    val glfwWindow = GlfwWindow(640, 480, "Cursor Test")
    val window = GlWindow(glfwWindow)

    window.makeCurrent()
//    gladLoadGL(glfwGetProcAddress)

    val vertexBuffer = gl.genBuffers()
    vertexBuffer.bind(BufferTarget.ARRAY)

    val vertexShader = gl.createShader(ShaderType.VERTEX_SHADER).apply {
        source(vertexShaderText)
        compile()
    }
    val fragmentShader = gl.createShader(ShaderType.FRAGMENT_SHADER).apply {
        source(fragmentShaderText)
        compile()
    }
    val program = gl.createProgram().apply {
        attach(vertexShader)
        attach(fragmentShader)
        link()
    }
    val mvpLocation = program getUniformLocation "MVP"
    val vposLocation = program getAttribLocation "vPos"

    gl.enableVertexAttribArray(vposLocation)
    gl.vertexAttribPointer(vposLocation, Vec2.length, VertexAttrType.FLOAT, false, Vec2.size, 0)
    program.use()

    cursor = window.cursorPos
    println("Cursor position: ${cursor.x} ${cursor.y}")

    window.cursorPosCB = ::cursorPositionCB
    window.keyCB = ::keyCB

    while (!window.shouldClose) {

        glClearColor(Color.BLACK)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)

        if (trackCursor) {

            val wndSize = window.size
            val fbSize = window.framebufferSize

            glViewport(fbSize)

            val scale = fbSize.x.f / wndSize.x
            val vertices = floatBufferOf(0.5f,
                (fbSize.y - floor(cursor.y * scale) - 1f + 0.5f).f,
                    fbSize.x +0.5f,
                    (fbSize.y - floor(cursor.y * scale) - 1f + 0.5f).f,
                    floor (cursor.x * scale).f + 0.5f,
                    0.5f,
                    floor (cursor.x * scale).f + 0.5f,
                    fbSize.y +0.5f)

            gl.bufferData(BufferTarget.ARRAY, vertices, Usage.STREAM_DRAW)
            vertices.free()

            val mvp = glm.ortho(0f, fbSize.x.f, 0f, fbSize.y.f, 0f, 1f)
            gl.uniform(mvpLocation, mvp)

            glDrawArrays(DrawMode.LINES, 4)
        }

        window.swapBuffers()

        if (animateCursor) {
            val i = (glfw.time * 30).i % CURSOR_FRAME_COUNT
            if (currentFrame != starCursors[i]) {
                window.cursor = starCursors[i]
                currentFrame = starCursors[i]
            }
        } else
            currentFrame = GlfwCursor.NULL

        if (waitEvents) {
            if (animateCursor)
                glfw.waitEventsTimeout(1.0 / 30.0)
            else
                glfw.waitEvents()
        } else
            glfw.pollEvents()

        // Workaround for an issue with msvcrt and mintty
//        fflush(stdout)
    }

    window.destroy()

    for (cursor in starCursors)
        cursor.destroy()

    for (cursor in standardCursors)
        cursor.destroy()

    glfw.terminate()
}
