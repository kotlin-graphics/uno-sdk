package uno

import kool.adr
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.VkInstance
import uno.glfw.GlfwWindow
import uno.glfw.GlfwWindowHandle
import uno.glfw.glfw
import uno.glfw.stak
import vkk.VK_CHECK_RESULT
import vkk.entities.VkSurfaceKHR

infix fun GlfwWindow.createSurface(instance: VkInstance): VkSurfaceKHR =
        VkSurfaceKHR(stak.longAdr { p ->
            VK_CHECK_RESULT(GLFWVulkan.nglfwCreateWindowSurface(instance.adr, handle.L, MemoryUtil.NULL, p))
        })