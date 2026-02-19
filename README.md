![arbor](https://github.com/ToxicBakery/Arbor/blob/master/art/arbor.png?raw=true)

# Arbor [![CircleCI](https://circleci.com/gh/ToxicBakery/Arbor.svg?style=svg)](https://circleci.com/gh/ToxicBakery/Arbor) [![Maven Central](https://img.shields.io/maven-metadata/v/https/oss.sonatype.org/content/repositories/releases/com/ToxicBakery/logging/arbor/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/logging/) [![Maven Central](https://img.shields.io/maven-metadata/v/https/oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/logging/arbor/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/logging/) [![codecov](https://codecov.io/gh/ToxicBakery/Arbor/branch/master/graph/badge.svg)](https://codecov.io/gh/ToxicBakery/Arbor)
Timber like logging implementation for Kotlin Multiplatform.

## Purpose
This library was built as a practical experiment with Kotlin Multiplatform and specifically the new single module approach. Despite Kotlin's excellent documentation it was unclear how to properly publish all artifacts to Maven such as sources and documentation. Hopefully this project provides a more complete template for others to use.

## API
Arbor follows a very similar usage pattern to [Timber](https://github.com/jakewharton/timber). Key differences exist in feature support and terminology which was done purely because I had a thesaurus laying around.

##### Sow Your Seedling
Seedlings are platform specific logging implementations such as using `Logcat` in Android and `println` in JavaScript.

Android
```kotlin
Arbor.sow(LogCatSeedling())
```

JavaScript
```kotlin
Arbor.sow(Seedling())
```

JVM
```kotlin
Arbor.sow(Seedling())
```

Custom seedlings can be created from the [com.toxicbakery.logging.ISeedling](https://github.com/ToxicBakery/Arbor/blob/master/common/src/commonMain/kotlin/com/toxicbakery/logging/ISeedling.kt) interface.

##### Logging
All logging is triggered from the [Arbor](https://github.com/ToxicBakery/Arbor/blob/master/common/src/commonMain/kotlin/com/toxicbakery/logging/Arbor.kt) static methods which supports debug, info, verbose, warning, error, and wtf. Logging calls are passed to the underlying branch implementation and finally to the sown seedling instances. 

```kotlin
Arbor.d("Hello World!")
```

Log tagging is automatic on Android but can be easily overridden.

```kotlin
Arbor.tag("Custom Tag").i("My Log with a tag.")
```

##### Kotlin Extensions
While Arbor supports writing string concatenations, leveraging Kotlin concatenations leads to strings being evaluated regardless of if they will be logged. In release that means CPU cycles may be wasted towards logging that is ultimately never printed. The `arbor` Kotlin extension leverages functions to work around this.

Debug message
```kotlin
arbor { "Debug" }
```

Changing the log level
```kotlin
arbor(LogLevel.E) { "Error" }
```

Levering custom tags via Arbor branches
```kotlin
val tag = Arbor.tag("MyTag")
arbor(branch = tag) { "Custom Tag" }
```

## Install
Arbor is a Kotlin Multiplatform project support multiple target platforms.

Supported Platforms:
 - Android
 - JS
 - JVM
 - LinuxArm64
 - LinuxX64
 - WasmWasi

Kotlin
```kotlin
implementation("com.ToxicBakery.logging:arbor:3.+")
```

Android
```groovy
implementation("com.ToxicBakery.logging:arbor-android:3.+")
```

## Migrating from Timber
Library migrations can be difficult. I've written a drop in kotlin file that can be placed anywhere in your application for a generally 1:1 mapping of Timber to Arbor. Notably missing is extension functions for string formatting.

https://gist.github.com/ToxicBakery/e55f55ec73450257901431c06eb1e969

Alternately, Kotlin can be leveraged more directly if you prefer.

```kotlin
package timber.log

typealias Timber = com.toxicbakery.logging.Arbor
```

This has a drawback in that `Timber.tag(...)` calls will not work.
