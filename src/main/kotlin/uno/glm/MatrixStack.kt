/** Copyright (C) 2011 by Jason L. McKesson **/
/** This file is licensed by the MIT License. **/

package uno.glm

import glm_.glm
import glm_.rad
import glm_.mat4x4.Mat4
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import java.util.*

/**
 * Implements a stack for glm::mat4 transformations.
 *
 * A matrix stack is a sequence of transforms which you can preserve and restore as needed. The
 * stack has the concept of a "current matrix", which can be retrieved with the Top() function.
 * The top matrix can even be obtained as a float array. The pointer returned will remain valid until
 * this object is destroyed (though its values will change). This is useful for uploading matrices
 * to OpenGL via glUniformMatrix4fv.
 *
 * The other functions will right-multiply a transformation matrix with the current matrix, thus
 * changing the current matrix.
 *
 * The main power of the matrix stack is the ability to preserve and restore matrices in a stack fashion.
 * The current matrix can be preserved on the stack with Push() and the most recently preserved matrix
 * can be restored with Pop(). You must ensure that you do not Pop() more times than you Push(). Also,
 * while this matrix stack does not have an explicit size limit, if you Push() more times than you Pop(),
 * then you can eventually run out of memory (unless you create and destroy the MatrixStack every frame).

 * The best way to manage the stack is to never use the Push() and Pop() methods directly.
 * Instead, use the PushStack object to do all pushing and popping. That will ensure that
 * overflows and underflows cannot not happen.
 */
