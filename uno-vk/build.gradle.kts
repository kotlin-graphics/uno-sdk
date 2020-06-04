import org.gradle.api.attributes.LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE
import org.gradle.api.attributes.java.TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE
import org.gradle.internal.os.OperatingSystem.*
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    java
    `java-library`
    kotlin("jvm") version "1.3.72"
    maven
    //    id "org.jetbrains.kotlin.kapt" version "1.3.10"
    id("org.jetbrains.dokka") version "0.10.1"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

val moduleName = "${group}.uno_vk"

dependencies {

    implementation(project(":uno-core"))
    implementation(project(":uno-gl"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    val kx = "com.github.kotlin-graphics"
    implementation("$kx:kool:${findProperty("koolVersion")}")
    implementation("$kx:vkk:${findProperty("vkkVersion")}")

    val lwjglNatives = when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
    listOf("", "-glfw", "-jemalloc", "-opengl", "-vulkan").forEach {
        implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}")
        if (it != "-vulkan")
            implementation("org.lwjgl:lwjgl$it:${findProperty("lwjglVersion")}:natives-$lwjglNatives")
    }

    attributesSchema.attribute(LIBRARY_ELEMENTS_ATTRIBUTE).compatibilityRules.add(ModularJarCompatibilityRule::class)
    components { withModule<ModularKotlinRule>(kotlin("stdlib")) }
    components { withModule<ModularKotlinRule>(kotlin("stdlib-jdk8")) }

    listOf("runner-junit5", "assertions-core", "runner-console"/*, "property"*/).forEach {
        testImplementation("io.kotest:kotest-$it-jvm:${findProperty("kotestVersion")}")
    }
}

java {
    modularity.inferModulePath.set(true)
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-XXLanguage:+InlineClasses", "-Xjvm-default=enable")
        }
        sourceCompatibility = "11"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
        sourceCompatibility = "11"
        destinationDir = compileJava.get().destinationDir
    }
    jar { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }

    compileJava {
//            dependsOn("compileKotlin")
//            inputs.property("moduleName", moduleName)
//            doFirst {
//                options.compilerArgs = listOf(
//                    "--module-path", classpath.asPath,
//                    "--patch-module", "$moduleName=${sourceSets["main"].output.asPath}")
//                classpath = files()
//            }
        // this is needed because we have a separate compile step in this example with the 'module-info.java' is in 'main/java' and the Kotlin code is in 'main/kotlin'
        options.compilerArgs = listOf("--patch-module", "$moduleName=${sourceSets.main.get().output.asPath}")
    }

    withType<Test> { useJUnitPlatform() }
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
}

val sourceJar = task("sourceJar", Jar::class) {
    dependsOn(tasks["classes"])
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

artifacts {
    archives(sourceJar)
    archives(dokkaJar)
}

// == Add access to the 'modular' variant of kotlin("stdlib"): Put this into a buildSrc plugin and reuse it in all your subprojects
configurations.all {
    attributes.attribute(TARGET_JVM_VERSION_ATTRIBUTE, 11)
    val n = name.toLowerCase()
    if (n.endsWith("compileclasspath") || n.endsWith("runtimeclasspath"))
        attributes.attribute(LIBRARY_ELEMENTS_ATTRIBUTE, objects.named("modular-jar"))
    if (n.endsWith("compile") || n.endsWith("runtime"))
        isCanBeConsumed = false
}

abstract class ModularJarCompatibilityRule : AttributeCompatibilityRule<LibraryElements> {
    override fun execute(details: CompatibilityCheckDetails<LibraryElements>): Unit = details.run {
        if (producerValue?.name == LibraryElements.JAR && consumerValue?.name == "modular-jar")
            compatible()
    }
}

abstract class ModularKotlinRule : ComponentMetadataRule {

    @javax.inject.Inject
    abstract fun getObjects(): ObjectFactory

    override fun execute(ctx: ComponentMetadataContext) {
        val id = ctx.details.id
        listOf("compile", "runtime").forEach { baseVariant ->
            ctx.details.addVariant("${baseVariant}Modular", baseVariant) {
                attributes {
                    attribute(LIBRARY_ELEMENTS_ATTRIBUTE, getObjects().named("modular-jar"))
                }
                withFiles {
                    removeAllFiles()
                    addFile("${id.name}-${id.version}-modular.jar")
                }
                withDependencies {
                    clear() // 'kotlin-stdlib-common' and  'annotations' are not modules and are also not needed
                }
            }
        }
    }
}