plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}
val COMPILE_SDK_VERSION: String by project.rootProject
val MIN_SDK_VERSION: String by project.rootProject
android {
    namespace = "com.electrolytej.fresco.avif"
    compileSdk = COMPILE_SDK_VERSION.toInt()

    defaultConfig {
        minSdk = Integer.parseInt(MIN_SDK_VERSION)

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.annotation:annotation:1.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    compileOnly(libs.fresco)
    compileOnly(libs.fresco.imagepipeline.base)
    compileOnly(libs.fresco.middleware)
    api("org.aomedia.avif.android:avif:1.1.1.14d8e3c4")
}