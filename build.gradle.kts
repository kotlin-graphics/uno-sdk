import kx.KxProject.*
import kx.LwjglModules.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.7.0+97"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build
    id("kx.dokka") version build
    id("kx.publish") version build
}

version = "0.7.9+34" // for ::bump

subprojects {
    version = rootProject.version
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