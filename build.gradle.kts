buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugin.kotlinGradle)
        classpath(BuildPlugin.androidGradle)
        classpath(BuildPlugin.hiltAndroidGradle)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}