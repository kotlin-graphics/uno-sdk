package uno.awt

import gli_.has
import glm_.vec2.Vec2i
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GLCapabilities
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.Platform
import org.lwjgl.system.jawt.*
import org.lwjgl.system.jawt.JAWTFunctions.*
import uno.glfw.*
import uno.kotlin.set
import uno.kotlin.unset
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Graphics
import java.awt.event.*
import java.util.*
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants
import org.lwjgl.system.jawt.JAWT as Jawt

/** AWT integration demo using jawt.  */
fun main(args: Array<String>) {

    if (Platform.get() != Platform.WINDOWS)
        throw UnsupportedOperationException("This demo can only run on Windows.")

    val canvas = LwjglCanvas()//.apply { setSize(640, 480) }

    val frame = JFrame("JAWT Demo").apply {
        // set it, because default is HIDE_ON_CLOSE
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            /*
            windowClosed will be called when it's too late, the awt hwnd will be invalid at that point and
            so will be also the glfw Window handle
             */
            override fun windowClosing(e: WindowEvent?) {
                canvas.destroyInternal()
            }
        })
    }

    val keyListener = object : KeyListener {
        override fun keyTyped(e: KeyEvent) {
            println("keyTyped " + Thread.currentThread().name)
        }

        override fun keyPressed(e: KeyEvent) {
            println("keyPressed")
            if (e.keyCode == KeyEvent.VK_ESCAPE)
                frame.dispose()
            else if (e.keyCode == KeyEvent.VK_A)
                canvas.toggleAnimation()
        }

        override fun keyReleased(e: KeyEvent) {
            println("keyReleased")
        }
    }

    frame.apply {
        layout = BorderLayout()
        add(canvas, BorderLayout.CENTER)

        pack()
        setSize(640, 480)
        addKeyListener(keyListener)
        isVisible = true
    }
}

/**
 * A Canvas component that uses OpenGL for rendering.
 *
 *
 * This implementation supports Windows only.
 */
open class LwjglCanvas : Canvas() {

    val awt = Jawt.calloc().apply { version(JAWT_VERSION_1_4) }

    val gears = AbstractGears()

    lateinit var glfwWindow: GlfwWindow

    var swapBuffers = true

    private fun initInternal(hwnd: HWND) {
//        println("LwjglCanvas.initInternal ${Date().toInstant()}")

        initialized = true

        GLFWErrorCallback.createPrint().set()
        glfw.init()

        // glfwWindowHint can be used here to configure the GL context
        glfwWindow = GlfwWindow.fromWin32Window(hwnd).apply { makeContextCurrent() }
        caps = GL.createCapabilities()

        glfw.swapInterval = VSync.OFF

        init()

//        println("/LwjglCanvas.initInternal ${Date().toInstant()}")
    }

    var initialized = false
    var resized = false
    var animated = true

    private lateinit var caps: GLCapabilities

    //  According to jawt.h > This value may be cached
    lateinit var surface: JAWTDrawingSurface

    init {

        if (!JAWT_GetAWT(awt))
            throw IllegalStateException("GetAWT failed")

        // this avoids to calling the super method
        this.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                resized = true
            }

            override fun componentHidden(e: ComponentEvent?) {}

            override fun componentMoved(e: ComponentEvent?) {}

            override fun componentShown(e: ComponentEvent?) {}
        })

        SwingUtilities.invokeAndWait {
            surface = JAWT_GetDrawingSurface(awt.GetDrawingSurface(), this@LwjglCanvas)!!
        }
    }

    // critical, call paint without default g.clearRect, because it causes flickering
    override fun update(g: Graphics) = paint(g)//.also { println("update") }

    var last = 0L
    var time = 0L
    var frames = 0

    override fun paint(g: Graphics) {
//        println("paint " + Thread.currentThread().name)

        // Lock the drawing surface
        val lock = JAWT_DrawingSurface_Lock(surface.Lock(), surface)
        if (lock has JAWT_LOCK_ERROR)
            throw IllegalStateException("ds->Lock() failed")

        try {
            // Get the drawing surface info
            val dsi = JAWT_DrawingSurface_GetDrawingSurfaceInfo(surface.GetDrawingSurfaceInfo(), surface)
                    ?: throw IllegalStateException("ds->GetDrawingSurfaceInfo() failed")

            try {
                // Get the window platform drawing info
                val surfaceInfo = JAWTWin32DrawingSurfaceInfo.create(dsi.platformInfo())

                val hdc = surfaceInfo.hdc()
                assert(hdc != NULL)


                if (!initialized)
                    initInternal(HWND(surfaceInfo.hwnd()))
                else {
                    glfwWindow.makeContextCurrent()
                    caps.set()
                }


                if (resized) {
//                    println("LwjglCanvas.reshape ${Date().toInstant()}")
                    val newSize = Vec2i(width, height)
                    glfwWindow.size = newSize
                    reshape(newSize)
                    resized = false
//                    println("/LwjglCanvas.reshape ${Date().toInstant()}")
                }

//                println("LwjglCanvas.render ${Date().toInstant()}")
                render()
//                println("/LwjglCanvas.render ${Date().toInstant()}")

                if (swapBuffers)
                    glfwWindow.swapBuffers()

                val now = System.currentTimeMillis()
                time += now - last
                last = now
                frames++
                if (time > 1000) {
                    time %= 1000
                    println("fps = $frames")
                    frames = 0
                }


                glfwWindow.unmakeContextCurrent()
                caps.unset()

            } finally {
                // Free the drawing surface info
                JAWT_DrawingSurface_FreeDrawingSurfaceInfo(surface.FreeDrawingSurfaceInfo(), dsi)
            }
        } finally {
            // Unlock the drawing surface
            JAWT_DrawingSurface_Unlock(surface.Unlock(), surface)
        }

        if (animated)
            repaint()
    }

    fun destroyInternal() {
        println("destroyInternal")

        println(glfwWindow.handle)
        glfwWindow.makeContextCurrent()
        caps.set()

        destroy()

        glfwWindow.unmakeContextCurrent()
        caps.unset()

        JAWT_FreeDrawingSurface(awt.FreeDrawingSurface(), surface)
        awt.free()

        glfwWindow.destroy()
        glfw.terminate()
    }

    fun toggleAnimation() {
        if (animated)
            animated = false
        else {
            animated = true
            repaint()
        }
    }

    // public methods to overwrite in application

    open fun init() = gears.init()

    open fun reshape(size: Vec2i) = gears.reshape(size)

    open fun render() = gears.render()

    open fun destroy() = gears.destroy()
}
