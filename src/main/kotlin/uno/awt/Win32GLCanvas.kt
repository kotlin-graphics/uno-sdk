package uno.awt

import gli_.has
import glm_.b
import glm_.bool
import glm_.i
import glm_.s
import kool.Adr
import kool.adr
import kool.pos
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.ARBMultisample.GL_SAMPLES_ARB
import org.lwjgl.opengl.ARBMultisample.GL_SAMPLE_BUFFERS_ARB
import org.lwjgl.opengl.ARBRobustness.GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT_ARB
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL32
import org.lwjgl.opengl.GL43C.GL_CONTEXT_FLAG_DEBUG_BIT
import org.lwjgl.opengl.NVMultisampleCoverage.GL_COLOR_SAMPLES_NV
import org.lwjgl.opengl.WGL.*
import org.lwjgl.opengl.WGLARBContextFlushControl.*
import org.lwjgl.opengl.WGLARBCreateContext.*
import org.lwjgl.opengl.WGLARBCreateContextProfile.*
import org.lwjgl.opengl.WGLARBCreateContextRobustness.*
import org.lwjgl.opengl.WGLARBPixelFormat.*
import org.lwjgl.opengl.WGLARBPixelFormatFloat.WGL_TYPE_RGBA_FLOAT_ARB
import org.lwjgl.opengl.WGLARBRobustnessApplicationIsolation.WGL_CONTEXT_RESET_ISOLATION_BIT_ARB
import org.lwjgl.opengl.WGLEXTCreateContextES2Profile.WGL_CONTEXT_ES2_PROFILE_BIT_EXT
import org.lwjgl.system.*
import org.lwjgl.system.MemoryUtil.*
import org.lwjgl.system.jawt.JAWTFunctions.*
import org.lwjgl.system.jawt.JAWTWin32DrawingSurfaceInfo
import org.lwjgl.system.windows.*
import org.lwjgl.system.windows.WindowsLibrary.HINSTANCE
import uno.glfw.HWND
import uno.kotlin.plusAssign
import java.awt.AWTException
import java.awt.Canvas

/**
 * Windows-specific implementation of {@link PlatformGLCanvas}.
 *
 * @author Kai Burjack
 */
class Win32GLCanvas : GLCanvas() {

    private var hwnd: Long = 0
    private var wglDelayBeforeSwapNVAddr = NULL
    private var wglDelayBeforeSwapNVAddr_set = false

    @Throws(AWTException::class)
    override fun createContext(canvas: Canvas, data: GLData, effective: GLData): WglContext {
        val ds = JAWT_GetDrawingSurface(awt.GetDrawingSurface(), canvas) ?: throw Error()
        try {
            val lock = JAWT_DrawingSurface_Lock(ds.Lock(), ds)
            if (lock has JAWT_LOCK_ERROR)
                throw AWTException("JAWT_DrawingSurface_Lock() failed")
            try {
                val dsi = JAWT_DrawingSurface_GetDrawingSurfaceInfo(ds.GetDrawingSurfaceInfo(), ds)!!
                try {
                    val dsiWin = JAWTWin32DrawingSurfaceInfo.create(dsi.platformInfo())
                    hwnd = dsiWin.hwnd()
                    val hwndDummy = createDummyWindow()
                    try {
                        return create(hwnd, hwndDummy, data, effective)
                    } finally {
                        User32.DestroyWindow(hwndDummy)
                    }
                } finally {
                    JAWT_DrawingSurface_FreeDrawingSurfaceInfo(ds.FreeDrawingSurfaceInfo(), dsi)
                }
            } finally {
                JAWT_DrawingSurface_Unlock(ds.Unlock(), ds)
            }
        } finally {
            JAWT_FreeDrawingSurface(awt.FreeDrawingSurface(), ds)
        }
    }

