

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")

}

android {
    compileSdk = 31
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
    }

}

dependencies {
    api(Libraries.retrofit)
    api(Libraries.daggerHilt)
    kapt(Libraries.daggerHiltCompiler)
    api(Libraries.gson)
    api(Libraries.retrofitGsonConverter)
    api(Libraries.httpLoggingInterceptor)
    api(Libraries.sandwich)
    api(KotlinLibraries.arrow)

    implementation(project(":data:model"))



}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}