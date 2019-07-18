module com.github.kotlin_graphics.uno_vk {

    requires kotlin.stdlib;

    requires org.lwjgl;
    requires org.lwjgl.vulkan;
    requires org.lwjgl.glfw;

    requires com.github.kotlin_graphics.uno_core;
    requires com.github.kotlin_graphics.vkk;
    requires com.github.kotlin_graphics.kool;

    exports uno;
}