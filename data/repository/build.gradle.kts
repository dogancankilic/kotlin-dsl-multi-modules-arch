
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 31
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    api(Libraries.retrofit)
    api(Libraries.daggerHilt)
    api(Libraries.gson)
    api(Libraries.retrofitGsonConverter)
    api(Libraries.httpLoggingInterceptor)
    api(Libraries.sandwich)
    kapt(Libraries.daggerHiltCompiler)
    api(KotlinLibraries.kotlinCoroutineCore)
    api(KotlinLibraries.arrow)

    implementation(project(":data:remote"))
    implementation(project(":data:responsemodel"))
    implementation(project(":core"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
