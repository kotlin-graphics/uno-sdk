import java.lang.invoke.MethodHandle

import java.lang.invoke.MethodHandles.*
import java.lang.invoke.MethodType.methodType

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.ClosedChannelException // javadoc @link
import java.nio.channels.FileChannel
import java.nio.channels.FileChannel.MapMode
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.security.AccessController
import java.security.PrivilegedAction
import java.util.Locale
import java.util.Objects
import java.util.concurrent.Future
import java.lang.reflect.Field
import java.lang.reflect.Method

import ByteBufferGuardKt.BufferCleaner

/**
 * Created by GBarbieri on 23.01.2017.
 */


private fun newBufferCleaner(unmappableBufferClass: Class<*>, unmapper: MethodHandle): BufferCleaner {
    assert(methodType(Void.TYPE, ByteBuffer::class.java) == unmapper.type())
    return { resourceDescription: String, buffer: ByteBuffer ->
        if (!buffer.isDirect)
            throw IllegalArgumentException("unmapping only works with direct buffers")

        if (!unmappableBufferClass.isInstance(buffer))
            throw IllegalArgumentException("buffer is not an instance of " + unmappableBufferClass.name)

        val error = AccessController.doPrivileged({
            try {
                unmapper.invokeExact(buffer)
                return@AccessController.doPrivileged null
            } catch (t: Throwable) {
                return@AccessController.doPrivileged t
            }
        } as PrivilegedAction<Throwable>)
        if (error != null)
            throw IOException("Unable to unmap the mapped buffer: " + resourceDescription, error)
    }
}