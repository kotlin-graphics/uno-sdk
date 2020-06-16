import org.gradle.internal.os.OperatingSystem.*


val moduleName = "${group}.uno_awt"

dependencies {

    implementation(project(":uno-core"))

    val kx = "com.github.kotlin-graphics"
//    implementation("$kx:kotlin-unsigned:${findProperty("unsignedVersion")}")
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:glm:${findProperty("glmVersion")}")
//    implementation("$kx:gli:${findProperty("gliVersion")}")
    implementation("$kx:gln:${findProperty("glnVersion")}")

    val lwjglNatives = when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-jawt", "-glfw", "-jemalloc", "-opengl").forEach {
        implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}")
        if (it != "-jawt")
            implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}:natives-$lwjglNatives")
    }
}