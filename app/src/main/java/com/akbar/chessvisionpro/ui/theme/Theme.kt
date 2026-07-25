package com.akbar.chessvisionpro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF3700B3),
    onPrimaryContainer = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC5),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color(0xFFB2EBE7),
    background = Color(0xFF121212),
    surface = Color(0xFF1F1F1F),
    error = Color(0xFFCF6679)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005E),
    secondary = Color(0xFF03DAC5),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFB2EBE7),
    onSecondaryContainer = Color(0xFF006661),
    background = Color(0xFFFBF8FF),
    surface = Color.White,
    error = Color(0xFFB3261E)
)

@Composable
fun ChessVisionProTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
