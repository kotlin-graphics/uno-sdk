package uno.gln.jogl

import com.jogamp.opengl.GL3
import glm_.f
import glm_.i
import glm_.mat2x2.Mat2
import glm_.mat2x3.Mat2x3
import glm_.mat2x4.Mat2x4
import glm_.mat3x2.Mat3x2
import glm_.mat3x3.Mat3
import glm_.mat3x4.Mat3x4
import glm_.mat4x2.Mat4x2
import glm_.mat4x3.Mat4x3
import glm_.mat4x4.Mat4
import glm_.vec1.Vec1
import glm_.vec1.Vec1bool
import glm_.vec1.Vec1i
import glm_.vec1.Vec1t
import glm_.vec2.Vec2
import glm_.vec2.Vec2bool
import glm_.vec2.Vec2i
import glm_.vec2.Vec2t
import glm_.vec3.Vec3
import glm_.vec3.Vec3bool
import glm_.vec3.Vec3i
import glm_.vec3.Vec3t
import glm_.vec4.Vec4
import glm_.vec4.Vec4bool
import glm_.vec4.Vec4i
import glm_.vec4.Vec4t
import uno.gl.*

// ----------------------------------------- vec -----------------------------------------------------------------------
// inferred length and type

fun GL3.glUniform(location: Int, float: Float) = glUniform1f(location, float)
fun GL3.glUniform(location: Int, int: Int) = glUniform1i(location, int)
fun GL3.glUniform(location: Int, boolean: Boolean) = glUniform1i(location, boolean.i)

fun GL3.glUniform(location: Int, vec1: Vec1) = glUniform1f(location, vec1.x)
fun GL3.glUniform(location: Int, vec1i: Vec1i) = glUniform1i(location, vec1i.x)
fun GL3.glUniform(location: Int, vec1bool: Vec1bool) = glUniform1i(location, vec1bool.x.i)

fun GL3.glUniform(location: Int, x: Float, y: Float) = glUniform2f(location, x, y)
fun GL3.glUniform(location: Int, vec2: Vec2) = glUniform2f(location, vec2.x, vec2.y)
fun GL3.glUniform(location: Int, x: Int, y: Int) = glUniform2i(location, x, y)
fun GL3.glUniform(location: Int, vec2i: Vec2i) = glUniform2i(location, vec2i.x, vec2i.y)
fun GL3.glUniform(location: Int, x: Boolean, y: Boolean) = glUniform2i(location, x.i, y.i)
fun GL3.glUniform(location: Int, vec2bool: Vec2bool) = glUniform2i(location, vec2bool.x.i, vec2bool.y.i)

fun GL3.glUniform(location: Int, x: Float, y: Float, z: Float) = glUniform3f(location, x, y, z)
fun GL3.glUniform(location: Int, vec3: Vec3) = glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun GL3.glUniform(location: Int, x: Int, y: Int, z: Int) = glUniform3i(location, x, y, z)
fun GL3.glUniform(location: Int, vec3i: Vec3i) = glUniform3i(location, vec3i.x, vec3i.y, vec3i.z)
fun GL3.glUniform(location: Int, x: Boolean, y: Boolean, z: Boolean) = glUniform3i(location, x.i, y.i, z.i)
fun GL3.glUniform(location: Int, vec3bool: Vec3bool) = glUniform3i(location, vec3bool.x.i, vec3bool.y.i, vec3bool.z.i)

fun GL3.glUniform(location: Int, x: Float, y: Float, z: Float, w: Float) = glUniform4f(location, x, y, z, w)
fun GL3.glUniform(location: Int, vec4: Vec4) = glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)
fun GL3.glUniform(location: Int, x: Int, y: Int, z: Int, w: Int) = glUniform4i(location, x, y, z, w)
fun GL3.glUniform(location: Int, vec4i: Vec4i) = glUniform4i(location, vec4i.x, vec4i.y, vec4i.z, vec4i.w)
fun GL3.glUniform(location: Int, x: Boolean, y: Boolean, z: Boolean, w: Boolean) = glUniform4i(location, x.i, y.i, z.i, w.i)
fun GL3.glUniform(location: Int, vec4bool: Vec4bool) = glUniform4i(location, vec4bool.x.i, vec4bool.y.i, vec4bool.z.i, vec4bool.w.i)


