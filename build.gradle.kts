import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.7.22"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.2.4")
    implementation("io.ktor:ktor-server-netty:2.2.4")
    testImplementation("io.ktor:ktor-server-test-host:2.2.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.22")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.example.myktorapp.ApplicationKt")
}
tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("ktor-app-fat")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "com.example.myktorapp.ApplicationKt"))
    }
}

task buildApp(type: JavaExec) {
    dependsOn("shadowJar")
    main = "com.example.myktorapp.ApplicationKt"
    classpath = tasks.shadowJar.get().outputs.files
}
