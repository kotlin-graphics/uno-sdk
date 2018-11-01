package uno

import glm_.f
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import uno.awt.AWTGLCanvas
import uno.awt.AbstractGears
import uno.awt.GLData
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame


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
    val data = GLData().apply {
        samples = 4
        swapInterval = 0
    }

    val canvas = object : AWTGLCanvas(data) {

        var last = 0L
        var time = 0L
        var frames = 0

        val gears = AbstractGears()

        override fun initGL() {
            System.out.println("OpenGL version: " + effective.majorVersion + "." + effective.minorVersion + " (Profile: " + effective.profile + ")")
            GL.createCapabilities()
//            glClearColor(0.3f, 0.4f, 0.5f, 1f)
            gears.init()
            last = System.currentTimeMillis()
        }

        override fun paintGL() {
            //                System.out.println("paintGL");
            val aspect = width.f / height
            glClear(GL_COLOR_BUFFER_BIT)
            glViewport(0, 0, width, height)
//            glBegin(GL_QUADS)
//            glColor3f(0.4f, 0.6f, 0.8f)
//            glVertex2f(-0.75f / aspect, 0.0f)
//            glVertex2f(0f, -0.75f)
//            glVertex2f(+0.75f / aspect, 0f)
//            glVertex2f(0f, +0.75f)
//            glEnd()
            gears.renderLoop()
            swapBuffers()

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
    }

    frame.apply {
        add(canvas, BorderLayout.CENTER)
        pack()
        isVisible = true
        transferFocus()
    }

//    val renderLoop = object : Runnable {
//        override fun run() {
//            if (!canvas.isValid)
//                return
//            canvas.render()
//            SwingUtilities.invokeLater(this)
//        }
//    }
//    SwingUtilities.invokeLater(renderLoop)

    var run = true

    Thread {
        canvas.init()
        while (run) {
            if (canvas.isValid)
                canvas.render()
        }
        canvas.end()
    }.start()
}