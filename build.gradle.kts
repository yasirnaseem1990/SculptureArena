// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven( url = "https://www.jitpack.io" )
        jcenter()
    }
    dependencies {
        classpath(ProjectPlugins.pathGradle)
        classpath(ProjectPlugins.pathGradleKotlin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven (url  = "https://www.jitpack.io" )
        jcenter()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}