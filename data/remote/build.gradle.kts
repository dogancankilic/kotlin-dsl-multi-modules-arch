plugins {
    id(BuildPlugins.AndroidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
}

android {
    defaultConfig {
        compileSdk = 31
        minSdk = 21
    }
}

dependencies {
    api(Libraries.retrofit)
    api(Libraries.daggerHilt)
    implementation("androidx.paging:paging-common-ktx:3.0.0")
    kapt(Libraries.daggerHiltCompiler)
    api(Libraries.gson)
    api(Libraries.retrofitGsonConverter)
    api(Libraries.httpLoggingInterceptor)
    api(Libraries.sandwich)
    implementation(project(Modules.Model))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
