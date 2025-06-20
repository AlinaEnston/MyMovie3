plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
<<<<<<< HEAD
    id("kotlin-parcelize")
=======
>>>>>>> aa8ce390169e0d983c36ab0787144729478f637e
}

android {
    namespace = "com.larina.mymovie"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.larina.mymovie"
        minSdk = 27
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
<<<<<<< HEAD
    android {
        buildFeatures {
            dataBinding = true
        }
    }
    android {
        buildFeatures {
            viewBinding = true
        }
    }
=======
>>>>>>> aa8ce390169e0d983c36ab0787144729478f637e
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
<<<<<<< HEAD

=======
>>>>>>> aa8ce390169e0d983c36ab0787144729478f637e
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
<<<<<<< HEAD
=======

>>>>>>> aa8ce390169e0d983c36ab0787144729478f637e
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.gridlayout)
<<<<<<< HEAD
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("com.github.bumptech.glide:compiler:4.12.0")
=======
>>>>>>> aa8ce390169e0d983c36ab0787144729478f637e
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}