// inferred type float

fun GL3.glUniform1(location: Int, x: Float) = glUniform1f(location, x)
fun GL3.glUniform1(location: Int, v: Vec2) = glUniform1f(location, v.x)
fun GL3.glUniform1(location: Int, v: Vec3) = glUniform1f(location, v.x)
fun GL3.glUniform1(location: Int, v: Vec4) = glUniform1f(location, v.x)

fun GL3.glUniform2(location: Int, x: Float) = glUniform2f(location, x, x)
fun GL3.glUniform2(location: Int, v: Vec2) = glUniform2f(location, v.x, v.y)
fun GL3.glUniform2(location: Int, v: Vec3) = glUniform2f(location, v.x, v.y)
fun GL3.glUniform2(location: Int, v: Vec4) = glUniform2f(location, v.x, v.y)

fun GL3.glUniform3(location: Int, x: Float) = glUniform3f(location, x, x, x)
fun GL3.glUniform3(location: Int, v: Vec2) = glUniform3f(location, v.x, v.y, 0f)
fun GL3.glUniform3(location: Int, v: Vec3) = glUniform3f(location, v.x, v.y, v.z)
fun GL3.glUniform3(location: Int, v: Vec4) = glUniform3f(location, v.x, v.y, v.z)

fun GL3.glUniform4(location: Int, x: Float) = glUniform4f(location, x, x, x, x)
fun GL3.glUniform4(location: Int, v: Vec2) = glUniform4f(location, v.x, v.y, 0f, 1f)
fun GL3.glUniform4(location: Int, v: Vec3) = glUniform4f(location, v.x, v.y, v.z, 1f)
fun GL3.glUniform4(location: Int, v: Vec4) = glUniform4f(location, v.x, v.y, v.z, v.w)


// inferred type int

fun GL3.glUniform1(location: Int, x: Int) = glUniform1i(location, x)
fun GL3.glUniform1(location: Int, v: Vec2i) = glUniform1i(location, v.x)
fun GL3.glUniform1(location: Int, v: Vec3i) = glUniform1i(location, v.x)
fun GL3.glUniform1(location: Int, v: Vec4i) = glUniform1i(location, v.x)

fun GL3.glUniform2(location: Int, x: Int) = glUniform2i(location, x, x)
fun GL3.glUniform2(location: Int, v: Vec2i) = glUniform2i(location, v.x, v.y)
fun GL3.glUniform2(location: Int, v: Vec3i) = glUniform2i(location, v.x, v.y)
fun GL3.glUniform2(location: Int, v: Vec4i) = glUniform2i(location, v.x, v.y)

fun GL3.glUniform3(location: Int, x: Int) = glUniform3i(location, x, x, x)
fun GL3.glUniform3(location: Int, v: Vec2i) = glUniform3i(location, v.x, v.y, 0)
fun GL3.glUniform3(location: Int, v: Vec3i) = glUniform3i(location, v.x, v.y, v.z)
fun GL3.glUniform3(location: Int, v: Vec4i) = glUniform3i(location, v.x, v.y, v.z)

fun GL3.glUniform4(location: Int, x: Int) = glUniform4i(location, x, x, x, x)
fun GL3.glUniform4(location: Int, v: Vec2i) = glUniform4i(location, v.x, v.y, 0, 1)
fun GL3.glUniform4(location: Int, v: Vec3i) = glUniform4i(location, v.x, v.y, v.z, 1)
fun GL3.glUniform4(location: Int, v: Vec4i) = glUniform4i(location, v.x, v.y, v.z, v.w)


// conversions, 1f

fun GL3.glUniform1f(location: Int) = glUniform1f(location, 0f)

fun GL3.glUniform1f(location: Int, x: Number) = glUniform1f(location, x.f)
fun GL3.glUniform1f(location: Int, vec1: Vec1t<*>) = glUniform1f(location, vec1.x.f)
fun GL3.glUniform1f(location: Int, vec2: Vec2t<*>) = glUniform1f(location, vec2.x.f)
fun GL3.glUniform1f(location: Int, vec3: Vec3t<*>) = glUniform1f(location, vec3.x.f)
fun GL3.glUniform1f(location: Int, vec4: Vec4t<*>) = glUniform1f(location, vec4.x.f)

