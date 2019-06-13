package uno.al

import glm_.vec3.Vec3
import gln.vec3Address
import kool.Stack
import org.lwjgl.openal.AL10
import uno.al.identifiers.AlBuffer
import uno.al.identifiers.AlBuffers
import uno.al.identifiers.AlSource
import uno.al.identifiers.AlSources
import java.nio.FloatBuffer
import java.nio.IntBuffer

interface al10 {

    // --- [ alGetError ] ---

    /**
     * Obtains error information.
     *
     * <p>Each detectable error is assigned a numeric code. When an error is detected by AL, a flag is set and the error code is recorded. Further errors, if they
     * occur, do not affect this recorded code. When alGetError is called, the code is returned and the flag is cleared, so that a further error will again
     * record its code. If a call to alGetError returns AL_NO_ERROR then there has been no detectable error since the last call to alGetError (or since the AL
     * was initialized).</p>
     *
     * <p>Error codes can be mapped to strings. The alGetString function returns a pointer to a constant (literal) string that is identical to the identifier used
     * for the enumeration value, as defined in the specification.</p>
     */
    val error: Int
        get() = AL10.alGetError()

    // --- [ alEnable ] ---

//    /**
//     * Enables AL capabilities.
//     *
//     * @param target the capability to enable
//     */
//    @NativeType("ALvoid")
//    public static void alEnable(@NativeType("ALenum") int target)
//    {
//        long __functionAddress = AL . getICD ().alEnable;
//        invokeV(target, __functionAddress);
//    }
//
//    // --- [ alDisable ] ---
//
//    /**
//     * Disables AL capabilities.
//     *
//     * @param target the capability to disable
//     */
//    @NativeType("ALvoid")
//    public static void alDisable(@NativeType("ALenum") int target)
//    {
//        long __functionAddress = AL . getICD ().alDisable;
//        invokeV(target, __functionAddress);
//    }
//
//    // --- [ alIsEnabled ] ---
//
//    /**
//     * Queries whether a given capability is currently enabled or not.
//     *
//     * @param target the capability to query
//     */
//    @NativeType("ALboolean")
//    public static boolean alIsEnabled(@NativeType("ALenum") int target)
//    {
//        long __functionAddress = AL . getICD ().alIsEnabled;
//        return invokeZ(target, __functionAddress);
//    }

    // --- [ alGet* ] ---

    // reified inline

    // --- [ alGetBooleanv ] ---

    /** Unsafe version of: {@link #alGetBooleanv GetBooleanv} */
//    public static void nalGetBooleanv(int paramName, long dest)
//    {
//        long __functionAddress = AL . getICD ().alGetBooleanv;
//        invokePV(paramName, dest, __functionAddress);
//    }
//
//    /**
//     * Pointer version of {@link #alGetBoolean GetBoolean}.
//     *
//     * @param paramName the parameter to query
//     * @param dest      a buffer that will receive the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetBooleanv(@NativeType("ALenum") int paramName, @NativeType("ALboolean *") ByteBuffer dest)
//    {
//        if (CHECKS) {
//            check(dest, 1);
//        }
//        nalGetBooleanv(paramName, memAddress(dest));
//    }
//
//    // --- [ alGetIntegerv ] ---
//
//    /** Unsafe version of: {@link #alGetIntegerv GetIntegerv} */
//    public static void nalGetIntegerv(int paramName, long dest)
//    {
//        long __functionAddress = AL . getICD ().alGetIntegerv;
//        invokePV(paramName, dest, __functionAddress);
//    }
//
//    /**
//     * Pointer version of {@link #alGetInteger GetInteger}.
//     *
//     * @param paramName the parameter to query
//     * @param dest      a buffer that will receive the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetIntegerv(@NativeType("ALenum") int paramName, @NativeType("ALint *") IntBuffer dest)
//    {
//        if (CHECKS) {
//            check(dest, 1);
//        }
//        nalGetIntegerv(paramName, memAddress(dest));
//    }
//
//    // --- [ alGetFloatv ] ---
//
//    /** Unsafe version of: {@link #alGetFloatv GetFloatv} */
//    public static void nalGetFloatv(int paramName, long dest)
//    {
//        long __functionAddress = AL . getICD ().alGetFloatv;
//        invokePV(paramName, dest, __functionAddress);
//    }
//
//    /**
//     * Pointer version of {@link #alGetFloat GetFloat}.
//     *
//     * @param paramName the parameter to query
//     * @param dest      a buffer that will receive the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetFloatv(@NativeType("ALenum") int paramName, @NativeType("ALfloat *") FloatBuffer dest)
//    {
//        if (CHECKS) {
//            check(dest, 1);
//        }
//        nalGetFloatv(paramName, memAddress(dest));
//    }
//
//    // --- [ alGetDoublev ] ---
//
//    /** Unsafe version of: {@link #alGetDoublev GetDoublev} */
//    public static void nalGetDoublev(int paramName, long dest)
//    {
//        long __functionAddress = AL . getICD ().alGetDoublev;
//        invokePV(paramName, dest, __functionAddress);
//    }
//
//    /**
//     * Pointer version of {@link #alGetDouble GetDouble}.
//     *
//     * @param paramName the parameter to query
//     * @param dest      a buffer that will receive the parameter values
//     */
//    @NativeType("ALvoid")
//    public static void alGetDoublev(@NativeType("ALenum") int paramName, @NativeType("ALdouble *") DoubleBuffer dest)
//    {
//        if (CHECKS) {
//            check(dest, 1);
//        }
//        nalGetDoublev(paramName, memAddress(dest));
//    }

