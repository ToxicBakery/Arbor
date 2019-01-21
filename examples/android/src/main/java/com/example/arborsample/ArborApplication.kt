package com.example.arborsample

import android.app.Application
import android.util.Log
import com.toxicbakery.logging.Arbor
import com.toxicbakery.logging.LogCatSeedling

class ArborApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Arbor.sow(LogCatSeedling())
        Arbor.d("Hello from kotlin")

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
