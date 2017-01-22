
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

import java.lang.invoke.MethodHandles.*
import java.lang.invoke.MethodType.methodType

import com.jogamp.opengl.util.GLBuffers
import java.nio.ByteBuffer
import java.lang.invoke.MethodHandles.guardWithTest
import java.lang.invoke.MethodHandles.filterReturnValue
import java.lang.invoke.MethodHandle
import java.util.Objects
import jdk.internal.dynalink.support.Lookup.unreflect
import java.lang.invoke.MethodHandles
import java.lang.reflect.AccessibleObject.setAccessible
import java.io.IOException
import java.security.PrivilegedAction
import java.security.AccessController
import jdk.nashorn.internal.objects.ArrayBufferView.buffer




/**
 * Created by elect on 22/01/2017.
 */

fun GLBuffers.clean(buffer: ByteBuffer) {

}

private class MMapDirectory {

    companion object {

//        private val hack =

        //        @SuppressForbidden(reason = "Needs access to private APIs in DirectBuffer, sun.misc.Cleaner, and sun.misc.Unsafe to enable hack")
        private fun unmapHackImpl(): Any {
            val lookup: Lookup = lookup()
            try {
                try {
                    // *** sun.misc.Unsafe unmapping (Java 9+) ***
                    val unsafeClass = Class.forName("sun.misc.Unsafe")
                    // first check if Unsafe has the right method, otherwise we can give up
                    // without doing any security critical stuff:
                    val unmapper = lookup.findVirtual(unsafeClass, "invokeCleaner",
                            methodType(Void.TYPE, ByteBuffer::class.java))
                    // fetch the unsafe instance and bind it to the virtual MH:
                    val f = unsafeClass.getDeclaredField("theUnsafe")
                    f.isAccessible = true
                    val theUnsafe = f.get(null)
                    return newBufferCleaner(ByteBuffer::class.java, unmapper.bindTo(theUnsafe))
                } catch (se: SecurityException) {
                    // rethrow to report errors correctly (we need to catch it here, as we also catch RuntimeException below!):
                    throw se
                } catch (e: ReflectiveOperationException) {
                    // *** sun.misc.Cleaner unmapping (Java 8) ***
                    val directBufferClass = Class.forName("java.nio.DirectByteBuffer")

                    val m = directBufferClass.getMethod("cleaner")
                    m.isAccessible = true
                    val directBufferCleanerMethod = lookup.unreflect(m)
                    val cleanerClass = directBufferCleanerMethod.type().returnType()

                    /* "Compile" a MH that basically is equivalent to the following code:
         * void unmapper(ByteBuffer byteBuffer) {
         *   sun.misc.Cleaner cleaner = ((java.nio.DirectByteBuffer) byteBuffer).cleaner();
         *   if (Objects.nonNull(cleaner)) {
         *     cleaner.clean();
         *   } else {
         *     noop(cleaner); // the noop is needed because MethodHandles#guardWithTest always needs ELSE
         *   }
         * }
         */
                    val cleanMethod = lookup.findVirtual(cleanerClass, "clean", methodType(Void.TYPE))
                    val nonNullTest = lookup.findStatic(Objects::class.java, "nonNull", methodType(Boolean::class.javaPrimitiveType, Any::class.java))
                            .asType(methodType(Boolean::class.javaPrimitiveType, cleanerClass))
                    val noop = dropArguments(constant(Void::class.java, null).asType(methodType(Void.TYPE)), 0, cleanerClass)
                    val unmapper = filterReturnValue(directBufferCleanerMethod, guardWithTest(nonNullTest, cleanMethod, noop))
                            .asType(methodType(Void.TYPE, ByteBuffer::class.java))
                    return newBufferCleaner(directBufferClass, unmapper)
                } catch (e: RuntimeException) {
                    val directBufferClass = Class.forName("java.nio.DirectByteBuffer")
                    val m = directBufferClass.getMethod("cleaner")
                    m.isAccessible = true
                    val directBufferCleanerMethod = lookup.unreflect(m)
                    val cleanerClass = directBufferCleanerMethod.type().returnType()
                    val cleanMethod = lookup.findVirtual(cleanerClass, "clean", methodType(Void.TYPE))
                    val nonNullTest = lookup.findStatic(Objects::class.java, "nonNull", methodType(Boolean::class.javaPrimitiveType, Any::class.java)).asType(methodType(Boolean::class.javaPrimitiveType, cleanerClass))
                    val noop = dropArguments(constant(Void::class.java, null).asType(methodType(Void.TYPE)), 0, cleanerClass)
                    val unmapper = filterReturnValue(directBufferCleanerMethod, guardWithTest(nonNullTest, cleanMethod, noop)).asType(methodType(Void.TYPE, ByteBuffer::class.java))
                    return newBufferCleaner(directBufferClass, unmapper)
                }

            } catch (se: SecurityException) {
                return "Unmapping is not supported, because not all required permissions are given to the Lucene JAR file: " + se +
                        " [Please grant at least the following permissions: RuntimePermission(\"accessClassInPackage.sun.misc\") " +
                        " and ReflectPermission(\"suppressAccessChecks\")]"
            } catch (e: ReflectiveOperationException) {
                return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Lucene version: " + e
            } catch (e: RuntimeException) {
                return "Unmapping is not supported on this platform, because internal Java APIs are not compatible with this Lucene version: " + e
            }

        }

        private fun newBufferCleaner(unmappableBufferClass: Class<*>, unmapper: MethodHandle): (String, ByteBuffer) -> Unit {
            assert(methodType(Void.TYPE, ByteBuffer::class.java) == unmapper.type())
            return { resourceDescription: String, buffer: ByteBuffer ->
                if (!buffer.isDirect)
                    throw IllegalArgumentException("unmapping only works with direct buffers")

                if (!unmappableBufferClass.isInstance(buffer))
                    throw IllegalArgumentException("buffer is not an instance of " + unmappableBufferClass.name)

                val error = AccessController.doPrivileged({
                    try {
                        unmapper.invokeExact(buffer)
                        return null
                    } catch (t: Throwable) {
                        return@AccessController.doPrivileged t
                    }
                } as PrivilegedAction<Throwable>)
                if (error != null) {
                    throw IOException("Unable to unmap the mapped buffer: " + resourceDescription, error)
                }
            }
        }
    }

    init {

    }
}