    // --- [ alGetString ] ---

    /**
     * Returns the string value of the specified parameter
     *
     * @param paramName the parameter to query. One of:<br><table><tr><td>{@link #AL_VENDOR VENDOR}</td><td>{@link #AL_VERSION VERSION}</td><td>{@link #AL_RENDERER RENDERER}</td><td>{@link #AL_EXTENSIONS EXTENSIONS}</td></tr></table>
     */
    fun getString(paramName: ALstringQuery): String? = AL10.alGetString(paramName.i)

    // --- [ alDistanceModel ] ---

    /**
     * Sets the distance attenuation model.
     *
     * <p>Samples usually use the entire dynamic range of the chosen format/encoding, independent of their real world intensity. For example, a jet engine and a
     * clockwork both will have samples with full amplitude. The application will then have to adjust source gain accordingly to account for relative differences.</p>
     *
     * <p>Source gain is then attenuated by distance. The effective attenuation of a source depends on many factors, among which distance attenuation and source
     * and listener gain are only some of the contributing factors. Even if the source and listener gain exceed 1.0 (amplification beyond the guaranteed
     * dynamic range), distance and other attenuation might ultimately limit the overall gain to a value below 1.0.</p>
     *
     * <p>OpenAL currently supports three modes of operation with respect to distance attenuation, including one that is similar to the IASIG I3DL2 model. The
     * application can choose one of these models (or chooses to disable distance-dependent attenuation) on a per-context basis.</p>
     *
     * @param modelName the distance attenuation model to set. One of:<br><table><tr><td>{@link #AL_INVERSE_DISTANCE INVERSE_DISTANCE}</td><td>{@link #AL_INVERSE_DISTANCE_CLAMPED INVERSE_DISTANCE_CLAMPED}</td><td>{@link AL11#AL_LINEAR_DISTANCE LINEAR_DISTANCE}</td><td>{@link AL11#AL_LINEAR_DISTANCE_CLAMPED LINEAR_DISTANCE_CLAMPED}</td></tr><tr><td>{@link AL11#AL_EXPONENT_DISTANCE EXPONENT_DISTANCE}</td><td>{@link AL11#AL_EXPONENT_DISTANCE_CLAMPED EXPONENT_DISTANCE_CLAMPED}</td><td>{@link #AL_NONE NONE}</td></tr></table>
     */
    fun distanceModel(modelName: DistanceModel) = AL10.alDistanceModel(modelName.i)

    // --- [ alDopplerFactor ] ---

    /**
     * Sets the doppler effect factor.
     *
     * <p>The Doppler Effect depends on the velocities of source and listener relative to the medium, and the propagation speed of sound in that medium. The
     * application might want to emphasize or de-emphasize the Doppler Effect as physically accurate calculation might not give the desired results. The amount
     * of frequency shift (pitch change) is proportional to the speed of listener and source along their line of sight. The Doppler Effect as implemented by
     * OpenAL is described by the formula below. Effects of the medium (air, water) moving with respect to listener and source are ignored.</p>
     *
     * <pre><code>
     * SS: AL_SPEED_OF_SOUND = speed of sound (default value 343.3)
     * DF: AL_DOPPLER_FACTOR = Doppler factor (default 1.0)
     * vls: Listener velocity scalar (scalar, projected on source-to-listener vector)
     * vss: Source velocity scalar (scalar, projected on source-to-listener vector)
     * f: Frequency of sample
     * f': effective Doppler shifted frequency
     *
     * 3D Mathematical representation of vls and vss:
     *
     * Mag(vector) = sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z)
     * DotProduct(v1, v2) = (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z)
     *
     * SL = source to listener vector
     * SV = Source velocity vector
     * LV = Listener velocity vector
     *
     * vls = DotProduct(SL, LV) / Mag(SL)
     * vss = DotProduct(SL, SV) / Mag(SL)
     *
     * Dopper Calculation:
     *
     * vss = min(vss, SS / DF)
     * vls = min(vls, SS / DF)
     *
     * f' = f * (SS - DF * vls) / (SS - DF * vss)</code></pre>
     *
     * <p>The {@code dopplerFactor} is a simple scaling of source and listener velocities to exaggerate or deemphasize the Doppler (pitch) shift resulting from
     * the calculation.</p>
     *
     * @param dopplerFactor the doppler factor
     */
    fun dopplerFactor(dopplerFactor: Float) = AL10.alDopplerFactor(dopplerFactor)

    // --- [ alDopplerVelocity ] ---

