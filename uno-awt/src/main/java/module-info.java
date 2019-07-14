module com.github.kotlin_graphics.uno_awt {

    requires kotlin.stdlib;

    requires com.github.kotlin_graphics.glm;
    requires com.github.kotlin_graphics.uno_core;

    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.jawt;

    requires java.desktop;

    exports uno.awt;
}