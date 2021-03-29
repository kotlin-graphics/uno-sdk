import kx.KxProject.*
import kx.LwjglModules.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.7.0+88"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build apply false
    id("kx.dokka") version build apply false
    id("kx.dokka.multimodule") version build
    id("kx.publish") version build
    //    java
}

version = "0.7.9+33" // for ::bump

subprojects {
    apply(plugin = "kx.lwjgl")
    apply(plugin = "kx.dokka")
    apply(plugin = "java")
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
        implementation(rootProject.projects.core)
        kxImplementation(kool, glm, gln)
        lwjglImplementation(jawt, glfw, jemalloc, opengl)
    }
}
project(":vk") {
    dependencies {
        implementation(rootProject.projects.core)
        kxImplementation(kool, vkk)
        lwjglImplementation(glfw, jemalloc, opengl, vulkan)
    }
}