fun GL3.glUniform1f(location: Int, vec1: Vec1) = glUniform1f(location, vec1.x)
fun GL3.glUniform1f(location: Int, vec2: Vec2) = glUniform1f(location, vec2.x)
fun GL3.glUniform1f(location: Int, vec3: Vec3) = glUniform1f(location, vec3.x)
fun GL3.glUniform1f(location: Int, vec4: Vec4) = glUniform1f(location, vec4.x)

fun GL3.glUniform1f(location: Int, x: Boolean) = glUniform1f(location, x.f)
fun GL3.glUniform1f(location: Int, vec1: Vec1bool) = glUniform1f(location, vec1.x.f)
fun GL3.glUniform1f(location: Int, vec2: Vec2bool) = glUniform1f(location, vec2.x.f)
fun GL3.glUniform1f(location: Int, vec3: Vec3bool) = glUniform1f(location, vec3.x.f)
fun GL3.glUniform1f(location: Int, vec4: Vec4bool) = glUniform1f(location, vec4.x.f)


// conversions, 1i

fun GL3.glUniform1i(location: Int) = glUniform1i(location, 0)

fun GL3.glUniform1i(location: Int, x: Number) = glUniform1i(location, x.i)
fun GL3.glUniform1i(location: Int, vec1: Vec1t<*>) = glUniform1i(location, vec1.x.i)
fun GL3.glUniform1i(location: Int, vec2: Vec2t<*>) = glUniform1i(location, vec2.x.i)
fun GL3.glUniform1i(location: Int, vec3: Vec3t<*>) = glUniform1i(location, vec3.x.i)
fun GL3.glUniform1i(location: Int, vec4: Vec4t<*>) = glUniform1i(location, vec4.x.i)

fun GL3.glUniform1i(location: Int, vec1: Vec1i) = glUniform1i(location, vec1.x)
fun GL3.glUniform1i(location: Int, vec2: Vec2i) = glUniform1i(location, vec2.x)
fun GL3.glUniform1i(location: Int, vec3: Vec3i) = glUniform1i(location, vec3.x)
fun GL3.glUniform1i(location: Int, vec4: Vec4i) = glUniform1i(location, vec4.x)

fun GL3.glUniform1i(location: Int, x: Boolean) = glUniform1i(location, x.i)
fun GL3.glUniform1i(location: Int, vec1: Vec1bool) = glUniform1i(location, vec1.x.i)
fun GL3.glUniform1i(location: Int, vec2: Vec2bool) = glUniform1i(location, vec2.x.i)
fun GL3.glUniform1i(location: Int, vec3: Vec3bool) = glUniform1i(location, vec3.x.i)
fun GL3.glUniform1i(location: Int, vec4: Vec4bool) = glUniform1i(location, vec4.x.i)

// conversions, 2f

fun GL3.glUniform2f(location: Int) = glUniform2f(location, 0f, 0f)

fun GL3.glUniform2f(location: Int, x: Number) = glUniform2f(location, x.f, x.f)
fun GL3.glUniform2f(location: Int, x: Number, y: Number) = glUniform2f(location, x.f, y.f)
fun GL3.glUniform2f(location: Int, vec1: Vec1t<*>) = glUniform2f(location, vec1.x.f, 0f)
fun GL3.glUniform2f(location: Int, vec1: Vec1t<*>, y: Number) = glUniform2f(location, vec1.x.f, y.f)
fun GL3.glUniform2f(location: Int, vec2: Vec2t<*>) = glUniform2f(location, vec2.x.f, vec2.y.f)
fun GL3.glUniform2f(location: Int, vec3: Vec3t<*>) = glUniform2f(location, vec3.x.f, vec3.y.f)
fun GL3.glUniform2f(location: Int, vec4: Vec4t<*>) = glUniform2f(location, vec4.x.f, vec4.y.f)

fun GL3.glUniform2f(location: Int, x: Float) = glUniform2f(location, x, x)
fun GL3.glUniform2f(location: Int, vec1: Vec1) = glUniform2f(location, vec1.x, 0f)
fun GL3.glUniform2f(location: Int, vec1: Vec1, y: Float) = glUniform2f(location, vec1.x, y)
fun GL3.glUniform2f(location: Int, vec2: Vec2) = glUniform2f(location, vec2.x, vec2.y)
fun GL3.glUniform2f(location: Int, vec3: Vec3) = glUniform2f(location, vec3.x, vec3.y)
fun GL3.glUniform2f(location: Int, vec4: Vec4) = glUniform2f(location, vec4.x, vec4.y)

