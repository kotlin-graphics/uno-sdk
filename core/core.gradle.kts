import magik.createGithubPublication
import magik.github
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.lwjgl.Lwjgl.Module.glfw
import org.lwjgl.Lwjgl.Module.jemalloc
import org.lwjgl.lwjgl

plugins {
    kotlin("jvm")
    id("org.lwjgl.plugin")
    id("elect86.magik")
    `maven-publish`
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("reflect"))

    api("kotlin.graphics:glm:0.9.9.1-11")
    lwjgl { implementation(glfw, jemalloc) }

    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
}

kotlin.jvmToolchain { languageVersion.set(JavaLanguageVersion.of(8)) }

tasks {
    withType<KotlinCompilationTask<*>>().configureEach { compilerOptions.freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn") }
    test { useJUnitPlatform() }
}

publishing {
    publications {
        createGithubPublication {
            from(components["java"])
            artifactId = "${rootProject.name}-${project.name}"
            suppressAllPomMetadataWarnings()
        }
    }
    repositories.github { domain = "kotlin-graphics/mary" }
}

java.withSourcesJar()