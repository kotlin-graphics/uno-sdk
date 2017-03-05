package uno.buffer

/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the extensions.getName of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import uno.buffer.ByteBufferGuard.BufferCleaner
import uno.buffer.MMapDirectory.newBufferCleaner
import com.jogamp.opengl.util.GLBuffers
import java.lang.invoke.MethodHandles.*
import java.lang.invoke.MethodType.methodType
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.nio.*
import java.security.AccessController
import java.security.PrivilegedAction
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


fun ByteBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun ShortBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun IntBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun LongBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun FloatBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun DoubleBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun CharBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)

class BufferUtils {

    companion object {

        // Oracle JRE / OpenJDK
        val cleanerMethod = loadMethod("sun.nio.ch.DirectBuffer", "cleaner")
        val cleanMethod = loadMethod("sun.misc.Cleaner", "clean")
        val viewedBufferMethod = loadMethod("sun.nio.ch.DirectBuffer", "viewedBuffer")
                ?: loadMethod("sun.nio.ch.DirectBuffer", "attachment")
        // Apache Harmony
        val freeMethod = try {
            ByteBuffer.allocateDirect(1)::class.java.getMethod("free")
        } catch (ex: NoSuchMethodException) {
            null
        } catch (ex: SecurityException) {
            null
        }

        private fun loadMethod(className: String, methodName: String): Method? {
            try {
                val method = Class.forName(className).getMethod(methodName)
                method.isAccessible = true// according to the Java documentation, by default, a reflected object is not accessible
                return method
            } catch (ex: NoSuchMethodException) {
                return null // the method was not found
            } catch (ex: SecurityException) {
                return null // setAccessible not allowed by security policy
            } catch (ex: ClassNotFoundException) {
                return null // the direct buffer implementation was not found
            } catch (t: Throwable) {
                if (t::class.java.name == "java.lang.reflect.InaccessibleObjectException")
                    return null// the class is in an unexported module
                else
                    throw t
            }
        }

        /**
         * This function explicitly calls the Cleaner method of a direct buffer.

         * @param toBeDestroyed
         * *            The direct buffer that will be "cleaned". Utilizes reflection.
         */
        @JvmStatic fun destroyDirectBuffer(toBeDestroyed: Buffer) {
            try {
                if (freeMethod != null)
                    freeMethod.invoke(toBeDestroyed)
                else {
                    //TODO load the methods only once, store them into a cache (only for Java >= 9)
                    val localCleanerMethod = cleanerMethod ?: loadMethod(toBeDestroyed.javaClass.name, "cleaner")

                    if (localCleanerMethod == null)
                        Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "Buffer cannot be destroyed: {0}", toBeDestroyed)
                    else {
                        val cleaner = localCleanerMethod.invoke(toBeDestroyed)
                        if (cleaner != null) {
                            val localCleanMethod = cleanMethod ?:
                                    if (cleaner is Runnable)    // jdk.internal.ref.Cleaner implements Runnable in Java 9
                                        loadMethod(Runnable::class.java.name, "run")
                                    else    // sun.misc.Cleaner does not implement Runnable in Java < 9
                                        loadMethod(cleaner::class.java.name, "clean")

                            if (localCleanMethod == null)
                                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                        "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            else
                                localCleanMethod.invoke(cleaner)

                        } else {
                            val localViewedBufferMethod = viewedBufferMethod ?: loadMethod(toBeDestroyed.javaClass.name, "viewedBuffer")

                            if (localViewedBufferMethod == null)
                                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                        "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            else {  // Try the alternate approach of getting the viewed buffer first
                                val viewedBuffer = localViewedBufferMethod.invoke(toBeDestroyed)
                                if (viewedBuffer != null)
                                    destroyDirectBuffer(viewedBuffer as Buffer)
                                else
                                    Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                            "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            }
                        }
                    }
                }
            } catch (ex: IllegalAccessException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: IllegalArgumentException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: InvocationTargetException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: SecurityException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            }
        }
    }
}

fun main(args: Array<String>) {

    val bb = GLBuffers.newDirectByteBuffer(1_000_000_000)

    println(bb)

    bb.destroy()

    println(bb)
}

public class BU {

    /**
     * `true`, if this platform supports unmapping mmapped files.
     */
    var UNMAP_SUPPORTED: Boolean

    /**
     * if [.UNMAP_SUPPORTED] is `false`, this contains the reason why unmapping is not supported.
     */
    var UNMAP_NOT_SUPPORTED_REASON: String?

    /** Reference to a BufferCleaner that does unmapping; `null` if not supported.  */
    internal var CLEANER: BufferCleaner?

    init {
        val hack = AccessController.doPrivileged(unmapHackImpl() as PrivilegedAction<Any>);
        if (hack is BufferCleaner) {
            CLEANER = hack as BufferCleaner
            UNMAP_SUPPORTED = true;
            UNMAP_NOT_SUPPORTED_REASON = null;
        } else {
            CLEANER = null;
            UNMAP_SUPPORTED = false;
            UNMAP_NOT_SUPPORTED_REASON = hack.toString();
        }
    }

    companion object {
        //    @SuppressForbidden(reason = "Needs access to private APIs in DirectBuffer, sun.misc.Cleaner, and sun.misc.Unsafe to enable hack")
        fun unmapHackImpl(): Any {
            val lookup = lookup()
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
    }
}
