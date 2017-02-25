/** Copyright (C) 2011 by Jason L. McKesson **/
/** This file is licensed by the MIT License. **/

package glm

import main.glm
import mat.Mat4
import vec._3.Vec3
import vec._4.Vec4
import java.nio.FloatBuffer
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
        mat: Mat4 = Mat4()
) {

    internal val matrices = Stack<Mat4>()
    internal var currMat = mat

    // Stack Maintanence Functions
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
        matrices.last() put matrices[matrices.lastIndex - 1]
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
        top().rotate_(angDegCCW, axisX, axisY, axisZ)
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

        TODO()
//        glm::mat4 theMat(1.0f);
//        theMat[0].x = (axis.x * axis.x) + ((1 - axis.x * axis.x) * fCos);
//        theMat[1].x = axis.x * axis.y * (fInvCos) - (axis.z * fSin);
//        theMat[2].x = axis.x * axis.z * (fInvCos) + (axis.y * fSin);
//
//        theMat[0].y = axis.x * axis.y * (fInvCos) + (axis.z * fSin);
//        theMat[1].y = (axis.y * axis.y) + ((1 - axis.y * axis.y) * fCos);
//        theMat[2].y = axis.y * axis.z * (fInvCos) - (axis.x * fSin);
//
//        theMat[0].z = axis.x * axis.z * (fInvCos) - (axis.y * fSin);
//        theMat[1].z = axis.y * axis.z * (fInvCos) + (axis.x * fSin);
//        theMat[2].z = (axis.z * axis.z) + ((1 - axis.z * axis.z) * fCos);
//        m_currMatrix *= theMat;
        return this
    }

    /** Applies a rotation matrix about the +X axis, with the given angle in degrees    */
    fun rotateX(angDegCCW: Float) = rotate(1f, 0f, 0f, angDegCCW)

    /** Applies a rotation matrix about the +Y axis, with the given angle in degrees    */
    fun rotateY(angDegCCW: Float) = rotate(0f, 1f, 0f, angDegCCW)

    /** Applies a rotation matrix about the +Z axis, with the given angle in degrees    */
    fun rotateZ(angDegCCW: Float) = rotate(0f, 0f, 1f, angDegCCW)

    // Scale Matrix Functions
    // These functions right-multiply the current matrix with a scaling matrix of some form.

    /** Applies a scale matrix, with the given glm::vec4 as the axis scales    */
    fun scale(scale: Vec4) = top().scale_(scale.x, scale.y, scale.z)

    /** Applies a scale matrix, with the given glm::vec3 as the axis scales    */
    fun scale(scale: Vec3) = top().scale_(scale.x, scale.y, scale.z)

    /** Applies a scale matrix, with the given values as the axis scales    */
    fun scale(scaleX: Float, scaleY: Float, scaleZ: Float) = top().scale_(scaleX, scaleY, scaleZ)

    /** Applies a uniform scale matrix  */
    fun scale(scale: Float) = top().scale_(scale, scale, scale)

    // Translation Matrix Functions
    // These functions right-multiply the current matrix with a translation matrix of some form.

    /** Applies a translation matrix, with the given glm::vec4 as the offset   */
    fun translate(offset: Vec4) = translate(offset.x, offset.y, offset.z)

    /** Applies a translation matrix, with the given glm::vec3 as the offset   */
    fun translate(offset: Vec3) = translate(offset.x, offset.y, offset.z)

    /** Applies a translation matrix, with the given X, Y and Z values as the offset   */
    fun translate(offset: Float) = translate(offset, offset, offset)

    /** Applies a uniform scale matrix  */
    fun translate(x: Float, y: Float, z: Float): MatrixStack {
        top().translate_(x, y, z)
        return this
    }

    fun setIdentity(): MatrixStack {
        currMat put 1f
        return this
    }


//
//    public MatrixStack applyMatrix(Mat4 mat4)
//    {
//        top().mul(mat4);
//        return this;
//    }
//


    fun setMatrix(mat: Mat4): MatrixStack {
        currMat put mat
        return this;
    }
//
//    public MatrixStack perspective(float defFOV, float aspectRatio, float zNear, float zFar)
//    {
//        // FIXME There is no mulPerspective method !!
////        top().mulPerspective((float) Math.toRadians(defFOV), aspectRatio, zNear, zFar);
//        top().perspective((float) Math . toRadians (defFOV), aspectRatio, zNear, zFar);
//        return this;
//    }

    infix fun to(buffer: FloatBuffer) = currMat to buffer

}