fun GL3.glUniform2f(location: Int, x: Boolean) = glUniform2f(location, x.f, x.f)
fun GL3.glUniform2f(location: Int, vec1: Vec1bool) = glUniform2f(location, vec1.x.f, 0f)
fun GL3.glUniform2f(location: Int, vec1: Vec1bool, y: Number) = glUniform2f(location, vec1.x.f, y.f)
fun GL3.glUniform2f(location: Int, vec1: Vec1bool, y: Boolean) = glUniform2f(location, vec1.x.f, y.f)
fun GL3.glUniform2f(location: Int, vec2: Vec2bool) = glUniform2f(location, vec2.x.f, vec2.y.f)
fun GL3.glUniform2f(location: Int, vec3: Vec3bool) = glUniform2f(location, vec3.x.f, vec3.y.f)
fun GL3.glUniform2f(location: Int, vec4: Vec4bool) = glUniform2f(location, vec4.x.f, vec4.y.f)


// conversions, 2i

fun GL3.glUniform2i(location: Int) = glUniform2i(location, 0, 0)

fun GL3.glUniform2i(location: Int, x: Number) = glUniform2i(location, x.i, x.i)
fun GL3.glUniform2i(location: Int, vec1: Vec1t<*>) = glUniform2i(location, vec1.x.i, 0)
fun GL3.glUniform2i(location: Int, vec1: Vec1t<*>, y: Number) = glUniform2i(location, vec1.x.i, y.i)
fun GL3.glUniform2i(location: Int, vec2: Vec2t<*>) = glUniform2i(location, vec2.x.i, vec2.y.i)
fun GL3.glUniform2i(location: Int, vec3: Vec3t<*>) = glUniform2i(location, vec3.x.i, vec3.y.i)
fun GL3.glUniform2i(location: Int, vec4: Vec4t<*>) = glUniform2i(location, vec4.x.i, vec4.y.i)

fun GL3.glUniform2i(location: Int, x: Int) = glUniform2i(location, x, x)
fun GL3.glUniform2i(location: Int, vec1: Vec1i) = glUniform2i(location, vec1.x, 0)
fun GL3.glUniform2i(location: Int, vec1: Vec1i, y: Int) = glUniform2i(location, vec1.x, y)
fun GL3.glUniform2i(location: Int, vec2: Vec2i) = glUniform2i(location, vec2.x, vec2.y)
fun GL3.glUniform2i(location: Int, vec3: Vec3i) = glUniform2i(location, vec3.x, vec3.y)
fun GL3.glUniform2i(location: Int, vec4: Vec4i) = glUniform2i(location, vec4.x, vec4.y)

fun GL3.glUniform2i(location: Int, x: Boolean) = glUniform2i(location, x.i, x.i)
fun GL3.glUniform2i(location: Int, vec1: Vec1bool) = glUniform2i(location, vec1.x.i, 0)
fun GL3.glUniform2i(location: Int, vec1: Vec1bool, y: Number) = glUniform2i(location, vec1.x.i, y.i)
fun GL3.glUniform2i(location: Int, vec1: Vec1bool, y: Boolean) = glUniform2i(location, vec1.x.i, y.i)
fun GL3.glUniform2i(location: Int, vec2: Vec2bool) = glUniform2i(location, vec2.x.i, vec2.y.i)
fun GL3.glUniform2i(location: Int, vec3: Vec3bool) = glUniform2i(location, vec3.x.i, vec3.y.i)
fun GL3.glUniform2i(location: Int, vec4: Vec4bool) = glUniform2i(location, vec4.x.i, vec4.y.i)


// conversions, 3f

fun GL3.glUniform3f(location: Int) = glUniform3f(location, 0f, 0f, 0f)

