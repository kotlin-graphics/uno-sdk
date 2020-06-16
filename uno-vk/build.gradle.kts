import org.gradle.internal.os.OperatingSystem.*


val moduleName = "${group}.uno_vk"

dependencies {

    implementation(project(":uno-core"))

    val kx = "com.github.kotlin-graphics"
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:vkk:${findProperty("vkkVersion")}")

    val lwjglNatives = when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-glfw", "-jemalloc", "-opengl", "-vulkan").forEach {
        implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}")
        if (it != "-vulkan")
            implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}:natives-$lwjglNatives")
    }
}