package com.example.arborsample

import android.app.Application
import com.toxicbakery.logging.Arbor
import com.toxicbakery.logging.LogCatSeedling

class ArborApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Arbor.sow(LogCatSeedling())
        Arbor.d("Hello from kotlin")
    }

}
