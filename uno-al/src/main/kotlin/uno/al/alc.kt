package uno.al

import glm_.BYTES
import kool.Adr
import kool.Stack
import kool.adr
import kool.toBuffer
import org.lwjgl.openal.ALC10
import org.lwjgl.openal.ALC11
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memGetInt
import uno.al.identifiers.ALCcontext
import uno.al.identifiers.ALCdevice
import java.nio.Buffer


object alc {

    // --- [ alcOpenDevice ] ---

    /**
     * Allows the application to connect to a device.
     *
     * <p>If the function returns {@code NULL}, then no sound driver/device has been found. The argument is a null terminated string that requests a certain device or
     * device configuration. If {@code NULL} is specified, the implementation will provide an implementation specific default.</p>
     *
     * @param deviceSpecifier the requested device or device configuration
     */
    fun openDevice(deviceSpecifier: CharSequence?): ALCdevice = Stack {
        it.nUTF8Safe(deviceSpecifier, true)
        val deviceSpecifierEncoded = if (deviceSpecifier == null) NULL else it.pointerAddress
        ALCdevice(ALC10.nalcOpenDevice(deviceSpecifierEncoded))
    }

    // --- [ alcCloseDevice ] ---

    /**
     * Allows the application to disconnect from a device.
     *
     * <p>The return code will be ALC_TRUE or ALC_FALSE, indicating success or failure. Failure will occur if all the device's contexts and buffers have not been
     * destroyed. Once closed, the {@code deviceHandle} is invalid.</p>
     *
     * @param device    the device to close
     */
    fun closeDevice(device: ALCdevice): Boolean = ALC10.alcCloseDevice(device.handle)

    // --- [ alcCreateContext ] ---

    /**
     * Creates an AL context.
     *
     * @param device       a valid device
     * @param attrList     null or a zero terminated list of integer pairs composed of valid ALC attribute tokens and requested values. One of:<br><table><tr><td>{@link #ALC_FREQUENCY FREQUENCY}</td><td>{@link #ALC_REFRESH REFRESH}</td><td>{@link #ALC_SYNC SYNC}</td><td>{@link ALC11#ALC_MONO_SOURCES MONO_SOURCES}</td><td>{@link ALC11#ALC_STEREO_SOURCES STEREO_SOURCES}</td></tr></table>
     */
    fun createContext(device: ALCdevice, attrList: IntArray? = null): ALCcontext =
            ALCcontext(Stack { ALC10.nalcCreateContext(device.handle, attrList?.toBuffer(it)?.adr ?: NULL) })

    // --- [ alcMakeContextCurrent ] ---

    /**
     * Makes a context current with respect to OpenAL operation.
     *
     * <p>The context parameter can be {@code NULL} or a valid context pointer. Using {@code NULL} results in no context being current, which is useful when shutting OpenAL down.
     * The operation will apply to the device that the context was created for.</p>
     *
     * <p>For each OS process (usually this means for each application), only one context can be current at any given time. All AL commands apply to the current
     * context. Commands that affect objects shared among contexts (e.g. buffers) have side effects on other contexts.</p>
     *
     * @param context the context to make current
     */
    fun makeContextCurrent(context: ALCcontext): Boolean = ALC10.alcMakeContextCurrent(context.L)

    // --- [ alcProcessContext ] ---

    /**
     * The current context is the only context accessible to state changes by AL commands (aside from state changes affecting shared objects). However,
     * multiple contexts can be processed at the same time. To indicate that a context should be processed (i.e. that internal execution state such as the
     * offset increments are to be performed), the application uses {@code alcProcessContext}.
     *
     * <p>Repeated calls to alcProcessContext are legal, and do not affect a context that is already marked as processing. The default state of a context created
     * by alcCreateContext is that it is processing.</p>
     *
     * @param context the context to mark for processing
     */
    fun processContext(context: ALCcontext) = ALC10.alcProcessContext(context.L)

    // --- [ alcSuspendContext ] ---