    private fun createDummyWindow(): Long {
        val defaultWndProc = WindowProcI { hwnd, msg, wParam, lParam ->
            User32.DefWindowProc(hwnd, msg, wParam, lParam)
        }
        val className = "AWTAPPWNDCLASS"
        val classNameBuffer = memUTF16(className)
        val wndAttr = WNDCLASSEX.calloc().apply {
            cbSize(WNDCLASSEX.SIZEOF)
            lpfnWndProc(defaultWndProc)
            hInstance(HINSTANCE)
            lpszClassName(classNameBuffer)
        }
        User32.RegisterClassEx(wndAttr)
        val hwnd = User32.CreateWindowEx(User32.WS_EX_APPWINDOW, className, "", 0, User32.CW_USEDEFAULT, User32.CW_USEDEFAULT, 800, 600, NULL, NULL,
                HINSTANCE, defaultWndProc.address())
        memFree(classNameBuffer)
        wndAttr.free()
        return hwnd
    }

    @Throws(AWTException::class)
    private fun create(windowHandle: Long, dummyWindowHandle: Long, data: GLData, effective: GLData): WglContext {

        val stack = MemoryStack.stackGet()
        val bufferAddr = stack.nmalloc(4, 4 * 2 shl 2)

        // Validate context attributes
        data.validate()

        // Find this exact pixel format, though for now without multisampling. This comes later!
        val pfd = PIXELFORMATDESCRIPTOR.callocStack(stack).apply {
            nSize(PIXELFORMATDESCRIPTOR.SIZEOF.s)
            nVersion(1.s) // this should always be 1
            dwLayerMask(GDI32.PFD_MAIN_PLANE.i)
            iPixelType(GDI32.PFD_TYPE_RGBA)
            var flags = GDI32.PFD_DRAW_TO_WINDOW or GDI32.PFD_SUPPORT_OPENGL
            if (data.doubleBuffer)
                flags = flags or GDI32.PFD_DOUBLEBUFFER
            if (data.stereo)
                flags = flags or GDI32.PFD_STEREO
            dwFlags(flags)
            cRedBits(data.redSize.b)
            cGreenBits(data.greenSize.b)
            cBlueBits(data.blueSize.b)
            cAlphaBits(data.alphaSize.b)
            cDepthBits(data.depthSize.b)
            cStencilBits(data.stencilSize.b)
            cAccumRedBits(data.accumRedSize.b)
            cAccumGreenBits(data.accumGreenSize.b)
            cAccumBlueBits(data.accumBlueSize.b)
            cAccumAlphaBits(data.accumAlphaSize.b)
            cAccumBits((data.accumRedSize + data.accumGreenSize + data.accumBlueSize + data.accumAlphaSize).b)
        }
        val hDCdummy = User32.GetDC(dummyWindowHandle)
        var pixelFormat = GDI32.ChoosePixelFormat(hDCdummy, pfd)
        if (pixelFormat == 0 || !GDI32.SetPixelFormat(hDCdummy, pixelFormat, pfd)) {
            User32.ReleaseDC(dummyWindowHandle, hDCdummy)
            throw AWTException("Unsupported pixel format")
        }

        /* Next, create a dummy context using Opengl32.lib's wglCreateContext. This should ALWAYS work, but won't give us a "new"/"core" context if we requested
         * that and also does not support multisampling. But we use this "dummy" context then to request the required WGL function pointers to create a new
         * OpenGL >= 3.0 context and with optional multisampling.         */
        val dummyContext = wglCreateContext(hDCdummy)
        if (dummyContext == NULL) {
            User32.ReleaseDC(dummyWindowHandle, hDCdummy)
            throw AWTException("Failed to create OpenGL context")
        }

        // Save current context to restore it later
        val currentContext = wglGetCurrentContext()
        val currentDc = wglGetCurrentDC()

        // Make the new dummy context current
        var success = wglMakeCurrent(hDCdummy, dummyContext)
        if (!success) {
            User32.ReleaseDC(dummyWindowHandle, hDCdummy)
            wglDeleteContext(dummyContext)
            throw AWTException("Failed to make OpenGL context current")
        }

        // Query supported WGL extensions
        var wglExtensions = ""
        val wglGetExtensionsStringARBAddr = wglGetProcAddress("wglGetExtensionsStringARB")
        if (wglGetExtensionsStringARBAddr != NULL) {
            val str = JNI.callPP(wglGetExtensionsStringARBAddr, hDCdummy)
            if (str != NULL)
                wglExtensions = memASCII(str)
        } else {
            // Try the EXT extension
            val wglGetExtensionsStringEXTAddr = wglGetProcAddress("wglGetExtensionsStringEXT")
            if (wglGetExtensionsStringEXTAddr != NULL) {
                val str = JNI.callP(wglGetExtensionsStringEXTAddr)
                if (str != NULL)
                    wglExtensions = memASCII(str)
            }
        }
        val wglExtensionsList = wglExtensions.split(" ".toRegex()).toSet()
        success = User32.ReleaseDC(dummyWindowHandle, hDCdummy)
        if (!success) {
            wglDeleteContext(dummyContext)
            wglMakeCurrent(currentDc, currentContext)
            throw AWTException("Could not release dummy DC")
        }

        // For some constellations of context attributes, we can stop right here.
        if (!data.atLeast30 && data.samples == 0 && !data.sRGB && !data.pixelFormatFloat
                && data.contextReleaseBehavior == null && !data.robustness && data.api != GLData.API.GLES) {
            /* Finally, create the real context on the real window */
            val hDC = User32.GetDC(windowHandle)
            GDI32.SetPixelFormat(hDC, pixelFormat, pfd)
            success = wglDeleteContext(dummyContext)
            if (!success) {
                User32.ReleaseDC(windowHandle, hDC)
                wglMakeCurrent(currentDc, currentContext)
                throw AWTException("Could not delete dummy GL context")
            }
            val context = wglCreateContext(hDC)

            data.swapInterval?.let {
                if ("WGL_EXT_swap_control" !in wglExtensionsList) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw AWTException("Swap interval requested but WGL_EXT_swap_control is unavailable")
                }
                // Only allowed if WGL_EXT_swap_control_tear is available
                if (it < 0 && "WGL_EXT_swap_control_tear" !in wglExtensionsList) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw AWTException("Negative swap interval requested but WGL_EXT_swap_control_tear is unavailable")
                }
                // Make context current to set the swap interval
                success = wglMakeCurrent(hDC, context)
                if (!success) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw AWTException("Could not make GL context current")
                }
                val wglSwapIntervalEXTAddr = wglGetProcAddress("wglSwapIntervalEXT")
                if (wglSwapIntervalEXTAddr != NULL)
                    JNI.callI(wglSwapIntervalEXTAddr, it)
            }

            if (data.swapGroupNV > 0 || data.swapBarrierNV > 0) {
                // Only allowed if WGL_NV_swap_group is available
                if ("WGL_NV_swap_group" !in wglExtensionsList) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw AWTException("Swap group or barrier requested but WGL_NV_swap_group is unavailable")
                }
                // Make context current to join swap group and/or barrier
                success = wglMakeCurrent(hDC, context)
                try {
                    wglNvSwapGroupAndBarrier(data, stack.address + stack.pointer, hDC)
                } catch (e: AWTException) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw e
                }
            }

            /* Check if we want to share context */
            data.shareContext?.let {
                success = wglShareLists(context, it.context)
                if (!success) {
                    User32.ReleaseDC(windowHandle, hDC)
                    wglMakeCurrent(currentDc, currentContext)
                    wglDeleteContext(context)
                    throw AWTException("Failed while configuring context sharing")
                }
            }

            // Describe pixel format
            val pixFmtIndex = GDI32.DescribePixelFormat(hDC, pixelFormat, pfd)
            if (pixFmtIndex == 0) {
                User32.ReleaseDC(windowHandle, hDC)
                wglMakeCurrent(currentDc, currentContext)
                wglDeleteContext(context)
                throw AWTException("Failed to describe pixel format")
            }
            success = User32.ReleaseDC(windowHandle, hDC)
            if (!success) {
                wglMakeCurrent(currentDc, currentContext)
                wglDeleteContext(context)
                throw AWTException("Could not release DC")
            }
            effective.apply {
                redSize = pfd.cRedBits().i
                greenSize = pfd.cGreenBits().i
                blueSize = pfd.cBlueBits().i
                alphaSize = pfd.cAlphaBits().i
                depthSize = pfd.cDepthBits().i
                stencilSize = pfd.cStencilBits().i
                val pixelFormatFlags = pfd.dwFlags()
                doubleBuffer = pixelFormatFlags has GDI32.PFD_DOUBLEBUFFER
                stereo = pixelFormatFlags has GDI32.PFD_STEREO
                accumRedSize = pfd.cAccumRedBits().i
                accumGreenSize = pfd.cAccumGreenBits().i
                accumBlueSize = pfd.cAccumBlueBits().i
                accumAlphaSize = pfd.cAccumAlphaBits().i
            }

            // Restore old context
            wglMakeCurrent(currentDc, currentContext)
            return context
        }

        // Check for WGL_ARB_create_context support
        if ("WGL_ARB_create_context" !in wglExtensionsList) {
            wglDeleteContext(dummyContext)
            wglMakeCurrent(currentDc, currentContext)
            throw AWTException("Extended context attributes requested but WGL_ARB_create_context is unavailable")
        }

        // Obtain wglCreateContextAttribsARB function pointer
        val wglCreateContextAttribsARBAddr = wglGetProcAddress("wglCreateContextAttribsARB")
        if (wglCreateContextAttribsARBAddr == NULL) {
            wglDeleteContext(dummyContext)
            wglMakeCurrent(currentDc, currentContext)
            throw AWTException("WGL_ARB_create_context available but wglCreateContextAttribsARB is NULL")
        }

        val attribList = BufferUtils.createIntBuffer(64) // TODO
        val attribListAddr = attribList.adr
        val hDC = User32.GetDC(windowHandle)

        fun throwSince(message: String) {
            User32.ReleaseDC(windowHandle, hDC)
            wglDeleteContext(dummyContext)
            wglMakeCurrent(currentDc, currentContext)
            throw AWTException(message)
        }

        // Obtain wglChoosePixelFormatARB if multisampling or sRGB or floating point pixel format is requested
        if (data.samples > 0 || data.sRGB || data.pixelFormatFloat) {
            // Try EXT function (the WGL constants are the same in both extensions)
            val wglChoosePixelFormatAddr = wglGetProcAddress("wglChoosePixelFormatARB").takeIf { it != NULL }
                    ?: wglGetProcAddress("wglChoosePixelFormatEXT")
            if (wglChoosePixelFormatAddr == NULL)
                throwSince("No support for wglChoosePixelFormatARB/EXT. Cannot query supported pixel formats.")
            if (data.samples > 0) {
                // Check for ARB or EXT extension (their WGL constants have the same value)
                if ("WGL_ARB_multisample" !in wglExtensionsList && "WGL_EXT_multisample" !in wglExtensionsList)
                    throwSince("Multisampling requested but neither WGL_ARB_multisample nor WGL_EXT_multisample available")
                if (data.colorSamplesNV > 0 && "WGL_NV_multisample_coverage" !in wglExtensionsList)
                    throwSince("Color samples requested but WGL_NV_multisample_coverage is unavailable")
            }
            // Check for WGL_EXT_framebuffer_sRGB
            if (data.sRGB && "WGL_EXT_framebuffer_sRGB" !in wglExtensionsList)
                throwSince("sRGB color space requested but WGL_EXT_framebuffer_sRGB is unavailable")
            // Check for WGL_ARB_pixel_format_float
            if (data.pixelFormatFloat && "WGL_ARB_pixel_format_float" !in wglExtensionsList)
                throwSince("Floating-point format requested but WGL_ARB_pixel_format_float is unavailable")
            // Query matching pixel formats
            data encodePixelFormatAttribs attribList
            success = JNI.callPPPPPI(wglChoosePixelFormatAddr, hDC, attribListAddr, NULL, 1, bufferAddr + 4, bufferAddr) == 1
            val numFormats = memGetInt(bufferAddr)
            if (!success || numFormats == 0)
                throwSince("No supported pixel format found.")
            pixelFormat = memGetInt(bufferAddr + 4)
            // Describe pixel format for the PIXELFORMATDESCRIPTOR to match the chosen format
            val pixFmtIndex = GDI32.DescribePixelFormat(hDC, pixelFormat, pfd)
            if (pixFmtIndex == 0)
                throwSince("Failed to validate supported pixel format.")
            // Obtain extended pixel format attributes, Try EXT function (function signature is the same)
            val wglGetPixelFormatAttribivAddr = wglGetProcAddress("wglGetPixelFormatAttribivARB").takeIf { it != NULL }
                    ?: wglGetProcAddress("wglGetPixelFormatAttribivEXT")
            if (wglGetPixelFormatAttribivAddr == NULL)
                throwSince("No support for wglGetPixelFormatAttribivARB/EXT. Cannot get effective pixel format attributes.")

            attribList.also {
                it.rewind()
                it += WGL_DOUBLE_BUFFER_ARB
                it += WGL_STEREO_ARB
                it += WGL_PIXEL_TYPE_ARB
                it += WGL_RED_BITS_ARB
                it += WGL_GREEN_BITS_ARB
                it += WGL_BLUE_BITS_ARB
                it += WGL_ALPHA_BITS_ARB
                it += WGL_ACCUM_RED_BITS_ARB
                it += WGL_ACCUM_GREEN_BITS_ARB
                it += WGL_ACCUM_BLUE_BITS_ARB
                it += WGL_ACCUM_ALPHA_BITS_ARB
                it += WGL_DEPTH_BITS_ARB
                it += WGL_STENCIL_BITS_ARB
            }
            val attribValues = BufferUtils.createIntBuffer(attribList.pos) // TODO
            val attribValuesAddr = attribValues.adr
            success = JNI.callPPPI(wglGetPixelFormatAttribivAddr, hDC, pixelFormat, GDI32.PFD_MAIN_PLANE.i, attribList.pos, attribListAddr, attribValuesAddr).bool
            if (!success)
                throwSince("Failed to get pixel format attributes.")

            effective.apply {
                doubleBuffer = attribValues[0].bool
                stereo = attribValues[1].bool
                pixelFormatFloat = attribValues[2] == WGL_TYPE_RGBA_FLOAT_ARB
                redSize = attribValues[3]
                greenSize = attribValues[4]
                blueSize = attribValues[5]
                alphaSize = attribValues[6]
                accumRedSize = attribValues[7]
                accumGreenSize = attribValues[8]
                accumBlueSize = attribValues[9]
                accumAlphaSize = attribValues[10]
                depthSize = attribValues[11]
                stencilSize = attribValues[12]
            }
        }

        // Compose the attributes list
        attribList.rewind()
        if (data.api == GLData.API.GL && data.atLeast30 || data.api == GLData.API.GLES && data.majorVersion > 0) {
            attribList += WGL_CONTEXT_MAJOR_VERSION_ARB; attribList += data.majorVersion
            attribList += WGL_CONTEXT_MINOR_VERSION_ARB; attribList += data.minorVersion
        }
        var profile = 0
        if (data.api == GLData.API.GL) {

            if (data.profile == GLData.Profile.COMPATIBILITY)
                profile = WGL_CONTEXT_COMPATIBILITY_PROFILE_BIT_ARB
            else if (data.profile == GLData.Profile.CORE)
                profile = WGL_CONTEXT_CORE_PROFILE_BIT_ARB

        } else if (data.api == GLData.API.GLES) {

            if ("WGL_EXT_create_context_es2_profile" !in wglExtensionsList)
                throwSince("OpenGL ES API requested but WGL_EXT_create_context_es2_profile is unavailable")
            profile = WGL_CONTEXT_ES2_PROFILE_BIT_EXT
        }
        if (profile > 0) {
            if ("WGL_ARB_create_context_profile" !in wglExtensionsList)
                throwSince("OpenGL profile requested but WGL_ARB_create_context_profile is unavailable")
            attribList += WGL_CONTEXT_PROFILE_MASK_ARB; attribList += profile
        }
        var contextFlags = 0
        if (data.debug)
            contextFlags = contextFlags or WGL_CONTEXT_DEBUG_BIT_ARB

        if (data.forwardCompatible)
            contextFlags = contextFlags or WGL_CONTEXT_FORWARD_COMPATIBLE_BIT_ARB

        if (data.robustness) {
            // Check for WGL_ARB_create_context_robustness
            if ("WGL_ARB_create_context_robustness" !in wglExtensionsList)
                throwSince("Context with robust buffer access requested but WGL_ARB_create_context_robustness is unavailable")
            contextFlags = contextFlags or WGL_CONTEXT_ROBUST_ACCESS_BIT_ARB
            if (data.loseContextOnReset)
                attribList += WGL_CONTEXT_RESET_NOTIFICATION_STRATEGY_ARB; attribList += WGL_LOSE_CONTEXT_ON_RESET_ARB
            // Note: WGL_NO_RESET_NOTIFICATION_ARB is default behaviour and need not be specified.

            if (data.contextResetIsolation) {
                // Check for WGL_ARB_robustness_application_isolation or WGL_ARB_robustness_share_group_isolation
                if ("WGL_ARB_robustness_application_isolation" !in wglExtensionsList && "WGL_ARB_robustness_share_group_isolation" !in wglExtensionsList)
                    throwSince("Robustness isolation requested but neither WGL_ARB_robustness_application_isolation nor WGL_ARB_robustness_share_group_isolation available")
                contextFlags = contextFlags or WGL_CONTEXT_RESET_ISOLATION_BIT_ARB
            }
        }
        if (contextFlags > 0)
            attribList += WGL_CONTEXT_FLAGS_ARB; attribList += contextFlags
        data.contextReleaseBehavior?.let {
            if ("WGL_ARB_context_flush_control" !in wglExtensionsList)
                throwSince("Context release behavior requested but WGL_ARB_context_flush_control is unavailable")
            if (it == GLData.ReleaseBehavior.NONE) {
                attribList += WGL_CONTEXT_RELEASE_BEHAVIOR_ARB; attribList += WGL_CONTEXT_RELEASE_BEHAVIOR_NONE_ARB
            } else if (it == GLData.ReleaseBehavior.FLUSH) {
                attribList += WGL_CONTEXT_RELEASE_BEHAVIOR_ARB; attribList += WGL_CONTEXT_RELEASE_BEHAVIOR_FLUSH_ARB
            }
        }
        attribList += 0; attribList += 0
        // Set pixelformat
        success = GDI32.SetPixelFormat(hDC, pixelFormat, pfd)
        if (!success)
            throwSince("Failed to set pixel format.")
        // And create new context with it
        val newCtx = JNI.callPPPP(wglCreateContextAttribsARBAddr, hDC, data.shareContext?.context
                ?: NULL, attribListAddr)
        wglDeleteContext(dummyContext)
        if (newCtx == NULL) {
            User32.ReleaseDC(windowHandle, hDC)
            wglMakeCurrent(currentDc, currentContext)
            throw AWTException("Failed to create OpenGL context.")
        }
        // Make context current for next operations
        wglMakeCurrent(hDC, newCtx)
        data.swapInterval?.let {
            if ("WGL_EXT_swap_control" !in wglExtensionsList)
                throwSince("Swap interval requested but WGL_EXT_swap_control is unavailable")
            // Only allowed if WGL_EXT_swap_control_tear is available
            if (it < 0 && "WGL_EXT_swap_control_tear" !in wglExtensionsList)
                throwSince("Negative swap interval requested but WGL_EXT_swap_control_tear is unavailable")
            val wglSwapIntervalEXTAddr = wglGetProcAddress("wglSwapIntervalEXT")
            if (wglSwapIntervalEXTAddr != NULL)
                JNI.callI(wglSwapIntervalEXTAddr, it)
        }
        if (data.swapGroupNV > 0 || data.swapBarrierNV > 0) {
            // Only allowed if WGL_NV_swap_group is available
            if ("WGL_NV_swap_group" !in wglExtensionsList)
                throwSince("Swap group or barrier requested but WGL_NV_swap_group is unavailable")
            try {
                wglNvSwapGroupAndBarrier(data, bufferAddr, hDC)
            } catch (e: AWTException) {
                throwSince(e.message ?: "")
            }

        }
        User32.ReleaseDC(windowHandle, hDC)
        val provider = GL.getFunctionProvider()!!
        val getInteger = provider.getFunctionAddress("glGetIntegerv")
        val getString = provider.getFunctionAddress("glGetString")
        effective.apply {
            api = data.api
            when {
                data.atLeast30 -> {
                    JNI.callPV(getInteger, GL_MAJOR_VERSION, bufferAddr)
                    majorVersion = memGetInt(bufferAddr)
                    JNI.callPV(getInteger, GL_MINOR_VERSION, bufferAddr)
                    minorVersion = memGetInt(bufferAddr)
                    JNI.callPV(getInteger, GL_CONTEXT_FLAGS, bufferAddr)
                    val effectiveContextFlags = memGetInt(bufferAddr)
                    debug = effectiveContextFlags has GL_CONTEXT_FLAG_DEBUG_BIT
                    forwardCompatible = effectiveContextFlags has GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT
                    robustness = effectiveContextFlags has GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT_ARB
                }
                data.api == GLData.API.GL -> {
                    val version = APIUtil.apiParseVersion(memUTF8(Checks.check(JNI.callP(getString, GL_VERSION))))
                    majorVersion = version.major
                    minorVersion = version.minor
                }
                data.api == GLData.API.GLES -> {
                    val version = APIUtil.apiParseVersion(memUTF8(Checks.check(JNI.callP(getString, GL_VERSION))), "OpenGL ES")
                    majorVersion = version.major
                    minorVersion = version.minor
                }
            }
            if (data.api == GLData.API.GL && atLeast32) {
                JNI.callPV(getInteger, GL32.GL_CONTEXT_PROFILE_MASK, bufferAddr)
                val effectiveProfileMask = MemoryUtil.memGetInt(bufferAddr)
                val core = effectiveProfileMask has GL32.GL_CONTEXT_CORE_PROFILE_BIT
                val comp = effectiveProfileMask has GL32.GL_CONTEXT_COMPATIBILITY_PROFILE_BIT
                this.profile = when {
                    comp -> GLData.Profile.COMPATIBILITY
                    core -> GLData.Profile.CORE
                    else -> null
                }
            }
            if (data.samples >= 1) {
                JNI.callPV(getInteger, GL_SAMPLES_ARB, bufferAddr)
                samples = memGetInt(bufferAddr)
                JNI.callPV(getInteger, GL_SAMPLE_BUFFERS_ARB, bufferAddr)
                sampleBuffers = memGetInt(bufferAddr)
                if ("WGL_NV_multisample_coverage" in wglExtensionsList) {
                    JNI.callPV(getInteger, GL_COLOR_SAMPLES_NV, bufferAddr)
                    colorSamplesNV = memGetInt(bufferAddr)
                }
            }
        }
        // Restore old context
        wglMakeCurrent(currentDc, currentContext)
        return newCtx
    }

    @Throws(AWTException::class)
    private fun wglNvSwapGroupAndBarrier(attribs: GLData, bufferAddr: Adr, hDC: Long) {
        val wglQueryMaxSwapGroupsNVAddr = wglGetProcAddress("wglQueryMaxSwapGroupsNV")
        var success = JNI.callPPPI(wglQueryMaxSwapGroupsNVAddr, hDC, bufferAddr, bufferAddr + 4)
        val maxGroups = memGetInt(bufferAddr)
        if (maxGroups < attribs.swapGroupNV)
            throw AWTException("Swap group exceeds maximum group index")

        val maxBarriers = memGetInt(bufferAddr + 4)
        if (maxBarriers < attribs.swapBarrierNV)
            throw AWTException("Swap barrier exceeds maximum barrier index")

        if (attribs.swapGroupNV > 0) {
            val wglJoinSwapGroupNVAddr = wglGetProcAddress("wglJoinSwapGroupNV")
            if (wglJoinSwapGroupNVAddr == NULL)
                throw AWTException("WGL_NV_swap_group available but wglJoinSwapGroupNV is NULL")

            success = JNI.callPI(wglJoinSwapGroupNVAddr, hDC, attribs.swapGroupNV)
            if (success == 0)
                throw AWTException("Failed to join swap group")

            if (attribs.swapBarrierNV > 0) {
                val wglBindSwapBarrierNVAddr = wglGetProcAddress("wglBindSwapBarrierNV")
                if (wglBindSwapBarrierNVAddr == NULL)
                    throw AWTException("WGL_NV_swap_group available but wglBindSwapBarrierNV is NULL")

                success = JNI.callI(wglBindSwapBarrierNVAddr, attribs.swapGroupNV, attribs.swapBarrierNV)
                if (success == 0)
                    throw AWTException("Failed to bind swap barrier. Probably no G-Sync card installed.")
            }
        }
    }

    override fun isCurrent(context: Long): Boolean = wglGetCurrentContext() == context

    override fun makeCurrent(context: Long): Boolean = when (context) {
        NULL -> wglMakeCurrent(NULL, NULL)
        else -> {
            val hdc = User32.GetDC(hwnd)
            when (hdc) {
                NULL -> false
                else -> wglMakeCurrent(hdc, context).also { User32.ReleaseDC(hwnd, hdc) }
            }
        }
    }

    override fun deleteContext(context: Long): Boolean = wglDeleteContext(context)

    override fun swapBuffers(): Boolean {
        val hdc = User32.GetDC(hwnd)
        return when (hdc) {
            NULL -> false
            else -> GDI32.SwapBuffers(hdc).also { User32.ReleaseDC(hwnd, hdc) }
        }
    }

    override fun delayBeforeSwapNV(seconds: Float): Boolean {
        if (!wglDelayBeforeSwapNVAddr_set) {
            wglDelayBeforeSwapNVAddr = wglGetProcAddress("wglDelayBeforeSwapNV")
            wglDelayBeforeSwapNVAddr_set = true
        }
        if (wglDelayBeforeSwapNVAddr == NULL)
            throw UnsupportedOperationException("wglDelayBeforeSwapNV is unavailable")

        val hDC = User32.GetDC(hwnd)
        val ret = JNI.callPI(wglDelayBeforeSwapNVAddr, hDC, seconds)
        User32.ReleaseDC(hwnd, hDC)
        return ret == 1
    }
}