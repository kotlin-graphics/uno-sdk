package uno.awt

import glm_.vec2.Vec2i
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GLUtil
import org.lwjgl.system.Callback
import org.lwjgl.system.jawt.JAWTDrawingSurface
import org.lwjgl.system.jawt.JAWTFunctions
import uno.glfw.GlWindow
import uno.glfw.GlfwWindow
import uno.glfw.VSync
import uno.glfw.glfw
import uno.kotlin.HWND
import java.awt.Canvas
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent


/**
 * A Canvas component that uses OpenGL for rendering.
 *
 * Spasi:  GLFW saves the AWT window proc somewhere and replaces it with its own on `glfwAttachWin32Window`.
 * It restores it back when the GLFW window is destroyed.
 * The difference between key and mouse events is that for key events the GLFW window proc returns `DefWindowProcW(...)`,
 * but for mouse events it returns 0, so the events do not propagate to AWT
 *
 * This implementation supports Windows only.
 */
abstract class LwjglCanvas(val glDebug: Boolean = false) : Canvas() {

    val awt = JAWT()

    lateinit var glWindow: GlWindow

    var swapBuffers = true
    var fps = true

    var debugProc: Callback? = null

    var awtDebug = false

    val glfwErrorCallback = GLFWErrorCallback.createPrint().set()

    init {
        glfw {
            init()
            hints.context.debug = glDebug
        }
    }

    private fun initInternal(hwnd: HWND) {
//        println("LwjglCanvas.initInternal ${Date().toInstant()}")

        initialized = true

        // glfwWindowHint can be used here to configure the GL context
        val glfwWindow = GlfwWindow fromWin32Window hwnd
        glWindow = GlWindow(glfwWindow, forwardCompatible = false).apply {
            makeContextCurrent()
        }

        if (glDebug)
            debugProc = GLUtil.setupDebugMessageCallback()

        glfw.swapInterval = VSync.OFF

        init()

        glfwWindow.makeContextCurrent(false)

//        println("/LwjglCanvas.initInternal ${Date().toInstant()}")
    }

    var initialized = false
    var resized = false
    var animated = true

//    lateinit var caps: Caps

    //  According to jawt.h > This value may be cached
    lateinit var surface: JAWTDrawingSurface

