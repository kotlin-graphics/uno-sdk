import org.gradle.internal.os.OperatingSystem.*

plugins {
    kotlin("jvm")
}

val moduleName = "$group.${rootProject.name}.core"

dependencies {
    println(moduleName)
    implementation(kotlin("reflect"))

    val kx = "com.github.kotlin-graphics"
    val unsignedVersion = findProperty("unsignedVersion") as String
    val koolVersion = findProperty("koolVersion") as String
    val glmVersion = findProperty("glmVersion") as String
    val gliVersion = findProperty("gliVersion") as String
    val glnVersion = findProperty("glnVersion") as String
    implementation("$kx:kotlin-unsigned:${findProperty("unsignedVersion")}")
    implementation("$kx:kool:$koolVersion")// { version { strictly(koolVersion) } }
    implementation("$kx:glm:${findProperty("glmVersion")}")
    implementation("$kx:gli:${findProperty("gliVersion")}")
    implementation("$kx:gln:${findProperty("glnVersion")}")

    val lwjglNatives = "natives-" + when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-glfw", "-jemalloc", "-opengl").forEach {
        implementation("org.lwjgl", "lwjgl$it")
        if (it != "-jawt")
            runtimeOnly("org.lwjgl", "lwjgl$it", classifier = lwjglNatives)
    }
}

tasks.compileJava { // this is needed because we have a separate compile step in this example with the 'module-info.java' is in 'main/java' and the Kotlin code is in 'main/kotlin'
    options.compilerArgs = listOf("--patch-module", "$moduleName=${sourceSets.main.get().output.asPath}")
}