import kx.*
import org.lwjgl.Lwjgl
import org.lwjgl.Lwjgl.Module.*

plugins {
    fun kx(vararg p: String) = p.forEach { id("io.github.kotlin-graphics.$it") }
    kx("align", "base", "publish", "utils")
    id("org.lwjgl.plugin")
}

version = rootProject.version

dependencies {
    implementation(kotlin("reflect"))
    implementation(unsigned, kool, glm, gli, gln)
    Lwjgl { implementation(glfw, jemalloc, opengl) }
}
