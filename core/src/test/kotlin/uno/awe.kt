//package uno
//
//import glm_.and
//import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
//import org.lwjgl.glfw.GLFW.*
//import org.lwjgl.glfw.GLFWErrorCallback
//import org.lwjgl.glfw.GLFWVidMode
//import org.lwjgl.opengl.GL
//import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
//import org.lwjgl.opengl.GL11C.glClear
//import org.lwjgl.system.MemoryStack.stackPush
//import org.lwjgl.system.MemoryUtil.NULL
//import org.lwjgl.util.tinyfd.TinyFileDialogs.*
//import java.util.*
//
//object HelloTinyFD {
//
//    @JvmStatic
//    fun main(args: Array<String>) {
//        GLFWErrorCallback.createPrint().set()
//        if (!glfwInit()) {
//            throw IllegalStateException("Unable to initialize GLFW")
//        }
//
//        val window = glfwCreateWindow(300, 300, "Hello tiny file dialogs!", NULL, NULL)
//        if (window == NULL) {
//            throw RuntimeException("Failed to create the GLFW window")
//        }
//
//        glfwSetKeyCallback(window) { windowHnd, key, scancode, action, mods ->
//            if (action == GLFW_RELEASE) {
//                return@glfwSetKeyCallback
//            }
//
//            when (key) {
//                GLFW_KEY_ESCAPE -> glfwSetWindowShouldClose(windowHnd, true)
//                GLFW_KEY_B -> tinyfd_beep()
//                GLFW_KEY_N -> {
//                    println("\nOpening notification popup...")
//                    println(tinyfd_notifyPopup("Please read...", "...this message.", "info"))
//                }
//                GLFW_KEY_1 -> {
//                    println("\nOpening message dialog...")
//                    println(if (tinyfd_messageBox("Please read...", "...this message.", "okcancel", "info", true)) "OK" else "Cancel")
//                }
//                GLFW_KEY_2 -> {
//                    println("\nOpening input box dialog...")
//                    println(tinyfd_inputBox("Input Value", "How old are you?", "30"))
//                }
//                GLFW_KEY_3 -> {
//                    println("\nOpening file open dialog...")
//                    println(tinyfd_openFileDialog("Open File(s)", "", null, null, true))
//                }
//                GLFW_KEY_4 -> stackPush().use({ stack ->
//                    val aFilterPatterns = stack.mallocPointer(2)
//
//                    aFilterPatterns.put(stack.UTF8("*.jpg"))
//                    aFilterPatterns.put(stack.UTF8("*.png"))
//
//                    aFilterPatterns.flip()
//
//                    println("\nOpening file save dialog...")
//                    println(tinyfd_saveFileDialog("Save Image", "", aFilterPatterns, "Image files (*.jpg, *.png)"))
//                })
//                GLFW_KEY_5 -> {
//                    println("\nOpening folder select dialog...")
//                    println(tinyfd_selectFolderDialog("Select Folder", ""))
//                }
//                GLFW_KEY_6 -> {
//                    println("\nOpening color chooser dialog...")
//                    stackPush().use { stack ->
//                        val color = stack.malloc(3)
//                        val hex = tinyfd_colorChooser("Choose Color", "#FF00FF", null, color)
//                        println(hex)
//                        if (hex != null) {
//                            println("\tR: " + (color.get(0) and 0xFF))
//                            println("\tG: " + (color.get(1) and 0xFF))
//                            println("\tB: " + (color.get(2) and 0xFF))
//                        }
//                    }
//                }
//            }
//        }
//
//        // Center window
//        val vidmode = Objects.requireNonNull<GLFWVidMode>(glfwGetVideoMode(glfwGetPrimaryMonitor()))
//        glfwSetWindowPos(
//                window,
//                (vidmode.width() - 300) / 2,
//                (vidmode.height() - 300) / 2
//        )
//
//        glfwMakeContextCurrent(window)
//        GL.createCapabilities()
//
//        glfwSwapInterval(1)
//
//        tinyfd_messageBox("tinyfd_query", "", "ok", "info", true)
//        println("tiny file dialogs " + tinyfd_version + " (" + tinyfd_response() + ")")
//        println()
//        println(tinyfd_needs)
//        println()
//        println("Press 1 to launch a message dialog.")
//        println("Press 2 to launch an input box fialog.")
//        println("Press 3 to launch a file open dialog.")
//        println("Press 4 to launch a file save dialog.")
//        println("Press 5 to launch a folder select dialog.")
//        println("Press 6 to launch a color chooser dialog.")
//        while (!glfwWindowShouldClose(window)) {
//            glfwPollEvents()
//
//            glClear(GL_COLOR_BUFFER_BIT)
//            glfwSwapBuffers(window)
//        }
//
//        GL.setCapabilities(null)
//
//        glfwFreeCallbacks(window)
//        glfwDestroyWindow(window)
//        glfwTerminate()
//
//        Objects.requireNonNull<GLFWErrorCallback>(glfwSetErrorCallback(null)).free()
//    }
//
//}