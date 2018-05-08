//package uno.mousePole
//
////import com.jogamp.newt.event.KeyEvent
////import com.jogamp.newt.event.MouseEvent
//import gli_.has
//import gli_.hasnt
//import glm_.glm
//import glm_.mat4x4.Mat4
//import glm_.quat.Quat
//import glm_.vec2.Vec2d
//import glm_.vec2.Vec2i
//import glm_.vec3.Vec3
//import uno.mousePole.ViewPole.RotateMode as Rm
//import uno.mousePole.ViewPole.TargetOffsetDir as Tod
//import org.lwjgl.glfw.GLFW.*
//
///**
// * Created by elect on 17/03/17.
// */
//
///**
// * Created by elect on 17/03/17.
// */
//
//interface ViewProvider {
//    /** Computes the camera matrix. */
//    fun calcMatrix(res: Mat4 = Mat4()): Mat4
//}
//
///** Utility object containing the ObjectPole's position and orientation information.    */
//class ObjectData(
//        /** The world-space position of the object. */
//        var position: Vec3,
//        /** The world-space orientation of the object.  */
//        var orientation: Quat)
//
//private val axisVectors = arrayOf(
//        Vec3(1, 0, 0),
//        Vec3(0, 1, 0),
//        Vec3(0, 0, 1))
//
//enum class Axis { X, Y, Z, MAX }
//enum class RotateMode { DUAL_AXIS, BIAXIAL, SPIN }
//
//
//fun calcRotationQuat(axis: Axis, radAngle: Float, res: Quat) = glm.angleAxis(radAngle, axisVectors[axis.ordinal], res)
//
///**
// *  Mouse-based control over the orientation and position of an object.
// *
// *  This Pole deals with three spaces: local, world, and view. Local refers to the coordinate system of vertices given
// *  to the matrix that this Pole generates. World represents the \em output coordinate system. So vertices enter in
// *  local and are transformed to world. Note that this does not have to actually be the real world-space. It could be
// *  the space of the parent node in some object hierarchy, though there is a caveat below.
// *
// *  View represents the space that vertices are transformed into by the ViewProvider's matrix.
// *  The ViewProvider is given to this class's constructor. The assumption that this Pole makes when using the view space
// *  matrix is that the matrix the ObjectPole generates will be right-multiplied by the view matrix given by the
// *  ViewProvider. So it is assumed that there is no intermediate space between world and view.
// *
// *  By defining these three spaces, it is possible to dictate orientation relative to these spaces.
// *  The ViewProvider exists to allow transformations relative to the current camera.
// *
// *  This Pole is given an action button, which it will listen for click events from. When the action button is held down
// *  and the mouse moved, the object's orientation will change. The orientation will be relative to the view's
// *  orientation if a ViewProvider was provided. If not, it will be relative to the world.
// *
// *  If no modifier keys (shift, ctrl, alt) were held when the click was given, then the object will be oriented in both
// *  the X and Y axes of the transformation space. If the CTRL key is held when the click was given, then the object will
// *  only rotate around either the X or Y axis.
// *  The selection is based on whether the X or the Y mouse coordinate is farthest from the initial position when
// *  dragging started.
// *  If the ALT key is held, then the object will rotate about the Z axis, and only the X position of the mouse affects
// *  the object.
// */
//class ObjectPole
///**
// * Creates an object pole with a given initial position and orientation.
// *
// * @param po The starting position and orientation of the object in world space.
// * @param rotateScale The number of degrees to rotate the object per window space pixel
// * @param actionButton The mouse button to listen for. All other mouse buttons are ignored.
// * @param view An object that will compute a view matrix. This defines the view space that orientations
// * can be relative to. If it is NULL, then orientations will be relative to the world.
// */
//constructor(
//        private var po: ObjectData,
//        /** The scaling factor is the number of degrees to rotate the object per window space pixel.
//         *  The scale is the same for all mouse movements.     */
//        private var rotateScale: Float,
//        private var actionButton: Short,
//        private var view: ViewProvider?) : ViewProvider {
//
//
//    private var initialPo = po
//
//    // Used when rotating.
//    private var rotateMode = RotateMode.DUAL_AXIS
//    private var isDragging = false
//
//    private var prevMousePos = Vec2i()
//    private var startDragMousePos = Vec2i()
//    private var startDragOrient = Quat()
//
//
//    /** Generates the local-to-world matrix for this object.    */
//    @Synchronized override fun calcMatrix(res: Mat4): Mat4 {
//
//        res put 1f
//        res.set(3, po.position, 1f)
//
//        res *= po.orientation to mat4[3]
//
//        return res
//    }
//
//    /** Retrieves the current position and orientation of the object.   */
//    val posOrient get() = po
//
//    /** Resets the object to the initial position/orientation. Will fail if currently dragging. */
//    fun reset() {
//        if (!isDragging) {
//            po.position put initialPo.position
//            po.orientation put initialPo.orientation
//        }
//    }
//
//
//    /* Input Providers
//     *
//     * These functions provide input, since Poles cannot get input for themselves.     */
//
//    /**
//     * Notifies the pole of a mouse button being pressed or released.
//     *
//     * @param event The mouse event */
//    @Synchronized
//    fun mousePressed(event: MouseEvent) {
//
//        // Ignore button presses when dragging.
//        if (!isDragging)
//
//            if (event.button == actionButton) {
//
//                rotateMode = when {
//                    event.isAltDown -> RotateMode.SPIN
//                    event.isControlDown -> RotateMode.BIAXIAL
//                    else -> RotateMode.DUAL_AXIS
//                }
//
//                prevMousePos.put(event.x, event.y)
//                startDragMousePos.put(event.x, event.y)
//                startDragOrient put po.orientation
//
//                isDragging = true
//            }
//    }
//
//    @Synchronized
//    fun mouseReleased(event: MouseEvent) {
//
//        // Ignore up buttons if not dragging.
//        if (isDragging)
//
//            if (event.button == actionButton) {
//
//                mouseDragged(event)
//
//                isDragging = false
//            }
//    }
//
//    /** Notifies the pole that the mouse has moved to the given absolute position.  */
//    @Synchronized
//    fun mouseDragged(event: MouseEvent) {
//
//        if (isDragging) {
//
//            val position = vec2i[0](event.x, event.y)
//            val diff = position.minus(prevMousePos, vec2i[1])
//
//            when (rotateMode) {
//
//                RotateMode.DUAL_AXIS -> {
//
//                    val rot = calcRotationQuat(Axis.Y, glm.radians(diff.x * rotateScale), quat[0])
//                    calcRotationQuat(Axis.X, glm.radians(diff.y * rotateScale), quat[1]).times(rot, rot).normalizeAssign()
//                    rotateView(rot)
//                }
//
//                RotateMode.BIAXIAL -> {
//
//                    val initDiff = position.minus(startDragMousePos, vec2i[2])
//
//                    var axis = Axis.X
//                    var degAngle = initDiff.y * rotateScale
//
//                    if (glm.abs(initDiff.x) > glm.abs(initDiff.y)) {
//                        axis = Axis.Y
//                        degAngle = initDiff.x * rotateScale
//                    }
//                    val rot = calcRotationQuat(axis, glm.radians(degAngle), quat[0])
//                    rotateView(rot, true)
//                }
//
//                RotateMode.SPIN -> rotateView(calcRotationQuat(Axis.Z, glm.radians(-diff.x * rotateScale), quat[0]))
//            }
//            prevMousePos put position
//        }
//    }
//
//    fun rotateWorld(rot: Quat, fromInitial: Boolean = false) {
//
//        val fromInitial_ = if (isDragging) fromInitial else false
//
//        val orient = if (fromInitial_) startDragOrient else po.orientation
//        po.orientation = rot.times(orient, quat[2]).normalizeAssign()
//    }
//
//    fun rotateLocal(rot: Quat, fromInitial: Boolean = false) {
//
//        val fromInitial_ = if (isDragging) fromInitial else false
//
//        val orient = if (fromInitial_) startDragOrient else po.orientation
//        po.orientation = orient.times(rot, quat[3]).normalizeAssign()
//    }
//
//    fun rotateView(rot: Quat, fromInitial: Boolean = false) {
//
//        val fromInitial_ = if (isDragging) fromInitial else false
//
//        if (view != null) {
//
//            val viewQuat = view!!.calcMatrix(mat4[0]) to quat[4]
//            val invViewQuat = viewQuat.conjugate(quat[5])
//            val orient = if (fromInitial_) startDragOrient else po.orientation
//
//            (rot.times(orient, po.orientation)).normalizeAssign()
//
//        } else
//            rotateWorld(rot, fromInitial_)
//    }
//}
//
//
///** Utility object containing the ViewPole's view information.     */
//class ViewData(
//        /** The starting target position position.  */
//        var targetPos: Vec3,
//        /** The initial orientation aroudn the target position. */
//        var orient: Quat,
//        /** The initial radius of the camera from the target point. */
//        var radius: Float,
//        /** The initial spin rotation of the "up" axis, relative to \a orient   */
//        var degSpinRotation: Float)
//
///** Utility object describing the scale of the ViewPole.    */
//class ViewScale(
//        /** The closest the radius to the target point can get. */
//        var minRadius: Float,
//        /** The farthest the radius to the target point can get.    */
//        var maxRadius: Float,
//        /** The radius change to use when the SHIFT key isn't held while mouse wheel scrolling. */
//        var largeRadiusDelta: Float,
//        /** The radius change to use when the SHIFT key \em is held while mouse wheel scrolling.    */
//        var smallRadiusDelta: Float,
//        /** The position offset to use when the SHIFT key isn't held while pressing a movement key. */
//        var largePosOffset: Float,
//        /** The position offset to use when the SHIFT key \em is held while pressing a movement key.    */
//        var smallPosOffset: Float,
//        /** The number of degrees to rotate the view per window space pixel the mouse moves when dragging.  */
//        var rotationScale: Float)
//
///**
// * Mouse-based control over the orientation and position of the camera.
// *
// * This view controller is based on a target point, which is centered in the camera, and an orientation around that
// * target point that represents the camera. The Pole allows the user to rotate around this point, move closer to/farther
// * from it, and to move the point itself.
// *
// * This Pole is given a ViewData object that contains the initial viewing orientation, as well as a ViewScale that
// * represents how fast the various movements change the view, as well as its limitations.
// *
// * This Pole is given an action button, which it will listen for click events from. If the mouse button is clicked and
// * no modifiers are pressed, the the view will rotate around the object in both the view-local X and Y axes. If the CTRL
// * key is held, then it will rotate about the X or Y axes, based on how far the mouse is from the starting point in the
// * X or Y directions. If the ALT key is held, then the camera will spin in the view-local Z direction.
// *
// * Scrolling the mouse wheel up or down moves the camera closer or farther from the object, respectively.
// * The distance is taken from ViewScale::largeRadiusDelta. If the SHIFT key is held while scrolling, then the movement
// * will be the ViewScale::smallRadiusDelta value instead.
// *
// * The target point can be moved, relative to the current view, with the WASD keys. W/S move forward and backwards,
// * while A/D move left and right, respectively. Q and E move down and up, respectively. If the rightKeyboardCtrl
// * parameter of the constructor is set, then it uses the IJKLUO keys instead of WASDQE. The offset applied to the
// * position is ViewScale::largePosOffset; if SHIFT is held, then ViewScale::smallPosOffset is used instead.
// */
//class ViewPole
///**
// * Creates a view pole with the given initial target position, view definition, and action button.
// *
// * @param currView The starting state of the view.
// * @param viewScale The viewport definition to use.
// * @param actionButton The mouse button to listen for. All other mouse buttons are ignored.
// * \param bRightKeyboardCtrls If true, then it uses IJKLUO instead of WASDQE keys.
// */
//constructor(
//        private var currView: ViewData,
//        private var viewScale: ViewScale,
//        private var actionButton: Int = GLFW_MOUSE_BUTTON_1,
//        private var rightKeyboardCtrls: Boolean = false) : ViewProvider {
//
//    private var initialView = currView
//
//    //Used when rotating.
//    var isDragging = false
//        private set
//    private var rotateMode = Rm.DUAL_AXIS_ROTATE
//
//    private var degStartDragSpin = 0f
//    private var startDragMouseLoc = Vec2i()
//    private val startDragOrient = Quat()
//
//
//    var rotationScale
//        /** Gets the current scaling factor for orientation changes.    */
//        get() = viewScale.rotationScale
//        /** Sets the scaling factor for orientation changes.
//         *
//         * The scaling factor is the number of degrees to rotate the view per window space pixel.
//         * The scale is the same for all mouse movements.     */
//        set(value) {
//            viewScale.rotationScale = value
//        }
//
//    /** Retrieves the current viewing information.  */
//    val view get() = currView
//
//
//    /** Generates the world-to-camera matrix for the view.     */
//    @Synchronized override fun calcMatrix(res: Mat4): Mat4 {
//
//        res put 1f
//
//        // Remember: these transforms are in reverse order.
//
//        /* In this space, we are facing in the correct direction. Which means that the camera point is directly behind
//         * us by the radius number of units.    */
//        res.translateAssign(0f, 0f, -currView.radius)
//
//        //Rotate the world to look in the right direction..
//        val fullRotation = glm.angleAxis(currView.degSpinRotation, 0f, 0f, 1f, quat[6])
//        fullRotation *= currView.orient
//        res *= (fullRotation to mat4[1])
//
//        // Translate the world by the negation of the lookat point, placing the origin at the lookat point.
//        res.translateAssign(-currView.targetPos.x, -currView.targetPos.y, -currView.targetPos.z)
//
//        return res
//    }
//
//    /** Resets the view to the initial view. Will fail if currently dragging.   */
//    fun reset() {
//        if (!isDragging) currView = initialView
//    }
//
//    fun processXChange(xDiff: Int, clearY: Boolean = false) {
//
//        val radAngleDiff = glm.radians(xDiff * viewScale.rotationScale)
//
//        // Rotate about the world-space Y axis.
//        glm.angleAxis(radAngleDiff, 0f, 1f, 0f, quat[7])
//        startDragOrient.times(quat[8], currView.orient)
//    }
//
//    fun processYChange(yDiff: Int, clearXZ: Boolean = false) {
//
//        val radAngleDiff = glm.radians(yDiff * viewScale.rotationScale)
//
//        // Rotate about the local-space X axis.
//        glm.angleAxis(radAngleDiff, 1f, 0f, 0f, quat[9])
//        quat[9].times(startDragOrient, currView.orient)
//    }
//
//    fun processXYChange(xDiff: Int, yDiff: Int) {
//
//        val radXAngleDiff = glm.radians(xDiff * viewScale.rotationScale)
//        val radYAngleDiff = glm.radians(yDiff * viewScale.rotationScale)
//
//        // Rotate about the world-space Y axis.
//        glm.angleAxis(radXAngleDiff, 0f, 0f, 1f, quat[10])
//        startDragOrient.times(quat[10], currView.orient)
//        //Rotate about the local-space X axis.
//        glm.angleAxis(radYAngleDiff, 1f, 0f, 0f, quat[11])
//        quat[11].times(currView.orient, currView.orient)
//    }
//
//    fun processSpinAxis(xDiff: Int, yDiff: Int) {
//
//        val degSpinDiff = xDiff * viewScale.rotationScale
//        currView.degSpinRotation = degSpinDiff + degStartDragSpin
//    }
//
//    fun beginDragRotate(start: Vec2i, rotMode: RotateMode) {
//
//        rotateMode = rotMode
//
//        startDragMouseLoc put start
//
//        degStartDragSpin = currView.degSpinRotation
//
//        startDragOrient put currView.orient
//
//        isDragging = true
//    }
//
//    fun onDragRotate(curr: Vec2i) {
//
//        val diff = curr.minus(startDragMouseLoc, vec2i[3])
//
//        when (rotateMode) {
//
//            Rm.DUAL_AXIS_ROTATE -> processXYChange(diff.x, diff.y)
//
//            Rm.BIAXIAL_ROTATE ->
//                if (glm.abs(diff.x) > glm.abs(diff.y))
//                    processXChange(diff.x, true)
//                else
//                    processYChange(diff.y, true)
//
//            Rm.XZ_AXIS_ROTATE -> processXChange(diff.x)
//
//            Rm.Y_AXIS_ROTATE -> processYChange(diff.y)
//
//            Rm.SPIN_VIEW_AXIS -> processSpinAxis(diff.x, diff.y)
//        }
//    }
//
//    fun endDragRotate(end: Vec2i, keepResult: Boolean = true) {
//
//        if (keepResult)
//            onDragRotate(end)
//        else
//            currView.orient put startDragOrient
//
//        isDragging = false
//    }
//
//    fun moveCloser(largeStep: Boolean = true) {
//
//        currView.radius -= if (largeStep) viewScale.largeRadiusDelta else viewScale.smallRadiusDelta
//
//        if (currView.radius < viewScale.minRadius)
//            currView.radius = viewScale.minRadius
//    }
//
//    fun moveAway(largeStep: Boolean = true) {
//
//        currView.radius += if (largeStep) viewScale.largeRadiusDelta else viewScale.smallRadiusDelta
//
//        if (currView.radius > viewScale.maxRadius)
//            currView.radius = viewScale.maxRadius
//    }
//
//    /**
//     * Input Providers
//     *
//     * These functions provide input, since Poles cannot get input for themselves. See \ref module_glutil_poles
//     * "the Pole manual" for details.   */
//    @Synchronized
//    fun mouseClick(button: Int, action: Int, mods: Int) {
//        lastMods = mods
//        if (action == GLFW_PRESS) {
//            // Ignore all other button presses when dragging.
//            if (!isDragging)
//                if (button == actionButton)
//                    beginDragRotate(lastPos, when {
//                        ctrlDown -> Rm.BIAXIAL_ROTATE
//                        altDown -> Rm.SPIN_VIEW_AXIS
//                        else -> Rm.DUAL_AXIS_ROTATE
//                    })
//        } else {
//            // Ignore all other button releases when not dragging
//            if (isDragging)
//                if (button == actionButton)
//                    if (rotateMode == Rm.DUAL_AXIS_ROTATE || rotateMode == Rm.SPIN_VIEW_AXIS || rotateMode == Rm.BIAXIAL_ROTATE)
//                        endDragRotate(vec2i[4].apply { put(lastPos) })
//        }
//    }
//
//    fun mouseMove(position: Vec2i) {
//        if (isDragging)
//            onDragRotate(vec2i[5].apply { put(position) })
//        lastPos put position
//    }
//
//    @Synchronized
//    fun mouseWheel(scroll: Vec2d) = if (scroll.y > 0) moveCloser(shiftUp) else moveAway(shiftUp)
//
//    @Synchronized
//    fun buttonPressed(event: KeyEvent) {
//
//        val distance = if (event.isShiftDown) viewScale.largePosOffset else viewScale.smallPosOffset
//
//        when (event.keyCode) {
//
//            KeyEvent.VK_W -> offsetTargetPos(Tod.FORWARD, distance)
//            KeyEvent.VK_K -> offsetTargetPos(Tod.BACKWARD, distance)
//            KeyEvent.VK_L -> offsetTargetPos(Tod.RIGHT, distance)
//            KeyEvent.VK_J -> offsetTargetPos(Tod.LEFT, distance)
//            KeyEvent.VK_O -> offsetTargetPos(Tod.UP, distance)
//            KeyEvent.VK_U -> offsetTargetPos(Tod.DOWN, distance)
//        }
//    }
//
//    fun offsetTargetPos(dir: Tod, worldDistance: Float) {
//
//        val offsetDir = offsets[dir.ordinal]
//        offsetTargetPos(offsetDir.times(worldDistance, vec3[0]))
//    }
//
//    fun offsetTargetPos(cameraoffset: Vec3) {
//
//        val currMat = calcMatrix(mat4[2])
//        val orientation = currMat.to(quat[12])
//
//        val invOrient = orientation.conjugate(quat[13])
//        val worldOffset = invOrient.times(cameraoffset, vec3[1])
//
//        currView.targetPos plusAssign worldOffset
//    }
//
//    fun keyPressed(key: Int, scancode: Int, action: Int, mods: Int) {
//        lastMods = mods
//        val distance = if (shiftDown) viewScale.smallPosOffset else viewScale.largePosOffset
//        when (key) {
//            GLFW_KEY_W -> offsetTargetPos(Tod.FORWARD, distance)
//            GLFW_KEY_S -> offsetTargetPos(Tod.BACKWARD, distance)
//            GLFW_KEY_D -> offsetTargetPos(Tod.RIGHT, distance)
//            GLFW_KEY_A -> offsetTargetPos(Tod.LEFT, distance)
//            GLFW_KEY_E -> offsetTargetPos(Tod.UP, distance)
//            GLFW_KEY_Q -> offsetTargetPos(Tod.DOWN, distance)
//        }
//    }
//
//    val offsets = arrayOf(
//            Vec3(+0f, +1f, +0f),
//            Vec3(+0f, -1f, +0f),
//            Vec3(+0f, +0f, -1f),
//            Vec3(+0f, +0f, +1f),
//            Vec3(+1f, +0f, +0f),
//            Vec3(-1f, +0f, +0f))
//
//    enum class TargetOffsetDir { UP, DOWN, FORWARD, BACKWARD, RIGHT, LEFT }
//
//    enum class RotateMode { DUAL_AXIS_ROTATE, BIAXIAL_ROTATE, XZ_AXIS_ROTATE, Y_AXIS_ROTATE, SPIN_VIEW_AXIS }
//
//    var lastMods = 0
//    val shiftUp get() = lastMods hasnt GLFW_MOD_SHIFT
//    val shiftDown get() = lastMods has GLFW_MOD_SHIFT
//    val ctrlDown get() = lastMods has GLFW_MOD_CONTROL
//    val altDown get() = lastMods has GLFW_MOD_ALT
//    val lastPos = Vec2i()
//}
//
//private val mat4 = Array(4, { Mat4() })
//private val vec2i = Array(2, { Vec2i() })
//private val vec3 = Array(2, { Vec3() })
//private val quat = Array(13, { Quat() })