package uno.al

import kool.free
import org.lwjgl.openal.AL
import org.lwjgl.system.MemoryStack


fun main(args: Array<String>) {

    val device = ALCdevice.open()

    val context = device.createContext().apply { makeCurrent() }

    val alcCapabilities = device.createCapabilities()
    val alCapabilities = AL.createCapabilities(alcCapabilities)

    val stack = MemoryStack.stackPush()
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