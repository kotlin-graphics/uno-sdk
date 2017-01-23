/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A guard that is created for every {@link ByteBufferIndexInput} that tries on best effort
 * to reject any access to the {@link ByteBuffer} behind, once it is unmapped. A single instance
 * of this is used for the original and all clones, so once the original is closed and unmapped
 * all clones also throw {@link AlreadyClosedException}, triggered by a {@link NullPointerException}.
 * <p>
 * This code tries to hopefully flush any CPU caches using a store-store barrier. It also yields the
 * current thread to give other threads a chance to finish in-flight requests...
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
}