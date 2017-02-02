package main

import com.jogamp.common.net.Uri
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.util.Animator
import extensions.uri
import glsl.Program
import io.kotlintest.specs.StringSpec

/**
 * Created by GBarbieri on 02.02.2017.
 */

class Test : StringSpec() {

    init {

        "test" {
            println("test")
            val app = App()

            window.addGLEventListener(app)
            window.isVisible = true

            animator.start()
            println("/test")
        }
    }
}

val window = GLWindow.create(GLCapabilities(GLProfile.get(GLProfile.GL3)))
val animator = Animator(window)

class App : GLEventListener {

    override fun init(drawable: GLAutoDrawable) {
        println("init")
        with(drawable.gl.gL3) {

//            println("a ${javaClass.classLoader.getResource("shader.vert").toString()}")
//            println("b ${Uri.valueOf(javaClass.getResource("shader.vert"))}")
            val program = Program(this, javaClass, arrayOf("shader.vert", "shader.frag"))
        }
    }

    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {
        println("reshape")
        animator.remove(window)
        window.destroy()
    }

    override fun display(drawable: GLAutoDrawable) {
        println("display")
    }

    override fun dispose(drawable: GLAutoDrawable) {
        println("dispose")
        System.exit(0)
    }
}