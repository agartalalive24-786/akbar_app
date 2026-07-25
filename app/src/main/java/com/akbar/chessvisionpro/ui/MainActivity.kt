package com.akbar.chessvisionpro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akbar.chessvisionpro.ui.theme.ChessVisionProTheme
import com.akbar.chessvisionpro.ui.screens.HomeScreen
import com.akbar.chessvisionpro.ui.screens.PuzzleScreen
import com.akbar.chessvisionpro.ui.screens.AnalysisScreen
import com.akbar.chessvisionpro.ui.screens.SearchScreen
import com.akbar.chessvisionpro.ui.screens.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessVisionProTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ChessVisionProApp()
                }
            }
        }
    }
}

@Composable
fun ChessVisionProApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("puzzle/{puzzleId}") { backStackEntry ->
            val puzzleId = backStackEntry.arguments?.getString("puzzleId") ?: ""
            PuzzleScreen(navController, puzzleId)
        }
        composable("analysis") {
            AnalysisScreen(navController)
        }
        composable("search") {
            SearchScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}
