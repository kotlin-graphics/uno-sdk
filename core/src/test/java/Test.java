import glm_.vec2.Vec2i;
import org.lwjgl.system.MemoryUtil;
import uno.glfw.GlfwWindow;
import uno.glfw.glfw;


public class Test {


    @org.junit.jupiter.api.Test
    void javaGlfwWindow() {
        glfw glfw = uno.glfw.glfw.INSTANCE;
        glfw.init();
        GlfwWindow window = GlfwWindow.create(1280, 720, "Dear ImGui GLFW+OpenGL3 OpenGL example", MemoryUtil.NULL, null, new Vec2i(30));
        assert(window.getHandle() != MemoryUtil.NULL);
    }
//    GlfwWindow w1 = GlfwWindow.Companion.(1280, 720, "Dear ImGui GLFW+OpenGL3 OpenGL example", MemoryUtil.NULL, null, new Vec2i(30));
}