    /**
     * Sets the doppler effect propagation velocity.
     *
     * <p>The OpenAL 1.1 Doppler implementation is different than that of OpenAL 1.0, because the older implementation was confusing and not implemented
     * consistently. The new "speed of sound" property makes the 1.1 implementation more intuitive than the old implementation. If your implementation wants to
     * support the AL_DOPPLER_VELOCITY parameter (the alDopplerVelocity call will remain as an entry point so that 1.0 applications can link with a 1.1
     * library), the above formula can be changed to the following:</p>
     *
     * <pre><code>
     * vss = min(vss, (SS * DV)/DF)
     * vls = min(vls, (SS * DV)/DF)
     *
     * f' = f * (SS * DV - DF*vls) / (SS * DV - DF * vss)</code></pre>
     *
     * <p>OpenAL 1.1 programmers would never use AL_DOPPLER_VELOCITY (which defaults to 1.0).</p>
     *
     * @param dopplerVelocity the doppler velocity
     */
    fun dopplerVelocity(dopplerVelocity: Float) = AL10.alDopplerVelocity(dopplerVelocity)

    // --- [ alListener*, alGetListener* ] ---

    var listenerGain: Float
        get() = AL10.alGetListenerf(AL10.AL_GAIN)
        set(value) = AL10.alListenerf(AL10.AL_GAIN, value)

    var listenerPosition: Vec3
        get() = Stack.vec3Address { AL10.nalGetListenerfv(AL10.AL_POSITION, it) }
        set(value) = Stack { AL10.alListenerfv(AL10.AL_POSITION, value.toFloatBuffer(it)) }

    var listenerVelocity: Vec3
        get() = Stack.vec3Address { AL10.nalGetListenerfv(AL10.AL_VELOCITY, it) }
        set(value) = Stack { AL10.alListenerfv(AL10.AL_VELOCITY, value.toFloatBuffer(it)) }

    var listenerOrientation: Pair<Vec3, Vec3>
        get() = Stack {
            val f = it.mallocFloat(Vec3.length * 2)
            AL10.alGetListenerfv(AL10.AL_ORIENTATION, f)
            Vec3(f) to Vec3(f, Vec3.length)
        }
        set(value) = Stack {
            val f = it.mallocFloat(Vec3.length * 2)
            value.first to f
            value.second.to(f, Vec3.length)
            AL10.alListenerfv(AL10.AL_ORIENTATION, f)
        }

    // --- [ alGenSources ] ---

    /**
     * Requests a number of source names.
     *
     * @param sources the buffer that will receive the source names
     */
    fun genSources(sources: AlSources) = AL10.nalGenSources(sources.rem, sources.adr)

    /**
     * Requests a number of source names.
     *
     * @param sources the buffer that will receive the source names
     */
    fun genSources(size: Int) = AlSources(size).apply(::genSources)

    /** Requests a number of source names. */
    fun genSources(): AlSource = AlSource(AL10.alGenSources())

    // --- [ alDeleteSources ] ---

    /**
     * Requests the deletion of a number of sources.
     *
     * @param sources the sources to delete
     */
    fun deleteSources(sources: AlSources) = AL10.nalDeleteSources(sources.rem, sources.adr)

    /** Requests the deletion of a number of sources. */
    fun deleteSources(source: AlSource) = AL10.alDeleteSources(source.name)

    // --- [ alIsSource ] ---

    /**
     * Verifies whether the specified object name is a source name.
     *
     * @param sourceName a value that may be a source name
     */
    fun isSource(source: AlSource) = AL10.alIsSource(source.name)

    // --- [ alSourcef ] ---

    /**
     * Sets the float value of a source parameter.
     *
     * @param source the source to modify
     * @param param  the parameter to modify. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param value  the parameter value
     */
    fun source(source: AlSource, param: SourceParam, value: Float) =
            AL10.alSourcef(source.name, param.i, value)

    // --- [ alSource3f ] ---

    /**
     * Sets the 3 dimensional values of a source parameter.
     *
     * @param source the source to modify
     * @param param  the parameter to modify. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param v1     the first parameter value
     * @param v2     the second parameter value
     * @param v3     the third parameter value
     */
    fun source(source: AlSource, param: SourceParam, v1: Float, v2: Float, v3: Float) =
            AL10.alSource3f(source.name, param.i, v1, v2, v3)

    // --- [ alSourcefv ] ---

    /**
     * Pointer version of {@link #alSourcef Sourcef}.
     *
     * @param source the source to modify
     * @param param  the parameter to modify
     * @param values the parameter values
     */
    fun source(source: AlSource, param: SourceParam, values: FloatBuffer) =
            AL10.alSourcefv(source.name, param.i, values)

    // --- [ alSourcei ] ---

    /**
     * Integer version of {@link #alSourcef Sourcef}.
     *
     * @param source the source to modify
     * @param param  the parameter to modify
     * @param value  the parameter value
     */
    fun source(source: AlSource, param: SourceParam, value: Int) =
            AL10.alSourcei(source.name, param.i, value)

    // --- [ alGetSource* ] ---

    // inline reified

    // --- [ alGetSourcefv ] ---

