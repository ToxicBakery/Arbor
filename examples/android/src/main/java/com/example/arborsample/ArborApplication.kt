package com.example.arborsample

import android.app.Application
import com.toxicbakery.logging.Arbor
import com.toxicbakery.logging.LogCatSeedling
import com.toxicbakery.logging.Seedling

class ArborApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Arbor.d("Log into the void! Make sure you sow a seedling before writing logs.")

        Seedling().let { seedling ->
            Arbor.sow(seedling)
            Arbor.d("Message using System.out")
            Arbor.d("With formatting %s", "example")
            Arbor.harvest(seedling)
        }

        Arbor.sow(LogCatSeedling())
        Arbor.d("Hello from kotlin")
        Arbor.tag("test").d("Hello with a tag")
        Arbor.d("Formatting %s", "messages")

        // Test extremely long logs
        Arbor.i((0..4096).joinToString(separator = "") { index ->
            ALPHABET[index % ALPHABET.length].toString()
        })

        Arbor.w((0..4096).joinToString(separator = "") { index ->
            val output = ALPHABET[index % ALPHABET.length].toString()
            if (output == "z") "\n"
            else output
        })

        Arbor.e(Exception("Test Message"), (0..4096).joinToString(separator = "") { index ->
            ALPHABET[index % ALPHABET.length].toString()
        })
    }

    companion object {
        private const val ALPHABET: String = "abcdefghijklmnopqrstuvwxyz"
    }

}
