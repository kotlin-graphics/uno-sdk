module kotlin.graphics.uno.vk {

    requires kotlin.stdlib;

    requires org.lwjgl;
    requires org.lwjgl.vulkan;
    requires org.lwjgl.glfw;

    requires kotlin.graphics.uno.core;
    requires kotlin.graphics.vkk;
//    requires kotlin.graphics.kool;

    exports uno.vk;
}