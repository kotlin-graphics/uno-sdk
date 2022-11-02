import magik.createGithubPublication
import magik.github
import org.lwjgl.lwjgl
import org.lwjgl.lwjgl.Module.*

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

    implementation("kotlin.graphics:gln:0.5.31")
    implementation("kotlin.graphics:gli:0.8.3.0-18")
    implementation("kotlin.graphics:glm:0.9.9.1-5")
    implementation("kotlin.graphics:unsigned:3.3.31")
    implementation("kotlin.graphics:kool:0.9.68")
    lwjgl { implementation(glfw, jemalloc, opengl) }

    testImplementation("io.kotest:kotest-runner-junit5:5.4.1")
    testImplementation("io.kotest:kotest-assertions-core:5.4.1")
}

publishing {
    publications {
        createGithubPublication {
            from(components["java"])
            suppressAllPomMetadataWarnings()
        }
    }
    repositories {
        github {
            domain = "kotlin-graphics/mary"
        }
    }
}