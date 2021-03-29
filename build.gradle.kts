import kx.KxProject.*
import kx.LwjglModules.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.7.0+84"
    id("kx.kotlin.11") version build apply false
    id("kx.lwjgl") version build apply false
    id("kx.dokka") version build apply false
    id("kx.dokka.multimodule") version build
    //    id("kx.publish.multimodule") version build
    //    java
    id("kx.snapshot") version "0.0.5"
}

version = "0.7.9+29" // for ::bump

subprojects {
    apply(plugin = "kx.kotlin.11")
    apply(plugin = "kx.lwjgl")
    apply(plugin = "kx.dokka")
    //    apply(plugin = "kx.publish")
    apply(plugin = "java")

    version = rootProject.version
    group = "kotlin.graphics"

    apply(plugin = "maven-publish")

    tasks {
        // this is needed because we have a separate compile step in this example with the 'module-info.java' is in 'main/java' and the Kotlin code is in 'main/kotlin'
        named<JavaCompile>("compileJava") {
            val module = project.run { "$group.uno.$name" }
            options.compilerArgs = listOf("--patch-module", "$module=${sourceSets.main.get().output.asPath}")
        }
    }

    // limited dsl support inside here
    extensions.configure<PublishingExtension>("publishing") {
        publications.create<MavenPublication>("maven") {
            artifactId = "uno-${project.name}"
            from(components["java"])
            suppressPomMetadataWarningsFor("runtimeElements")
        }
        repositories {
            maven {
                url = uri("$rootDir/mary")
            }
        }
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