package uno.mousePole

import glm.mat.Mat4
import glm.quat.Quat
import glm.vec._3.Vec3
import glm.glm

/**
 * Created by elect on 17/03/17.
 */

/** Abstract base class used by ViewPole to identify that it provides a viewing matrix. */
abstract class ViewProvider {

    /** Computes the camera matrix. */
    abstract fun calcMatrix(): Mat4
}

/** Utility object containing the ObjectPole's position and orientation information.    */
class ObjectData(

        /** The world-space position of the object. */
        var position: Vec3,
        /** The world-space orientation of the object.  */
        var orientation: Quat)


/**
 *  Mouse-based control over the orientation and position of an object.
 *
 *  This Pole deals with three spaces: local, world, and view. Local refers to the coordinate system of vertices given
 *  to the matrix that this Pole generates. World represents the \em output coordinate system. So vertices enter in
 *  local and are transformed to world. Note that this does not have to actually be the real world-space. It could be
 *  the space of the parent node in some object hierarchy, though there is a caveat below.
 *
 *  View represents the space that vertices are transformed into by the ViewProvider's matrix.
 *  The ViewProvider is given to this class's constructor. The assumption that this Pole makes when using the view space
 *  matrix is that the matrix the ObjectPole generates will be right-multiplied by the view matrix given by the
 *  ViewProvider. So it is assumed that there is no intermediate space between world and view.
 *
 *  By defining these three spaces, it is possible to dictate orientation relative to these spaces.
 *  The ViewProvider exists to allow transformations relative to the current camera.
 *
 *  This Pole is given an action button, which it will listen for click events from. When the action button is held down
 *  and the mouse moved, the object's orientation will change. The orientation will be relative to the view's
 *  orientation if a ViewProvider was provided. If not, it will be relative to the world.
 *
 *  If no modifier keys (shift, ctrl, alt) were held when the click was given, then the object will be oriented in both
 *  the X and Y axes of the transformation space. If the CTRL key is held when the click was given, then the object will
 *  only rotate around either the X or Y axis.
 *  The selection is based on whether the X or the Y mouse coordinate is farthest from the initial position when
 *  dragging started.
 *  If the ALT key is held, then the object will rotate about the Z axis, and only the X position of the mouse affects
 *  the object.
 */
class ObjectPole {

    var view: ViewProvider
    var po: ObjectData
    var initialPo: ObjectData

    /** The scaling factor is the number of degrees to rotate the object per window space pixel.
     *  The scale is the same for all mouse movements.     */
    var rotateScale: Float
    var actionButton: Short

    // Used when rotating.
    var rotateMode = RotateMode.DualAxis
    var isDragging = false

    /**
     * Creates an object pole with a given initial position and orientation.
     *
     * @param initialData The starting position and orientation of the object in world space.
     * @param rotateScale The number of degrees to rotate the object per window space pixel
    \param actionButton The mouse button to listen for. All other mouse buttons are ignored.
    \param pLookatProvider An object that will compute a view matrix. This defines the view space
    that orientations can be relative to. If it is NULL, then orientations will be relative to the world.
     */
    constructor(initialData: ObjectData, rotateScale: Float, actionButton: Short, lookatProvider: ViewProvider) {

        view = lookatProvider
        po = initialData
        initialPo = initialData
        this.rotateScale = rotateScale
        this.actionButton = actionButton
    }

    /** Generates the local-to-world matrix for this object.    */
    fun calcMatrix(): Mat4 {

        val translateMat = Mat4()
        translateMat.set(3, po.position, 1f)

        return translateMat times_ po.orientation.toMat4()
    }

    /** Retrieves the current position and orientation of the object.   */
    fun getPosOrient() = po

    /** Resets the object to the initial position/orientation. Will fail if currently dragging. */
    fun reset() {
        if(!isDragging) {
            po.position put initialPo.position
            po.orientation put initialPo.orientation
        }
    }

    val axisVectors = arrayOf(Vec3(1, 0, 0), Vec3(0, 1, 0), Vec3(0, 0, 1))

//    fun calcRotationQuat(axis: Axis, degAngle: Float) = glm.angleAxis()

    enum class Axis {X, Y, Z, MAX }
    enum class RotateMode {DualAxis, Biaxial, Spin }
}