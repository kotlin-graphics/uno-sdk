import org.gradle.internal.os.OperatingSystem.*

plugins {
    java
    kotlin("jvm") version "1.3.72" apply false
    maven
    //    id "org.jetbrains.kotlin.kapt" version "1.3.10"
    id("org.jetbrains.dokka") version "0.10.1" apply false
    id("com.github.johnrengelman.shadow") version "5.2.0" apply false
}


val group = "com.github.kotlin_graphics"
val moduleName = "$group.uno"

val kotestVersion = "4.0.5"

//ext {
//    moduleName = "${group}.uno"
//    kot = 'org.jetbrains.kotlin:kotlin'
//    kx = "com.github.kotlin-graphics"
//    kotlin_version = '1.3.70'
//    kotlintest_version = '3.4.2'
//    vkk_version = 'bc3da1dd67594af6f2d3e49be74b6fc260025c12'
//    gln_version = '8dea51af29a2a1d6e51ed3b6d7b06ba2ca2f99b9'
//    gli_version = 'ac3da8180268b5d121ea83866de5bfe0e8998045'
//    glm_version = '1b4ac18dd1a3c23440d3f33596688aac60bc0141'
//    unsigned_version = '18131d0fe0b7465a145a4502d31452c5ae0e59a1'
//    kool_version = 'fcf04b2c03b8949d9d9a8b0a580082e927903510'
//    lwjgl_version = "3.2.3"
//    lwjgl_natives = current() == WINDOWS ? "windows" : current() == LINUX ? "linux" : "macos"
//}
//
//dependencies {
//
//    implementation "$kot-stdlib-jdk8"
//    implementation "$kot-reflect"
//}
//
//allprojects {
//    repositories {
//        mavenCentral()
//        jcenter()
//        maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
//        maven { url 'https://jitpack.io' }
//    }
//}
//
//compileKotlin {
//    // Enable Kotlin compilation to Java 8 class files with method parameter name metadata
//    kotlinOptions {
//        jvmTarget = "11"
////        javaParameters = true
//    }
//    // As per https://stackoverflow.com/a/47669720
//    // See also https://discuss.kotlinlang.org/t/kotlin-support-for-java-9-module-system/2499/9
////    destinationDir = compileJava.destinationDir
//}
//
//compileTestKotlin {
//    kotlinOptions {
//        jvmTarget = "11"
////        javaParameters = true
//    }
//}
//
//compileJava {
//    dependsOn(':compileKotlin')
//    doFirst {
//        options.compilerArgs = [
//                '--module-path', classpath.asPath,
//                '--patch-module', "$moduleName=${sourceSets["main"].output.asPath}"]
//        classpath = files()
//    }
//}
//
//jar { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }
//
//task sourcesJar(type: Jar, dependsOn: classes) {
//    archiveClassifier = 'sources'
//    from sourceSets.main.allSource
//}
//
//task javadocJar(type: Jar, dependsOn: javadoc) {
//    archiveClassifier = 'javadoc'
//    from javadoc.destinationDir
//}
//
//artifacts {
//    archives sourcesJar
////    archives javadocJar
//}
//
//jar {
//    inputs.property("moduleName", moduleName)
////    manifest.attributes('Automatic-Module-Name': moduleName)
//}
//
//shadowJar.archiveClassifier = 'all'