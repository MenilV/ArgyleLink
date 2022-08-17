import org.gradle.api.JavaVersion

object Config {
    object Android {
        const val appId = "com.menilv"
        const val compileSDK = 32
        const val minSDK = 26
        const val versionCode = 1
        const val versionName = "1.0.0"
        const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    const val jvmTarget = "11"
    val javaVersion = JavaVersion.VERSION_11

    object Env {
        const val API = "\"https://api-sandbox.argyle.com/v1/\""
    }

}