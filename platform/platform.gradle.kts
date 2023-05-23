
plugins {
    `java-platform`
}

dependencies {
    // The platform declares constraints on all components that require alignment
    constraints {
        api(projects.awt)
        api(projects.core)
        api(projects.gl)
    }
}