class MatrixStack(
        /** Initializes the matrix stack with the identity matrix or the given matrix   */
        mat: Mat4 = Mat4()) {

    internal val matrices = Stack<Mat4>()
    internal var currMat = mat

    // Stack Maintenance Functions
    // These functions maintain the matrix stack. You must take care not to underflow or overflow the stack

    /** Preserves the current matrix on the stack   */
    fun push(): MatrixStack {
        matrices.push(Mat4(currMat))
        return this
    }

    /** Restores the most recently preserved matrix.    */
    fun pop(): MatrixStack {
        currMat = matrices.pop()
        return this
    }

    /** Restores the current matrix to the value of the most recently preserved matrix.
     *  This function does not affect the depth of the matrix stack.    */
    fun reset(): MatrixStack {
        if (matrices.size != 1) {
            matrices.last() put matrices[matrices.lastIndex - 1]
            currMat = matrices.last()
        }
        return this
    }

    fun top() = currMat

    // Rotation Matrix Functions

    // These functions right-multiply the current matrix with a rotation matrix of some form.
    // All rotation angles are counter-clockwise for an observer looking down the axis direction.
    // If an observer is facing so that the axis of rotation is pointing directly towards the user,
    // then positive angles will rotate counter-clockwise.

    /** Applies a rotation matrix about the given axis, with the given angle in degrees.    */
    fun rotate(axis: Vec3, angDegCCW: Float) = rotate(axis.x, axis.y, axis.z, angDegCCW)

    fun rotate(axisX: Float, axisY: Float, axisZ: Float, angDegCCW: Float): MatrixStack {
        currMat.rotate_(angDegCCW.rad, axisX, axisY, axisZ)
        return this
    }

    /** Applies a rotation matrix about the given axis, with the given angle in radians.    */
    fun rotateRadians(axis: Vec3, angRadCCW: Float): MatrixStack {

        val cos = glm.cos(angRadCCW)
        val invCos = 1f - cos
        val sin = glm.sin(angRadCCW)
        val invSin = 1f - sin

        val dot = axis.x * axis.x + axis.y * axis.y + axis.z * axis.z
        val inv = glm.inverseSqrt(dot)
        val x = axis.x * inv
        val y = axis.y * inv
        val z = axis.z * inv

        val theMat = Mat4(1.0f)
        theMat[0].x = axis.x * axis.x + (1 - axis.x * axis.x) * cos
        theMat[1].x = axis.x * axis.y * invCos - axis.z * sin
        theMat[2].x = axis.x * axis.z * invCos + axis.y * sin

        theMat[0].y = axis.x * axis.y * invCos + axis.z * sin
        theMat[1].y = axis.y * axis.y + (1 - axis.y * axis.y) * cos
        theMat[2].y = axis.y * axis.z * invCos - axis.x * sin

        theMat[0].z = axis.x * axis.z * invCos - axis.y * sin
        theMat[1].z = axis.y * axis.z * invCos + axis.x * sin
        theMat[2].z = axis.z * axis.z + (1 - axis.z * axis.z) * cos
        currMat.times_(theMat)

        return this
    }

    /** Applies a rotation matrix about the +X axis, with the given angle in degrees    */
    infix fun rotateX(angDegCCW: Float) = rotate(1f, 0f, 0f, angDegCCW)

    /** Applies a rotation matrix about the +Y axis, with the given angle in degrees    */
    infix fun rotateY(angDegCCW: Float) = rotate(0f, 1f, 0f, angDegCCW)

    /** Applies a rotation matrix about the +Z axis, with the given angle in degrees    */
    infix fun rotateZ(angDegCCW: Float) = rotate(0f, 0f, 1f, angDegCCW)

    // Scale Matrix Functions
    // These functions right-multiply the current matrix with a scaling matrix of some form.

    /** Applies a scale matrix, with the given glm::vec4 as the axis scales    */
    infix fun scale(scale: Vec4) = scale(scale.x, scale.y, scale.z)

    /** Applies a scale matrix, with the given glm::vec3 as the axis scales    */
    infix fun scale(scale: Vec3) = scale(scale.x, scale.y, scale.z)

    /** Applies a scale matrix, with the given values as the axis scales    */
    fun scale(scaleX: Float, scaleY: Float, scaleZ: Float): MatrixStack {
        currMat.scale_(scaleX, scaleY, scaleZ)
        return this
    }

    /** Applies a uniform scale matrix  */
    infix fun scale(scale: Float) = scale(scale, scale, scale)

    // Translation Matrix Functions
    // These functions right-multiply the current matrix with a translation matrix of some form.

    /** Applies a translation matrix, with the given glm::vec4 as the offset   */
    infix fun translate(offset: Vec4) = translate(offset.x, offset.y, offset.z)

    /** Applies a translation matrix, with the given glm::vec3 as the offset   */
    infix fun translate(offset: Vec3) = translate(offset.x, offset.y, offset.z)

    /** Applies a translation matrix, with the given X, Y and Z values as the offset   */
    infix fun translate(offset: Float) = translate(offset, offset, offset)

    /** Applies a uniform scale matrix  */
    fun translate(x: Float, y: Float, z: Float): MatrixStack {
        currMat.translate_(x, y, z)
        return this
    }

    // Camera Matrix Functions
    // These functions right-multiply the current matrix with a matrix that transforms from a world space to
    // the camera space expected by the Perspective() or Orthographic() functions.

    /**
     * Applies a matrix that transforms to a camera-space defined by a position, a target in the world, and an up direction.
     *
     * @param cameraPos The world-space position of the camera.
     * @param lookatPos The world-space position the camera should be facing. It should not be equal to \a cameraPos.
     * @param upDir The world-space direction vector that should be considered up. The generated matrix will be bad
     *      if the up direction is along the same direction as the direction the camera faces (the direction between
     *      cameraPos and lookatPos).        */
    fun lookAt(cameraPos: Vec3, lookatPos: Vec3, upDir: Vec3): MatrixStack {
        TODO()
        //currMat.mul_(glm.lookAt(cameraPos, lookatPos, upDir))
    }


    // Projection Matrix Functions
    // These functions right-multiply the current matrix with a projection matrix of some form. These functions all
    // transform positions into the 4D homogeneous space expected by the output of OpenGL vertex shaders. As such, these
    // can be used directly with GLSL shaders.
    // The space that these matrices transform from is defined as follows. The pre-projection space, called camera space
    // or eye space, has the camera/eye position at the origin. The camera faces down the -Z axis, so objects with larger
    // negative Z values are farther away. +Y is up and +X is to the right.

    /**
     * Applies a standard, OpenGL-style perspective projection matrix.
     * @param degFOV The field of view. This is the angle in degrees between directly forward and the farthest visible
     *      point horizontally.
     * @param aspectRatio The ratio of the width of the view area to the height.
     * @param zNear The closest camera-space distance to the camera that can be seen. The projection will be clipped
     * against this value. It cannot be negative or 0.0.
     * @param zFar The farthest camera-space distance from the camera that can be seen. The projection will be clipped
     * against this value. It must be larger than zNear.     */
    fun perspective(degFOV: Float, aspectRatio: Float, zNear: Float, zFar: Float): MatrixStack {
        currMat.times_(glm.perspective(glm.radians(degFOV), aspectRatio, zNear, zFar))
        return this
    }

    /**
     * Applies a standard, OpenGL-style orthographic projection matrix.
     * @param left The left camera-space position in the X axis that will be captured within the projection.
     * @param right The right camera-space position in the X axis that will be captured within the projection.
     * @param bottom The bottom camera-space position in the Y axis that will be captured within the projection.
     * @param top The top camera-space position in the Y axis that will be captured within the projection.
     * @param zNear The front camera-space position in the Z axis that will be captured within the projection.
     * @param zFar The rear camera-space position in the Z axis that will be captured within the projection.     */
    @JvmOverloads fun orthographic(left: Float, right: Float, bottom: Float, top: Float, zNear: Float = -1f, zFar: Float = 1f): MatrixStack {
        currMat.times_(glm.ortho(left, right, bottom, top, zNear, zFar))
        return this
    }

    /**
     * Applies an ortho matrix for pixel-accurate reproduction.
     * A common use for orthographic projections is to create an ortho matrix that allows for pixel-accurate
     * reproduction of textures. It allows you to provide vertices directly in window space.
     * The camera space that this function creates can have the origin at the top-left (with +y going down) or
     * bottom-left (with +y going up). Note that a top-left orientation will have to flip the Y coordinate, which means
     * that the winding order of any triangles are reversed.
     * The depth range is arbitrary and up to the user.
     * @param size The size of the window space.
     * @param depthRange The near and far depth range. The x coord is zNear, and the y coord is zFar.
     * @param isTopLeft True if this should be top-left orientation, false if it should be bottom-left.     */
    @JvmOverloads fun pixelPerfectOrtho(size: Vec2i, depthRange: Vec2i, isTopLeft: Boolean = true) =
            if (isTopLeft)
                translate(-1.0f, 1.0f, (depthRange.x + depthRange.y) / 2.0f)
                        .scale(2.0f / size.x, -2.0f / size.y, 1.0f)
            else
                translate(-1.0f, -1.0f, (depthRange.x + depthRange.y) / 2.0f)
                        .scale(2.0f / size.x, 2.0f / size.y, 2.0f / (depthRange.y - depthRange.x))


    infix fun applyMatrix(theMatrix: Mat4): MatrixStack {
        currMat.times_(theMatrix)
        return this
    }


    infix fun setMatrix(mat: Mat4): MatrixStack {
        currMat put mat
        return this
    }


    fun setIdentity(): MatrixStack {
        currMat put 1f
        return this
    }

    inline infix fun apply(block: MatrixStack.() -> Unit): MatrixStack {
        push()
        block()
        pop()
        return this
    }

    inline infix fun <R> run(block: MatrixStack.() -> R): R {
        push()
        val result = block()
        pop()
        return result
    }
}