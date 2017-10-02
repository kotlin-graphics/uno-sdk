package uno.glf

import glm_.L
import glm_.vec2.Vec2
import glm_.vec2.Vec2us
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import glm_.vec4.Vec4ub
import glm_.glm
import glm_.vec4.Vec4b
import org.lwjgl.opengl.GL11.*

/**
 * Created by GBarbieri on 29.03.2017.
 */


object glf {

    object pos2 : VertexLayout {
        val stride = Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0))
    }

    object pos3 : VertexLayout {
        val stride = Vec3.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0))
    }

    object pos4 : VertexLayout {
        val stride = Vec4.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec4.length, GL_FLOAT, false, stride, 0))
    }

    object pos3_col4 : VertexLayout {
        val stride = Vec3.size + Vec4.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    object pos3_col4b : VertexLayout {
        val stride = Vec3.size + Vec4b.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4b.length, GL_UNSIGNED_BYTE, false, stride, Vec3.size.L))
    }

    object pos4_col4 : VertexLayout {
        val stride = Vec4.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec4.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4.length, GL_FLOAT, false, stride, Vec4.size.L))
    }


    object pos2_tc2 : VertexLayout {
        val stride = Vec2.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec2.size.L))
    }

    object pos2_tc2_col4b : VertexLayout {
        val stride = Vec2.size * 2 + Vec4b.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec2.size.L),
                VertexAttribute(semantic.attr.COLOR, Vec4b.length, GL_FLOAT, false, stride, Vec2.size.L * 2))
    }

    object pos2us_tc2us : VertexLayout {
        val stride = Vec2us.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2us.length, GL_UNSIGNED_SHORT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2us.length, GL_UNSIGNED_SHORT, false, stride, Vec2us.size.L))
    }

    object pos3_tc2 : VertexLayout {
        val stride = Vec3.size + Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    object pos3_col4ub : VertexLayout {
        val stride = Vec3.size + Vec4ub.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4ub.length, GL_UNSIGNED_BYTE, false, stride, Vec3.size.L))
    }

    object pos2_tc3 : VertexLayout {
        val stride = Vec2.size + Vec3.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec3.length, GL_FLOAT, false, stride, Vec2.size.L))
    }

    object pos3_tc3 : VertexLayout {
        val stride = Vec3.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    object pos3_nor3 : VertexLayout {
        val stride = Vec3.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.NORMAL, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    object pos3_nor3_tc2 : VertexLayout {
        val stride = Vec3.size * 2 + Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.NORMAL, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec3.size.L * 2))
    }

    object pos3_nor3_col4 : VertexLayout {
        val stride = Vec3.size * 2 + Vec4.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.NORMAL, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L),
                VertexAttribute(semantic.attr.COLOR, Vec4.length, GL_FLOAT, false, stride, Vec3.size.L * 2))
    }

}

object Vertex { // TODO

    class pos2 : VertexLayout {
        val stride = Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0))
    }

    class pos3 : VertexLayout {
        val stride = Vec3.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0))
    }

    class pos4 : VertexLayout {
        val stride = Vec4.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec4.length, GL_FLOAT, false, stride, 0))
    }

    class pos3_col4 : VertexLayout {
        val stride = Vec3.size + Vec4.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    class pos3_col4b : VertexLayout {
        val stride = Vec3.size + Vec4b.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4b.length, GL_UNSIGNED_BYTE, false, stride, Vec3.size.L))
    }

    class pos4_col4 : VertexLayout {
        val stride = Vec4.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec4.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.COLOR, Vec4.length, GL_FLOAT, false, stride, Vec4.size.L))
    }


    class pos2_tc2(val p: Vec2, val t: Vec2)

    class pos2_tc2_col4b : VertexLayout {
        val stride = Vec2.size * 2 + Vec4b.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec2.size.L),
                VertexAttribute(semantic.attr.COLOR, Vec4b.length, GL_FLOAT, false, stride, Vec2.size.L * 2))
    }

    class pos2us_tc2us : VertexLayout {
        val stride = Vec2us.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2us.length, GL_UNSIGNED_SHORT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2us.length, GL_UNSIGNED_SHORT, false, stride, Vec2us.size.L))
    }

    class pos3_tc2 : VertexLayout {
        val stride = Vec3.size + Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    class pos3_col4ub(var p: Vec3, var c: Vec4ub)

    class pos2_tc3 : VertexLayout {
        val stride = Vec2.size + Vec3.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec2.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec3.length, GL_FLOAT, false, stride, Vec2.size.L))
    }

    class pos3_tc3 : VertexLayout {
        val stride = Vec3.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.TEX_COORD, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    class pos3_nor3 : VertexLayout {
        val stride = Vec3.size * 2
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.NORMAL, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L))
    }

    class pos3_nor3_tc2 : VertexLayout {
        val stride = Vec3.size * 2 + Vec2.size
        override var attributes = arrayOf(
                VertexAttribute(semantic.attr.POSITION, Vec3.length, GL_FLOAT, false, stride, 0),
                VertexAttribute(semantic.attr.NORMAL, Vec3.length, GL_FLOAT, false, stride, Vec3.size.L),
                VertexAttribute(semantic.attr.TEX_COORD, Vec2.length, GL_FLOAT, false, stride, Vec3.size.L * 2))
    }

    class pos3_nor3_col4 (var p: Vec3, var n: Vec3, var c: Vec4)

    abstract class Base
}