fun GL3.glUniform3f(location: Int, x: Number) = glUniform3f(location, x.f, x.f, x.f)
fun GL3.glUniform3f(location: Int, x: Number, y: Number, z: Number) = glUniform3f(location, x.f, y.f, z.f)
fun GL3.glUniform3f(location: Int, vec1: Vec1t<*>) = glUniform3f(location, vec1.x.f, 0f, 0f)
fun GL3.glUniform3f(location: Int, vec1: Vec1t<*>, y: Number, z: Number) = glUniform3f(location, vec1.x.f, y.f, z.f)
fun GL3.glUniform3f(location: Int, vec2: Vec2t<*>) = glUniform3f(location, vec2.x.f, vec2.y.f, 0f)
fun GL3.glUniform3f(location: Int, vec2: Vec2t<*>, z: Number) = glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun GL3.glUniform3f(location: Int, vec3: Vec3t<*>) = glUniform3f(location, vec3.x.f, vec3.y.f, vec3.y.f)
fun GL3.glUniform3f(location: Int, vec4: Vec4t<*>) = glUniform3f(location, vec4.x.f, vec4.y.f, vec4.y.f)

fun GL3.glUniform3f(location: Int, x: Float) = glUniform3f(location, x, x, x)
fun GL3.glUniform3f(location: Int, vec1: Vec1) = glUniform3f(location, vec1.x, 0f, 0f)
fun GL3.glUniform3f(location: Int, vec1: Vec1, y: Float, z: Float) = glUniform3f(location, vec1.x, y, z)
fun GL3.glUniform3f(location: Int, vec2: Vec2) = glUniform3f(location, vec2.x, vec2.y, 0f)
fun GL3.glUniform3f(location: Int, vec2: Vec2, z: Float) = glUniform3f(location, vec2.x, vec2.y, z)
fun GL3.glUniform3f(location: Int, vec3: Vec3) = glUniform3f(location, vec3.x, vec3.y, vec3.z)
fun GL3.glUniform3f(location: Int, vec4: Vec4) = glUniform3f(location, vec4.x, vec4.y, vec4.z)

fun GL3.glUniform3f(location: Int, x: Boolean) = glUniform3f(location, x.f, x.f, x.f)
fun GL3.glUniform3f(location: Int, vec1: Vec1bool) = glUniform3f(location, vec1.x.f, 0f, 0f)
fun GL3.glUniform3f(location: Int, vec1: Vec1bool, y: Number, z: Number) = glUniform3f(location, vec1.x.f, y.f, z.f)
fun GL3.glUniform3f(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean) = glUniform3f(location, vec1.x.f, y.f, z.f)
fun GL3.glUniform3f(location: Int, vec2: Vec2bool) = glUniform3f(location, vec2.x.f, vec2.y.f, 0f)
fun GL3.glUniform3f(location: Int, vec2: Vec2bool, z: Number) = glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun GL3.glUniform3f(location: Int, vec2: Vec2bool, z: Boolean) = glUniform3f(location, vec2.x.f, vec2.y.f, z.f)
fun GL3.glUniform3f(location: Int, vec3: Vec3bool) = glUniform3f(location, vec3.x.f, vec3.y.f, vec3.z.f)
fun GL3.glUniform3f(location: Int, vec4: Vec4bool) = glUniform3f(location, vec4.x.f, vec4.y.f, vec4.z.f)


// conversions, 3i

fun GL3.glUniform3i(location: Int) = glUniform3i(location, 0, 0, 0)

fun GL3.glUniform3i(location: Int, x: Number) = glUniform3i(location, x.i, x.i, x.i)
fun GL3.glUniform3i(location: Int, vec1: Vec1t<*>) = glUniform3i(location, vec1.x.i, 0, 0)
fun GL3.glUniform3i(location: Int, vec1: Vec1t<*>, y: Number, z: Number) = glUniform3i(location, vec1.x.i, y.i, z.i)
fun GL3.glUniform3i(location: Int, vec2: Vec2t<*>, z: Number) = glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun GL3.glUniform3i(location: Int, vec3: Vec3t<*>) = glUniform3i(location, vec3.x.i, vec3.y.i, vec3.z.i)
fun GL3.glUniform3i(location: Int, vec4: Vec4t<*>) = glUniform3i(location, vec4.x.i, vec4.y.i, vec4.z.i)

