module kotlin.graphics.uno.core {

    requires kotlin.stdlib;

    requires java.desktop;

    requires org.lwjgl;

    requires kotlin.graphics.gln;
//    requires com.github.kotlin_graphics.gli;
    requires kotlin.graphics.glm;
    requires kotlin.graphics.kool;
    requires kotlin.graphics.unsigned;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;

    exports uno.buffer;
    exports uno.convert;
    exports uno.glfw;
    exports uno.glm;
    exports uno.kotlin;
//    exports uno.mousePole;
    exports uno.stb;
    exports uno.time;
}