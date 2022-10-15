pluginManagement {
    repositories {
        mavenLocal()
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/public") }
        maven { url = uri("https://maven.oschina.net/content/groups/public/") }
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()

    }
    plugins {
//        id("com.google.devtools.ksp") version "1.7.0-1.0.6"
//        kotlin("kapt") version "1.7.20"
//        id 'com.android.library' version '7.0.2'
//        id 'org.jetbrains.kotlin.android' version '1.6.21'
    }
}
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        maven { url uri("$rootDir/repo") }
//        mavenLocal()
//        mavenCentral()
//        maven { url 'https://s01.oss.sonatype.org/content/repositories/public/' }
//        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
//        maven { url "https://frontjs-static.pgyer.com/dist/sdk/pgyersdk" }  //主力仓库
//        maven { url "https://raw.githubusercontent.com/Pgyer/mvn_repo_pgyer/master" } //备用仓库（主力仓库下载不下来使用）
//        maven { url "https://raw.githubusercontent.com/PGYER/analytics/master" }  //备用仓库（主力仓库下载不下来使用）
//        maven { url 'https://maven.aliyun.com/repository/google/' }
//        maven { url "https://maven.aliyun.com/repository/public" }
//        maven { url "https://jitpack.io" }
//        gradlePluginPortal()
//        maven { url "https://plugins.gradle.org/m2/" }
////        maven { url "https://plugins.gradle.org/m2/" }
//    }
//}
include(":app")
include(":framesequence-integration")
