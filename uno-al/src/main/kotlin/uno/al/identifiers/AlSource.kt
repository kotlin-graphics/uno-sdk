package uno.al.identifiers

import glm_.bool
import glm_.vec3.Vec3
import gln.vec3Address
import kool.*
import org.lwjgl.openal.AL10
import uno.al.SourceState
import uno.al.SourceType
import uno.al.al
import java.nio.IntBuffer

inline class AlSource(val name: Int) {

    val isValid: Boolean
        get() = AL10.alIsSource(name)

    val isInvalid: Boolean
        get() = !AL10.alIsSource(name)

    fun delete() = AL10.alDeleteSources(name)

    // --- [ alGetSource* ] ---

    val coneOuterAngle: Float
        get() = AL10.alGetSourcef(name, AL10.AL_CONE_OUTER_ANGLE)
    val pitch: Float
        get() = AL10.alGetSourcef(name, AL10.AL_PITCH)
    val coneOuterGain: Float
        get() = AL10.alGetSourcef(name, AL10.AL_CONE_OUTER_GAIN)
    val gain: Float
        get() = AL10.alGetSourcef(name, AL10.AL_GAIN)
    val referenceDistance: Float
        get() = AL10.alGetSourcef(name, AL10.AL_REFERENCE_DISTANCE)
    val rolloffFactor: Float
        get() = AL10.alGetSourcef(name, AL10.AL_ROLLOFF_FACTOR)
    val maxDistance: Float
        get() = AL10.alGetSourcef(name, AL10.AL_MAX_DISTANCE)

    val direction: Vec3
        get() = Stack.vec3Address { AL10.nalGetSourcefv(name, AL10.AL_DIRECTION, it) }
    val position: Vec3
        get() = Stack.vec3Address { AL10.nalGetSourcefv(name, AL10.AL_POSITION, it) }
    val velocity: Vec3
        get() = Stack.vec3Address { AL10.nalGetSourcefv(name, AL10.AL_VELOCITY, it) }

    val looping: Boolean
        get() = AL10.alGetSourcei(name, AL10.AL_LOOPING).bool

    val buffer: AlBuffer
        get() = AlBuffer(AL10.alGetSourcei(name, AL10.AL_BUFFER))

    val sourceState: SourceState
        get() = SourceState(AL10.alGetSourcei(name, AL10.AL_SOURCE_STATE))

    val sourceType: SourceType
        get() = SourceType(AL10.alGetSourcei(name, AL10.AL_SOURCE_TYPE))

    companion object {
        fun gen(): AlSource = AlSource(AL10.alGenSources())
    }
}

fun AlSources(size: Int) = AlSources(IntBuffer(size))

inline class AlSources(val names: IntBuffer) {

    val rem: Int
        get() = names.rem

    val adr: Adr
        get() = names.adr

    fun gen() = AL10.nalGenSources(names.rem, names.adr)

    fun delete() = AL10.nalDeleteSources(names.rem, names.adr)

    companion object {

        fun gen(size: Int) = al.genSources(size)
    }
}