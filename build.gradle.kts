import kx.KxProject.gli
import kx.KxProject.glm
import kx.KxProject.gln
import kx.KxProject.kool
import kx.KxProject.unsigned
import kx.KxProject.vkk
import kx.Lwjgl
import kx.Lwjgl.Modules.*
import kx.implementation

plugins {
    val build = "0.7.3+44"
    id("kx.kotlin") version build
    //    id("kx.dokka") version build
    id("kx.publish") version build
    id("kx.dynamic-align") version build
    id("kx.util") version build
}

subprojects {
    apply(plugin = "kx.kotlin")
    apply(plugin = "kx.publish")
    apply(plugin = "kx.dynamic-align")
    apply(plugin = "kx.util")
}

dependencies {
    project.subprojects.forEach(::implementation)
}

project(":core").dependencies {
    implementation(kotlin("reflect"))
    implementation(unsigned, kool, glm, gli, gln)
    Lwjgl { implementation(glfw, jemalloc, opengl) }
}
project(":awt").dependencies {
    implementation(projects.core)
    implementation(kool, glm, gln)
    Lwjgl { implementation(jawt, glfw, jemalloc, opengl) }
}
project(":vk").dependencies {
    implementation(projects.core)
    implementation(kool, vkk)
    Lwjgl { implementation(glfw, jemalloc, opengl, vulkan) }
}