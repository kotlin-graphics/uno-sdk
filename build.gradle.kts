import magik.createGithubPublication
import magik.github
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.embeddedKotlinVersion
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.version

plugins {
    kotlin("jvm") version embeddedKotlinVersion
    id("org.lwjgl.plugin") version "0.0.29"
    id("elect86.magik") version "0.3.1"
    `maven-publish`
}

dependencies {
    implementation(projects.core)
    implementation(projects.awt)
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