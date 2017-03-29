package uno.glf

import glm.vec._2.Vec2

/**
 * Created by GBarbieri on 29.03.2017.
 */


class Vertex_v2fv2f(
    var position: Vec2 = Vec2(),
    var texCoord: Vec2 = Vec2()) {

    companion object {
        val SIZE = Vec2.SIZE * 2
    }
}