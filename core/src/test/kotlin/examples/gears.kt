package examples

import glm_.f
import glm_.func.cos
import glm_.func.sin
import glm_.glm
import glm_.has
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import gln.gl
import gln.glClearColor
import kool.floatBufferOf
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL11
import uno.glfw.*
import kotlin.math.sqrt

/* program entry */
fun main() {

    glfw {
        init(installDefaultErrorCB = true)

        hints {
            framebuffer.depthBits = 16
            window.transparentFramebuffer = true
            println(context.version)
        }
    }

    val glfwWindow = GlfwWindow(300, "Gears")
    val window = GlWindow(glfwWindow, forwardCompatible = false)

    // Set callback functions
    window.framebufferSizeCB = ::reshape
    window.keyCB = ::key

    window.makeCurrent()
//    gladLoadGL(glfwGetProcAddress) -> in GlWindow::caps
    glfw.swapInterval = VSync.ON

    val size = window.framebufferSize
    reshape(window, size)

    // Parse command-line options
    init()

    // Main loop
    window.loop {
        // Draw gears
        draw()

        // Update animation
        animate()
    }

    // Terminate GLFW
    glfw.terminate()
}

var viewRot = Vec3(20f, 30f, 0f)
var gear1 = 0
var gear2 = 0
var gear3 = 0
var angle = 0f

val pos = floatBufferOf(5f, 5f, 10f, 0f)
val red = floatBufferOf(0.8f, 0.1f, 0f, 1f)
val green = floatBufferOf(0f, 0.8f, 0.2f, 1f)
val blue = floatBufferOf(0.2f, 0.2f, 1f, 1f)

/* new window size */
fun reshape(window: GlfwWindow, size: Vec2i) {
    val h = size.y.f / size.x

    val znear = 5.0
    val zfar = 30.0
    val xmax = znear * 0.5

    gl.viewport(size)
    GL11.glMatrixMode(GL11.GL_PROJECTION)
    GL11.glLoadIdentity()
    GL11.glFrustum(-xmax, xmax, -xmax * h, xmax * h, znear, zfar)
    GL11.glMatrixMode(GL11.GL_MODELVIEW)
    GL11.glLoadIdentity()
    GL11.glTranslatef(0f, 0f, -20f)
}

/* change view angle, exit upon ESC */
fun key(window: GlfwWindow, k: Key, s: Int, action: InputAction, mods: Int) {

    if (action != InputAction.Press) return

    when (k) {
        Key.Z ->
            if (mods has GLFW.GLFW_MOD_SHIFT)
                viewRot.z -= 5f
            else
                viewRot.z += 5f

        Key.ESCAPE -> window.shouldClose = true
        Key.UP -> viewRot.x += 5f
        Key.DOWN -> viewRot.x -= 5f
        Key.LEFT -> viewRot.y += 5f
        Key.RIGHT -> viewRot.y -= 5f
        else -> {}
    }
}

/* program & OpenGL initialization */
fun init() {

    GL11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, pos)
    GL11.glEnable(GL11.GL_CULL_FACE)
    GL11.glEnable(GL11.GL_LIGHTING)
    GL11.glEnable(GL11.GL_LIGHT0)
    GL11.glEnable(GL11.GL_DEPTH_TEST)

    /* make the gears */
    gear1 = GL11.glGenLists(1)
    GL11.glNewList(gear1, GL11.GL_COMPILE)
    GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, red)
    gear(1f, 4f, 1f, 20, 0.7f)
    GL11.glEndList()

    gear2 = GL11.glGenLists(1)
    GL11.glNewList(gear2, GL11.GL_COMPILE)
    GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, green)
    gear(0.5f, 2f, 2f, 10, 0.7f)
    GL11.glEndList()

    gear3 = GL11.glGenLists(1)
    GL11.glNewList(gear3, GL11.GL_COMPILE)
    GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, blue)
    gear(1.3f, 2f, 0.5f, 10, 0.7f)
    GL11.glEndList()

    GL11.glEnable(GL11.GL_NORMALIZE)
}

/**

Draw a gear wheel.  You'll probably want to call this function when
building a display list since we do a lot of trig here.

Input:  inner_radius - radius of hole at center
outer_radius - radius at center of teeth
width - width of gear teeth - number of teeth
tooth_depth - depth of tooth

 **/
