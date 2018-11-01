package uno.awt

import org.lwjgl.opengl.WGLARBMultisample.WGL_SAMPLES_ARB
import org.lwjgl.opengl.WGLARBMultisample.WGL_SAMPLE_BUFFERS_ARB
import org.lwjgl.opengl.WGLARBPixelFormat.*
import org.lwjgl.opengl.WGLARBPixelFormatFloat.WGL_TYPE_RGBA_FLOAT_ARB
import org.lwjgl.opengl.WGLEXTFramebufferSRGB.WGL_FRAMEBUFFER_SRGB_CAPABLE_EXT
import org.lwjgl.opengl.WGLNVMultisampleCoverage.WGL_COLOR_SAMPLES_NV
import uno.kotlin.plusAssign
import java.nio.IntBuffer

/**
 * Contains all information to create an OpenGL context on an {@link AWTGLCanvas}.
 *
 * @author Kai Burjack
 */
class GLData {

    // The following fields are taken from SWT's original GLData

    /** Whether to use double-buffering. */
    var doubleBuffer = true

    /** Whether to use different LEFT and RIGHT backbuffers for stereo rendering. */
    var stereo: Boolean = false

    /** The number of bits for the red color channel. */
    var redSize = 8

    /** The number of bits for the green color channel.  */
    var greenSize = 8

    /** The number of bits for the blue color channel. */
    var blueSize = 8

    /** The number of bits for the alpha color channel.  */
    var alphaSize = 8

    /** The number of bits for the depth channel.  */
    var depthSize = 24

    /** The number of bits for the stencil channel. */
    var stencilSize = 0

    /** The number of bits for the red accumulator color channel. */
    var accumRedSize = 0

    /** The number of bits for the green accumulator color channel.  */
    var accumGreenSize = 0

    /** The number of bits for the blue accumulator color channel.  */
    var accumBlueSize = 0

    /** The number of bits for the alpha accumulator color channel. */
    var accumAlphaSize = 0

    /** This is ignored. It will implicitly be 1 if [.samples] is set to a value greater than or equal to 1. */
    var sampleBuffers = 0

    /** The number of (coverage) samples for multisampling. Multisampling will only be requested for a value greater than or equal to 1. */
    var samples = 0

    /** The [AWTGLCanvas] whose context objects should be shared with the context created using `this` GLData. */
    var shareContext: AWTGLCanvas? = null

    /** The major GL context version to use. It defaults to 0 for "not specified". */
    var majorVersion = 0

    /** The minor GL context version to use. If [.majorVersion] is 0 this field is unused. */
    var minorVersion = 0

    /** Whether a forward-compatible context should be created. This has only an effect when ([.majorVersion].[.minorVersion]) is at least 3.2. */
    var forwardCompatible = false

    /** The profile to use. This is only valid when ([.majorVersion].[.minorVersion]) is at least 3.0. */
    var profile: Profile? = null

    /** The client API to use. It defaults to [OpenGL for Desktop][API.GL]. */
    var api = API.GL

    /** Whether a debug context should be requested. */
    var debug = false

    /** Set the swap interval. It defaults to `null` for "not specified". */
    var swapInterval: Int? = null

    /** Whether to use sRGB color space. */
    var sRGB: Boolean = false

    /** Whether to use a floating point pixel format. */
    var pixelFormatFloat: Boolean = false

    /** Specify the behavior on context switch. Defaults to `null` for "not specified". */
    var contextReleaseBehavior: ReleaseBehavior? = null

    /** The number of color samples per pixel. This is only valid when [.samples] is at least 1. */
    var colorSamplesNV = 0

    /** The swap group index. Use this to synchronize buffer swaps across multiple windows on the same system. */
    var swapGroupNV = 0

    /** The swap barrier index. Use this to synchronize buffer swaps across multiple systems. This requires a Nvidia G-Sync card. */
    var swapBarrierNV = 0

    /** Whether robust buffer access should be used. */
    var robustness = false

    /** When [.robustness] is `true` then this specifies whether a GL_LOSE_CONTEXT_ON_RESET_ARB reset notification is sent, as described by GL_ARB_robustness. */
    var loseContextOnReset = false

    /** When [.robustness] is `true` and [.loseContextOnReset] is `true` then this specifies whether a graphics reset only affects the current application and
     *  no other application in the system. */
    var contextResetIsolation = false

    // New fields not in SWT's GLData

    enum class Profile { CORE, COMPATIBILITY }

    enum class API { GL, GLES }

    enum class ReleaseBehavior { NONE, FLUSH }

