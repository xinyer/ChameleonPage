// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
}

allprojects {
    apply("$rootDir/config/detekt.gradle")
//    apply("$rootDir/code_analysis/ktlint.gradle")
}
