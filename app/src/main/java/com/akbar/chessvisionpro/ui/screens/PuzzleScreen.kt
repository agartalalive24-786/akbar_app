package com.akbar.chessvisionpro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PuzzleScreen(navController: NavController, puzzleId: String) {
    var showEvaluation by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Puzzle #$puzzleId", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Chess Board
            ChessBoardWidget()
            
            // Puzzle Info
            PuzzleInfoCard()
            
            // Controls
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { /* Previous move */ },
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.NavigateBefore, contentDescription = "Previous")
                }
                
                IconButton(
                    onClick = { /* Hint */ },
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Hint")
                }
                
                IconButton(
                    onClick = { showEvaluation = !showEvaluation },
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.QueryStats, contentDescription = "Evaluation")
                }
                
                IconButton(
                    onClick = { /* Next puzzle */ },
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
                ) {
                    Icon(Icons.Default.NavigateNext, contentDescription = "Next")
                }
            }
            
            if (showEvaluation) {
                EvaluationBar()
            }
        }
    }
}

@Composable
fun ChessBoardWidget() {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .background(
                color = Color(0xFFD2B48C),
                shape = RoundedCornerShape(12.dp)
            )
            .border(3.dp, Color(0xFF8B7355), RoundedCornerShape(12.dp))
    ) {
        // Chess board grid
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            repeat(8) { row ->
                Row(modifier = Modifier.weight(1f)) {
                    repeat(8) { col ->
                        val isLight = (row + col) % 2 == 0
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    if (isLight) Color(0xFFF0D9B5) else Color(0xFFB58863)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PuzzleInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Rating: 1500", fontWeight = FontWeight.Bold)
                Text("Popularity: 92%", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Theme: Removal of Defender",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                "Opening: Sicilian Defense",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun EvaluationBar() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text("Engine Analysis", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { 0.65f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                trackColor = Color(0xFFE0E0E0)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("White: +2.3 | Best: Nf3", style = MaterialTheme.typography.labelSmall)
        }
    }
}
