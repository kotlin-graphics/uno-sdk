apply {
    plugin("kotlin")
    plugin("maven")
}

buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", "1.1.0"))
    }
}

repositories {
    gradleScriptKotlin()
}

dependencies {

    compile(kotlinModule("stdlib", "1.1.0"))

    testCompile("com.github.elect86:kotlintest:d8878d6da0944ec6bcbcdad6a1540bba021d768d")

    compile("com.github.elect86:glm:a2b384e0128111c06e4bcbdab3550acb2cf5da35")

    val jogl = "2.3.2"
    compile("org.jogamp.gluegen:gluegen-rt-android:$jogl")
    compile("org.jogamp.jogl:jogl-all-android:$jogl")
}

allprojects {
    repositories {
        maven { setUrl("https://jitpack.io") }
    }
}

//the<ShadowJar>().apply {
//    manifest.attributes.apply {
//        put("Implementation-Title", "Gradle Jar File Example")
//        put("Implementation-Version", version)
//        put("Main-Class", "com.mkyong.DateUtils")
//    }
//
//    baseName = project.extensions.getName + "-all"
//}