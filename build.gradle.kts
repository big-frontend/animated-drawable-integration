// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenLocal()
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven{ url = uri("https://dl.bintray.com/kotlin/kotlin-eap")}
        mavenCentral()
        google()

    }
    dependencies {
        classpath("io.github.jamesfchen:module-publisher-plugin:1.4.3")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply(false)
    id("com.android.library") version "8.2.2" apply(false)
    id("org.jetbrains.kotlin.android") version "1.8.22" apply(false)
    id("com.google.devtools.ksp") version "1.8.22-1.0.11" apply(false)
}
allprojects {
    repositories {
        mavenLocal()
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
        google()

    }
    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_11.majorVersion
        targetCompatibility = JavaVersion.VERSION_11.majorVersion
    }
    tasks.withType<Test>().configureEach {
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.majorVersion
            apiVersion = "1.5"
            freeCompilerArgs = listOf("-Xno-optimized-callable-references")
        }
    }
}