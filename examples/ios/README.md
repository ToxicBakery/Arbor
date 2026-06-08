# Arbor iOS Sample

A minimal SwiftUI app that consumes the Arbor Kotlin Multiplatform library from Swift and logs through
Arbor's iOS `Seedling`, which writes to the Apple unified logging system via `NSLog`.

It is the iOS counterpart to [`examples/android`](../android). Because an iOS app is an Xcode project rather
than a Gradle module, this sample is **not** part of `settings.gradle.kts`; instead it links the `:arbor`
module's framework using the standard Kotlin/Native direct-integration approach.

## What it shows

`ArborSampleApp` sows a `Seedling()` and then exercises the API (see `ArborDemo.run()`):

```swift
import ArborKit

Arbor.shared.sow(seedling: Seedling())
Arbor.shared.d(msg_: "Hello from Swift")                 // plain message
Arbor.shared.tag(tag: "Sample").i(msg_: "Hello with a tag")
Arbor.shared.v(msg: { "Verbose message built lazily" })   // lazy () -> String overload
Arbor.shared.d(msg: "Formatting %s and %s", args: /* KotlinArray */)
Arbor.shared.e(throwable: KotlinThrowable(message: "..."), msg_: "Something went wrong")
```

> Note the `msg_:` argument label on the String overloads: Arbor also exposes a `msg:` overload that takes a
> `() -> String` closure, so the Kotlin/Native binding disambiguates the plain-String overload with a trailing
> underscore.

## How the framework is wired up

The `:arbor` module declares static iOS frameworks named `ArborKit` (see `arbor/build.gradle.kts`). The Xcode
project pulls them in with two pieces:

1. A **Run Script** build phase (`Build Arbor framework`) that runs before compilation:

   ```sh
   cd "$SRCROOT/../.."
   ./gradlew :arbor:embedAndSignAppleFrameworkForXcode
   ```

   The Gradle task reads Xcode's environment (`CONFIGURATION`, `SDK_NAME`, `ARCHS`, …) and assembles the right
   framework under `arbor/build/xcode-frameworks/<config>/<sdk>/`.

2. Build settings that point at that output and link it:

   - `FRAMEWORK_SEARCH_PATHS = $(SRCROOT)/../../arbor/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`
   - `OTHER_LDFLAGS = -framework ArborKit`
   - `ENABLE_USER_SCRIPT_SANDBOXING = NO` — required so the Gradle script may write outside the Xcode project
     directory.

## Prerequisites

- Xcode (with an iOS simulator runtime installed)
- A JDK on `PATH` (used by `./gradlew`)

## Run in Xcode

```sh
open examples/ios/ArborSample.xcodeproj
```

Select the `ArborSample` scheme and an iOS Simulator, then Run. The first build invokes Gradle to compile the
Kotlin/Native framework, so it takes a little longer than subsequent builds.

## Build / run from the command line

```sh
# Build for a simulator
xcodebuild -project examples/ios/ArborSample.xcodeproj \
  -scheme ArborSample \
  -destination 'platform=iOS Simulator,name=iPhone 17 Pro' \
  build

# Install and launch on the booted simulator
APP=$(xcodebuild -project examples/ios/ArborSample.xcodeproj -scheme ArborSample -showBuildSettings \
  | awk '/ BUILT_PRODUCTS_DIR /{d=$3}/ FULL_PRODUCT_NAME /{n=$3}END{print d"/"n}')
xcrun simctl install booted "$APP"
xcrun simctl launch booted com.example.arborsample
```

## Viewing the logs

`NSLog` writes to the unified logging system, so the messages show up in **Console.app** and in the Xcode
console while debugging. From the command line they are emitted at info/debug level, so include those flags:

```sh
xcrun simctl spawn booted log show --last 1m --info --debug \
  --predicate 'process == "ArborSample"' | grep -E '^[DVIWEF]/'
```

Expected output:

```
D/Hello from Swift
I/Sample: Hello with a tag
W/Something worth a warning
V/Verbose message built lazily
D/Formatting one and two
E/Something went wrong
...stack trace...
```
