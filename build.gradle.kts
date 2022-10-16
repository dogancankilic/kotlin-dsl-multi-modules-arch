// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(BuildPlugins.androidApplication) version "7.2.1" apply false
    id(BuildPlugins.AndroidLibrary) version "7.2.1" apply false
    id(BuildPlugins.kotlinJetBrains) version "1.6.10" apply false
    id(BuildPlugins.NavSafeArgsKt) version "2.5.0-alpha01" apply false
    id(BuildPlugins.Hilt) version "2.41" apply false
    id(BuildPlugins.ktlint) version "10.2.1"
    id(BuildPlugins.kotlinJvm) version "1.6.10" apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    // Version should be inherited from parent
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
