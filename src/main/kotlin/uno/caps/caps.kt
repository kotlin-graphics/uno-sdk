package uno.caps

import com.jogamp.opengl.GL

/**
 * Created by GBarbieri on 10.03.2017.
 */

class Caps(
        gl: GL,
        profile: Profile) {

//    val version = Version(gl, profile)





}

enum class Profile(@JvmField val i: Int) {

    CORE(0x1),
    COMPATIBILITY(0x2),
    ES(0x4)
}