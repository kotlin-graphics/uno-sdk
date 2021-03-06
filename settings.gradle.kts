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
    repositories {
        gradlePluginPortal()
        maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    }
}

rootProject.name = "uno"

include("core", "awt", "vk")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//prefix("uno")
//postfix("jdk8")