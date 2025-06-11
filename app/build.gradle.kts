plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}
val COMPILE_SDK_VERSION: String by project.rootProject
val BUILD_TOOLS_VERSION: String by project.rootProject
val MIN_SDK_VERSION: String by project.rootProject
val TARGET_SDK_VERSION: String by project.rootProject
val KEY_ALIAS: String by project.rootProject
val KEY_PASSWORD: String by project.rootProject
val STORE_PASSWORD: String by project.rootProject
val STORE_FILEPATH: String by project.rootProject
android {
    namespace = "com.electrolytej.animated"
    compileSdk = COMPILE_SDK_VERSION.toInt()

    defaultConfig {
        applicationId = "com.electrolytej.animated"
        minSdk = Integer.parseInt(MIN_SDK_VERSION)
        targetSdk = Integer.parseInt(TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("debugSigningConfig") {
            storeFile = file("${project.rootDir}/${STORE_FILEPATH}")
            storePassword = STORE_PASSWORD
            keyAlias = KEY_ALIAS
            keyPassword = KEY_PASSWORD
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debugSigningConfig")
        }
//        create("staging") {
//            ...
//        }
    }
    buildFeatures {
        viewBinding = true
    }

//    flavorDimensions += listOf("tier")
//    productFlavors {
//        create("free") {
//            dimension = "tier"
//            applicationId = "com.example.myapp.free"
//        }
//
//        create("paid") {
//            dimension = "tier"
//            applicationId = "com.example.myapp.paid"
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation(project(":glide-framesequence-integration"))
    implementation(project(":glide-lottie-integration"))
    implementation(project(":glide-pag-integration"))
    implementation(project(":glide-svg-integration"))
    implementation(project(":fresco-animated-lottie"))
    implementation(project(":fresco-animated-pag"))
    api(libs.glide.core)
    api(libs.glide.recyclerview)
    api(libs.glide.okhttp3)
    ksp(libs.glide.ksp)
    annotationProcessor(libs.glide.compiler)
    api(libs.fresco)
    api(libs.fresco.animated.webp)
    api(libs.fresco.animated.gif)
    api(libs.fresco.webp)
    api(libs.fresco.okhttp3)
}