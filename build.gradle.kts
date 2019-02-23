import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.3.21"
}

group = "com.kousenit"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("commons-validator:commons-validator:1.6")

    testImplementation("org.hamcrest:hamcrest:2.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")

    testImplementation("junit:junit:4.12")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    test {
        useJUnitPlatform()
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}
