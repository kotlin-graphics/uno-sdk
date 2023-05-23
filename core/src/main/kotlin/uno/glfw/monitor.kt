package uno.glfw

import glm_.vec2.Vec2
import glm_.vec2.Vec2i
import glm_.vec4.Vec4i
import kool.Ptr
import kool.get
import kool.stack
import kool.toPtr
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryUtil
import uno.kotlin.ptrInt
import uno.kotlin.readVec2
import uno.kotlin.readVec2i
import uno.kotlin.readVec4i

@JvmInline
value class GlfwMonitors(val handles: LongArray) : Iterable<GlfwMonitor> {

    val size: Int
        get() = handles.size

    fun isEmpty(): Boolean = handles.isEmpty()
    fun isNotEmpty(): Boolean = handles.isNotEmpty()

    operator fun get(index: Int): GlfwMonitor = GlfwMonitor(handles[index])
    operator fun set(index: Int, monitor: GlfwMonitor) = handles.set(index, monitor.handle)
    override fun iterator(): Iterator<GlfwMonitor> = GlfwMonitorsIterator(handles)

    class GlfwMonitorsIterator(private val handles: LongArray) : Iterator<GlfwMonitor> {

        private var index = 0

        override fun hasNext(): Boolean = index in handles.indices

        override fun next(): GlfwMonitor = GlfwMonitor(handles[index++])
    }
}

@JvmInline
value class GlfwMonitor(val handle: Long) {

    val isValid: Boolean
        get() = handle != MemoryUtil.NULL
    val isNotValid: Boolean
        get() = !isValid

    // --- [ glfwGetMonitorPos ] ---
    val pos: Vec2i
        get() = readVec2i { x, y -> nglfwGetMonitorPos(handle, x, y) }

    // --- [ glfwGetMonitorWorkarea ] ---
    val workArea: Vec4i
        get() = readVec4i { x, y, z, w -> nglfwGetMonitorWorkarea(handle, x, y, z, w) }

    // --- [ glfwGetMonitorPhysicalSize ] ---
    val physicalSize: Vec2i
        get() = readVec2i { x, y -> nglfwGetMonitorPhysicalSize(handle, x, y) }

    // --- [ glfwGetMonitorContentScale ] ---
    val contentScale: Vec2
        get() = readVec2 { x, y -> nglfwGetMonitorPhysicalSize(handle, x, y) }

    // --- [ glfwGetMonitorName ] ---
    val name: String?
        get() = glfwGetMonitorName(handle)

    // --- [ glfwSetMonitorUserPointer ] ---
    var userPointer: Ptr<*>
        get() = glfwGetMonitorUserPointer(handle).toPtr<Nothing>()
        set(value) = glfwSetMonitorUserPointer(handle, value.adr.toLong())


    // --- [ glfwGetVideoModes ] ---
    val videoModes: Array<GlfwVidMode>
        get() = stack { s ->
            val pCount = s.ptrInt()
            val pModes = nglfwGetVideoModes(handle, pCount.adr.toLong()).toPtr<GlfwVidMode>()
            val count = pCount[0]
            Array(count) { pModes[it] }
        }

    // --- [ glfwGetVideoMode ] ---
    val videoMode: GlfwVidMode
        get() = GlfwVidMode(nglfwGetVideoMode(handle).toPtr())

    // --- [ glfwSetGamma ] ---
    var gamma: Float
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) = glfwSetGamma(handle, value)

    // --- [ glfwGetGammaRamp ] ---
    var gammaRamp: GlfwGammaRamp
        get() = GlfwGammaRamp(nglfwGetGammaRamp(handle).toPtr())
        set(value) = stack { nglfwSetGammaRamp(handle, value.toStack(it).adr.toLong()) }

    companion object {
        val NULL = GlfwMonitor(MemoryUtil.NULL)
    }
}

typealias GlfwMonitorFun = (monitor: GlfwMonitor, connected: Boolean) -> Unit