fun GL3.glUniform3i(location: Int, x: Int) = glUniform3i(location, x, x, x)
fun GL3.glUniform3i(location: Int, vec1: Vec1i) = glUniform3i(location, vec1.x, 0, 0)
fun GL3.glUniform3i(location: Int, vec1: Vec1i, y: Int, z: Int) = glUniform3i(location, vec1.x, y, z)
fun GL3.glUniform3i(location: Int, vec2: Vec2i) = glUniform3i(location, vec2.x, vec2.y, 0)
fun GL3.glUniform3i(location: Int, vec2: Vec2i, z: Int) = glUniform3i(location, vec2.x, vec2.y, z)
fun GL3.glUniform3i(location: Int, vec3: Vec3i) = glUniform3i(location, vec3.x, vec3.y, vec3.z)
fun GL3.glUniform3i(location: Int, vec4: Vec4i) = glUniform3i(location, vec4.x, vec4.y, vec4.z)

fun GL3.glUniform3i(location: Int, x: Boolean) = glUniform3i(location, x.i, x.i, x.i)
fun GL3.glUniform3i(location: Int, vec1: Vec1bool) = glUniform3i(location, vec1.x.i, 0, 0)
fun GL3.glUniform3i(location: Int, vec1: Vec1bool, y: Number, z: Number) = glUniform3i(location, vec1.x.i, y.i, z.i)
fun GL3.glUniform3i(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean) = glUniform3i(location, vec1.x.i, y.i, z.i)
fun GL3.glUniform3i(location: Int, vec2: Vec2bool) = glUniform3i(location, vec2.x.i, vec2.y.i, 0)
fun GL3.glUniform3i(location: Int, vec2: Vec2bool, z: Number) = glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun GL3.glUniform3i(location: Int, vec2: Vec2bool, z: Boolean) = glUniform3i(location, vec2.x.i, vec2.y.i, z.i)
fun GL3.glUniform3i(location: Int, vec3: Vec3bool) = glUniform3i(location, vec3.x.i, vec3.y.i, vec3.z.i)
fun GL3.glUniform3i(location: Int, vec4: Vec4bool) = glUniform3i(location, vec4.x.i, vec4.y.i, vec4.z.i)


// conversions, 4f

fun GL3.glUniform4f(location: Int) = glUniform4f(location, 0f, 0f, 0f, 1f)

fun GL3.glUniform4f(location: Int, x: Number) = glUniform4f(location, x.f, x.f, x.f, x.f)
fun GL3.glUniform4f(location: Int, x: Number, y: Number, z: Number, w: Number) = glUniform4f(location, x.f, y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec1: Vec1t<*>) = glUniform4f(location, vec1.x.f, 0f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec1: Vec1t<*>, y: Number, z: Number, w: Number) = glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec2: Vec2t<*>) = glUniform4f(location, vec2.x.f, vec2.y.f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec2: Vec2t<*>, z: Number, w: Number) = glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, a: Vec2t<*>, b: Vec2t<*>) = glUniform4f(location, a.x.f, a.y.f, b.x.f, b.y.f)
fun GL3.glUniform4f(location: Int, vec3: Vec3t<*>) = glUniform4f(location, vec3.x.f, vec3.y.f, vec3.y.f, 1f)
fun GL3.glUniform4f(location: Int, vec3: Vec3t<*>, w: Number) = glUniform4f(location, vec3.x.f, vec3.y.f, vec3.y.f, w.f)
fun GL3.glUniform4f(location: Int, vec4: Vec4t<*>) = glUniform4f(location, vec4.x.f, vec4.y.f, vec4.y.f, vec4.w.f)

