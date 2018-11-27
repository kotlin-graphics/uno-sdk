package uno.awt

import gli_.has
import glm_.i
import kool.cap
import kool.intBufferOf
import org.lwjgl.opengl.GLX13.*
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.jawt.JAWTDrawingSurface
import org.lwjgl.system.jawt.JAWTFunctions.*
import org.lwjgl.system.jawt.JAWTX11DrawingSurfaceInfo
import org.lwjgl.system.linux.X11
import uno.buffer.intBufferOf
import java.awt.AWTException
import java.awt.Canvas

internal class LinuxGLCanvas : GLCanvas() {

    private var display: Display = NULL
    private var drawable: Drawable = NULL
    private lateinit var ds: JAWTDrawingSurface

    @Throws(AWTException::class)
    private fun create(depth: Int, attribs: GLData, effective: GLData): WglContext {
        val screen = X11.XDefaultScreen(display)
        val attribs = intBufferOf( // TODO stak
                GLX_DRAWABLE_TYPE, GLX_WINDOW_BIT,
                GLX_RENDER_TYPE, GLX_RGBA_BIT,
                GLX_RED_SIZE, attribs.redSize,
                GLX_GREEN_SIZE, attribs.greenSize,
                GLX_BLUE_SIZE, attribs.blueSize,
                GLX_DEPTH_SIZE, attribs.depthSize,
                GLX_DOUBLEBUFFER, attribs.doubleBuffer.i,
                0, 0)
        val fbConfigs = glXChooseFBConfig(display, screen, attribs)
        if (fbConfigs == null || fbConfigs.cap == 0)
            throw AWTException("No supported framebuffer configurations found")

        return glXCreateNewContext(display, fbConfigs[0], GLX_RGBA_TYPE, NULL, true)
    }

    @Throws(AWTException::class)
    override fun lock() {
        val lock = JAWT_DrawingSurface_Lock(ds.Lock(), ds)
        if (lock has JAWT_LOCK_ERROR)
            throw AWTException("JAWT_DrawingSurface_Lock() failed")
    }

    @Throws(AWTException::class)
    override fun unlock() = JAWT_DrawingSurface_Unlock(ds.Unlock(), ds)

    @Throws(AWTException::class)
    override fun createContext(canvas: Canvas, data: GLData, effective: GLData): WglContext {
        ds = JAWT_GetDrawingSurface(awt.GetDrawingSurface(), canvas)!!
        val ds = JAWT_GetDrawingSurface(awt.GetDrawingSurface(), canvas)!!
        try {
            lock()
            try {
                val dsi = JAWT_DrawingSurface_GetDrawingSurfaceInfo(ds.GetDrawingSurfaceInfo(), ds)!!
                try {
                    val dsiWin = JAWTX11DrawingSurfaceInfo.create(dsi.platformInfo())
                    val depth = dsiWin.depth()
                    display = dsiWin.display()
                    drawable = dsiWin.drawable()
                    return create(depth, data, effective)
                } finally {
                    JAWT_DrawingSurface_FreeDrawingSurfaceInfo(ds.FreeDrawingSurfaceInfo(), dsi)
                }
            } finally {
                unlock()
            }
        } finally {
            JAWT_FreeDrawingSurface(awt.FreeDrawingSurface(), ds)
        }
    }

    override fun makeCurrent(context: WglContext): Boolean = when (context) {
        NULL -> glXMakeCurrent(display, NULL, context)
        else -> glXMakeCurrent(display, drawable, context)
    }

    override fun isCurrent(context: Long): Boolean = glXGetCurrentContext() == context

    override fun swapBuffers(): Boolean {
        glXSwapBuffers(display, drawable)
        return true
    }

    override fun delayBeforeSwapNV(seconds: Float): Boolean = throw UnsupportedOperationException("NYI")
}

typealias Display = Long
typealias Drawable = Long