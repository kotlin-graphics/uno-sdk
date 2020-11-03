import org.gradle.internal.os.OperatingSystem.*


//val moduleName = "$group.${rootProject.name}.awt"

dependencies {

    implementation(project(":core"))

    val kx = "com.github.kotlin-graphics"
//    implementation("$kx:kotlin-unsigned:${findProperty("unsignedVersion")}")
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:glm:${findProperty("glmVersion")}")
//    implementation("$kx:gli:${findProperty("gliVersion")}")
    implementation("$kx:gln:${findProperty("glnVersion")}")

    val lwjglNatives = "natives-" + when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-jawt", "-glfw", "-jemalloc", "-opengl").forEach {
        implementation("org.lwjgl", "lwjgl$it")
        if (it != "-jawt")
            runtimeOnly("org.lwjgl", "lwjgl$it", classifier = lwjglNatives)
    }
}