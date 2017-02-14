buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin", "1.1.0-beta-17"))
    }
}

apply {
    plugin("kotlin")
    plugin("maven")
}

repositories {
    gradleScriptKotlin()
}

dependencies {
    compile(kotlinModule("stdlib", "1.1.0-beta-17"))
    testCompile("io.kotlintest:kotlintest:1.3.5")
    compile("com.github.elect86:glm:232a15ac00")

    val version = "2.3.2"
    
    compile("org.jogamp.gluegen:gluegen-rt:$version")
    compile("org.jogamp.jogl:jogl-all:$version")

    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-android-aarch64")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-android-armv6")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-linux-amd64")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-linux-armv6")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-linux-armv6hf")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-linux-i586")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-macosx-universal")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-solaris-amd64")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-solaris-i586")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-windows-amd64")
    runtime("org.jogamp.gluegen:gluegen-rt:$version:natives-windows-i586")

    runtime("org.jogamp.jogl:jogl-all:$version:natives-android-aarch64")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-android-armv6")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-linux-amd64")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-linux-armv6")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-linux-armv6hf")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-linux-i586")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-macosx-universal")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-solaris-amd64")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-solaris-i586")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-windows-amd64")
    runtime("org.jogamp.jogl:jogl-all:$version:natives-windows-i586")
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