    /**
     * Returns the float values of the specified source parameter.
     *
     * @param source the source to query
     * @param param  the parameter to query. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param values the parameter values
     */
    fun getSource(source: AlSource, param: SourceParam, values: FloatBuffer) =
            AL10.alGetSourcefv(source.name, param.i, values)

    // --- [ alGetSourceiv ] ---

    /**
     * Returns the integer values of the specified source parameter.
     *
     * @param source the source to query
     * @param param  the parameter to query. One of:<br><table><tr><td>{@link #AL_CONE_INNER_ANGLE CONE_INNER_ANGLE}</td><td>{@link #AL_CONE_OUTER_ANGLE CONE_OUTER_ANGLE}</td><td>{@link #AL_PITCH PITCH}</td><td>{@link #AL_DIRECTION DIRECTION}</td><td>{@link #AL_LOOPING LOOPING}</td><td>{@link #AL_BUFFER BUFFER}</td><td>{@link #AL_SOURCE_STATE SOURCE_STATE}</td></tr><tr><td>{@link #AL_CONE_OUTER_GAIN CONE_OUTER_GAIN}</td><td>{@link #AL_SOURCE_TYPE SOURCE_TYPE}</td><td>{@link #AL_POSITION POSITION}</td><td>{@link #AL_VELOCITY VELOCITY}</td><td>{@link #AL_GAIN GAIN}</td><td>{@link #AL_REFERENCE_DISTANCE REFERENCE_DISTANCE}</td><td>{@link #AL_ROLLOFF_FACTOR ROLLOFF_FACTOR}</td></tr><tr><td>{@link #AL_MAX_DISTANCE MAX_DISTANCE}</td></tr></table>
     * @param values the parameter values
     */
    fun getSource(source: AlSource, param: SourceParam, values: IntBuffer) =
            AL10.alGetSourceiv(source.name, param.i, values)

    // --- [ alSourceQueueBuffers ] ---

    /**
     * Queues up one or multiple buffer names to the specified source.
     *
     * <p>The buffers will be queued in the sequence in which they appear in the array. This command is legal on a source in any playback state (to allow for
     * streaming, queuing has to be possible on a AL_PLAYING source). All buffers in a queue must have the same format and attributes, with the exception of
     * the {@code NULL} buffer (i.e., 0) which can always be queued.</p>
     *
     * @param source  the target source
     * @param buffers the buffer names
     */
    fun sourceQueueBuffers(source: AlSource, buffers: AlBuffers) =
            AL10.nalSourceQueueBuffers(source.name, buffers.rem, buffers.adr)

    /**
     * Queues up one or multiple buffer names to the specified source.
     *
     * <p>The buffers will be queued in the sequence in which they appear in the array. This command is legal on a source in any playback state (to allow for
     * streaming, queuing has to be possible on a AL_PLAYING source). All buffers in a queue must have the same format and attributes, with the exception of
     * the {@code NULL} buffer (i.e., 0) which can always be queued.</p>
     *
     * @param source the target source
     */
    fun sourceQueueBuffers(source: AlSource, buffer: AlBuffer) =
            AL10.alSourceQueueBuffers(source.name, buffer.name)

    // --- [ alSourceUnqueueBuffers ] ---

    /**
     * Removes a number of buffer entries that have finished processing, in the order of apperance, from the queue of the specified source.
     *
     * <p>Once a queue entry for a buffer has been appended to a queue and is pending processing, it should not be changed. Removal of a given queue entry is not
     * possible unless either the source is stopped (in which case then entire queue is considered processed), or if the queue entry has already been processed
     * (AL_PLAYING or AL_PAUSED source). A playing source will enter the AL_STOPPED state if it completes playback of the last buffer in its queue (the same
     * behavior as when a single buffer has been attached to a source and has finished playback).</p>
     *
     * @param source  the target source
     * @param buffers the buffer names
     */
    fun sourceUnqueueBuffers(source: AlSource, buffers: AlBuffers) =
            AL10.nalSourceUnqueueBuffers(source.name, buffers.rem, buffers.adr)

    /**
     * Removes a number of buffer entries that have finished processing, in the order of apperance, from the queue of the specified source.
     *
     * <p>Once a queue entry for a buffer has been appended to a queue and is pending processing, it should not be changed. Removal of a given queue entry is not
     * possible unless either the source is stopped (in which case then entire queue is considered processed), or if the queue entry has already been processed
     * (AL_PLAYING or AL_PAUSED source). A playing source will enter the AL_STOPPED state if it completes playback of the last buffer in its queue (the same
     * behavior as when a single buffer has been attached to a source and has finished playback).</p>
     *
     * @param source the target source
     */
    fun sourceUnqueueBuffers(source: AlSource, buffer: AlBuffer) =
            Stack.intAddress(buffer.name) { AL10.nalSourceUnqueueBuffers(source.name, 1, it) }

    // --- [ alSourcePlay ] ---

