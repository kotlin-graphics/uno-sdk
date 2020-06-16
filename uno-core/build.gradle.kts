import org.gradle.internal.os.OperatingSystem.*

plugins {
    kotlin("jvm")
}

val moduleName = "${group}.uno_core"

dependencies {

    implementation(kotlin("reflect"))

    val kx = "com.github.kotlin-graphics"
    implementation("$kx:kotlin-unsigned:${findProperty("unsignedVersion")}")
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:glm:${findProperty("glmVersion")}")
    implementation("$kx:gli:${findProperty("gliVersion")}")
    implementation("$kx:gln:${findProperty("glnVersion")}")

    val lwjglNatives = when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-glfw", "-jemalloc", "-opengl").forEach {
        implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}")
        implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}:natives-$lwjglNatives")
    }
}