    init {

        if (!awt.get())
            throw IllegalStateException("GetAWT failed")

        // this avoids to calling the super method
        this.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
//                println("resized")
                resized = true
            }
//          Not working
//            override fun componentHidden(e: ComponentEvent?) {
//                println("hidden")
//            }
//
//            override fun componentMoved(e: ComponentEvent?) {
//                println("moved")
//            }
//
//            override fun componentShown(e: ComponentEvent?) {
//                println("shown")
//            }
        })

        if (EventQueue.isDispatchThread())
            surface = awt.getDrawingSurface(this)!!
        else EventQueue.invokeAndWait {
            surface = awt.getDrawingSurface(this)!!
        }
    }

    /** critical, call paint without default g.clearRect, because it causes flickering */
    override fun update(g: Graphics) = paint(g)//.also { println("update") }

    var last = 0L
    var time = 0L
    var frames = 0

    override fun paint(g: Graphics) {

        if (awtDebug)
            println("paint start " + Thread.currentThread().name)

        // Lock the drawing surface
        if (surface.lock() == JawtLock.ERROR)
            throw IllegalStateException("ds->Lock() failed")

        wrapped { hwnd ->

            if (!initialized)
                initInternal(hwnd)

            glWindow.inContext {

                if (resized) {
//                    println("LwjglCanvas.reshape ${Date().toInstant()}")
                    val newSize = Vec2i(width, height)
                    glWindow.size = newSize
                    reshape(newSize)
                    resized = false
//                    println("/LwjglCanvas.reshape ${Date().toInstant()}")
                }

//                println("LwjglCanvas.render ${Date().toInstant()}")
                if (awtDebug)
                    println("paint before render ")

                render()

//                println("/LwjglCanvas.render ${Date().toInstant()}")
                if (awtDebug)
                    println("paint end")

                if (swapBuffers)
                    glWindow.swapBuffers()

                if (fps) {
                    val now = System.currentTimeMillis()
                    time += now - last
                    last = now
                    frames++
                    if (time > 1000) {
                        time %= 1000
                        if (awtDebug) println("fps = $frames")
                        frames = 0
                    }
                }
            }
        }

        if (animated)
            repaint()
    }

    /*override fun paint(g: Graphics) {
        if (awtDebug) {
            println("paint start " + Thread.currentThread().name)
        }

        // Lock the drawing surface
        val lock = JAWT_DrawingSurface_Lock(surface, surface.Lock())
        if (lock has JAWT_LOCK_ERROR)
            throw IllegalStateException("ds->Lock() failed")

        try {
            // Get the drawing surface info
            val dsi = JAWT_DrawingSurface_GetDrawingSurfaceInfo(surface, surface.GetDrawingSurfaceInfo())
                    ?: throw IllegalStateException("ds->GetDrawingSurfaceInfo() failed")

            try {
                // Get the window platform drawing info
                val surfaceInfo = JAWTWin32DrawingSurfaceInfo.create(dsi.platformInfo())

                val hdc = surfaceInfo.hdc()
                assert(hdc != NULL)

                if (!initialized)
                    initInternal(HWND(surfaceInfo.hwnd()))

                glfwWindow.makeContextCurrent()

                if (resized) {
//                    println("LwjglCanvas.reshape ${Date().toInstant()}")
                    val newSize = Vec2i(width, height)
                    glfwWindow.size = newSize
                    reshape(newSize)
                    resized = false
//                    println("/LwjglCanvas.reshape ${Date().toInstant()}")
                }

//                println("LwjglCanvas.render ${Date().toInstant()}")
                if (awtDebug) {
                    println("paint before render ")
                }
                render()
//                println("/LwjglCanvas.render ${Date().toInstant()}")

                if (swapBuffers)
                    glfwWindow.swapBuffers()

                if (fps) {
                    val now = System.currentTimeMillis()
                    time += now - last
                    last = now
                    frames++
                    if (time > 1000) {
                        time %= 1000
                        println("fps = $frames")
                        frames = 0
                    }
                }

                glfwWindow.unmakeContextCurrent()

            } finally {
                // Free the drawing surface info
                JAWT_DrawingSurface_FreeDrawingSurfaceInfo(dsi, surface.FreeDrawingSurfaceInfo())
            }
        } finally {
            // Unlock the drawing surface
            JAWT_DrawingSurface_Unlock(surface, surface.Unlock())
        }

        if (awtDebug) {
            println("paint end")
        }

        if (animated)
            repaint()
    }*/

    inline fun wrapped(block: (HWND) -> Unit) {

        try {
            // Get the drawing surface info
            val dsi = surface.info ?: throw IllegalStateException("ds->GetDrawingSurfaceInfo() failed")

            try {
                // Get the window platform drawing info
                val surfaceInfo = JAWTWin32DrawingSurfaceInfo(dsi)

                val hdc = surfaceInfo.hdc
                assert(hdc.isValid)

                block(surfaceInfo.hwnd)

            } finally {
                // Free the drawing surface info
                surface free dsi
            }
        } finally {
            // Unlock the drawing surface
            surface.unlock()
        }
    }

    fun destroyInternal() {
        if (awtDebug) println("destroyInternal")

        glWindow.inContext {
            destroy()
            debugProc?.free()
        }

        JAWTFunctions.JAWT_FreeDrawingSurface(surface, awt.FreeDrawingSurface())
        awt.free()

        glWindow.destroy()
        glfw.terminate()
        glfwErrorCallback.free()
    }

    fun toggleAnimation() {
        if (animated)
            animated = false
        else {
            animated = true
            repaint()
        }
    }

    @JvmOverloads
    fun animate(animate: Boolean = true) {

        if (animated != animate) {

            animated = animate

            if (animate)
                graphics?.let { g ->
                    if (EventQueue.isDispatchThread()) {
                        paint(g)
                        g.dispose()
                    } else EventQueue.invokeAndWait {
                        paint(g)
                        g.dispose()
                    }
                }
        }
    }

    // public methods to overwrite in application

    abstract fun init()
    abstract fun reshape(size: Vec2i)
    abstract fun render()
    abstract fun destroy()
}