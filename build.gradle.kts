import kx.*
import org.lwjgl.Lwjgl
import org.lwjgl.Lwjgl.Module.*

plugins {
    for ((p, v) in listOf("align" to "0.0.7",
                          "base" to "0.0.10",
                          "publish" to "0.0.6",
                          "utils" to "0.0.5"))
        id("io.github.kotlin-graphics.$p") version v
    id("org.lwjgl.plugin") version "0.0.20"
}

dependencies {
    subprojects.forEach(::implementation)
}

subprojects {
    version = rootProject.version
    fun kx(vararg p: String) = p.forEach { apply(plugin = "io.github.kotlin-graphics.$it") }
    kx("align", "base", "publish", "utils")
}

projects.core.dependencyProject.dependencies {
    implementation(kotlin("reflect"))
    implementation(unsigned, kool, glm, gli, gln)
    Lwjgl { implementation(glfw, jemalloc, opengl) }
}
projects.awt.dependencyProject.dependencies {
    implementation(projects.core)
    implementation(kool, glm, gln)
    Lwjgl { implementation(jawt, glfw, jemalloc, opengl) }
}
projects.vk.dependencyProject.dependencies {
    implementation(projects.core)
    implementation(kool, vkk)
    Lwjgl { implementation(glfw, jemalloc, opengl, vulkan) }
}