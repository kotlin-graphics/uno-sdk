import org.gradle.internal.os.OperatingSystem.*


val moduleName = "$group.${rootProject.name}.vk"

dependencies {

    implementation(project(":core"))

    val kx = "com.github.kotlin-graphics"
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:vkk:${findProperty("vkkVersion")}")

    val lwjglNatives = "natives-" + when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-glfw", "-jemalloc", "-opengl", "-vulkan").forEach {
        implementation("org.lwjgl", "lwjgl$it")
        if (it != "-vulkan")
            runtimeOnly("org.lwjgl", "lwjgl$it", classifier = lwjglNatives)
    }
}

tasks.compileJava { // this is needed because we have a separate compile step in this example with the 'module-info.java' is in 'main/java' and the Kotlin code is in 'main/kotlin'
    options.compilerArgs = listOf("--patch-module", "$moduleName=${sourceSets.main.get().output.asPath}")
}