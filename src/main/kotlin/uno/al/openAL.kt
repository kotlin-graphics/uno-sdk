package uno.al

import kool.adr
import kool.free
import kool.remSize
import kool.stak
import org.lwjgl.openal.ALC10.*
import org.lwjgl.openal.ALC10.alcMakeContextCurrent
import org.lwjgl.openal.AL
import org.lwjgl.openal.AL10.*
import org.lwjgl.openal.ALC
import org.lwjgl.openal.ALCCapabilities
import org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename
import org.lwjgl.system.MemoryStack.stackGet
import org.lwjgl.system.MemoryStack.stackPush
import java.nio.Buffer
import java.nio.IntBuffer
import java.nio.ShortBuffer

fun main(args: Array<String>) {

    val device = ALCdevice.open()

    val context = device.createContext().apply { makeCurrent() }

    val alcCapabilities = device.createCapabilities()
    val alCapabilities = AL.createCapabilities(alcCapabilities)

    val stack = stackPush()
    //Allocate space to store return information from the function
    val channelsBuffer = stack.mallocInt(1)
    val sampleRateBuffer = stack.mallocInt(1)

    val (rawAudioBuffer, channels, sampleRate) = stbVorbis.decodeFilename("C:\\Users\\elect\\Downloads\\sample.ogg")

    //Find the correct OpenAL format
    val format = when (channels) {
        1 -> al.Format.MONO16
        2 -> al.Format.STEREO16
        else -> al.Format.UNDEFINED
    }

    //Request space for the buffer
    val buffer = al.genBuffer()

    //Send the data to OpenAL
    buffer.data(format, rawAudioBuffer, sampleRate)

    //Free the memory allocated by STB
    rawAudioBuffer.free()

    //Request a source
    val source = al.genSource()

    //Assign the sound we just loaded to the source
    source.buffer = buffer

    //Play the sound
    source.play()

    try {
        //Wait for a second
        Thread.sleep(100_000)
    } catch (ignored: InterruptedException) {
    }

    //Terminate OpenAL
    source.delete()
    buffer.delete()
    context.destroy()
    device.close()
}

object alc {

    val currentContext: ALCcontext
        get() = ALCcontext(alcGetCurrentContext())
}

object al {

    enum class Format(val i: Int) {
        UNDEFINED(-1),
        MONO8(0x1100),
        MONO16(0x1101),
        STEREO8(0x1102),
        STEREO16(0x1103)
    }

    fun genBuffer() = ALBuffer(alGenBuffers())
    fun genSource() = ALSource(alGenSources())
}

inline class ALBuffer(val i: Int) {

    fun data(format: al.Format, data: Buffer, frequency: Int) = nalBufferData(i, format.i, data.adr, data.remSize, frequency)

    fun delete() = alDeleteBuffers(i)
}

inline class ALSource(val i: Int) {

    var buffer: ALBuffer
        get() = ALBuffer(alGetSourcei(i, AL_BUFFER))
        set(value) = alSourcei(i, AL_BUFFER, value.i)

    fun play() = alSourcePlay(i)

    fun delete() = alDeleteSources(i)
}

object stbVorbis {

    fun decodeFilename(filename: String): Triple<ShortBuffer, Int, Int> = stak {
        val channels = it.mallocInt(1)
        val sampleRate = it.mallocInt(1)
        val buf = stb_vorbis_decode_filename(filename, channels, sampleRate)
        Triple(buf!!, channels[0], sampleRate[0])
    }
}

inline class ALCdevice(val L: Long) {

    fun createContext() = createContext(stackGet().callocInt(1))
    fun createContext(attrList: IntBuffer) = ALCcontext(alcCreateContext(L, attrList))

    fun close() = alcCloseDevice(L)

    infix fun isExtensionPresent(extName: CharSequence) = alcIsExtensionPresent(L, extName)

    fun createCapabilities(): ALCCapabilities = ALC.createCapabilities(L)

    companion object {
        fun open(deviceSpecifier: String? = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER)) = ALCdevice(alcOpenDevice(deviceSpecifier))
    }
}

inline class ALCcontext(val L: Long) {

    fun makeCurrent() = alcMakeContextCurrent(L)

    fun process() = alcProcessContext(L)

    fun suspend() = alcSuspendContext(L)

    fun destroy() = alcDestroyContext(L)

    val device: ALCdevice
        get() = ALCdevice(alcGetContextsDevice(L))
}