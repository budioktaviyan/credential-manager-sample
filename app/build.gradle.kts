plugins {
  id("com.android.application")
  kotlin("android")
}

android {
  namespace = "id.android.credential.sample"
  compileSdk = 34
  buildToolsVersion = "34.0.0"

  defaultConfig {
    applicationId = "id.android.credential.sample"
    minSdk = 33
    targetSdk = 34
    versionCode = 1
    versionName = "1.0.0"

    vectorDrawables.useSupportLibrary = true
  }
  signingConfigs {
    getByName("debug") {
      keyAlias = "androiddebugkey"
      keyPassword = "android"
      storeFile = file("../debug.keystore")
      storePassword = "android"
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("debug")
    }
    debug {
      signingConfig = signingConfigs.getByName("debug")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures.viewBinding = true
  composeOptions.kotlinCompilerExtensionVersion = "1.5.4"
  packaging {
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  implementation("androidx.core:core-ktx:1.12.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.10.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
  implementation("androidx.credentials:credentials:1.2.0")
  implementation("androidx.credentials:credentials-play-services-auth:1.2.0")

  testImplementation("junit:junit:4.13.2")

  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}