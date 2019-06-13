package uno.al

import glm_.vec3.Vec3
import gln.vec3Address
import kool.FloatBuffer
import kool.Stack
import org.lwjgl.openal.AL10
import org.lwjgl.openal.ALC10
import uno.al.identifiers.ALCcontext
import uno.al.identifiers.ALCdevice
import uno.al.identifiers.AlSource
import java.nio.FloatBuffer

object al : al10 {

    /* InitAL opens a device and sets up a context using default attributes, making
     * the program ready to call OpenAL functions. */
    fun init(args: Array<String>? = null): Boolean {

        var device = ALCdevice.NULL

        // Open and initialize a device
        if (args?.isNotEmpty() == true && args[0] == "-device") {
            device = ALCdevice.open(args[1])
            if (device.isInvalid)
                System.err.println("Failed to open \"${args[1]}\", trying default")
        }
        if (device.isInvalid)
            device = ALCdevice.open()
        if (device.isInvalid) {
            System.err.println("Could not open a device!")
            return false
        }

        val ctx = device.createContext()
        if (ctx.isInvalid || !ctx.makeCurrent()) {
            if (ctx.isValid)
                ctx.destroy()
            device.close()
            System.err.println("Could not set a context!")
            return false
        }

        var name: String? = null
        if (device isExtensionPresent "ALC_ENUMERATE_ALL_EXT")
            name = device getString ALCstringQuery.ALL_DEVICES_SPECIFIER
        if (name == null || device.error != ALCerror.NO_ERROR)
            name = device getString ALCstringQuery.DEVICE_SPECIFIER
        println("Opened \"$name\"")

        return true
    }

    /* CloseAL closes the device belonging to the current context, and destroys the context. */
    fun close() {

        val ctx = ALCcontext.current
        if (ctx.isInvalid) return

        val device = ctx.device

        ctx.unmakeCurrent()
        ctx.destroy()
        device.close()
    }

    // --- [ alGet* ] ---

    inline fun <reified N : Number> get(paramName: Int): N = when (N::class) {
        Boolean::class -> AL10.alGetBoolean(paramName)
        Int::class -> AL10.alGetInteger(paramName)
        Float::class -> AL10.alGetFloat(paramName)
        Double::class -> AL10.alGetDouble(paramName)
        else -> error("invalid")
    } as N

    // --- [ alGetSourcef ] ---

    inline fun <reified T> getSource(source: AlSource, param: SourceParam): T = Stack { s ->
        when (T::class) {
            Float::class -> AL10.alGetSourcef(source.name, param.i)
            Int::class -> AL10.alGetSourcei(source.name, param.i)
            Vec3::class -> s.vec3Address { AL10.nalGetSourcef(source.name, param.i, it) }
            else -> error("[al::getSource] Invalid type")
        } as T
    }

    // --- [ alGetSourcei ] ---

    /** Unsafe version of: {@link #alGetSourcei GetSourcei} */
    public static void nalGetSourcei(int source, int param, long value)
    {
        long __functionAddress = AL . getICD ().alGetSourcei;
        invokePV(source, param, value, __functionAddress);
    }

    /**
     * Returns the integer value of the specified source parameter.
     *
     * @param source the source to query
     * @param param  the parameter to query. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param value  the parameter value
     */
    @NativeType("ALvoid")
    public static void alGetSourcei(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer value)
    {
        if (CHECKS) {
            check(value, 1);
        }
        nalGetSourcei(source, param, memAddress(value));
    }

    /**
     * Returns the integer value of the specified source parameter.
     *
     * @param source the source to query
     * @param param  the parameter to query. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     */
    @NativeType("ALvoid")
    public static int alGetSourcei(@NativeType("ALuint") int source, @NativeType("ALenum") int param)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer value = stack . callocInt (1);
            nalGetSourcei(source, param, memAddress(value));
            return value.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alGetSourceiv ] ---

    /** Unsafe version of: {@link #alGetSourceiv GetSourceiv} */
    public static void nalGetSourceiv(int source, int param, long values)
    {
        long __functionAddress = AL . getICD ().alGetSourceiv;
        invokePV(source, param, values, __functionAddress);
    }

    /**
     * Returns the integer values of the specified source parameter.
     *
     * @param source the source to query
     * @param param  the parameter to query. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param values the parameter values
     */
    @NativeType("ALvoid")
    public static void alGetSourceiv(@NativeType("ALuint") int source, @NativeType("ALenum") int param, @NativeType("ALint *") IntBuffer values)
    {
        if (CHECKS) {
            check(values, 1);
        }
        nalGetSourceiv(source, param, memAddress(values));
    }

    fun openDevice() = ALC10.alcOpenDevice()
}