    /**
     * The application can suspend any context from processing (including the current one). To indicate that a context should be suspended from processing
     * (i.e. that internal execution state such as offset increments are not to be changed), the application uses {@code alcSuspendContext}.
     *
     * <p>Repeated calls to alcSuspendContext are legal, and do not affect a context that is already marked as suspended.</p>
     *
     * @param context the context to mark as suspended
     */
    fun suspendContext(context: ALCcontext) = ALC10.alcSuspendContext(context.L)

    // --- [ alcDestroyContext ] ---

    /**
     * Destroys a context.
     *
     * <p>The correct way to destroy a context is to first release it using alcMakeCurrent with a {@code NULL} context. Applications should not attempt to destroy a
     * current context – doing so will not work and will result in an ALC_INVALID_OPERATION error. All sources within a context will automatically be deleted
     * during context destruction.</p>
     *
     * @param context the context to destroy
     */
    fun destroyContext(context: ALCcontext) = ALC10.alcDestroyContext(context.L)

    // --- [ alcGetCurrentContext ] ---

    /** Queries for, and obtains a handle to, the current context for the application. If there is no current context, {@code NULL} is returned. */
    val currentContext: ALCcontext
        get() = ALCcontext(ALC10.alcGetCurrentContext())

    // --- [ alcGetContextsDevice ] ---

    /**
     * Queries for, and obtains a handle to, the device of a given context.
     *
     * @param context the context to query
     */
    fun getContextsDevice(context: ALCcontext): ALCdevice = ALCdevice(ALC10.alcGetContextsDevice(context.L))

    // --- [ alcIsExtensionPresent ] ---

    /**
     * Verifies that a given extension is available for the current context and the device it is associated with.
     *
     * <p>Invalid and unsupported string tokens return ALC_FALSE. A {@code NULL} deviceHandle is acceptable. {@code extName} is not case sensitive – the implementation
     * will convert the name to all upper-case internally (and will express extension names in upper-case).</p>
     *
     * @param device       the device to query
     * @param extName      the extension name
     */
    fun isExtensionPresent(device: ALCdevice, extName: CharSequence): Boolean = ALC10.alcIsExtensionPresent(device.handle, extName)

    // --- [ alcGetProcAddress ] ---

    /**
     * Retrieves extension entry points.
     *
     * <p>The application is expected to verify the applicability of an extension or core function entry point before requesting it by name, by use of
     * {@link #alcIsExtensionPresent IsExtensionPresent}.</p>
     *
     * <p>Entry points can be device specific, but are not context specific. Using a {@code NULL} device handle does not guarantee that the entry point is returned,
     * even if available for one of the available devices.</p>
     *
     * @param device the device to query
     * @param funcName     the function name
     */
    fun getProcAddress(device: ALCdevice, funcName: CharSequence): Adr = ALC10.alcGetProcAddress(device.handle, funcName)

    // --- [ alcGetEnumValue ] ---

    /**
     * Returns extension enum values.
     *
     * <p>Enumeration/token values are device independent, but tokens defined for extensions might not be present for a given device. Using a {@code NULL} handle is
     * legal, but only the tokens defined by the AL core are guaranteed. Availability of extension tokens depends on the ALC extension.</p>
     *
     * @param device the device to query
     * @param enumName     the enum name
     */
    fun getEnumValue(device: ALCdevice, enumName: CharSequence): Int = ALC10.alcGetEnumValue(device.handle, enumName)

    // --- [ alcGetError ] ---

    /**
     * Queries ALC errors.
     *
     * <p>ALC uses the same conventions and mechanisms as AL for error handling. In particular, ALC does not use conventions derived from X11 (GLX) or Windows
     * (WGL).</p>
     *
     * <p>Error conditions are specific to the device, and (like AL) a call to alcGetError resets the error state.</p>
     *
     * @param device the device to query
     */
    fun getError(device: ALCdevice): ALCerror = ALCerror(ALC10.alcGetError(device.handle))

    // --- [ alcGetString ] ---

