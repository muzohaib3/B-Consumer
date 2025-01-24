plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.consumer_sales_blueex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.consumer_sales_blueex"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //NAVIGATION COMPONENT
    implementation( libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //CIRCULAR IMAGEVIEW
    implementation(libs.circleimageview)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation( "com.google.code.gson:gson:2.10.1")
    implementation( libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:3.1.2")

    //ARCHITECTURE PATTERN

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    /* MP Android Chart */
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    /* awesome-calendar */
    implementation("io.github.architshah248.calendar:awesome-calendar:2.0.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}