package uno.kotlin

import kool.BYTES
import kool.Ptr
import kool.toPtr
import org.lwjgl.system.MemoryStack

fun MemoryStack.ptrUByte(size: Int = 1): Ptr<UByte> = nmalloc(UByte.BYTES, size shl 1).toPtr()
fun MemoryStack.ptrUShort(size: Int = 1): Ptr<UShort> = nmalloc(UShort.BYTES, size shl 1).toPtr()
fun MemoryStack.ptrInt(size: Int = 1): Ptr<Int> = nmalloc(Int.BYTES, size shl 2).toPtr()
fun MemoryStack.ptrLong(size: Int = 1): Ptr<Long> = nmalloc(Long.BYTES, size shl 3).toPtr()
fun MemoryStack.ptrFloat(size: Int = 1): Ptr<Float> = nmalloc(Float.BYTES, size shl 2).toPtr()
