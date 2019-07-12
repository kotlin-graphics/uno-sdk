package uno

import kool.adr
import org.lwjgl.glfw.GLFWVulkan
import org.lwjgl.system.MemoryUtil
import org.lwjgl.vulkan.VkInstance
import uno.glfw.GlfwWindowHandle
import uno.glfw.glfw
import uno.glfw.stak
import vkk.VK_CHECK_RESULT
import vkk.entities.VkSurfaceKHR

fun glfw.createWindowSurface(instance: VkInstance, windowHandle: GlfwWindowHandle): VkSurfaceKHR =
        VkSurfaceKHR(stak.longAddress { surface ->
            VK_CHECK_RESULT(GLFWVulkan.nglfwCreateWindowSurface(instance.adr, windowHandle.L, MemoryUtil.NULL, surface))
        })