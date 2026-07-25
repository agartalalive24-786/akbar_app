package com.akbar.chessvisionpro

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ChessVisionProApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize chess engine
        initializeChessEngine()
    }
    
    private fun initializeChessEngine() {
        // Chess engine initialization logic
        Timber.d("Chess Vision Pro initialized successfully")
    }
}
