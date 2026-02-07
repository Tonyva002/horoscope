plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias {libs.plugins.ksp}
    alias {libs.plugins.hilt}
    alias(libs.plugins.safeargs)
}

android {
    namespace = "com.pangea.horoscope"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.pangea.horoscope"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
       getByName("release")  {
            isMinifyEnabled = false
           isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
           resValue("string", "pangea", "HoroscopeApp")
           buildConfigField("String", "BASE_URL", "\"https://newastro.vercel.app/\"")
        }
        getByName("debug"){
            isDebuggable = true
            resValue("string", "pangea", "[DEBUG] HoroscopeApp" )
            buildConfigField("String", "BASE_URL", "\"https://newastro-debug.vercel.app/\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }


}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Retrifit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.logging.interceptor)


    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.compiler)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}