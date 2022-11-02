import magik.createGithubPublication
import magik.github

plugins {
    kotlin("jvm") version embeddedKotlinVersion
    id("org.lwjgl.plugin") version "0.0.29"
    id("elect86.magik") version "0.3.1"
    `maven-publish`
}

dependencies {
    api(projects.core)
    api(projects.awt)
}

kotlin.jvmToolchain {
    this as JavaToolchainSpec
    languageVersion.set(JavaLanguageVersion.of(8))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
        kotlinOptions {
            freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
        }
    }
    withType<Test>().configureEach { useJUnitPlatform() }
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