interface VertexLayout {
    var attributes: Array<VertexAttribute>
    operator fun get(index: Int) = attributes[index]
}

class VertexAttribute(
        var index: Int,
        var size: Int,
        var type: Int,
        var normalized: Boolean,
        var interleavedStride: Int,
        var pointer: Long)

fun generateIcosahedron(subdivision: Int): List<Vec3> {

    //The golden ratio
    val t = (1 + glm.sqrt(5f)) / 2
    val size = 1.0f

    val A = glm.normalize(Vec3(-size, t * size, 0.0f))    // 0
    val B = glm.normalize(Vec3(+size, t * size, 0.0f))    // 1
    val C = glm.normalize(Vec3(-size, -t * size, 0.0f))    // 2
    val D = glm.normalize(Vec3(+size, -t * size, 0.0f))    // 3

    val E = glm.normalize(Vec3(0.0f, -size, t * size))    // 4
    val F = glm.normalize(Vec3(0.0f, size, t * size))    // 5
    val G = glm.normalize(Vec3(0.0f, -size, -t * size))    // 6
    val H = glm.normalize(Vec3(0.0f, size, -t * size))    // 7

    val I = glm.normalize(Vec3(t * size, 0.0f, -size))    // 8
    val J = glm.normalize(Vec3(t * size, 0.0f, size))    // 9
    val K = glm.normalize(Vec3(-t * size, 0.0f, -size))    // 10
    val L = glm.normalize(Vec3(-t * size, 0.0f, size))    // 11

    val vertexData = ArrayList<Vec3>()

    subdiviseIcosahedron(vertexData, A, L, F, subdivision)
    subdiviseIcosahedron(vertexData, A, F, B, subdivision)
    subdiviseIcosahedron(vertexData, A, B, H, subdivision)
    subdiviseIcosahedron(vertexData, A, H, K, subdivision)
    subdiviseIcosahedron(vertexData, A, K, L, subdivision)

    subdiviseIcosahedron(vertexData, B, F, J, subdivision)
    subdiviseIcosahedron(vertexData, F, L, E, subdivision)
    subdiviseIcosahedron(vertexData, L, K, C, subdivision)
    subdiviseIcosahedron(vertexData, K, H, G, subdivision)
    subdiviseIcosahedron(vertexData, H, B, I, subdivision)

    subdiviseIcosahedron(vertexData, D, J, E, subdivision)
    subdiviseIcosahedron(vertexData, D, E, C, subdivision)
    subdiviseIcosahedron(vertexData, D, C, G, subdivision)
    subdiviseIcosahedron(vertexData, D, G, I, subdivision)
    subdiviseIcosahedron(vertexData, D, I, J, subdivision)

    subdiviseIcosahedron(vertexData, E, J, F, subdivision)
    subdiviseIcosahedron(vertexData, C, E, L, subdivision)
    subdiviseIcosahedron(vertexData, G, C, K, subdivision)
    subdiviseIcosahedron(vertexData, I, G, H, subdivision)
    subdiviseIcosahedron(vertexData, J, I, B, subdivision)

    return vertexData
}

fun subdiviseIcosahedron(vertexData: ArrayList<Vec3>, a0: Vec3, b0: Vec3, c0: Vec3, subdivise: Int) {

    if (subdivise == 0) {
        vertexData.add(a0)
        vertexData.add(b0)
        vertexData.add(c0)
    } else {
        val a1 = (b0 + c0) * 0.5f
        val b1 = (c0 + a0) * 0.5f
        val c1 = (a0 + b0) * 0.5f

        if (glm.length(a1) > 0.0f)
            a1.normalize_()
        if (glm.length(b1) > 0.0f)
            b1.normalize_()
        if (glm.length(c1) > 0.0f)
            c1.normalize_()

        subdiviseIcosahedron(vertexData, a0, b1, c1, subdivise - 1)
        subdiviseIcosahedron(vertexData, b0, c1, a1, subdivise - 1)
        subdiviseIcosahedron(vertexData, c0, a1, b1, subdivise - 1)
        subdiviseIcosahedron(vertexData, b1, a1, c1, subdivise - 1)
    }
}