    /**
     * Sets the source state to AL_PLAYING.
     *
     * <p>alSourcePlay applied to an AL_INITIAL source will promote the source to AL_PLAYING, thus the data found in the buffer will be fed into the processing,
     * starting at the beginning. alSourcePlay applied to a AL_PLAYING source will restart the source from the beginning. It will not affect the configuration,
     * and will leave the source in AL_PLAYING state, but reset the sampling offset to the beginning. alSourcePlay applied to a AL_PAUSED source will resume
     * processing using the source state as preserved at the alSourcePause operation. alSourcePlay applied to a AL_STOPPED source will propagate it to
     * AL_INITIAL then to AL_PLAYING immediately.</p>
     *
     * @param source the source to play
     */
    fun sourcePlay(source: AlSource) =
            AL10.alSourcePlay(source.name)

    // --- [ alSourcePause ] ---

    /**
     * Sets the source state to AL_PAUSED.
     *
     * <p>alSourcePause applied to an AL_INITIAL source is a legal NOP. alSourcePause applied to a AL_PLAYING source will change its state to AL_PAUSED. The
     * source is exempt from processing, its current state is preserved. alSourcePause applied to a AL_PAUSED source is a legal NOP. alSourcePause applied to a
     * AL_STOPPED source is a legal NOP.</p>
     *
     * @param source the source to pause
     */
    fun sourcePause(source: AlSource) =
            AL10.alSourcePause(source.name)

    // --- [ alSourceStop ] ---

    /**
     * Sets the source state to AL_STOPPED.
     *
     * <p>alSourceStop applied to an AL_INITIAL source is a legal NOP. alSourceStop applied to a AL_PLAYING source will change its state to AL_STOPPED. The source
     * is exempt from processing, its current state is preserved. alSourceStop applied to a AL_PAUSED source will change its state to AL_STOPPED, with the same
     * consequences as on a AL_PLAYING source. alSourceStop applied to a AL_STOPPED source is a legal NOP.</p>
     *
     * @param source the source to stop
     */
    fun sourceStop(source: AlSource) =
            AL10.alSourceStop(source.name)

    // --- [ alSourceRewind ] ---

    /**
     * Sets the source state to AL_INITIAL.
     *
     * <p>alSourceRewind applied to an AL_INITIAL source is a legal NOP. alSourceRewind applied to a AL_PLAYING source will change its state to AL_STOPPED then
     * AL_INITIAL. The source is exempt from processing: its current state is preserved, with the exception of the sampling offset, which is reset to the
     * beginning. alSourceRewind applied to a AL_PAUSED source will change its state to AL_INITIAL, with the same consequences as on a AL_PLAYING source.
     * alSourceRewind applied to an AL_STOPPED source promotes the source to AL_INITIAL, resetting the sampling offset to the beginning.</p>
     *
     * @param source the source to rewind
     */
    fun sourceRewind(source: AlSource) =
            AL10.alSourceRewind(source.name)

    // --- [ alSourcePlayv ] ---

    /**
     * Pointer version of {@link #alSourcePlay SourcePlay}.
     *
     * @param sources the sources to play
     */
    fun sourcePlay(sources: AlSources) =
            AL10.nalSourcePlayv(sources.rem, sources.adr)

    // --- [ alSourcePausev ] ---

    /**
     * Pointer version of {@link #alSourcePause SourcePause}.
     *
     * @param sources the sources to pause
     */
    fun sourcePause(sources: AlSources) =
            AL10.nalSourcePausev(sources.rem, sources.adr)

    // --- [ alSourceStopv ] ---

    /**
     * Pointer version of {@link #alSourceStop SourceStop}.
     *
     * @param sources the sources to stop
     */
    fun sourceStop(sources: AlSources) =
            AL10.nalSourceStopv(sources.rem, sources.adr)

    // --- [ alSourceRewindv ] ---

    /**
     * Pointer version of {@link #alSourceRewind SourceRewind}.
     *
     * @param sources the sources to rewind
     */
    fun sourceRewind(sources: AlSources) =
            AL10.nalSourceRewindv(sources.rem, sources.adr)

    // --- [ alGenBuffers ] ---

    /**
     * Requests a number of buffer names.
     *
     * @param buffers the buffer that will receive the buffer names
     */
    fun genBuffers(buffers: AlBuffers) =
            AL10.nalGenBuffers(buffers.rem, buffers.adr)

