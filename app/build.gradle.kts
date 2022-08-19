import io.gitlab.arturbosch.detekt.Detekt

plugins {
    androidApp()
    kotlinAndroid()
    kotlinParcelize()
    kotlinKapt()
    dependencyUpdates()
    hilt()
    ktlint()
    detekt()
}

android {
    compileSdk = Config.Android.compileSDK

    defaultConfig {
        applicationId = Config.Android.appId
        minSdk = Config.Android.minSDK
        versionCode = Config.Android.versionCode
        versionName = Config.Android.versionName

        testInstrumentationRunner = Config.Android.instrumentationRunner
    }

    buildTypes {
        debug {
            buildConfigField("String", "API", Config.Env.API)
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    lint {
        abortOnError = false
    }

    kapt {
        correctErrorTypes = true
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    ktlint {
        android.set(true)
        outputColorName.set("RED")
        additionalEditorconfigFile.set(file("$projectDir/config/.editorconfig"))
    }

    detekt {
        buildUponDefaultConfig = true
        source = files("$projectDir/app/src")
        config = files("$projectDir/config/detekt.yml")
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
            html.outputLocation.set(file("$projectDir/app/build/reports/detekt/report.html"))
        }
    }

    tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask>().configureEach {
        workerMaxHeapSize.set("2048m")
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    dependencies {
        implementation(Library.AndroidX.appCompat)
        implementation(Library.AndroidX.Ktx.core)
        implementation(Library.AndroidX.Ktx.navigationFragment)

        implementation(Library.Google.material)
        implementation(Library.Google.hilt)
        kapt(Library.Google.hiltCompiler)
        implementation(Library.Rx.rxBinding)
        implementation(Library.UI.glide)
        kapt(Library.UI.glideCompiler)

        implementation(Library.Other.okHttp)
        implementation(Library.Other.okHttpLoggingInterceptor)
        implementation(Library.Other.moshi)
        implementation(Library.Other.moshiKotlin)
        implementation(Library.Other.moshiConverter)
        implementation(Library.Other.moshiAdapters)
        implementation(Library.Other.retrofit)
        implementation(Library.Other.retrofitAdapterRxJava2)
        implementation(Library.Other.rxjavaAndroid)

        testImplementation(TestLibrary.Test.junit)
        testImplementation(TestLibrary.Test.hilt)
        testImplementation(TestLibrary.Test.mockWebServer)
        testImplementation(TestLibrary.Test.mockito)
        testImplementation(TestLibrary.Test.roboelectric)
        kaptTest(TestLibrary.Test.hiltCompiler)

        androidTestImplementation(TestLibrary.AndroidTest.junitExt)
        androidTestImplementation(TestLibrary.AndroidTest.espresso)
        androidTestImplementation(TestLibrary.AndroidTest.navigation)
        androidTestImplementation(TestLibrary.AndroidTest.hilt)
        kaptAndroidTest(TestLibrary.AndroidTest.hilt)
    }
}
