plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}
val COMPILE_SDK_VERSION: String by project.rootProject
val MIN_SDK_VERSION: String by project.rootProject
val GLIDE_VERSION: String by project.rootProject
android {
    namespace = "com.electrolytej.pag"
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
    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    api("com.github.bumptech.glide:glide:${GLIDE_VERSION}")
    annotationProcessor("com.github.bumptech.glide:compiler:${GLIDE_VERSION}")
//    kapt("com.github.bumptech.glide:compiler:${GLIDE_VERSION}")
    ksp("com.github.bumptech.glide:ksp:${GLIDE_VERSION}")
    api("com.tencent.tav:libpag:4.2.100")
}