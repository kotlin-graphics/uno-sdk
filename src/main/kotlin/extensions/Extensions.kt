package extensions

import com.jogamp.common.net.Uri
import com.jogamp.opengl.util.glsl.ShaderProgram
import java.nio.ByteBuffer
import java.nio.ShortBuffer

/**
 * Created by GBarbieri on 27.01.2017.
 */

val String.uri
    get() = Uri.valueOf(javaClass.getResource(this))

val ShaderProgram.name
    get() = program()