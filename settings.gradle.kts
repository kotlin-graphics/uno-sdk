fun prefix(prefix: String) {
    rootProject.name = "$prefix-${rootProject.name}"
    rootProject.children.forEach { it.name = "$prefix-${it.name}" }
}

fun postfix(postfix: String) {
    rootProject.name = "${rootProject.name}-$postfix"
    rootProject.children.forEach { it.name = "${it.name}-$postfix" }
}

include("core", "awt", "vk")

rootProject.name = "uno"

//prefix("uno")
//postfix("jdk8")