

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31
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
    api(KotlinLibraries.arrow)

    implementation(project(":data:responsemodel"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
