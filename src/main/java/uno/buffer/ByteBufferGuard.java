package uno.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by GBarbieri on 24.01.2017.
 */

final class ByteBufferGuard {

    /**
     * Pass in an implementation of this interface to cleanup ByteBuffers.
     * MMapDirectory implements this to allow unmapping of bytebuffers with private Java APIs.
     */
    @FunctionalInterface
    static interface BufferCleaner {
        void freeBuffer(String resourceDescription, ByteBuffer b) throws IOException;
    }

//    private final String resourceDescription;
//    private final BufferCleaner cleaner;

}