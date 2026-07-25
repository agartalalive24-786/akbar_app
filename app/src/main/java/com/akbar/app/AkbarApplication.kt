package com.akbar.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AkbarApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize application-level components
    }
}
