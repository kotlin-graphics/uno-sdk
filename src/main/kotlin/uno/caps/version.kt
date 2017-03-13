package uno.caps

import com.jogamp.opengl.GL
import com.jogamp.opengl.GL.*
import com.jogamp.opengl.GL2ES2.GL_SHADING_LANGUAGE_VERSION
import com.jogamp.opengl.GLContext
import com.jogamp.opengl.GLProfile

/**
 * Created by GBarbieri on 10.03.2017.
 */

class Version(
        gl: GL,
        var profile: GLProfile,
        var minorVersion: Int = 0,
        var majorVersion: Int = 0,
        var contextFlags: Int = 0,
        var numExtensions: Int = 0) {

    var renderer: String
    var vendor: String
    var version: String
    var shadingLanguageVersion = ""
    var numShadingLanguageVersions = 0

    init {

        majorVersion = GLContext.getMaxMajor(0)
        minorVersion = GLContext.getMaxMinor(0, majorVersion)

        renderer = gl.glGetString(GL_RENDERER)
        vendor = gl.glGetString(GL_VENDOR)
        version = gl.glGetString(GL_VERSION)
        shadingLanguageVersion = gl.glGetString(GL_SHADING_LANGUAGE_VERSION)
    }
}