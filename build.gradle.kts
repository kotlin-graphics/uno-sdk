import kx.KxProject.*
import kx.LwjglModules.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.7.0+71"
    id("kx.kotlin.11") version build apply false
    id("kx.lwjgl") version build apply false
    id("kx.dokka") version build apply false
    id("kx.publish") version build apply false
    id("org.jetbrains.dokka") version "1.4.20"
    java
}

version = "0.7.9+23" // for ::bump

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "kx.kotlin.11")
    apply(plugin = "kx.lwjgl")
    apply(plugin = "kx.dokka")
    apply(plugin = "kx.publish")
    apply(plugin = "java")

    version = rootProject.version
    group = "kotlin.graphics.uno"

    repositories {
        mavenCentral()
    }
}

project(":core") {
    dependencies {
        implementation(kotlin("reflect"))
        kxImplementation(unsigned, kool, glm, gli, gln)
        lwjglImplementation(glfw, jemalloc, opengl)
    }
}
project(":awt") {
    dependencies {
        implementation(project(":core"))
        kxImplementation(kool, glm, gln)
        lwjglImplementation(jawt, glfw, jemalloc, opengl)
    }
}
project(":vk") {
    dependencies {
        implementation(projects.core)
        kxImplementation(kool, vkk)
        lwjglImplementation(glfw, jemalloc, opengl, vulkan)
    }
}

println(buildDir.resolve("dokkaCustomMultiModuleOutput"))
tasks {
    dokkaHtmlMultiModule.configure {
        outputDirectory.set(buildDir.resolve("dokkaCustomMultiModuleOutput"))
    }
}