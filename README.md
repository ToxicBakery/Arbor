![arbor](https://github.com/ToxicBakery/Arbor/blob/master/art/arbor.png?raw=true)

# Arbor [![CircleCI](https://circleci.com/gh/ToxicBakery/Arbor.svg?style=svg)](https://circleci.com/gh/ToxicBakery/Arbor) [![Maven Central](https://img.shields.io/maven-metadata/v/https/repo1.maven.org/maven2/com/toxicbakery/logging/arbor/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/logging/)
Logging implementation for Kotlin Multiplatform. Unlike most multiplatform logging implementations, Android is 
isolated to its own optional module to allow for pure JVM libraries to be mixed with Android libraries and applications.

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
Arbor is a Kotlin Multiplatform project supporting multiple target platforms.

**Supported Platforms:**
 - js
 - jvm
 - linuxArm64
 - linuxX64
 - macosX64()
 - macosArm64()
 - iosX64()
 - iosArm64()
 - iosSimulatorArm64()
 - watchosArm32()
 - watchosArm64()
 - watchosSimulatorArm64()
 - watchosDeviceArm64()
 - watchosX64()
 - tvosArm64()
 - tvosSimulatorArm64()
 - tvosX64()
 - mingwX64
 - wasmJS
 - wasmWasi

**Optional Platforms:**
 - android

Kotlin
```kotlin
implementation("com.toxicbakery.logging:arbor:<version>")
```

Android
```kotlin
implementation("com.toxicbakery.logging:arbor:<version>")
implementation("com.toxicbakery.logging:arbor-android:<version>")
```
