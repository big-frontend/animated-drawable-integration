plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
//    id("com.google.devtools.ksp")
}
val COMPILE_SDK_VERSION: String by project.rootProject
val BUILD_TOOLS_VERSION: String by project.rootProject
val MIN_SDK_VERSION: String by project.rootProject
val TARGET_SDK_VERSION: String by project.rootProject
val GLIDE_VERSION: String by project.rootProject

android {
    namespace = "com.electrolye.framesequence"
    compileSdk = COMPILE_SDK_VERSION.toInt()
    buildToolsVersion = BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Integer.parseInt(MIN_SDK_VERSION)
        targetSdk = Integer.parseInt(TARGET_SDK_VERSION)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                arguments += listOf("-DANDROID_ARM_NEON=TRUE", "-DANDROID_TOOLCHAIN=clang")
                cppFlags += listOf(
                    "-std=c++14", "-fexceptions", "-frtti"
//                        "DHAVE_CONFIG_H", "-Wno-sign-compare"
                )

                cFlags += listOf(
                    "-D__STDC_FORMAT_MACROS",
                    "-Wall",
                    "-Werror",
                    "-Wno-unused-parameter",
                    "-Wno-unused-variable",
                    "-Wno-overloaded-virtual",
                    "-fvisibility=hidden"
//                cppFlags "-std=gnu++11"
//                targets "hawks","hotfix"
                )

                abiFilters += listOf(
                    "arm64-v8a", "armeabi-v7a" /*,"x86","x86_64"*/
                    //输出制定三种abi体系结构下的so库
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    ndkVersion = "22.0.7026061"
    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }
}

dependencies {
    api("com.github.bumptech.glide:glide:${GLIDE_VERSION}")
    kapt("com.github.bumptech.glide:compiler:${GLIDE_VERSION}")
//    ksp("com.github.bumptech.glide:ksp:${GLIDE_VERSION}")
}