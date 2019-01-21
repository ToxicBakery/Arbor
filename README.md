![arbor](https://github.com/ToxicBakery/Arbor/blob/master/art/arbor.png?raw=true)

# Arbor [![CircleCI](https://circleci.com/gh/ToxicBakery/Arbor.svg?style=svg)](https://circleci.com/gh/ToxicBakery/Arbor) [![Maven Central](https://img.shields.io/maven-central/v/com.ToxicBakery.logging/common.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/logging/) [![Maven Central](https://img.shields.io/maven-metadata/v/https/oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/logging/common/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/logging/)
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

## Install
Arbor is a Kotlin Multiplatform project supporting JavaScript, JVM, and Android platforms.

Android
```groovy
implementation "com.ToxicBakery.logging:arbor-android:1.+"
```

JavaScript
```groovy
implementation "com.ToxicBakery.logging:arbor-js:1.+"
```

JVM
```groovy
implementation "com.ToxicBakery.logging:arbor-jvm:1.+"
```

Additionally, other multiplatform projects can references the metadata module for common source usage.
```groovy
implementation "com.ToxicBakery.logging:arbor-metadata:1.+"
```
