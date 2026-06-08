import SwiftUI
import ArborKit

@main
struct ArborSampleApp: App {

    init() {
        // No seedling has been sown yet, so this log goes into the void. Always sow before logging.
        Arbor.shared.d(msg_: "Log into the void! Make sure you sow a seedling before writing logs.")

        // Sow the iOS Seedling. It writes to the Apple unified logging system via NSLog, so messages
        // show up in the Xcode console and Console.app.
        Arbor.shared.sow(seedling: Seedling())

        ArborDemo.run()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

/// A handful of calls exercising the Arbor API from Swift. Mirrors the Android sample's ArborApplication.
enum ArborDemo {

    static func run() {
        // Plain messages. Note the `msg_:` label: Arbor also exposes a `msg:` overload that takes a
        // `() -> String` closure, so the Kotlin/Native binding disambiguates the String overload with a
        // trailing underscore.
        Arbor.shared.d(msg_: "Hello from Swift")
        Arbor.shared.tag(tag: "Sample").i(msg_: "Hello with a tag")
        Arbor.shared.w(msg_: "Something worth a warning")

        // Lazily evaluated message. The closure only runs when at least one seedling is sown.
        Arbor.shared.v(msg: { "Verbose message built lazily" })

        // Kotlin/Native has no String.format, so the iOS Seedling interpolates positional args itself.
        let values: [NSString] = ["one", "two"]
        let formatArgs = KotlinArray<AnyObject>(size: Int32(values.count)) { index in
            values[Int(truncating: index)]
        }
        Arbor.shared.d(msg: "Formatting %s and %s", args: formatArgs)

        // Logging a throwable appends its stack trace to the message.
        Arbor.shared.e(
            throwable: KotlinThrowable(message: "Sample failure"),
            msg_: "Something went wrong"
        )
    }

}
