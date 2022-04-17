plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}