fun gear(innerRadius: Float, outerRadius: Float, width: Float, teeth: Int, toothDepth: Float) {

    val r0 = innerRadius
    val r1 = outerRadius - toothDepth / 2f
    val r2 = outerRadius + toothDepth / 2f

    var da = 2f * glm.πf / teeth / 4f

    GL11.glShadeModel(GL11.GL_FLAT)

    GL11.glNormal3f(0f, 0f, 1f)

    /* draw front face */
    GL11.glBegin(GL11.GL_QUAD_STRIP)
    for (i in 0..teeth) {
        val angle = i * 2f * glm.πf / teeth
        GL11.glVertex3f(r0 * angle.cos, r0 * angle.sin, width * 0.5f)
        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
        if (i < teeth) {
            GL11.glVertex3f(r0 * angle.cos, r0 * angle.sin, width * 0.5f)
            GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, width * 0.5f)
        }
    }
    GL11.glEnd()

    /* draw front sides of teeth */
    GL11.glBegin(GL11.GL_QUADS)
    da = 2f * glm.πf / teeth / 4f
    for (i in 0 until teeth) {
        val angle = i * 2f * glm.πf / teeth

        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
        GL11.glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, width * 0.5f)
        GL11.glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, width * 0.5f)
        GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, width * 0.5f)
    }
    GL11.glEnd()

    GL11.glNormal3f(0f, 0f, -1f)

    /* draw back face */
    GL11.glBegin(GL11.GL_QUAD_STRIP)
    for (i in 0..teeth) {
        val angle = i * 2f * glm.πf / teeth
        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)
        GL11.glVertex3f(r0 * angle.cos, r0 * angle.sin, -width * 0.5f)
        if (i < teeth) {
            GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
            GL11.glVertex3f(r0 * (angle).cos, r0 * angle.sin, -width * 0.5f)
        }
    }
    GL11.glEnd()

    /* draw back sides of teeth */
    GL11.glBegin(GL11.GL_QUADS)
    da = 2f * glm.πf / teeth / 4f
    for (i in 0 until teeth) {
        val angle = i * 2f * glm.πf / teeth

        GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
        GL11.glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, -width * 0.5f)
        GL11.glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, -width * 0.5f)
        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)
    }
    GL11.glEnd()

    /* draw outward faces of teeth */
    GL11.glBegin(GL11.GL_QUAD_STRIP)
    for (i in 0 until teeth) {
        val angle = i * 2f * glm.πf / teeth

        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
        GL11.glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)
        var u = r2 * (angle + da).cos - r1 * angle.cos
        var v = r2 * (angle + da).sin - r1 * angle.sin
        val len = sqrt(u * u + v * v)
        u /= len
        v /= len
        GL11.glNormal3f(v, -u, 0f)
        GL11.glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, width * 0.5f)
        GL11.glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, -width * 0.5f)
        GL11.glNormal3f(angle.cos, angle.sin, 0f)
        GL11.glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, width * 0.5f)
        GL11.glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, -width * 0.5f)
        u = r1 * (angle + 3 * da).cos - r2 * (angle + 2 * da).cos
        v = r1 * (angle + 3 * da).sin - r2 * (angle + 2 * da).sin
        GL11.glNormal3f(v, -u, 0f)
        GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, width * 0.5f)
        GL11.glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
        GL11.glNormal3f(angle.cos, angle.sin, 0f)
    }

    GL11.glVertex3f(r1 * 0f.cos, r1 * 0f.sin, width * 0.5f)
    GL11.glVertex3f(r1 * 0f.cos, r1 * 0f.sin, -width * 0.5f)

    GL11.glEnd()

    GL11.glShadeModel(GL11.GL_SMOOTH)

    /* draw inside radius cylinder */
    GL11.glBegin(GL11.GL_QUAD_STRIP)
    for (i in 0..teeth) {
        val angle = i * 2f * glm.πf / teeth
        GL11.glNormal3f(-angle.cos, -angle.sin, 0f)
        GL11.glVertex3f(r0 * angle.cos, r0 * angle.sin, -width * 0.5f)
        GL11.glVertex3f(r0 * angle.cos, r0 * angle.sin, width * 0.5f)
    }
    GL11.glEnd()
}

/* OpenGL draw function & timing */
fun draw() {

    glClearColor(0f)
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

    GL11.glPushMatrix()
    GL11.glRotatef(viewRot.x, 1f, 0f, 0f)
    GL11.glRotatef(viewRot.y, 0f, 1f, 0f)
    GL11.glRotatef(viewRot.z, 0f, 0f, 1f)

    GL11.glPushMatrix()
    GL11.glTranslatef(-3f, -2f, 0f)
    GL11.glRotatef(angle, 0f, 0f, 1f)
    GL11.glCallList(gear1)
    GL11.glPopMatrix()

    GL11.glPushMatrix()
    GL11.glTranslatef(3.1f, -2f, 0f)
    GL11.glRotatef(-2f * angle - 9f, 0f, 0f, 1f)
    GL11.glCallList(gear2)
    GL11.glPopMatrix()

    GL11.glPushMatrix()
    GL11.glTranslatef(-3.1f, 4.2f, 0f)
    GL11.glRotatef(-2f * angle - 25f, 0f, 0f, 1f)
    GL11.glCallList(gear3)
    GL11.glPopMatrix()

    GL11.glPopMatrix()
}

/* update animation parameters */
fun animate() {
    angle = 100f * glfw.time.f
}