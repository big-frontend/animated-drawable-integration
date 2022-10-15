import com.android.builder.signing.DefaultSigningConfig.Companion.debugSigningConfig

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
//    id("com.google.devtools.ksp")
}
val COMPILE_SDK_VERSION: String by project.rootProject
val BUILD_TOOLS_VERSION: String by project.rootProject
val MIN_SDK_VERSION: String by project.rootProject
val TARGET_SDK_VERSION: String by project.rootProject
val GLIDE_VERSION: String by project.rootProject
val KEY_ALIAS: String by project.rootProject
val KEY_PASSWORD: String by project.rootProject
val STORE_PASSWORD: String by project.rootProject
val STORE_FILEPATH: String by project.rootProject
android {
    compileSdk = COMPILE_SDK_VERSION.toInt()
    buildToolsVersion = BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = "com.jamesfchen.animationdrawableintegration"
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
    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":framesequence-integration"))
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    kapt("com.github.bumptech.glide:compiler:${GLIDE_VERSION}")
//    ksp("com.github.bumptech.glide:ksp:${GLIDE_VERSION}")
}