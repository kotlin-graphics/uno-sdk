package uno.gln

/**
 * Created by elect on 28/04/17.
 */

import glm.f
import glm.mat3x3.Mat3
import glm.mat4x4.Mat4
import glm.set
import glm.vec1.Vec1bool
import glm.vec1.Vec1i
import glm.vec1.Vec1t
import glm.vec2.Vec2
import glm.vec2.Vec2bool
import glm.vec2.Vec2i
import glm.vec2.Vec2t
import glm.vec3.Vec3
import glm.vec3.Vec3bool
import glm.vec3.Vec3i
import glm.vec4.Vec4
import glm.vec4.Vec4bool
import glm.vec4.Vec4i
import org.lwjgl.opengl.GL20


// inferred
fun glUniform(location: Int, float: Float) = GL20.glUniform1f(location, float)
fun glUniform(location: Int, int: Int) = GL20.glUniform1i(location, int)
fun glUniform(location: Int, boolean: Boolean) = GL20.glUniform1i(location, if (boolean) 1 else 0)

fun glUniform(location: Int, vec1: Vec1) = GL20.glUniform1f(location, vec1.x)
fun glUniform(location: Int, vec1i: Vec1i) = GL20.glUniform1i(location, vec1i.x)
fun glUniform(location: Int, vec1bool: Vec1bool) = GL20.glUniform1i(location, if (vec1bool.x) 1 else 0)

fun glUniform(location: Int, vec2: Vec2) = GL20.glUniform2f(location, vec2.x, vec2.y)
fun glUniform(location: Int, vec2i: Vec2i) = GL20.glUniform2i(location, vec2i.x, vec2i.y)
fun glUniform(location: Int, vec2bool: Vec2bool) = GL20.glUniform2i(location, if (vec2bool.x) 1 else 0, if (vec2bool.y) 1 else 0)

fun glUniform(location: Int, vec3: Vec3) = GL20.glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun glUniform(location: Int, vec3i: Vec3i) = GL20.glUniform3i(location, vec3i.x, vec3i.y, vec3i.z)
fun glUniform(location: Int, vec3bool: Vec3bool) = GL20.glUniform3i(location, if (vec3bool.x) 1 else 0, if (vec3bool.y) 1 else 0, if (vec3bool.z) 1 else 0)

fun glUniform(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)
fun glUniform(location: Int, vec4i: Vec4i) = GL20.glUniform4i(location, vec4i.x, vec4i.y, vec4i.z, vec4i.w)
fun glUniform(location: Int, vec4bool: Vec4bool) = GL20.glUniform4i(location, if (vec4bool.x) 1 else 0, if (vec4bool.y) 1 else 0, if (vec4bool.z) 1 else 0, if (vec4bool.w) 1 else 0)

// conversions
fun glUniform1f(location: Int, number: Number) = GL20.glUniform1f(location, number.f)
fun glUniform1f(location: Int, vec1: Vec1t<*>) = GL20.glUniform1f(location, vec1.x.f)
fun glUniform1f(location: Int, vec2: Vec2t<*>) = GL20.glUniform2f(location, vec1.x.f)

fun glUniform2f(location: Int) = GL20.glUniform2f(location, 0f, 0f)
fun glUniform2f(location: Int, f: Float) = GL20.glUniform2f(location, f, f)
// TODO vec1
fun glUniform2f(location: Int, vec2: Vec2) = GL20.glUniform2f(location, vec2.x, vec2.y)

fun glUniform2f(location: Int, vec3: Vec3) = GL20.glUniform2f(location, vec3.x, vec3.y)
fun glUniform2f(location: Int, vec4: Vec4) = GL20.glUniform2f(location, vec4.x, vec4.y)

fun glUniform3f(location: Int) = GL20.glUniform3f(location, 0f, 0f, 0f)
fun glUniform3f(location: Int, f: Float) = GL20.glUniform3f(location, f, f, f)
fun glUniform3f(location: Int, vec2: Vec2) = GL20.glUniform3f(location, vec2.x, vec2.y, 0f)
fun glUniform3f(location: Int, vec3: Vec3) = GL20.glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun glUniform3f(location: Int, vec4: Vec4) = GL20.glUniform3f(location, vec4.x, vec4.y, vec4.z)

fun glUniform4(location: Int) = GL20.glUniform4f(location, 0f, 0f, 0f, 1f)
fun glUniform4(location: Int, f: Float) = GL20.glUniform4f(location, f, f, f, f)
fun glUniform4(location: Int, vec2: Vec2) = GL20.glUniform4f(location, vec2.x, vec2.y, 0f, 1f)
fun glUniform4(location: Int, vec3: Vec3) = GL20.glUniform4f(location, vec3.x, vec3.y, vec3.z, 1f)
fun glUniform4(location: Int, vec4: Vec4) = GL20.glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)


fun glUniform(location: Int, mat4: Mat4) = GL20.glUniform4fv(location, mat4 to mat4Buffer)

fun glUniformMatrix4f(location: Int, value: FloatArray) {
    for (i in 0..15)
        mat4Buffer[i] = value[i]
    GL20.glUniformMatrix4fv(location, false, mat4Buffer)
}

fun glUniformMatrix4f(location: Int, value: Mat4) = GL20.glUniformMatrix4fv(location, false, value to mat4Buffer)
fun glUniformMatrix3f(location: Int, value: Mat3) = GL20.glUniformMatrix3fv(location, false, value to mat4Buffer)
fun glUniformMatrix3f(location: Int, value: Mat4) {
    mat4Buffer[0] = value[0][0]
    mat4Buffer[1] = value[0][1]
    mat4Buffer[2] = value[0][2]
    mat4Buffer[3] = value[1][0]
    mat4Buffer[4] = value[1][1]
    mat4Buffer[5] = value[1][2]
    mat4Buffer[6] = value[2][0]
    mat4Buffer[7] = value[2][1]
    mat4Buffer[8] = value[2][2]
    GL20.glUniformMatrix3fv(location, false, value to mat4Buffer)
}