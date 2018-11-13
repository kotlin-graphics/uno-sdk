package uno.awt

import gli_.has
import glm_.vec2.Vec2i
import org.lwjgl.Version
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.Platform
import org.lwjgl.system.jawt.*
import org.lwjgl.system.jawt.JAWTFunctions.*
import uno.glfw.*
import java.awt.BorderLayout
import java.awt.Canvas
import java.awt.Graphics
import java.awt.event.*
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
            override fun windowClosed(e: WindowEvent?) {
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

    val glfwWindow: GlfwWindow by lazy(::initInternal)

    var swapBuffers = true

    private fun initInternal(): GlfwWindow {
        println("LwjglCanvas.initInternal")
        GLFWErrorCallback.createPrint().set()
        glfw.init()

        return lockWithHWND { hwnd ->
            // glfwWindowHint can be used here to configure the GL context
            GlfwWindow.fromWin32Window(hwnd).also {

                it.makeContextCurrent()
//                caps = GL.createCapabilities()
                GL.createCapabilities()

                glfw.swapInterval = VSync.OFF

                init()
            }
        }
    }

    var initialized = false
    var resized = false
    var animated = true

//    var caps: GLCapabilities? = null

    //  According to jawt.h: "This value may be cached"
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
        println("paint " + Thread.currentThread().name)

        lockWithHWND {

            //            glfwMakeContextCurrent(glfwWindow)
//            GL.setCapabilities(caps)

            if (resized) {
                println("LwjglCanvas.reshape")
                reshape(glfwWindow.size(width, height))
                resized = false
            }

            println("LwjglCanvas.render")
            render()

            if (swapBuffers)
                glfwWindow.swapBuffers()

//            glfwMakeContextCurrent(NULL)
//            GL.setCapabilities(null)


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

        if (animated)
            repaint()
    }

    /*fun init() {
        println("init")

        lockWithHWND { hwnd ->
            // glfwWindowHint can be used here to configure the GL context
            glfwWindow = glfwAttachWin32Window(hwnd, NULL)
            if (glfwWindow == NULL)
                throw IllegalStateException("Failed to attach win32 window.")

            glfwMakeContextCurrent(glfwWindow)
            caps = GL.createCapabilities()
            GL.setCapabilities(caps)

            glfwSwapInterval(0)

            initGL()

            resized = true
        }
        println("/init")
    }*/

    private inline fun <R> lockWithHWND(block: (hwnd: HWND) -> R): R {

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
                return block(HWND(surfaceInfo.hwnd()))

            } finally {
                // Free the drawing surface info
                JAWT_DrawingSurface_FreeDrawingSurfaceInfo(surface.FreeDrawingSurfaceInfo(), dsi)
            }
        } finally {
            // Unlock the drawing surface
            JAWT_DrawingSurface_Unlock(surface.Unlock(), surface)
        }
    }

    fun destroyInternal() {
        println("destroyInternal")

        destroy()

        glfwWindow.makeContextCurrent(GlfwWindowHandle(NULL))
        GL.setCapabilities(null)

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
