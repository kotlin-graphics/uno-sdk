package uno.awt


import glm_.d
import glm_.f
import glm_.func.cos
import glm_.func.sin
import glm_.vec2.Vec2i
import kool.stak
import org.lwjgl.opengl.GL11.*
import kotlin.math.sqrt


class AbstractGears {

    private val viewRotZ = 0f

    private var gear1 = 0
    private var gear2 = 0
    private var gear3 = 0

    private var angle = 0f

    fun init() = stak {
        System.out.println("GL_VENDOR: ${glGetString(GL_VENDOR)}")
        System.out.println("GL_RENDERER: ${glGetString(GL_RENDERER)}")
        System.out.println("GL_VERSION: ${glGetString(GL_VERSION)}")

        // setup ogl
        glEnable(GL_CULL_FACE)
        glEnable(GL_LIGHTING)
        glEnable(GL_LIGHT0)
        glEnable(GL_DEPTH_TEST)

        // make the gears
        gear1 = glGenLists(1)
        glNewList(gear1, GL_COMPILE)
        glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, it.floats(0.8f, 0.1f, 0f, 1f))
        gear(1f, 4f, 1f, 20, 0.7f)
        glEndList()

        gear2 = glGenLists(1)
        glNewList(gear2, GL_COMPILE)
        glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, it.floats(0f, 0.8f, 0.2f, 1f))
        gear(0.5f, 2f, 2f, 10, 0.7f)
        glEndList()

        gear3 = glGenLists(1)
        glNewList(gear3, GL_COMPILE)
        glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, it.floats(0.2f, 0.2f, 1f, 1f))
        gear(1.3f, 2f, 0.5f, 10, 0.7f)
        glEndList()

        glEnable(GL_NORMALIZE)

        glMatrixMode(GL_MODELVIEW)
        glLoadIdentity()
        glTranslatef(0f, 0f, -40f)
    }

    fun render() {
        angle += 2f

        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        glPushMatrix()
        glRotatef(view_rotx, 1f, 0f, 0f)
        glRotatef(view_roty, 0f, 1f, 0f)
        glRotatef(viewRotZ, 0f, 0f, 1f)

        stak { glLightfv(GL_LIGHT0, GL_POSITION, it.floats(5f, 5f, 10f, 0f)) }

        glPushMatrix()
        glTranslatef(-3f, -2f, 0f)
        glRotatef(angle, 0f, 0f, 1f)
        glCallList(gear1)
        glPopMatrix()

        glPushMatrix()
        glTranslatef(3.1f, -2f, 0f)
        glRotatef(-2f * angle - 9f, 0f, 0f, 1f)
        glCallList(gear2)
        glPopMatrix()

        glPushMatrix()
        glTranslatef(-3.1f, 4.2f, 0f)
        glRotatef(-2f * angle - 25f, 0f, 0f, 1f)
        glCallList(gear3)
        glPopMatrix()

        glPopMatrix()
    }

    fun reshape(size: Vec2i) {

        glViewport(0, 0, size.x, size.y)

        val f = size.aspect

        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        glFrustum(-1.0, 1.0, -f.d, f.d, 5.0, 100.0)
        glMatrixMode(GL_MODELVIEW)
    }

    fun destroy() = glDeleteLists(gear1, 3)

    companion object {

        private val view_rotx = 20f
        private val view_roty = 30f

        /**
         * Draw a gear wheel.  You'll probably want to call this function when
         * building a display list since we do a lot of trig here.
         *
         * @param innerRadius radius of hole at center
         * @param outerRadius radius at center of teeth
         * @param width        width of gear
         * @param teeth        number of teeth
         * @param toothDepth  depth of tooth
         */
        private fun gear(innerRadius: Float, outerRadius: Float, width: Float, teeth: Int, toothDepth: Float) {
            var angle: Float
            val da = 2f * Math.PI.f / teeth.f / 4f

            val r1 = outerRadius - toothDepth / 2.0f
            val r2 = outerRadius + toothDepth / 2.0f

            glShadeModel(GL_FLAT)

            glNormal3f(0.0f, 0.0f, 1.0f)

            var i = 0

            /* draw front face */
            glBegin(GL_QUAD_STRIP)
            while (i <= teeth) {
                angle = i.f * 2f * Math.PI.f / teeth

                glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, width * 0.5f)
                glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
                if (i < teeth) {
                    glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, width * 0.5f)
                    glVertex3f(r1 * (angle + 3f * da).cos, r1 * (angle + 3f * da).sin, width * 0.5f)
                }
                i++
            }
            glEnd()

            /* draw front sides of teeth */
            glBegin(GL_QUADS)
            i = 0
            while (i < teeth) {
                angle = i.toFloat() * 2.0f * Math.PI.toFloat() / teeth

                glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
                glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, width * 0.5f)
                glVertex3f(r2 * (angle + 2f * da).cos, r2 * (angle + 2f * da).sin, width * 0.5f)
                glVertex3f(r1 * (angle + 3f * da).cos, r1 * (angle + 3f * da).sin, width * 0.5f)
                i++
            }
            glEnd()

            /* draw back face */
            glBegin(GL_QUAD_STRIP)
            i = 0
            while (i <= teeth) {
                angle = i.f * 2f * Math.PI.f / teeth

                glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)
                glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, -width * 0.5f)
                glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
                glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, -width * 0.5f)
                i++
            }
            glEnd()

            /* draw back sides of teeth */
            glBegin(GL_QUADS)
            i = 0
            while (i < teeth) {
                angle = i.f * 2f * Math.PI.f / teeth

                glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
                glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, -width * 0.5f)
                glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, -width * 0.5f)
                glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)
                i++
            }
            glEnd()

            /* draw outward faces of teeth */
            glBegin(GL_QUAD_STRIP)
            i = 0
            while (i < teeth) {
                angle = i.toFloat() * 2.0f * Math.PI.toFloat() / teeth

                glVertex3f(r1 * angle.cos, r1 * angle.sin, width * 0.5f)
                glVertex3f(r1 * angle.cos, r1 * angle.sin, -width * 0.5f)

                var u = r2 * (angle + da).cos - r1 * angle.cos
                var v = r2 * (angle + da).sin - r1 * angle.sin

                val len = sqrt(u * u + v * v)

                u /= len
                v /= len

                glNormal3f(v, -u, 0f)
                glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, width * 0.5f)
                glVertex3f(r2 * (angle + da).cos, r2 * (angle + da).sin, -width * 0.5f)
                glNormal3f(angle.cos, angle.sin, 0f)
                glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, width * 0.5f)
                glVertex3f(r2 * (angle + 2 * da).cos, r2 * (angle + 2 * da).sin, -width * 0.5f)

                u = r1 * (angle + 3 * da).cos - r2 * (angle + 2 * da).cos
                v = r1 * (angle + 3 * da).sin - r2 * (angle + 2 * da).sin

                glNormal3f(v, -u, 0f)
                glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, width * 0.5f)
                glVertex3f(r1 * (angle + 3 * da).cos, r1 * (angle + 3 * da).sin, -width * 0.5f)
                glNormal3f(angle.cos, angle.sin, 0f)
                i++
            }
            glVertex3f(r1 * 0f.cos, r1 * 0f.sin, width * 0.5f)
            glVertex3f(r1 * 0f.cos, r1 * 0f.sin, -width * 0.5f)
            glEnd()

            glShadeModel(GL_SMOOTH)

            /* draw inside radius cylinder */
            glBegin(GL_QUAD_STRIP)
            i = 0
            while (i <= teeth) {
                angle = i.f * 2f * Math.PI.f / teeth

                glNormal3f(-angle.cos, -angle.sin, 0f)
                glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, -width * 0.5f)
                glVertex3f(innerRadius * angle.cos, innerRadius * angle.sin, width * 0.5f)
                i++
            }
            glEnd()
        }
    }

}