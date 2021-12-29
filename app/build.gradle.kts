import extensions.androidTestImplementation
import extensions.implementation
import extensions.stringBuildConfigField
import extensions.testImplementation

plugins {
    id (Plugins.application)
    id (Plugins.kotlinAndroid)
    id (Plugins.kotlinKapt)
    id (Plugins.kotlinParcelize)
}


android {
    compileSdkVersion(AndroidConfig.compileSdk)
    defaultConfig {
        applicationId  = AndroidConfig.applicationId
        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.androidTestInstrumentationRunner
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeProd.isMinifyEnabled
            isDebuggable = BuildTypeProd.isDebuggable
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            stringBuildConfigField(
                BuildConfigVariables.BASE_URL,
                BuildConfigVariables.RELEASE_APP_BASE_URL
            )
        }

        maybeCreate(BuildType.DEBUG).apply {
            isShrinkResources = false
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            stringBuildConfigField(
                BuildConfigVariables.BASE_URL,
                BuildConfigVariables.DEV_APP_BASE_URL
            )
        }
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Android Core Dependencies
    implementation (Dependencies.coreKTX)
    implementation (Dependencies.appCompat)
    implementation (Dependencies.material)


    // Android Jetpack Components
    implementation (Dependencies.fragmentKTX)
    implementation (Dependencies.activityKTX)
    implementation (Dependencies.recyclerView)
    implementation (Dependencies.constraintLayout)
    implementation (Dependencies.liveDataKTX)
    implementation (Dependencies.viewModelKTX)
    implementation (Dependencies.commonJava8)
    implementation (Dependencies.fragmentNavigationKTX)
    implementation (Dependencies.navigationUiKTX)
    implementation (Dependencies.legacySupport)


    // Testing
    testImplementation (Dependencies.jUnit)
    testImplementation (Dependencies.coreTesting)
    testImplementation (Dependencies.kotlinxCoroutinesTest)
    testImplementation (Dependencies.mockk)
    testImplementation (Dependencies.mockkWebServer)
    androidTestImplementation (Dependencies.extJunit)
    androidTestImplementation (Dependencies.espressoCore)
    androidTestImplementation (Dependencies.mockAndroid)

    // Coroutines
    implementation (Dependencies.coroutinesCore)
    implementation (Dependencies.coroutinesAndroid)

    // Koin
    implementation (Dependencies.koinScope)
    implementation (Dependencies.koinViewModel)
    implementation (Dependencies.koinFragment)

    // Retrofit
    implementation (Dependencies.retrofit)
    implementation (Dependencies.okHttp)
    implementation (Dependencies.okHttpLogging)
    implementation (Dependencies.ok2Curl)
    implementation (Dependencies.retrofitConverterGson)
    implementation (Dependencies.gson)

    // Coil
    implementation (Dependencies.coil)

    // Photo View
    implementation (Dependencies.photoView)

    // Timber
    implementation (Dependencies.timber)
    implementation (Dependencies.coreKTXPlus)
    implementation (Dependencies.lifecycleViewModelKTX)
    implementation (Dependencies.kotlinStdlibJdk7)
}
repositories {
    mavenCentral()
}