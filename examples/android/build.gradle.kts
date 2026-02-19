plugins {
    alias(libs.plugins.gradle.android.application)
}

group = "com.ToxicBakery.logging"

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.arborsample"
        namespace = "com.example.arborsample"
        minSdk = 21
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlin.stdlib)
    implementation(projects.arbor)
    implementation(projects.arborAndroid)
}
