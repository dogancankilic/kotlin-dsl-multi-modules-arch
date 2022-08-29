pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "kotlin-dsl-multi-modules-arch"
include(":app")
include(":core")
include(":data")
include(":data:remote")
include(":data:local")
include(":data:repository")
include(":domain")
include(":data:model")
