package uno

import kool.adr
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.system.MemoryUtil
import uno.glfw.GlfwWindow
import uno.glfw.stak
import vkk.VK_CHECK_RESULT
import vkk.entities.VkSurfaceKHR
import vkk.identifiers.Instance

infix fun GlfwWindow.createSurface(instance: Instance): VkSurfaceKHR =
        VkSurfaceKHR(stak.longAdr { p ->
            VK_CHECK_RESULT(GLFWVulkan.nglfwCreateWindowSurface(instance.adr, handle.L, MemoryUtil.NULL, p))
        })