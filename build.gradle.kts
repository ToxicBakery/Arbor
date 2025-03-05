import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.targets.js.binaryen.BinaryenRootExtension
import org.jetbrains.kotlin.gradle.targets.js.binaryen.BinaryenRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(libs.plugins.gradle.android.application) apply false
    alias(libs.plugins.gradle.android.library) apply false
    alias(libs.plugins.gradle.android.kotlin) apply false
    alias(libs.plugins.gradle.detekt) apply false
    alias(libs.plugins.gradle.dokka) apply false
}

buildscript {
    dependencies {
        classpath(libs.jacoco.core)
    }
}

fun getGitCommitCount(): String? {
    try {
        val process = ProcessBuilder("git", "rev-list", "HEAD", "--count").start()
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
}

plugins.apply {
    withType<NodeJsRootPlugin> {
        the<NodeJsRootExtension>().downloadBaseUrl = null
    }
    withType<YarnPlugin> {
        the<YarnRootExtension>().downloadBaseUrl = null
    }
    withType<BinaryenRootPlugin> {
        the<BinaryenRootExtension>().downloadBaseUrl = null
    }
}