    /** Validate the this GLData and throw an exception on validation error. */
    fun validate() {

        if (alphaSize < 0)
            throw IllegalArgumentException("Alpha bits cannot be less than 0")

        if (redSize < 0)
            throw IllegalArgumentException("Red bits cannot be less than 0")

        if (greenSize < 0)
            throw IllegalArgumentException("Green bits cannot be less than 0")

        if (blueSize < 0)
            throw IllegalArgumentException("Blue bits cannot be less than 0")

        if (stencilSize < 0)
            throw IllegalArgumentException("Stencil bits cannot be less than 0")

        if (depthSize < 0)
            throw IllegalArgumentException("Depth bits cannot be less than 0")

        if (forwardCompatible && !atLeast30(majorVersion, minorVersion))
            throw IllegalArgumentException("Forward-compatibility is only defined for OpenGL version 3.0 and above")

        if (samples < 0)
            throw IllegalArgumentException("Invalid samples count")

        if (profile != null && !atLeast32(majorVersion, minorVersion))
            throw IllegalArgumentException("Context profiles are only defined for OpenGL version 3.2 and above")

        if (api == null)
            throw IllegalArgumentException("Unspecified client API")

        if (api == GLData.API.GL && !validVersionGL(majorVersion, minorVersion))
            throw IllegalArgumentException("Invalid OpenGL version")

        if (api == GLData.API.GLES && !validVersionGLES(majorVersion, minorVersion))
            throw IllegalArgumentException("Invalid OpenGL ES version")

        if (!doubleBuffer && swapInterval != null)
            throw IllegalArgumentException("Swap interval set but not using double buffering")

        if (colorSamplesNV < 0)
            throw IllegalArgumentException("Invalid color samples count")

        if (colorSamplesNV > samples)
            throw IllegalArgumentException("Color samples greater than number of (coverage) samples")

        if (swapGroupNV < 0)
            throw IllegalArgumentException("Invalid swap group")

        if (swapBarrierNV < 0)
            throw IllegalArgumentException("Invalid swap barrier")

        if ((swapGroupNV > 0 || swapBarrierNV > 0) && !doubleBuffer)
            throw IllegalArgumentException("Swap group or barrier requested but not using double buffering")

        if (swapBarrierNV > 0 && swapGroupNV == 0)
            throw IllegalArgumentException("Swap barrier requested but no valid swap group set")

        if (loseContextOnReset && !robustness)
            throw IllegalArgumentException("Lose context notification requested but not using robustness")

        if (contextResetIsolation && !robustness)
            throw IllegalArgumentException("Context reset isolation requested but not using robustness")
    }

    /** Encode the pixel format attributes stored in this [GLData] into the given [IntBuffer] for wglChoosePixelFormatARB to consume. */
    infix fun encodePixelFormatAttribs(buf: IntBuffer) {

        buf += WGL_DRAW_TO_WINDOW_ARB; buf += 1

        buf += WGL_SUPPORT_OPENGL_ARB; buf += 1

        buf += WGL_ACCELERATION_ARB; buf += WGL_FULL_ACCELERATION_ARB

        if (doubleBuffer)
            buf += WGL_DOUBLE_BUFFER_ARB; buf += true

        buf += WGL_PIXEL_TYPE_ARB; buf += if (pixelFormatFloat) WGL_TYPE_RGBA_FLOAT_ARB else WGL_TYPE_RGBA_ARB

        if (redSize > 0)
            buf += WGL_RED_BITS_ARB; buf += redSize

        if (greenSize > 0)
            buf += WGL_GREEN_BITS_ARB; buf += greenSize

        if (blueSize > 0)
            buf += WGL_BLUE_BITS_ARB; buf += blueSize

        if (alphaSize > 0)
            buf += WGL_ALPHA_BITS_ARB; buf += alphaSize

        if (depthSize > 0)
            buf += WGL_DEPTH_BITS_ARB; buf += depthSize

        if (stencilSize > 0)
            buf += WGL_STENCIL_BITS_ARB; buf += stencilSize

        if (accumRedSize > 0)
            buf += WGL_ACCUM_RED_BITS_ARB; buf += accumRedSize

        if (accumGreenSize > 0)
            buf += WGL_ACCUM_GREEN_BITS_ARB; buf += accumGreenSize

        if (accumBlueSize > 0)
            buf += WGL_ACCUM_BLUE_BITS_ARB; buf += accumBlueSize

        if (accumAlphaSize > 0)
            buf += WGL_ACCUM_ALPHA_BITS_ARB; buf += accumAlphaSize

        if (accumRedSize > 0 || accumGreenSize > 0 || accumBlueSize > 0 || accumAlphaSize > 0)
            buf += WGL_ACCUM_BITS_ARB; buf += accumRedSize + accumGreenSize + accumBlueSize + accumAlphaSize

        if (sRGB)
            buf += WGL_FRAMEBUFFER_SRGB_CAPABLE_EXT; buf += true

        if (samples > 0) {
            buf += WGL_SAMPLE_BUFFERS_ARB; buf += true
            buf += WGL_SAMPLES_ARB; buf += samples
            if (colorSamplesNV > 0)
                buf += WGL_COLOR_SAMPLES_NV; buf += colorSamplesNV
        }
        buf += 0
    }

    val atLeast30 get() = atLeast30(majorVersion, minorVersion)

    val atLeast32 get() = atLeast32(majorVersion, minorVersion)
}