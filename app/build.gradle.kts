plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.dogancan.kotlin_dsl_multi_modules_arch"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dataBinding.isEnabled = true

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    kapt {
        correctErrorTypes = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.10")
    implementation("androidx.appcompat:appcompat:1.6.0-alpha01")
    implementation("androidx.core:core-ktx:1.9.0-alpha02")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.0-alpha02")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0-alpha05")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    debugImplementation("app.cash.turbine:turbine:0.9.0")

    api(Libraries.retrofit)
    api(Libraries.daggerHilt)
    api(Libraries.gson)
    api(Libraries.retrofitGsonConverter)
    api(Libraries.httpLoggingInterceptor)
    api(Libraries.sandwich)
    kapt(Libraries.daggerHiltCompiler)
    api(KotlinLibraries.kotlinCoroutineCore)
    api(AndroidLibraries.kotlinCoroutineAndroid)
    api(AndroidLibraries.lifeCycleViewModel)
    api(AndroidLibraries.lifecycleViewModelExt)
    api(AndroidLibraries.lifecycleRuntime)
    api(AndroidLibraries.activityExt)
    api(AndroidLibraries.navigation)
    api(AndroidLibraries.navigationFrag)
    api(TestLibraries.mockk)
    testImplementation(TestLibraries.coroutine)
    testImplementation(TestLibraries.archCoreTest)

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data:model"))
}
