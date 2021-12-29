import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

// Required since Gradle 4.10+.
repositories {
    google()
    jcenter()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.google.com")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginVersions {
    const val gradle = "4.0.0"
    const val kotlin = "1.6.10"
    const val googleServices = "4.3.3"
}

object Plugins {
    const val gradleDependency = "com.android.tools.build:gradle:${PluginVersions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}"
    const val googleServices = "com.google.gms:google-services:${PluginVersions.googleServices}"
}

dependencies {
    implementation(Plugins.gradleDependency)
    implementation(Plugins.googleServices)
    implementation(Plugins.kotlinGradle)
}