fun GL3.glUniform4f(location: Int, x: Float) = glUniform4f(location, x, x, x, x)
fun GL3.glUniform4f(location: Int, vec1: Vec1) = glUniform4f(location, vec1.x, 0f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec1: Vec1, y: Float, z: Float, w: Float) = glUniform4f(location, vec1.x, y, z, w)
fun GL3.glUniform4f(location: Int, vec2: Vec2) = glUniform4f(location, vec2.x, vec2.y, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec2: Vec2, z: Float, w: Float) = glUniform4f(location, vec2.x, vec2.y, z, w)
fun GL3.glUniform4f(location: Int, a: Vec2, b: Vec2) = glUniform4f(location, a.x, a.y, a.x, a.y)
fun GL3.glUniform4f(location: Int, vec3: Vec3) = glUniform4f(location, vec3.x, vec3.y, vec3.z, 1f)
fun GL3.glUniform4f(location: Int, vec3: Vec3, w: Float) = glUniform4f(location, vec3.x, vec3.y, vec3.z, w)
fun GL3.glUniform4f(location: Int, vec4: Vec4) = glUniform4f(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun GL3.glUniform4f(location: Int, x: Boolean) = glUniform4f(location, x.f, 0f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec1: Vec1bool) = glUniform4f(location, vec1.x.f, 0f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec1: Vec1bool, y: Number, z: Number, w: Number) = glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean, w: Boolean) = glUniform4f(location, vec1.x.f, y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec2: Vec2bool) = glUniform4f(location, vec2.x.f, vec2.y.f, 0f, 1f)
fun GL3.glUniform4f(location: Int, vec2: Vec2bool, z: Number, w: Number) = glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec2: Vec2bool, z: Boolean, w: Boolean) = glUniform4f(location, vec2.x.f, vec2.y.f, z.f, w.f)
fun GL3.glUniform4f(location: Int, vec3: Vec3bool) = glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, 1f)
fun GL3.glUniform4f(location: Int, vec3: Vec3bool, w: Number) = glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, w.f)
fun GL3.glUniform4f(location: Int, vec3: Vec3bool, w: Boolean) = glUniform4f(location, vec3.x.f, vec3.y.f, vec3.z.f, w.f)
fun GL3.glUniform4f(location: Int, vec4: Vec4bool) = glUniform4f(location, vec4.x.f, vec4.y.f, vec4.z.f, vec4.w.f)


// conversions, 4i

fun GL3.glUniform4i(location: Int) = glUniform4i(location, 0, 0, 0, 1)

fun GL3.glUniform4i(location: Int, x: Number) = glUniform4i(location, x.i, x.i, x.i, x.i)
fun GL3.glUniform4i(location: Int, vec1: Vec1t<*>) = glUniform4i(location, vec1.x.i, 0, 0, 1)
fun GL3.glUniform4i(location: Int, vec1: Vec1t<*>, y: Number, z: Number, w: Number) = glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, vec2: Vec2t<*>, z: Number, w: Number) = glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, a: Vec2t<*>, b: Vec2t<*>) = glUniform4i(location, a.x.i, a.y.i, b.x.i, b.y.i)
fun GL3.glUniform4i(location: Int, vec3: Vec3t<*>) = glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, 1)
fun GL3.glUniform4i(location: Int, vec3: Vec3t<*>, w: Number) = glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun GL3.glUniform4i(location: Int, vec4: Vec4t<*>) = glUniform4i(location, vec4.x.i, vec4.y.i, vec4.z.i, vec4.w.i)

fun GL3.glUniform4i(location: Int, x: Int) = glUniform4i(location, x, x, x, x)
fun GL3.glUniform4i(location: Int, vec1: Vec1i) = glUniform4i(location, vec1.x, 0, 0, 1)
fun GL3.glUniform4i(location: Int, vec1: Vec1i, y: Int, z: Int, w: Int) = glUniform4i(location, vec1.x, y, z, w)
fun GL3.glUniform4i(location: Int, vec2: Vec2i) = glUniform4i(location, vec2.x, vec2.y, 0, 1)
fun GL3.glUniform4i(location: Int, vec2: Vec2i, z: Int, w: Int) = glUniform4i(location, vec2.x, vec2.y, z, w)
fun GL3.glUniform4i(location: Int, vec3: Vec3i) = glUniform4i(location, vec3.x, vec3.y, vec3.z, 1)
fun GL3.glUniform4i(location: Int, vec3: Vec3i, w: Int) = glUniform4i(location, vec3.x, vec3.y, vec3.z, w)
fun GL3.glUniform4i(location: Int, vec4: Vec4i) = glUniform4i(location, vec4.x, vec4.y, vec4.z, vec4.w)

