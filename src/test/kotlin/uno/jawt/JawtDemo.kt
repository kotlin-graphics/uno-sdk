package uno.jawt

import glm_.vec2.Vec2i
import org.lwjgl.system.Platform
import uno.awt.LwjglCanvas
import java.awt.BorderLayout
import java.awt.event.*
import javax.swing.JFrame
import javax.swing.WindowConstants
import org.lwjgl.system.jawt.JAWT as Jawt


/** AWT integration demo using jawt.  */
fun main() {

    if (Platform.get() != Platform.WINDOWS)
        throw UnsupportedOperationException("This demo can only run on Windows.")

    val viewer = Viewer()//.apply { setSize(640, 480) }

    val frame = JFrame("JAWT Demo").apply {
        // set it, because default is HIDE_ON_CLOSE
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            /*
            windowClosed will be called when it's too late, the awtOld hwnd will be invalid at that point and
            so will be also the glfw Window handle
             */
            override fun windowClosing(e: WindowEvent?) {
                println("windowClosing")
                viewer.destroyInternal()
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
                viewer.toggleAnimation()
        }

        override fun keyReleased(e: KeyEvent) {
            println("keyReleased")
        }
    }

    val mouseListener = object : MouseListener, MouseMotionListener, MouseWheelListener {
        override fun mouseClicked(e: MouseEvent) {
            println("clicked")
        }

        override fun mouseEntered(e: MouseEvent?) {
            println("entered")
        }

        override fun mouseExited(e: MouseEvent?) {
            println("exited")
        }

        override fun mousePressed(e: MouseEvent?) {
            println("pressed")
        }

        override fun mouseReleased(e: MouseEvent?) {
            println("released")
        }

        override fun mouseDragged(e: MouseEvent?) {
            println("dragged")
        }

        override fun mouseMoved(e: MouseEvent) {
            println("moved (" + e.x + ", " + e.y + ") " + Thread.currentThread().name)
        }

        override fun mouseWheelMoved(e: MouseWheelEvent?) {
            println("wheel")
        }
    }

    frame.apply {
        layout = BorderLayout()
        add(viewer, BorderLayout.CENTER)

        pack()
        setSize(640, 480)
        viewer.addKeyListener(keyListener)
//        canvas.addMouseListener(mouseListener)
//        canvas.addMouseMotionListener(mouseListener)
//        canvas.addMouseWheelListener(mouseListener)
        isVisible = true
    }
}


class Viewer : LwjglCanvas() {

    val gears = AbstractGears()

    override fun init() = gears.init()

    override fun render() = gears.render()

    override fun reshape(size: Vec2i) = gears.reshape(size)

    override fun destroy() = gears.destroy()
}