/**
 * Created by GBarbieri on 23.01.2017.
 */

import java.io.IOException
import java.nio.ByteBuffer

/**
 * A guard that is created for every [ByteBufferIndexInput] that tries on best effort
 * to reject any access to the [ByteBuffer] behind, once it is unmapped. A single instance
 * of this is used for the original and all clones, so once the original is closed and unmapped
 * all clones also throw [AlreadyClosedException], triggered by a [NullPointerException].
 *
 *
 * This code tries to hopefully flush any CPU caches using a store-store barrier. It also yields the
 * current thread to give other threads a chance to finish in-flight requests...
 */
internal class ByteBufferGuardKt {

    /**
     * Pass in an implementation of this interface to cleanup ByteBuffers.
     * MMapDirectory implements this to allow unmapping of bytebuffers with private Java APIs.
     */
    @FunctionalInterface
    internal interface BufferCleaner {
        @Throws(IOException::class)
        fun freeBuffer(resourceDescription: String, b: ByteBuffer)
    }
}