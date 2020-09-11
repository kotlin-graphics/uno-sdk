module com.github.kotlin_graphics.uno.vk {

    requires kotlin.stdlib;

    requires org.lwjgl;
    requires org.lwjgl.vulkan;
    requires org.lwjgl.glfw;

    requires com.github.kotlin_graphics.uno.core;
    requires com.github.kotlin_graphics.vkk;
    requires com.github.kotlin_graphics.kool;

    exports uno.vk;
}