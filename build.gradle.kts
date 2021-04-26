import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
    id("me.champeau.gradle.jmh") version "0.5.3"
    id("com.palantir.graal") version "0.7.2"
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
    //implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.1.0")

    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("commons-validator:commons-validator:1.7")
    implementation("io.ktor:ktor-client-core:1.5.3")
    implementation("io.ktor:ktor-client-cio:1.5.3")
    implementation("io.ktor:ktor-client-serialization:1.5.3")


    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")
    testImplementation(kotlin("test-junit5"))

    implementation(kotlin("script-runtime"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        maxParallelForks = 4
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict")
        suppressWarnings = true
        useIR = true
    }
}
