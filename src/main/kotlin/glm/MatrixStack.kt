package glm

import mat.Mat4
import vec._3.Vec3
import vec._4.Vec4

/**
 * Created by elect on 13/02/17.
 */

class MatrixStack {

    private val matrices = ArrayList<Mat4>()

    constructor() : this(Mat4())

    constructor(mat:Mat4) {
        matrices.add(mat)
    }

    fun identity():MatrixStack {
        top() put 1f
        return this
    }

    fun translate(offset:Vec4) = translate(offset.x, offset.y, offset.z)
    fun translate(offset:Vec3) = translate(offset.x, offset.y, offset.z)
    fun translate(offset:Float) = translate(offset, offset, offset)

    fun translate(x:Float, y:Float, z:Float):MatrixStack {
        top().translate(x, y, z)
        return this
    }

    fun scale(offset:Vec4) = scale(offset.x, offset.y, offset.z)
    fun scale(offset:Vec3) = scale(offset.x, offset.y, offset.z)
    fun scale(offset:Float) = scale(offset, offset, offset)

    fun scale(x:Float, y:Float, z:Float):MatrixStack {
        top().scale(x, y, z)
        return this
    }

    fun rotate( axis:Vec3, angDegCCW:Float) {
        top().rotate((float) Math.toRadians(angDegCCW), axis);
        return this;
    }

    public MatrixStack rotateX(float angDeg) {
        top().rotateX(Math.toRadians(angDeg));
        return this;
    }

    public MatrixStack rotateY(float angDeg) {
        top().rotateY(Math.toRadians(angDeg));
        return this;
    }

    public MatrixStack rotateZ(float angDeg) {
        top().rotateZ(Math.toRadians(angDeg));
        return this;
    }

    public MatrixStack applyMatrix(Mat4 mat4) {
        top().mul(mat4);
        return this;
    }

    public MatrixStack push() {
        matrices.add(new Mat4(top()));
        return this;
    }

    public MatrixStack pop() {
        matrices.remove(matrices.size() - 1);
        return this;
    }

    fun top() = matrices.last()

    public MatrixStack setMatrix(Mat4 mat4) {
        matrices.set(matrices.size() - 1, mat4);
        return this;
    }

    public MatrixStack perspective(float defFOV, float aspectRatio, float zNear, float zFar) {
        // FIXME There is no mulPerspective method !!
//        top().mulPerspective((float) Math.toRadians(defFOV), aspectRatio, zNear, zFar);
        top().perspective((float) Math.toRadians(defFOV), aspectRatio, zNear, zFar);
        return this;
    }


    public MatrixStack resetStack() {
        matrices.get(matrices.size() - 1).set(matrices.get(matrices.size() - 2));
        return this;
    }
}