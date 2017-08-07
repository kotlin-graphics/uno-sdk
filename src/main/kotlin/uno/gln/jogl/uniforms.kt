package uno.gln.jogl

import com.jogamp.opengl.GL3
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.set
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import uno.gln.mat4Buffer

fun GL3.glUniform2f(location: Int) = glUniform2f(location, 0f, 0f)
fun GL3.glUniform2f(location: Int, f: Float) = glUniform2f(location, f, f)
// TODO vec1
fun GL3.glUniform2f(location: Int, vec2: Vec2) = glUniform2f(location, vec2.x, vec2.y)

fun GL3.glUniform2f(location: Int, vec3: Vec3) = glUniform2f(location, vec3.x, vec3.y)
fun GL3.glUniform2f(location: Int, vec4: Vec4) = glUniform2f(location, vec4.x, vec4.y)

fun GL3.glUniform3f(location: Int) = glUniform3f(location, 0f, 0f, 0f)
fun GL3.glUniform3f(location: Int, f: Float) = glUniform3f(location, f, f, f)
fun GL3.glUniform3f(location: Int, vec2: Vec2) = glUniform3f(location, vec2.x, vec2.y, 0f)
fun GL3.glUniform3f(location: Int, vec3: Vec3) = glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun GL3.glUniform3f(location: Int, vec4: Vec4) = glUniform3f(location, vec4.x, vec4.y, vec4.z)

fun GL3.glUniform4f(location: Int) = glUniform4f(location, 0f, 0f, 0f, 1f)
fun GL3.glUniform4f(location: Int, f: Float) = glUniform4f(location, f, f, f, f)
fun GL3.glUniform4f(location: Int, vec2: Vec2) = glUniform4f(location, vec2.x, vec2.y, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec3: Vec3) = glUniform4f(location, vec3.x, vec3.y, vec3.z, 1f)
fun GL3.glUniform4f(location: Int, vec4: Vec4) = glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun GL3.glUniformMatrix4f(location: Int, value: FloatArray) {
    for (i in 0..15)
        mat4Buffer[i] = value[i]
    glUniformMatrix4fv(location, 1, false, mat4Buffer)
}

fun GL3.glUniformMatrix4f(location: Int, value: Mat4) = glUniformMatrix4fv(location, 1, false, value to mat4Buffer)
fun GL3.glUniformMatrix3f(location: Int, value: Mat3) = glUniformMatrix3fv(location, 1, false, value to mat4Buffer)
fun GL3.glUniformMatrix3f(location: Int, value: Mat4) {
    mat4Buffer[0] = value[0][0]
    mat4Buffer[1] = value[0][1]
    mat4Buffer[2] = value[0][2]
    mat4Buffer[3] = value[1][0]
    mat4Buffer[4] = value[1][1]
    mat4Buffer[5] = value[1][2]
    mat4Buffer[6] = value[2][0]
    mat4Buffer[7] = value[2][1]
    mat4Buffer[8] = value[2][2]
    glUniformMatrix3fv(location, 1, false, value to mat4Buffer)
}