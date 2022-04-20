// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "7.2.0-beta04" apply false
    id ("com.android.library") version "7.2.0-beta04" apply false
    id ("org.jetbrains.kotlin.android") version "1.6.10" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.5.0-alpha01" apply false
    id ("com.google.dagger.hilt.android") version "2.41" apply false
    id ("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()

    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

