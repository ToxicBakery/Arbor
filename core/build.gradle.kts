@file:OptIn(ExperimentalWasmDsl::class)

import java.net.URI
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    `maven-publish`
    signing
    alias(libs.plugins.gradle.detekt)
    alias(libs.plugins.gradle.dokka)
    jacoco
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

kotlin {
    explicitApi()
    linuxX64()
    linuxArm64()
    jvm()
    js(IR) {
        browser {
            testTask {
                // Skip if CI
                enabled = "${System.getenv()["CI"]}".isEmpty()
            }
        }
        nodejs()
        useEsModules()
    }
    wasmWasi {
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.annotations.common)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(libs.kotlin.test.js)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }
    }
}

tasks.register("jacocoTestReportJvm", JacocoReport::class) {
    group = "jacoco"
    dependsOn.add("jvmTest")

    val buildDir = project.layout.buildDirectory.get().asFile
    val fileFilter =
        listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val commonTree = fileTree("$buildDir/classes/kotlin/common/main") {
        include(fileFilter)
    }
    val jvmTree = fileTree("\$buildDir/classes/kotlin/jvm/main") {
        include(fileFilter)
    }

    classDirectories.from(files(commonTree, jvmTree))
    sourceDirectories.from(
        files(
            "src/commonMain/kotlin",
            "src/jvmMain/kotlin"
        )
    )
    executionData.from(files("$buildDir/jacoco/jvmTest.exec"))

    reports {
        xml.required = true
        html.required = true
    }
}

tasks.register("jacocoTestReportUnified", JacocoReport::class) {
    group = "jacoco"
    dependsOn.addAll(listOf("jacocoTestReportJvm"/*, ":android:build"*/))

    val buildDirectory = layout.buildDirectory.get().asFile
    val fileFilter =
        listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val commonTree = fileTree("$buildDirectory/classes/kotlin/common/main") {
        include(fileFilter)
    }
    val jvmTree = fileTree("$buildDirectory/classes/kotlin/jvm/main") {
        include(fileFilter)
    }

    classDirectories.from(
        files(
            commonTree,
            jvmTree,
        )
    )
    sourceDirectories.from(
        files(
            "src/commonMain/kotlin",
            "src/jvmMain/kotlin",
        )
    )
    executionData.from(
        files(
            "$buildDirectory/jacoco/jvmTest.exec",
        )
    )

    reports {
        xml.required = true
        xml.outputLocation.set(file("$buildDirectory/reports/jacoco/unified/jacocoTestReportUnified.xml"))
        html.required = true
        html.outputLocation.set(file("$buildDirectory/reports/jacoco/unified/html"))
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("detekt.yml")
}

tasks.register("cleanGhPages", Delete::class.java) {
    group = "build"
    tasks.named("clean").get().dependsOn(this)

    delete(fileTree("gh-pages") {
        include("**")
        exclude("CNAME", "incdex.html")
    })
}

dokka {
    dokkaPublications.html {
        moduleName.set(project.name)
        moduleVersion.set(project.version.toString())
        outputDirectory.set(layout.projectDirectory.dir("gh-pages"))
        failOnWarning.set(false)
        suppressInheritedMembers.set(false)
        suppressObviousFunctions.set(true)
        offlineMode.set(false)
    }
}

val tasksNeedingSigning = listOf(
    tasks.register("dokkaJavadocCommonJar", Jar::class.java) {
        group = "publishing"
        dependsOn(tasks.getByName("dokkaGenerate"))
        archiveClassifier.set("javadoc")
        from(layout.buildDirectory.dir("dokka/html"))
    },
)

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            description = providers.gradleProperty("POM_DESCRIPTION")
            name = providers.gradleProperty("POM_NAME")
            url = providers.gradleProperty("POM_URL")
            scm {
                url = providers.gradleProperty("POM_SCM_URL")
                connection = providers.gradleProperty("POM_SCM_CONNECTION")
                developerConnection = providers.gradleProperty("POM_SCM_DEV_CONNECTION")
            }
            licenses {
                license {
                    name = providers.gradleProperty("POM_LICENCE_NAME")
                    url = providers.gradleProperty("POM_LICENCE_URL")
                    distribution = providers.gradleProperty("POM_LICENCE_DIST")
                }
            }
            developers {
                developer {
                    id = providers.gradleProperty("POM_DEVELOPER_ID")
                    name = providers.gradleProperty("POM_DEVELOPER_NAME")
                    email = providers.gradleProperty("POM_DEVELOPER_EMAIL")
                    organization = providers.gradleProperty("POM_DEVELOPER_ORGANIZATION")
                    organizationUrl = providers.gradleProperty("POM_DEVELOPER_ORGANIZATION_URL")
                }
            }
        }
    }

//    afterEvaluate {
//        publications.named("js") {
//            artifact tasks.named("dokkaJavadocCommonJar")
//        }
//        publications.named("jvm") {
//            artifact tasks.named("dokkaJavadocCommonJar")
//        }
//        publications.named("wasmJs") {
//            artifact tasks.named("dokkaJavadocCommonJar")
//        }
//        publications.named("wasmWasi") {
//            artifact tasks.named("dokkaJavadocCommonJar")
//        }
//        publications.named("kotlinMultiplatform") {
//            artifact tasks.named("dokkaJavadocCommonJar")
//        }
//    }

    repositories {
        val releaseUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
        val snapshotUrl = "https://oss.sonatype.org/content/repositories/snapshots"

        maven {
            url = URI.create(if (!"$version".contains("SNAPSHOT")) releaseUrl else snapshotUrl)
            credentials {
                username = System.getenv()["SONATYPE_USERNAME"].orEmpty()
                password = System.getenv()["SONATYPE_PASSWORD"].orEmpty()
            }
        }
    }
}

signing {
    isRequired = false
    sign(publishing.publications)
}

tasks.withType(AbstractPublishToMaven::class.java).configureEach {
    dependsOn(tasksNeedingSigning)
}
tasks.withType(Sign::class.java).configureEach {
    dependsOn(tasksNeedingSigning)
}
tasks.withType(PublishToMavenRepository::class.java).configureEach {
    dependsOn(tasks.withType(Sign::class.java))
}

tasks.named("check").get().dependsOn("detekt")
tasks.named("build").get().dependsOn(
    "jacocoTestReportJvm",
    "jacocoTestReportUnified",
)
