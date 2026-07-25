package com.akbar.chessvisionpro.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akbar.chessvisionpro.ui.screens.HomeScreen
import com.akbar.chessvisionpro.ui.screens.PuzzleScreen
import com.akbar.chessvisionpro.ui.screens.SearchScreen
import com.akbar.chessvisionpro.ui.screens.AnalysisScreen
import com.akbar.chessvisionpro.ui.theme.ChessVisionProTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessVisionProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        
        composable("puzzle/{puzzleId}") { backStackEntry ->
            val puzzleId = backStackEntry.arguments?.getString("puzzleId") ?: "random"
            PuzzleScreen(navController, puzzleId)
        }
        
        composable("puzzle/random") {
            PuzzleScreen(navController, "random")
        }
        
        composable("search") {
            SearchScreen(navController)
        }
        
        composable("analysis") {
            AnalysisScreen(navController)
        }
    }
}
