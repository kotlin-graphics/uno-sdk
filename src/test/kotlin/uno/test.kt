package uno

import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.util.Animator
import glm.mat4x4.Mat4
import glm.rad
import io.kotlintest.specs.StringSpec
import uno.glm.MatrixStack
import uno.glsl.Program

/**
 * Created by GBarbieri on 02.02.2017.
 */


class Test : StringSpec() {

    init {

        "test" {

            val parthenonWidth = 14.0f
            val parthenonLength = 20.0f
            val parthenonColumnHeight = 5.0f
            val parthenonBaseHeight = 1.0f
            val parthenonTopHeight = 2.0f

            val stack = MatrixStack()


            val a = stack
                    .translate(20.0f, 0.0f, -10.0f)
                    .translate(
                            0.0f,
                            parthenonColumnHeight + parthenonBaseHeight + parthenonTopHeight / 2.0f,
                            parthenonLength / 2.0f)
                    .rotateX(-135f)
                    .top()
//                    .rotateY(45f)

            val top = stack.top()

            val b = Mat4()
                    .translate_(20.0f, 0.0f, -10.0f)
                    .translate_(
                            0.0f,
                            parthenonColumnHeight + parthenonBaseHeight + parthenonTopHeight / 2.0f,
                            parthenonLength / 2.0f)
                    .rotate(-135f.rad, 1f, 0f, 0f)

//            for(i in 0 until 16){
//                println("a "+a[i])
////                println("b "+b[i])
//            }

// .rotate_(45f, 0f, 1f, 0f)

            println()

//            val app = App()
//
//            window.addGLEventListener(app)
//            window.isVisible = true
//
//            animator.start()
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