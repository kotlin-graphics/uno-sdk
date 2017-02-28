package main

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.util.Animator
import com.jogamp.opengl.util.glsl.ShaderProgram
import glsl.Program
import glsl.shaderCodeOf
import io.kotlintest.specs.StringSpec

/**
 * Created by GBarbieri on 02.02.2017.
 */


class Test : StringSpec() {

    init {

        "test" {
            val app = App()

            window.addGLEventListener(app)
            window.isVisible = true

            animator.start()
        }
    }
}

val window: GLWindow = GLWindow.create(GLCapabilities(GLProfile.get(GLProfile.GL3)))
val animator = Animator(window)

class App : GLEventListener {

    override fun init(drawable: GLAutoDrawable) {

        val gl = drawable.gl.gL3

        with(gl) {

//            val program = ShaderProgram()
//
//            val vert = shaderCodeOf("main/shader.vert", gl, this::class.java)
//            val frag = shaderCodeOf("main/shader.frag", gl, this::class.java)
//
//            program.add(gl, vert, System.err)
//            program.add(gl, frag, System.err)
//
//            program.link(gl, System.err)

            val a = Program(gl, this::class.java, "main", "shader.vert", "shader.frag", "matrix", "myTexture")
            val b = Program(gl, this::class.java, "main/shader.vert", "main/shader.frag", "matrix", "myTexture")

            println("ok")

//            println("a ${javaClass.classLoader.getResource("shader.vert").toString()}")
//            println("b ${Uri.valueOf(javaClass.getResource("shader.vert"))}")
//            val program = Program(this, javaClass, arrayOf("shader.vert", "shader.frag"))
        }
    }

    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {

        animator.remove(window)
        window.destroy()
    }

    override fun display(drawable: GLAutoDrawable) {
    }

    override fun dispose(drawable: GLAutoDrawable) {
        System.exit(0)
    }
}