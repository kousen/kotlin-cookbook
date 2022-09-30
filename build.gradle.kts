import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.graal)
}

group = "com.kousenit"
version = "1.0"

val scriptname: String by project  // read value from gradle.properties

graal {
    mainClass("scripts.${scriptname.capitalize()}Kt")
    outputName(scriptname)     // output is build/graal/${scriptname}
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.commons.math3)
    implementation(libs.gson)
    implementation(libs.commons.validator) {
        // Avoid security vulnerability in commons-collections 3.2.2
        exclude(group = "commons-collections", module = "commons-collections")
    }
    implementation(libs.commons.collections4)

    implementation(libs.bundles.ktor.client)

    testImplementation(libs.assertj)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(kotlin("test-junit5"))

    implementation(kotlin("script-runtime"))
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        maxParallelForks = Runtime.getRuntime().availableProcessors() / 2 + 1
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict")
        suppressWarnings = true
    }
}
