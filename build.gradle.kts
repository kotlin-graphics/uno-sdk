import magik.createGithubPublication
import magik.github
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("jvm") version embeddedKotlinVersion
    id("org.lwjgl.plugin") version "0.0.34"
    id("elect86.magik") version "0.3.2"
    `maven-publish`
}

dependencies {
    api(projects.core)
    api(projects.awt)
    api(projects.gl)
    // vk
}

kotlin.jvmToolchain { languageVersion.set(JavaLanguageVersion.of(8)) }

tasks {
    withType<KotlinCompile<*>>().all { kotlinOptions { freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn") } }
    test { useJUnitPlatform() }
}

publishing {
    publications {
        createGithubPublication {
            from(components["java"])
            suppressAllPomMetadataWarnings()
        }
    }
    repositories.github { domain = "kotlin-graphics/mary" }
}

java.withSourcesJar()