    /** Requests a number of buffer names. */
    fun genBuffers(): AlBuffer
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer bufferNames = stack . callocInt (1);
            nalGenBuffers(1, memAddress(bufferNames));
            return bufferNames.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alDeleteBuffers ] ---

    /**
     * Unsafe version of: {@link #alDeleteBuffers DeleteBuffers}
     *
     * @param n the number of buffers to delete
     */
    public static void nalDeleteBuffers(int n, long bufferNames)
    {
        long __functionAddress = AL . getICD ().alDeleteBuffers;
        invokePV(n, bufferNames, __functionAddress);
    }

    /**
     * Requests the deletion of a number of buffers.
     *
     * @param bufferNames the buffers to delete
     */
    @NativeType("ALvoid")
    public static void alDeleteBuffers(@NativeType("ALuint const *") IntBuffer bufferNames)
    {
        nalDeleteBuffers(bufferNames.remaining(), memAddress(bufferNames));
    }

    /** Requests the deletion of a number of buffers. */
    @NativeType("ALvoid")
    public static void alDeleteBuffers(@NativeType("ALuint const *") int bufferName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer bufferNames = stack . ints (bufferName);
            nalDeleteBuffers(1, memAddress(bufferNames));
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alIsBuffer ] ---

    /**
     * Verifies whether the specified object name is a buffer name.
     *
     * @param bufferName a value that may be a buffer name
     */
    @NativeType("ALboolean")
    public static boolean alIsBuffer(@NativeType("ALuint") int bufferName)
    {
        long __functionAddress = AL . getICD ().alIsBuffer;
        return invokeZ(bufferName, __functionAddress);
    }

    // --- [ alGetBufferf ] ---

    /** Unsafe version of: {@link #alGetBufferf GetBufferf} */
    public static void nalGetBufferf(int bufferName, int paramName, long value)
    {
        long __functionAddress = AL . getICD ().alGetBufferf;
        invokePV(bufferName, paramName, value, __functionAddress);
    }

    /**
     * Returns the float value of the specified buffer parameter.
     *
     * @param bufferName the buffer to query
     * @param paramName  the parameter to query. One of:<br><table><tr><td>{@link #AL_FREQUENCY FREQUENCY}</td><td>{@link #AL_BITS BITS}</td><td>{@link #AL_CHANNELS CHANNELS}</td><td>{@link #AL_SIZE SIZE}</td></tr></table>
     * @param value      the parameter value
     */
    @NativeType("ALvoid")
    public static void alGetBufferf(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int paramName, @NativeType("ALfloat *") FloatBuffer value)
    {
        if (CHECKS) {
            check(value, 1);
        }
        nalGetBufferf(bufferName, paramName, memAddress(value));
    }

    /**
     * Returns the float value of the specified buffer parameter.
     *
     * @param bufferName the buffer to query
     * @param paramName  the parameter to query. One of:<br><table><tr><td>{@link #AL_FREQUENCY FREQUENCY}</td><td>{@link #AL_BITS BITS}</td><td>{@link #AL_CHANNELS CHANNELS}</td><td>{@link #AL_SIZE SIZE}</td></tr></table>
     */
    @NativeType("ALvoid")
    public static float alGetBufferf(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int paramName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            FloatBuffer value = stack . callocFloat (1);
            nalGetBufferf(bufferName, paramName, memAddress(value));
            return value.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alGetBufferi ] ---

    /** Unsafe version of: {@link #alGetBufferi GetBufferi} */
    public static void nalGetBufferi(int bufferName, int paramName, long value)
    {
        long __functionAddress = AL . getICD ().alGetBufferi;
        invokePV(bufferName, paramName, value, __functionAddress);
    }

    /**
     * Returns the integer value of the specified buffer parameter.
     *
     * @param bufferName the buffer to query
     * @param paramName  the parameter to query. One of:<br><table><tr><td>{@link #AL_FREQUENCY FREQUENCY}</td><td>{@link #AL_BITS BITS}</td><td>{@link #AL_CHANNELS CHANNELS}</td><td>{@link #AL_SIZE SIZE}</td></tr></table>
     * @param value      the parameter value
     */
    @NativeType("ALvoid")
    public static void alGetBufferi(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int paramName, @NativeType("ALint *") IntBuffer value)
    {
        if (CHECKS) {
            check(value, 1);
        }
        nalGetBufferi(bufferName, paramName, memAddress(value));
    }

    /**
     * Returns the integer value of the specified buffer parameter.
     *
     * @param bufferName the buffer to query
     * @param paramName  the parameter to query. One of:<br><table><tr><td>{@link #AL_FREQUENCY FREQUENCY}</td><td>{@link #AL_BITS BITS}</td><td>{@link #AL_CHANNELS CHANNELS}</td><td>{@link #AL_SIZE SIZE}</td></tr></table>
     */
    @NativeType("ALvoid")
    public static int alGetBufferi(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int paramName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            IntBuffer value = stack . callocInt (1);
            nalGetBufferi(bufferName, paramName, memAddress(value));
            return value.get(0);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alBufferData ] ---

    /**
     * Unsafe version of: {@link #alBufferData BufferData}
     *
     * @param size the data buffer size, in bytes
     */
    public static void nalBufferData(int bufferName, int format, long data , int size, int frequency)
    {
        long __functionAddress = AL . getICD ().alBufferData;
        invokePV(bufferName, format, data, size, frequency, __functionAddress);
    }

    /**
     * Sets the sample data of the specified buffer.
     *
     * <p>The data specified is copied to an internal software, or if possible, hardware buffer. The implementation is free to apply decompression, conversion,
     * resampling, and filtering as needed.</p>
     *
     * <p>8-bit data is expressed as an unsigned value over the range 0 to 255, 128 being an audio output level of zero.</p>
     *
     * <p>16-bit data is expressed as a signed value over the range -32768 to 32767, 0 being an audio output level of zero. Byte order for 16-bit values is
     * determined by the native format of the CPU.</p>
     *
     * <p>Stereo data is expressed in an interleaved format, left channel sample followed by the right channel sample.</p>
     *
     * <p>Buffers containing audio data with more than one channel will be played without 3D spatialization features – these formats are normally used for
     * background music.</p>
     *
     * @param bufferName the buffer to modify
     * @param format     the data format. One of:<br><table><tr><td>{@link #AL_FORMAT_MONO8 FORMAT_MONO8}</td><td>{@link #AL_FORMAT_MONO16 FORMAT_MONO16}</td><td>{@link #AL_FORMAT_STEREO8 FORMAT_STEREO8}</td><td>{@link #AL_FORMAT_STEREO16 FORMAT_STEREO16}</td></tr></table>
     * @param data       the sample data
     * @param frequency  the data frequency
     */
    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int format, @NativeType("ALvoid const *") ByteBuffer data , @NativeType("ALsizei") int frequency)
    {
        nalBufferData(bufferName, format, memAddress(data), data.remaining(), frequency);
    }

    /**
     * Sets the sample data of the specified buffer.
     *
     * <p>The data specified is copied to an internal software, or if possible, hardware buffer. The implementation is free to apply decompression, conversion,
     * resampling, and filtering as needed.</p>
     *
     * <p>8-bit data is expressed as an unsigned value over the range 0 to 255, 128 being an audio output level of zero.</p>
     *
     * <p>16-bit data is expressed as a signed value over the range -32768 to 32767, 0 being an audio output level of zero. Byte order for 16-bit values is
     * determined by the native format of the CPU.</p>
     *
     * <p>Stereo data is expressed in an interleaved format, left channel sample followed by the right channel sample.</p>
     *
     * <p>Buffers containing audio data with more than one channel will be played without 3D spatialization features – these formats are normally used for
     * background music.</p>
     *
     * @param bufferName the buffer to modify
     * @param format     the data format. One of:<br><table><tr><td>{@link #AL_FORMAT_MONO8 FORMAT_MONO8}</td><td>{@link #AL_FORMAT_MONO16 FORMAT_MONO16}</td><td>{@link #AL_FORMAT_STEREO8 FORMAT_STEREO8}</td><td>{@link #AL_FORMAT_STEREO16 FORMAT_STEREO16}</td></tr></table>
     * @param data       the sample data
     * @param frequency  the data frequency
     */
    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int format, @NativeType("ALvoid const *") ShortBuffer data , @NativeType("ALsizei") int frequency)
    {
        nalBufferData(bufferName, format, memAddress(data), data.remaining() < < 1, frequency);
    }

    /**
     * Sets the sample data of the specified buffer.
     *
     * <p>The data specified is copied to an internal software, or if possible, hardware buffer. The implementation is free to apply decompression, conversion,
     * resampling, and filtering as needed.</p>
     *
     * <p>8-bit data is expressed as an unsigned value over the range 0 to 255, 128 being an audio output level of zero.</p>
     *
     * <p>16-bit data is expressed as a signed value over the range -32768 to 32767, 0 being an audio output level of zero. Byte order for 16-bit values is
     * determined by the native format of the CPU.</p>
     *
     * <p>Stereo data is expressed in an interleaved format, left channel sample followed by the right channel sample.</p>
     *
     * <p>Buffers containing audio data with more than one channel will be played without 3D spatialization features – these formats are normally used for
     * background music.</p>
     *
     * @param bufferName the buffer to modify
     * @param format     the data format. One of:<br><table><tr><td>{@link #AL_FORMAT_MONO8 FORMAT_MONO8}</td><td>{@link #AL_FORMAT_MONO16 FORMAT_MONO16}</td><td>{@link #AL_FORMAT_STEREO8 FORMAT_STEREO8}</td><td>{@link #AL_FORMAT_STEREO16 FORMAT_STEREO16}</td></tr></table>
     * @param data       the sample data
     * @param frequency  the data frequency
     */
    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int format, @NativeType("ALvoid const *") IntBuffer data , @NativeType("ALsizei") int frequency)
    {
        nalBufferData(bufferName, format, memAddress(data), data.remaining() < < 2, frequency);
    }

    /**
     * Sets the sample data of the specified buffer.
     *
     * <p>The data specified is copied to an internal software, or if possible, hardware buffer. The implementation is free to apply decompression, conversion,
     * resampling, and filtering as needed.</p>
     *
     * <p>8-bit data is expressed as an unsigned value over the range 0 to 255, 128 being an audio output level of zero.</p>
     *
     * <p>16-bit data is expressed as a signed value over the range -32768 to 32767, 0 being an audio output level of zero. Byte order for 16-bit values is
     * determined by the native format of the CPU.</p>
     *
     * <p>Stereo data is expressed in an interleaved format, left channel sample followed by the right channel sample.</p>
     *
     * <p>Buffers containing audio data with more than one channel will be played without 3D spatialization features – these formats are normally used for
     * background music.</p>
     *
     * @param bufferName the buffer to modify
     * @param format     the data format. One of:<br><table><tr><td>{@link #AL_FORMAT_MONO8 FORMAT_MONO8}</td><td>{@link #AL_FORMAT_MONO16 FORMAT_MONO16}</td><td>{@link #AL_FORMAT_STEREO8 FORMAT_STEREO8}</td><td>{@link #AL_FORMAT_STEREO16 FORMAT_STEREO16}</td></tr></table>
     * @param data       the sample data
     * @param frequency  the data frequency
     */
    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int bufferName, @NativeType("ALenum") int format, @NativeType("ALvoid const *") FloatBuffer data , @NativeType("ALsizei") int frequency)
    {
        nalBufferData(bufferName, format, memAddress(data), data.remaining() < < 2, frequency);
    }

    // --- [ alGetEnumValue ] ---

    /** Unsafe version of: {@link #alGetEnumValue GetEnumValue} */
    public static int nalGetEnumValue(long enumName)
    {
        long __functionAddress = AL . getICD ().alGetEnumValue;
        return invokePI(enumName, __functionAddress);
    }

    /**
     * Returns the enumeration value of the specified enum.
     *
     * @param enumName the enum name
     */
    @NativeType("ALuint")
    public static int alGetEnumValue(@NativeType("ALchar const *") ByteBuffer enumName)
    {
        if (CHECKS) {
            checkNT1(enumName);
        }
        return nalGetEnumValue(memAddress(enumName));
    }

    /**
     * Returns the enumeration value of the specified enum.
     *
     * @param enumName the enum name
     */
    @NativeType("ALuint")
    public static int alGetEnumValue(@NativeType("ALchar const *") CharSequence enumName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            stack.nASCII(enumName, true);
            long enumNameEncoded = stack . getPointerAddress ();
            return nalGetEnumValue(enumNameEncoded);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alGetProcAddress ] ---

    /** Unsafe version of: {@link #alGetProcAddress GetProcAddress} */
    public static long nalGetProcAddress(long funcName)
    {
        long __functionAddress = AL . getICD ().alGetProcAddress;
        return invokePP(funcName, __functionAddress);
    }

    /**
     * Retrieves extension entry points.
     *
     * <p>Returns {@code NULL} if no entry point with the name funcName can be found. Implementations are free to return {@code NULL} if an entry point is present, but not
     * applicable for the current context. However the specification does not guarantee this behavior.</p>
     *
     * <p>Applications can use alGetProcAddress to obtain core API entry points, not just extensions. This is the recommended way to dynamically load and unload
     * OpenAL DLL's as sound drivers.</p>
     *
     * @param funcName the function name
     */
    @NativeType("void *")
    public static long alGetProcAddress(@NativeType("ALchar const *") ByteBuffer funcName)
    {
        if (CHECKS) {
            checkNT1(funcName);
        }
        return nalGetProcAddress(memAddress(funcName));
    }

    /**
     * Retrieves extension entry points.
     *
     * <p>Returns {@code NULL} if no entry point with the name funcName can be found. Implementations are free to return {@code NULL} if an entry point is present, but not
     * applicable for the current context. However the specification does not guarantee this behavior.</p>
     *
     * <p>Applications can use alGetProcAddress to obtain core API entry points, not just extensions. This is the recommended way to dynamically load and unload
     * OpenAL DLL's as sound drivers.</p>
     *
     * @param funcName the function name
     */
    @NativeType("void *")
    public static long alGetProcAddress(@NativeType("ALchar const *") CharSequence funcName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            stack.nASCII(funcName, true);
            long funcNameEncoded = stack . getPointerAddress ();
            return nalGetProcAddress(funcNameEncoded);
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ alIsExtensionPresent ] ---

    /** Unsafe version of: {@link #alIsExtensionPresent IsExtensionPresent} */
    public static boolean nalIsExtensionPresent(long extName)
    {
        long __functionAddress = AL . getICD ().alIsExtensionPresent;
        return invokePZ(extName, __functionAddress);
    }

    /**
     * Verifies that a given extension is available for the current context and the device it is associated with.
     *
     * <p>Invalid and unsupported string tokens return ALC_FALSE. {@code extName} is not case sensitive – the implementation will convert the name to all
     * upper-case internally (and will express extension names in upper-case).</p>
     *
     * @param extName the extension name
     */
    @NativeType("ALCboolean")
    public static boolean alIsExtensionPresent(@NativeType("ALchar const *") ByteBuffer extName)
    {
        if (CHECKS) {
            checkNT1(extName);
        }
        return nalIsExtensionPresent(memAddress(extName));
    }

    /**
     * Verifies that a given extension is available for the current context and the device it is associated with.
     *
     * <p>Invalid and unsupported string tokens return ALC_FALSE. {@code extName} is not case sensitive – the implementation will convert the name to all
     * upper-case internally (and will express extension names in upper-case).</p>
     *
     * @param extName the extension name
     */
    @NativeType("ALCboolean")
    public static boolean alIsExtensionPresent(@NativeType("ALchar const *") CharSequence extName)
    {
        MemoryStack stack = stackGet (); int stackPointer = stack . getPointer ();
        try {
            stack.nASCII(extName, true);
            long extNameEncoded = stack . getPointerAddress ();
            return nalIsExtensionPresent(extNameEncoded);
        } finally {
            stack.setPointer(stackPointer);
        }
    }
}