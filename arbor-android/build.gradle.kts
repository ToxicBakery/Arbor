import com.vanniktech.maven.publish.DeploymentValidation
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.gradle.android.library)
    alias(libs.plugins.gradle.detekt)
    alias(libs.plugins.gradle.dokka)
    alias(libs.plugins.gradle.dokka.javadoc)
    alias(libs.plugins.gradle.maven.publish)
    jacoco
}

group = "com.ToxicBakery.logging"

android {
    namespace = "$group"
    compileSdk = 36
    defaultConfig {
        minSdk = 21
    }
    buildTypes {
        debug {
            enableUnitTestCoverage = true
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

dependencies {
    implementation(projects.arbor)
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.mockito.core)
}

dokka {
    dokkaSourceSets {
        create("arbor-android") {
            sourceRoots.from(file("src/main/kotlin"))
        }
    }
    dokkaPublications {
        html {
            moduleName.set(project.name)
            moduleVersion.set(project.version.toString())
            outputDirectory.set(rootProject.layout.projectDirectory.dir("gh-pages/${project.name}"))
            failOnWarning.set(false)
            suppressInheritedMembers.set(false)
            suppressObviousFunctions.set(true)
            offlineMode.set(false)
        }
        javadoc {
            moduleName.set(project.name)
            moduleVersion.set(project.version.toString())
            failOnWarning.set(false)
            suppressInheritedMembers.set(false)
            suppressObviousFunctions.set(true)
            offlineMode.set(false)
        }
    }
}

val tasksNeedingSigning = listOf(
    tasks.register("dokkaJavadocAndroidJar", Jar::class.java) {
        group = "publishing"
        dependsOn(tasks.getByName("dokkaGenerateJavadoc"))
        archiveClassifier.set("javadoc")
        from(tasks.dokkaGeneratePublicationJavadoc.flatMap { it.outputDirectory })
    },
)

mavenPublishing {
    coordinates("${project.group}", project.name, "${project.version}")

    if ("${findProperty("ci")}" == "true") {
        publishToMavenCentral(automaticRelease = false, validateDeployment = DeploymentValidation.NONE)
        signAllPublications()
    }

    pom {
        description = providers.gradleProperty("POM_DESCRIPTION")
        name = "${providers.gradleProperty("POM_NAME")}-Android"
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

tasks.register("jacocoTestReportAndroid", JacocoReport::class.java) {
    group = "jacoco"
    dependsOn.add("test")

    val fileFilter =
        listOf("**/R.class", "**/R\$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*", "android/**/*.*")
    val debugTree = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug")) {
        include(fileFilter)
    }

    classDirectories.from(files(debugTree))
    sourceDirectories.from(files("src/androidMain/kotlin"))
    executionData.from(fileTree(layout.buildDirectory.dir("jacoco")) {
        include("*.exec")
    })

    reports {
        xml.required = true
        html.required = true
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(project(projects.arbor.path).files("detekt.yml"))
}

tasks.withType<Detekt> {
    jvmTarget = JavaVersion.VERSION_21.majorVersion
}

tasks.named("check").get().dependsOn("detekt")
tasks.named("build").get().dependsOn(
    "dokkaJavadocAndroidJar",
    "jacocoTestReportAndroid",
)
