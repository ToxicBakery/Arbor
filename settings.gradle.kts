@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    // Do not allow modules to configure repositories
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        // KJS Requirements
        mavenCentral {
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
            }
        }
        google {
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
            }
        }
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/yarnpkg/yarn/releases/download") {
                    name = "Yarn Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.yarnpkg", "yarn") }
                }
            }
            filter { includeGroup("com.yarnpkg") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/WebAssembly/binaryen/releases/download") {
                    name = "Binaryen Distributions at $url"
                    patternLayout { artifact("version_[revision]/[module]-version_[revision]-[classifier].[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.github.webassembly", "binaryen") }
                }
            }
            filter { includeGroup("com.github.webassembly") }
        }
        mavenLocal()
    }
}

rootProject.name = "ArborLogging"

include(
    ":arbor",
    ":arbor-android",
)

if (settings.ext.properties.getOrElse("LibrariesOnly") { "false" } == "false") {
    include(
        ":examples:android",
        ":examples:java",
    )
}
