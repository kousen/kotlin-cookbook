import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.3.70"
    id("me.champeau.gradle.jmh") version "0.5.0"
    id("com.palantir.graal") version "0.6.0-93-g176e02b"
}

group = "com.kousenit"
version = "1.0"

val scriptname: String by project  // read value from gradle.properties

graal {
    mainClass("scripts.${scriptname.capitalize()}Kt")
    outputName(scriptname)     // output is build/graal/${scriptname}
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")

    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("commons-validator:commons-validator:1.6")
    implementation("io.ktor:ktor-client-gson:1.3.0")
    implementation("io.ktor:ktor-client-apache:1.3.0")


    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("junit:junit:4.12")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")

    implementation(kotlin("script-runtime"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    test {
        useJUnitPlatform {
            maxParallelForks = Runtime.getRuntime().availableProcessors() / 2
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}
