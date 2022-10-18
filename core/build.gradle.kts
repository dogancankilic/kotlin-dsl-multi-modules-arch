plugins {
    id(BuildPlugins.AndroidLibrary)
    id(BuildPlugins.kotlinJetBrains)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinParcelize)
    id(BuildPlugins.DaggerHiltPlugin)
    id(BuildPlugins.NavSafeArgs)
}

android {
    compileSdkVersion(AndroidSDK.compile)

    defaultConfig {
        minSdkVersion(AndroidSDK.min)
        targetSdkVersion(AndroidSDK.target)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
 
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    dataBinding.isEnabled = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0-alpha02")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(Libraries.glide)
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
}
