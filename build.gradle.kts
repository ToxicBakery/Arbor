
plugins {
    alias(libs.plugins.gradle.android.application) apply false
    alias(libs.plugins.gradle.android.library) apply false
    alias(libs.plugins.gradle.android.kotlin) apply false
    alias(libs.plugins.gradle.detekt) apply false
    alias(libs.plugins.gradle.dokka) apply false
}

fun getGitCommitCount(): String? {
    try {
        val process = Runtime.getRuntime().exec("git rev-list HEAD --count")
        return process.inputStream.bufferedReader().readText().trim()
    } catch (e: Exception) {
        logger.log(LogLevel.ERROR, "Failed to get Git commit count, is Git installed?", e)
        return null
    }
}

subprojects {
    val isCI = !"${System.getenv().getOrDefault("CI", "")}".isEmpty()
    val isMaster = System.getenv().getOrDefault("CIRCLE_BRANCH", "") == "master"
    val buildNumber = getGitCommitCount() ?: "0"

    group = "com.ToxicBakery.logging"
    version = "2.0.$buildNumber" + if (isCI && isMaster) "" else "-SNAPSHOT"

    repositories {
        google()
        mavenCentral()
    }
}
