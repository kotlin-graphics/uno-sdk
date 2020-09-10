fun prefix(prefix: String) {
    rootProject.name = prefix
    rootProject.children.forEach { it.name = "$prefix-${it.name}" }
}

include("core", "awt", "vk")

//prefix("uno")