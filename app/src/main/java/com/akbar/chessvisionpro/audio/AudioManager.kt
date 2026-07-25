package com.akbar.chessvisionpro.audio

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var mediaPlayer: MediaPlayer? = null
    private var isEnabled = true
    
    fun setEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
    
    fun playMoveSound(soundType: SoundType = SoundType.MOVE) {
        if (!isEnabled) return
        
        try {
            val fileName = when (soundType) {
                SoundType.MOVE -> "move.mp3"
                SoundType.CHECK -> "check.mp3"
                SoundType.CAPTURE -> "capture.mp3"
                SoundType.CASTLE -> "castle.mp3"
                SoundType.CHECKMATE -> "checkmate.mp3"
                SoundType.ERROR -> "error.mp3"
            }
            
            val assetFile = File(context.filesDir, fileName)
            
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(assetFile.absolutePath)
                setVolume(0.7f, 0.7f)
                prepare()
                start()
            }
            
            Timber.d("Playing sound: $fileName")
        } catch (e: Exception) {
            Timber.e(e, "Error playing sound")
        }
    }
    
    fun stopSound() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            Timber.e(e, "Error stopping sound")
        }
    }
    
    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

enum class SoundType {
    MOVE,
    CHECK,
    CAPTURE,
    CASTLE,
    CHECKMATE,
    ERROR
}
