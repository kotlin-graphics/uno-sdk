package uno.awt

import gln.L
import kool.adr
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memGetAddress
import org.lwjgl.system.jawt.JAWT
import org.lwjgl.system.jawt.JAWTDrawingSurface
import org.lwjgl.system.jawt.JAWTDrawingSurfaceInfo
import org.lwjgl.system.jawt.JAWTFunctions.*
import org.lwjgl.system.jawt.JAWTWin32DrawingSurfaceInfo
import uno.kotlin.HWND
import java.awt.Canvas


@JvmInline
value class JawtVersion(val int: Int) {
    companion object {
        val _1_3 = JawtVersion(0x10003)
        val _1_4 = JawtVersion(0x10004)
        val _1_7 = JawtVersion(0x10007)
        val _9 = JawtVersion(0x90000)
    }
}

fun JAWT(version: JawtVersion = JawtVersion._1_4) = JAWT.calloc().version(version.int)

fun JAWT.get(): Boolean = nJAWT_GetAWT(adr.L)

fun JAWT.getDrawingSurface(canvas: Canvas): JAWTDrawingSurface? {
    val adr = memGetAddress(adr.L + JAWT.GETDRAWINGSURFACE)
    val result = nJAWT_GetDrawingSurface(canvas, adr)
    return JAWTDrawingSurface.createSafe(result)
}



@JvmInline
value class JawtLock(val int: Int) {
    companion object {
        val ERROR = JawtLock(0x1)
        val CLIP_CHANGED = JawtLock(0x2)
        val BOUNDS_CHANGED = JawtLock(0x4)
        val SURFACE_CHANGED = JawtLock(0x8)
    }
}

fun JAWTDrawingSurface.lock(): JawtLock {
    val funAdr = memGetAddress(adr.L + JAWTDrawingSurface.LOCK)
    return JawtLock(nJAWT_DrawingSurface_Lock(adr.L, funAdr))
}
fun JAWTDrawingSurface.unlock() {
    val funAdr = memGetAddress(adr.L + JAWTDrawingSurface.UNLOCK)
    nJAWT_DrawingSurface_Unlock(adr.L, funAdr)
}

val JAWTDrawingSurface.info: JAWTDrawingSurfaceInfo?
    get() {
        val funAdr = memGetAddress(adr.L + JAWTDrawingSurface.GETDRAWINGSURFACEINFO)
        val result = nJAWT_DrawingSurface_GetDrawingSurfaceInfo(adr.L, funAdr)
        return JAWTDrawingSurfaceInfo.createSafe(result)
    }

fun JAWTWin32DrawingSurfaceInfo(info: JAWTDrawingSurfaceInfo): JAWTWin32DrawingSurfaceInfo {
    val adr = memGetAddress(info.adr.L + JAWTDrawingSurfaceInfo.PLATFORMINFO)
    return org.lwjgl.system.jawt.JAWTWin32DrawingSurfaceInfo.create(adr)
}

@JvmInline
value class HDC(val L: Long) {
    val isValid get() = L != NULL
    val isInvalid get() = L == NULL
}

val JAWTWin32DrawingSurfaceInfo.hdc: HDC
    get() = HDC(memGetAddress(adr.L + JAWTWin32DrawingSurfaceInfo.HDC))
val JAWTWin32DrawingSurfaceInfo.hwnd: HWND
    get() = HWND(memGetAddress(adr.L + JAWTWin32DrawingSurfaceInfo.HWND))

infix fun JAWTDrawingSurface.free(dsi: JAWTDrawingSurfaceInfo) {
    val adr = memGetAddress(adr.L + JAWTDrawingSurface.FREEDRAWINGSURFACEINFO)
    nJAWT_DrawingSurface_FreeDrawingSurfaceInfo(dsi.adr.L, adr)
}