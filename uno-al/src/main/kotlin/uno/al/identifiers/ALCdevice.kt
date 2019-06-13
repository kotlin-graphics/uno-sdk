package uno.al.identifiers

import kool.Adr
import kool.Stack
import kool.adr
import kool.toBuffer
import org.lwjgl.openal.ALC10
import org.lwjgl.openal.ALC11
import org.lwjgl.system.MemoryUtil
import uno.al.ALCerror
import uno.al.ALCstringQuery
import uno.al.alc

inline class ALCdevice(val handle: Long) {

    fun createContext(attrList: IntArray? = null): ALCcontext =
            ALCcontext(Stack { ALC10.nalcCreateContext(handle, attrList?.toBuffer(it)?.adr ?: MemoryUtil.NULL) })

    fun close() = ALC10.alcCloseDevice(handle)

    infix fun isExtensionPresent(extName: CharSequence) = ALC10.alcIsExtensionPresent(handle, extName)

    infix fun getProcAddress(funcName: CharSequence): Adr = ALC10.alcGetProcAddress(handle, funcName)

    infix fun getEnumValue(enumName: CharSequence): Int = ALC10.alcGetEnumValue(handle, enumName)

    val error: ALCerror
        get() = ALCerror(ALC10.alcGetError(handle))

    infix fun getString(token: ALCstringQuery): String? = ALC10.alcGetString(handle, token.i)

    val majorVersion: Int
        get() = ALC10.alcGetInteger(handle, ALC10.ALC_MAJOR_VERSION)
    val minorVersion: Int
        get() = ALC10.alcGetInteger(handle, ALC10.ALC_MINOR_VERSION)
    val allAttributes: IntArray
        get() = alc.getAllAttributes(this)
    val captureSamples: Int
        get() = ALC10.alcGetInteger(handle, ALC11.ALC_CAPTURE_SAMPLES)

    fun captureCloseDevice(): Boolean = ALC11.alcCaptureCloseDevice(handle)

    fun captureStart() = ALC11.alcCaptureStart(handle)

    fun captureStop() = ALC11.alcCaptureStop(handle)

    inline fun <R> capture(block: () -> R): R {
        ALC11.alcCaptureStart(handle)
        return block().also {
            ALC11.alcCaptureStop(handle)
        }
    }

    fun captureSamples(buffer: Buffer, samples: Int) = ALC11.nalcCaptureSamples(handle, buffer.adr, samples)

//    fun createCapabilities(): ALCCapabilities = ALC.createCapabilities(handle)

    val isValid: Boolean
        get() = handle != NULL

    val isInvalid: Boolean
        get() = handle == NULL

    companion object {

        fun open(deviceSpecifier: String? = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER)): ALCdevice =
                ALCdevice(ALC10.alcOpenDevice(deviceSpecifier))

        // TODO captureOpen?
        fun captureOpenDevice(device: CharSequence?, frequency: Int, format: Int, samples: Int): ALCdevice =
                ALCdevice(ALC11.alcCaptureOpenDevice(device, frequency, format, samples))

        val NULL = ALCdevice(MemoryUtil.NULL)
    }
}