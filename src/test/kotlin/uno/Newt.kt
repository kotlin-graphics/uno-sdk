package uno

import com.jogamp.newt.awt.NewtCanvasAWT
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT
import com.jogamp.opengl.GL2ES3.GL_QUADS
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.util.Animator
import glm_.f
import glm_.vec2.operators.opVec2
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main(args: Array<String>) {

    val frame = JFrame("AWT test").apply {
        defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        layout = BorderLayout()
        preferredSize = Dimension(600, 600)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                e!!.window.dispose()
                System.exit(0)
            }
        })
    }

    var last = 0L
    var time = 0L
    var frames = 0

    val caps = GLCapabilities(null)
    val window = GLWindow.create(caps).apply {

//        setSize(600, 600)

        addGLEventListener(object : GLEventListener {

            override fun init(drawable: GLAutoDrawable) {
                drawable.gl.gL2.apply {
                    glClearColor(0.3f, 0.4f, 0.5f, 1f)
                    swapInterval = 0
                }
                last = System.currentTimeMillis()
            }

            override fun display(drawable: GLAutoDrawable) = drawable.gl.gL2.run {
                val aspect = width.f / height
                glClear(GL_COLOR_BUFFER_BIT)
                glViewport(0, 0, width, height)
                glBegin(GL_QUADS)
                glColor3f(0.4f, 0.6f, 0.8f)
                glVertex2f(-0.75f / aspect, 0.0f)
                glVertex2f(0f, -0.75f)
                glVertex2f(+0.75f / aspect, 0f)
                glVertex2f(0f, +0.75f)
                glEnd()

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

            override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {
                println()
            }

            override fun dispose(drawable: GLAutoDrawable) {
                println()
            }
        })
    }

    val newtCanvas = NewtCanvasAWT(window)

    frame.apply {

        add(newtCanvas, BorderLayout.CENTER)

        setSize(600, 600)
        isVisible = true
    }

    val animator = Animator(window).apply {
        start()
    }
}