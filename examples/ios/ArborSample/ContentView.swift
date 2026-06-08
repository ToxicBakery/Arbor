import SwiftUI
import ArborKit

struct ContentView: View {

    var body: some View {
        VStack(spacing: 16) {
            Image(systemName: "leaf.fill")
                .imageScale(.large)
                .font(.system(size: 48))
                .foregroundStyle(.green)
            Text("Arbor iOS Sample")
                .font(.headline)
            Text("Tap to write logs through Arbor's iOS Seedling (NSLog).\nWatch the Xcode console or Console.app.")
                .font(.footnote)
                .multilineTextAlignment(.center)
                .foregroundStyle(.secondary)
            Button("Write logs") {
                ArborDemo.run()
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
