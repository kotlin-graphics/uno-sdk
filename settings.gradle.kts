//fun prefix(prefix: String) {
//    rootProject.name = "$prefix-${rootProject.name}"
//    rootProject.children.forEach { it.name = "$prefix-${it.name}" }
//}
//
//fun postfix(postfix: String) {
//    rootProject.name = "${rootProject.name}-$postfix"
//    rootProject.children.forEach { it.name = "${it.name}-$postfix" }
//}

pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
//    }
    //    includeBuild("../build-logic")
    //    includeBuild("../magik")
}

for (module in listOf("core", "awt", "gl"/*, "vk"*/)) {
    include(module)
    project(":$module").buildFileName = "$module.gradle.kts"
}

rootProject.name = "uno"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//prefix("uno")
//postfix("jdk8")

gradle.rootProject {
    group = "kotlin.graphics"
    version = "0.7.19"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}