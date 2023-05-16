@file:OptIn(ExperimentalUnsignedTypes::class)

package uno.glfw

import glm_.bool
import kool.Ptr
import kool.get
import kool.stack
import kool.toPtr
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWGamepadState
import org.lwjgl.glfw.GLFWJoystickCallbackI
import uno.kotlin.ptrFloat
import uno.kotlin.ptrInt
import uno.kotlin.ptrUByte

@JvmInline
value class Joystick(val id: Int) {

    // --- [ glfwJoystickPresent ] ---
    val isPresent: Boolean
        get() = GLFW.glfwJoystickPresent(id)

    // --- [ glfwGetJoystickAxes ] ---

    val axes: FloatArray?
        get() = stack { s ->
            val pCount = s.ptrInt()
            val pAxes = GLFW.nglfwGetJoystickAxes(id, pCount.adr.toLong()).toPtr<Float>()
            if (pAxes.isValid)
                FloatArray(pCount[0]) { pAxes[it] }
            else null
        }

    // --- [ glfwGetJoystickButtons ] ---

    val buttons: BooleanArray?
        get() = stack { s ->
            val pCount = s.ptrInt()
            val pButtons = GLFW.nglfwGetJoystickButtons(id, pCount.adr.toLong()).toPtr<Int>()
            if (pButtons.isValid)
                BooleanArray(pCount[0]) { pButtons[0] == GLFW.GLFW_PRESS }
            else null
        }

    // --- [ glfwGetJoystickHats ] ---

    enum class Hat(val i: Int) {
        Centered(0),
        Up(1),
        Right(2),
        Down(4),
        Left(8),
        RightUp(Right.i or Up.i),
        RightDown(Right.i or Down.i),
        LeftUp(Left.i or Up.i),
        LeftDown(Left.i or Down.i);

        companion object {
            infix fun of(i: Int) = values().first { it.i == i }
        }
    }

    val hats: Array<Hat>?
        get() = stack { s ->
            val pCount = s.ptrInt()
            val pHats = GLFW.nglfwGetJoystickHats(id, pCount.adr.toLong()).toPtr<UByte>()
            if (pHats.isValid)
                Array(pCount[0]) { Hat of pHats[it].toInt() }
            else null
        }

    // --- [ glfwGetJoystickName ] ---
    val name: String?
        get() = GLFW.glfwGetJoystickName(id)

    // --- [ glfwGetJoystickGUID ] ---
    val guid: String?
        get() = GLFW.glfwGetJoystickGUID(id)

    // --- [ glfwSetJoystickUserPointer ] ---
    // --- [ glfwGetJoystickUserPointer ] ---
    var userPointer: Ptr<*>
        get() = GLFW.glfwGetJoystickUserPointer(id).toPtr<Nothing>()
        set(value) = GLFW.glfwSetJoystickUserPointer(id, value.adr.toLong())

    // --- [ glfwJoystickIsGamepad ] ---
    val isGamepad: Boolean
        get() = GLFW.glfwJoystickIsGamepad(id)

    // --- [ glfwSetJoystickCallback ] ---
    var connectionCB: JoystickCB?
        @Deprecated(message = "Write only property", level = DeprecationLevel.HIDDEN) get() = error("")
        set(value) {
            val cb = value?.let { GLFWJoystickCallbackI { jid, event -> it(Joystick(jid), event == GLFW.GLFW_CONNECTED) } }
            GLFW.glfwSetJoystickCallback(cb)?.free()
        }

    // --- [ glfwUpdateGamepadMappings ] ---
    // -> glfw

    // --- [ glfwGetGamepadName ] ---
    val gamepadName: String?
        get() = GLFW.glfwGetGamepadName(id)

    // --- [ glfwGetGamepadState ] ---
    val gamepadState: GamepadState?
        get() = stack { s ->
            val pState = s.nmalloc(GLFWGamepadState.ALIGNOF, GLFWGamepadState.SIZEOF)
            if (GLFW.nglfwGetGamepadState(id, pState).bool) {
                val pButtons = s.ptrUByte(15)
                val pAxes = s.ptrFloat(6)
                GamepadState(UByteArray(15) { pButtons[it] }, FloatArray(6) { pAxes[it] })
            } else null
        }

    companion object {
        val joysticks: Array<Joystick> = Array(16) { Joystick(GLFW.GLFW_JOYSTICK_1 + it) }
    }
}


class GamepadState(val buttons: UByteArray, val axes: FloatArray)