fun GL3.glUniform4i(location: Int, x: Boolean) = glUniform4i(location, x.i, x.i, x.i, x.i)
fun GL3.glUniform4i(location: Int, vec1: Vec1bool) = glUniform4i(location, vec1.x.i, 0, 0, 1)
fun GL3.glUniform4i(location: Int, vec1: Vec1bool, y: Number, z: Number, w: Number) = glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, vec1: Vec1bool, y: Boolean, z: Boolean, w: Boolean) = glUniform4i(location, vec1.x.i, y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, vec2: Vec2bool) = glUniform4i(location, vec2.x.i, vec2.y.i, 0, 1)
fun GL3.glUniform4i(location: Int, vec2: Vec2bool, z: Number, w: Number) = glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, vec2: Vec2bool, z: Boolean, w: Boolean) = glUniform4i(location, vec2.x.i, vec2.y.i, z.i, w.i)
fun GL3.glUniform4i(location: Int, vec3: Vec3bool) = glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, 1)
fun GL3.glUniform4i(location: Int, vec3: Vec3bool, w: Number) = glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun GL3.glUniform4i(location: Int, vec3: Vec3bool, w: Boolean) = glUniform4i(location, vec3.x.i, vec3.y.i, vec3.z.i, w.i)
fun GL3.glUniform4i(location: Int, vec4: Vec4bool) = glUniform4i(location, vec4.x.i, vec4.y.i, vec4.z.i, vec4.w.i)


// ----------------------------------------- mat -----------------------------------------------------------------------

// inferred length and type

fun GL3.glUniform(location: Int, mat2: Mat2) = glUniformMatrix2fv(location, 1, false, mat2 to m2Buf)
fun GL3.glUniform(location: Int, mat2x3: Mat2x3) = glUniformMatrix2x3fv(location, 1, false, mat2x3 to m23Buf)
fun GL3.glUniform(location: Int, mat2x4: Mat2x4) = glUniformMatrix2x4fv(location, 1, false, mat2x4 to m24Buf)
fun GL3.glUniform(location: Int, mat3x2: Mat3x2) = glUniformMatrix3x2fv(location, 1, false, mat3x2 to m32Buf)
fun GL3.glUniform(location: Int, mat3: Mat3) = glUniformMatrix3fv(location, 1, false, mat3 to m3Buf)
fun GL3.glUniform(location: Int, mat3x4: Mat3x4) = glUniformMatrix3x4fv(location, 1, false, mat3x4 to m34Buf)
fun GL3.glUniform(location: Int, mat4x2: Mat4x2) = glUniformMatrix4x2fv(location, 1, false, mat4x2 to m42Buf)
fun GL3.glUniform(location: Int, mat4x3: Mat4x3) = glUniformMatrix4x3fv(location, 1, false, mat4x3 to m43Buf)
fun GL3.glUniform(location: Int, mat4: Mat4) = glUniformMatrix4fv(location, 1, false, mat4 to m4Buf)

// TODO double mat and vectors
//fun GL3.glUniform(location: Int, mat2: Mat2) = GL40.glUniformMatrix2dv(location, false, mat2 to m2Buf)
//fun GL3.glUniform(location: Int, mat2x3: Mat2x3) = GL40.glUniformMatrix2x3dv(location, false, mat2x3 to m23Buf)
//fun GL3.glUniform(location: Int, mat2x4: Mat2x4) = GL40.glUniformMatrix2x4dv(location, false, mat2x4 to m24Buf)
//fun GL3.glUniform(location: Int, mat3x2: Mat3x2) = GL40.glUniformMatrix3x2dv(location, false, mat3x2 to m32Buf)
//fun GL3.glUniform(location: Int, mat3: Mat3) = GL40.glUniformMatrix3dv(location, false, mat3 to m3Buf)
//fun GL3.glUniform(location: Int, mat3x4: Mat3x4) = GL40.glUniformMatrix3x4dv(location, false, mat3x4 to m34Buf)
//fun GL3.glUniform(location: Int, mat4x2: Mat4x2) = GL40.glUniformMatrix4x2dv(location, false, mat4x2 to m42Buf)
//fun GL3.glUniform(location: Int, mat4x3: Mat4x3) = GL40.glUniformMatrix4x3dv(location, false, mat4x3 to m43Buf)
//fun GL3.glUniform(location: Int, mat4: Mat4) = GL40.glUniformMatrix4dv(location, false, mat4 to m4Buf)