    /**
     * Obtains string value(s) from ALC.
     *
     * <p><b>LWJGL note</b>: Use {@link ALUtil#getStringList} for those tokens that return multiple values.</p>
     *
     * @param device the device to query
     * @param token        the information to query. One of:<br><table><tr><td>{@link #ALC_DEFAULT_DEVICE_SPECIFIER DEFAULT_DEVICE_SPECIFIER}</td><td>{@link #ALC_DEVICE_SPECIFIER DEVICE_SPECIFIER}</td><td>{@link #ALC_EXTENSIONS EXTENSIONS}</td></tr><tr><td>{@link ALC11#ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER CAPTURE_DEFAULT_DEVICE_SPECIFIER}</td><td>{@link ALC11#ALC_CAPTURE_DEVICE_SPECIFIER CAPTURE_DEVICE_SPECIFIER}</td></tr></table>
     */
    fun getString(device: ALCdevice, token: ALCstringQuery): String? = ALC10.alcGetString(device.handle, token.i)

    // --- [ alcGetIntegerv ] ---

    fun getMajorVersion(device: ALCdevice): Int = ALC10.alcGetInteger(device.handle, ALC10.ALC_MAJOR_VERSION)
    fun getMinorVersion(device: ALCdevice): Int = ALC10.alcGetInteger(device.handle, ALC10.ALC_MINOR_VERSION)
    fun getAllAttributes(device: ALCdevice): IntArray = Stack { s ->
        val count = ALC10.alcGetInteger(device.handle, ALC10.ALC_ATTRIBUTES_SIZE)
        val pAttributes = s.mallocInt(count).adr
        ALC10.nalcGetIntegerv(device.handle, ALC10.ALC_ALL_ATTRIBUTES, count, pAttributes)
        IntArray(count) { memGetInt(pAttributes + it * Int.BYTES) }
    }
    fun getCaptureSamples(device: ALCdevice): Int = ALC10.alcGetInteger(device.handle, ALC11.ALC_CAPTURE_SAMPLES)

    // ----------------------------------------------------- ALC11 -----------------------------------------------------


    // --- [ alcCaptureOpenDevice ] ---

    /**
     * Allows the application to connect to a capture device.
     *
     * <p>The {@code device} argument is a null terminated string that requests a certain device or device configuration. If {@code NULL} is specified, the implementation
     * will provide an implementation specific default.</p>
     *
     * @param device the device or device configuration
     * @param frequency  the audio frequency
     * @param format     the audio format
     * @param samples    the number of sample frames to buffer in the AL
     */
    fun captureOpenDevice(device: CharSequence?, frequency: Int, format: Int, samples: Int): ALCdevice =
            ALCdevice(ALC11.alcCaptureOpenDevice(device, frequency, format, samples))

    // --- [ alcCaptureCloseDevice ] ---

    /**
     * Allows the application to disconnect from a capture device.
     *
     * @param device the capture device to close
     */
    fun captureCloseDevice( device: ALCdevice): Boolean = ALC11.alcCaptureCloseDevice(device.handle)

    // --- [ alcCaptureStart ] ---

    /**
     * Starts recording audio on the specific capture device.
     *
     * <p>Once started, the device will record audio to an internal ring buffer, the size of which was specified when opening the device. The application may
     * query the capture device to discover how much data is currently available via the alcGetInteger with the ALC_CAPTURE_SAMPLES token. This will report the
     * number of sample frames currently available.</p>
     *
     * @param device the capture device
     */
    fun captureStart(device: ALCdevice) = ALC11.alcCaptureStart(device.handle)

    // --- [ alcCaptureStop ] ---

    /**
     * Halts audio capturing without closing the capture device.
     *
     * <p>The implementation is encouraged to optimize for this case. The amount of audio samples available after restarting a stopped capture device is reset to
     * zero. The application does not need to stop the capture device to read from it.</p>
     *
     * @param device the capture device
     */
    fun captureStop(device: ALCdevice) = ALC11.alcCaptureStop(device.handle)

    // --- [ alcCaptureSamples ] ---

    /**
     * Obtains captured audio samples from the AL.
     *
     * <p>The implementation may defer conversion and resampling until this point. Requesting more sample frames than are currently available is an error.</p>
     *
     * @param device  the capture device
     * @param buffer  the buffer that will receive the samples. It must be big enough to contain at least {@code samples} sample frames.
     * @param samples the number of sample frames to obtain
     */
    fun captureSamples(device: ALCdevice, buffer: Buffer, samples: Int) = ALC11.nalcCaptureSamples(device.handle, buffer.adr, samples)
}