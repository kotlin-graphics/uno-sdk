package uno.awt

import gln.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL30C
import java.awt.*
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JPanel
import kotlin.system.exitProcess
import javax.swing.RepaintManager




lateinit var test: Test

fun main() {
    test = Test()
}

class Test : JFrame() {

    lateinit var borderPanel: BorderPanel
    lateinit var centerPanel: CenterPanel
    var viewer: Viewer

    init {
        JFrame.setDefaultLookAndFeelDecorated(false)
        isUndecorated = false

        val rm = RepaintManager.currentManager(this)
        val b = rm.isDoubleBufferingEnabled()
        rm.setDoubleBufferingEnabled(false)

        // Set Look & Feel
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        initComponents()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(event: WindowEvent) = println("windowClosing")
        })

        JPopupMenu.setDefaultLightWeightPopupEnabled(false)

        viewer = Viewer().apply {
            isVisible = true
            size = Dimension(500,500)
        }
        centerPanel.add(viewer, BorderLayout.CENTER)

        size = Dimension(500, 500)
        location = Point(100, 50)
        isVisible = true


    }

    fun makeScreenShot(){
        viewer.renderImmediatly()
        System.err.println("1")
        viewer.renderImmediatly()
        System.err.println("2")
        viewer.renderImmediatly()
        System.err.println("3")

    }

    fun initComponents() {

        borderPanel = BorderPanel().apply { size = Dimension(50, 50) }
        centerPanel = CenterPanel().apply {
            size = Dimension(50, 50)
        }

        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE

        centerPanel.layout = BorderLayout()
        borderPanel.add(centerPanel, BorderLayout.CENTER)

        contentPane.add(borderPanel, BorderLayout.CENTER)

        pack()

        borderPanel.isVisible = true
        centerPanel.isVisible = true
    }

    override fun paint(g: Graphics) {
        super.paint(g)
        println("[Test] paint")
    }
}

class CenterPanel : JPanel() {
    override fun paint(g: Graphics) {
        super.paint(g)
        test.viewer.paint(g)
        println("[CenterPanel] paint")
    }
}

class BorderPanel : JPanel() {
    override fun paint(g: Graphics) {
        super.paint(g)
        println("[BorderPanel] paint")
    }
}

class Viewer : LwjglCanvas() {
     var count = 0;
    var halloCount = 0;
    init {
        animated = false
        focusTraversalKeysEnabled = false
        val rm = RepaintManager.currentManager(this)
        val b = rm.isDoubleBufferingEnabled()
        rm.setDoubleBufferingEnabled(false)

        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                println("keyPressed")
                if (e.keyCode == KeyEvent.VK_F10) {
                    (test.centerPanel as JComponent).paintImmediately(0, 0, 500, 500)
                } else if (e.keyCode == KeyEvent.VK_F11){

                    test.makeScreenShot()

            }    else if (e.keyCode == KeyEvent.VK_ESCAPE)
                    exitProcess(0)
            }
        })
    }

    fun renderImmediatly(){
        val g = test.viewer.graphics;
        if(g != null) {
            halloCount++
            test.viewer.paint(g)

            //g.clearRect(0,0,500,500)
            //g.drawString(" HALLO!"+ halloCount, 350, 250);
            g.dispose()
        }
    }

    override fun render() {
        val time = System.currentTimeMillis()
        GL30C.glClearColor((time % 1000) / 1000f, 0.5f, 0f, 1f)
        GL30C.glClear(GL_COLOR_BUFFER_BIT.i)
    }
}