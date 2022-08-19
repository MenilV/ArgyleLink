import org.gradle.kotlin.dsl.provideDelegate

object Versions {
    //region Kotlin
    const val kotlin = "1.6.21"
    //endregion

    //region Gradle
    const val gradle = "7.1.3"
    //endregion

    //region Plugins
    const val versionsPlugin = "0.42.0"
    const val ktlintPlugin = "10.3.0"
    const val detektPlugin = "1.20.0"
    //endregion

    //region Dependencies
    const val core = "1.7.0"
    const val appCompat = "1.4.1"
    const val material = "1.5.0"
    const val junit = "4.13.2"
    const val junitExt = "1.1.3"
    const val espresso = "3.4.0"
    const val rxJava = "3.0.0"
    const val rxBinding = "3.1.0"
    const val hilt = "2.42"
    const val mockito = "4.4.0"
    const val roboelectric = "4.8.1"
    const val nav = "2.4.2"
    const val lifecycle = "2.4.1"
    const val glide = "4.13.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.10.0"
    const val okkHttpLoggingInterceptor = "4.10.0"
    const val mockWebServer = "4.10.0"
    const val moshi = "1.13.0"
    const val moshiKotlin = "1.13.0"
    const val moshiConverter = "2.9.0"
    const val moshiAdapters = "1.13.0"
    const val retrofitConverterScalars = "2.9.0"
    const val retrofitAdapterRxJava2 = "2.9.0"
    const val rxjavaAndroid = "2.1.1"
    //endregion
}

object Library {
    object AndroidX {
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
        val lifecycleCommon by lazy { "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}" }

        object Ktx {
            val core by lazy { "androidx.core:core-ktx:${Versions.core}" }
            val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.nav}" }
            val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.nav}" }
        }
    }

    object Google {
        val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
        val material by lazy { "com.google.android.material:material:${Versions.material}" }
    }

    object UI {
        val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }
        val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    }

    object Rx {
        val rxBinding by lazy { "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}" }
    }

    object Other {
        val okHttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
        val okHttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okkHttpLoggingInterceptor}" }
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
        val retrofitAdapterRxJava2 by lazy { "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitAdapterRxJava2}" }
        val rxjavaAndroid by lazy { "io.reactivex.rxjava2:rxandroid:${Versions.rxjavaAndroid}" }
        val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshi}" }
        val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshiKotlin}" }
        val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }
        val moshiAdapters by lazy { "com.squareup.moshi:moshi-adapters:${Versions.moshiAdapters}" }
    }

}

object TestLibrary {
    object Test {
        val junit by lazy { "junit:junit:${Versions.junit}" }
        val hilt by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
        val mockWebServer by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}" }
        val mockito by lazy { "org.mockito:mockito-core:${Versions.mockito}" }
        val roboelectric by lazy { "org.robolectric:robolectric:${Versions.roboelectric}" }
    }

    object AndroidTest {
        val junitExt by lazy { "androidx.test.ext:junit:${Versions.junitExt}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
        val hilt by lazy { "com.google.dagger:hilt-android-testing:${Versions.hilt}" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
        val navigation by lazy { "androidx.navigation:navigation-testing:${Versions.nav}" }

    }
}
