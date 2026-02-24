plugins {
    alias(libs.plugins.gradle.android.application) apply false
    alias(libs.plugins.gradle.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.gradle.detekt) apply false
    alias(libs.plugins.gradle.dokka) apply false
    alias(libs.plugins.gradle.dokka.javadoc) apply false
    alias(libs.plugins.gradle.maven.publish) apply false
}

buildscript {
    dependencies {
        classpath(libs.jacoco.core)
    }
}

fun getGitCommitCount(): String? {
    try {
        return providers.exec {
            commandLine("git", "rev-list", "HEAD", "--count")
        }.standardOutput.asText.get().trim()
    } catch (e: Exception) {
        logger.log(LogLevel.ERROR, "Failed to get Git commit count, is Git installed?", e)
        return null
    }
}

subprojects {
    val isCI = "${findProperty("ci")}" == "true"
    val isMaster = System.getenv()["CIRCLE_BRANCH"] == "master"
    val buildNumber = getGitCommitCount() ?: "0"

    version = "3.0.$buildNumber" + if (isCI && isMaster) "" else "-SNAPSHOT"
}

tasks.register("cleanGhPages", Delete::class.java) {
    group = "build"
    tasks.named("clean").get().dependsOn(this)

    delete(fileTree("gh-pages") {
        include("**")
        exclude("CNAME", "incdex.html")
    })
}
