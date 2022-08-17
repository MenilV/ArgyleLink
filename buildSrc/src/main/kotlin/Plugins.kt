import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object BuildPlugin{
    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltAndroidGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
}

fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.kotlinKapt(): PluginDependencySpec =
    kotlin("kapt")

fun PluginDependenciesSpec.kotlinParcelize(): PluginDependencySpec =
    id("kotlin-parcelize")

fun PluginDependenciesSpec.dependencyUpdates(): PluginDependencySpec =
    id("com.github.ben-manes.versions").version(Versions.versionsPlugin)

fun PluginDependenciesSpec.ktlint(includeVersion: Boolean = true): PluginDependencySpec =
    id("org.jlleitschuh.gradle.ktlint").also { if (includeVersion) it.version(Versions.ktlintPlugin) }

fun PluginDependenciesSpec.detekt(includeVersion: Boolean = true): PluginDependencySpec =
    id("io.gitlab.arturbosch.detekt").also { if (includeVersion) it.version(Versions.detektPlugin) }

fun PluginDependenciesSpec.hilt() : PluginDependencySpec =
    id("dagger.hilt.android.plugin")