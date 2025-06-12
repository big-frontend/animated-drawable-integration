pluginManagement {
    repositories {
        mavenLocal()
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/public") }
        maven { url = uri("https://maven.aliyun.com/repository/public/") } //central+jcenter
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
//        maven { url = uri("https://maven.oschina.net/content/groups/public/") }
        maven { url = uri("https://jitpack.io") }
        maven{ url = uri("https://dl.bintray.com/kotlin/kotlin-eap")}
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        maven { url = uri("https://dl.bintray.com/ttgamesdk/public") }
        maven { url = uri("https://artifact.bytedance.com/repository/Volcengine/") }
        maven { url = uri("https://s01.oss.sonatype.org/content/repositories/public") }
        maven { url = uri("https://maven.aliyun.com/repository/public/") } //central+jcenter
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
//        maven { url = uri("https://maven.oschina.net/content/groups/public/") }
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}
include(":app")
include(":glide-framesequence-integration")
include(":glide-lottie-integration")
include(":glide-pag-integration")
include(":glide-svg-integration")
include(":glide-heif-integration")
include(":fresco-animated-pag")
include(":fresco-animated-lottie")
include(":fresco-static-heif")
include(":fresco-static-avif")
