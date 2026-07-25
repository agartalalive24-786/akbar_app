package com.akbar.chessvisionpro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AnalysisScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analysis Board", fontWeight = FontWeight.Bold) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Deep Engine Analysis", style = MaterialTheme.typography.headlineSmall)
            
            // Large analysis board
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Text(
                    "Chess Board Analysis",
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Multi-PV Display
            Text("Multi-PV Engine Lines", style = MaterialTheme.typography.titleMedium)
            repeat(4) { index ->
                AnalysisLineItem(lineNumber = index + 1, evaluation = "2.${index}", line = "e4 c5 Nf3 d6")
            }
        }
    }
}

@Composable
fun AnalysisLineItem(lineNumber: Int, evaluation: String, line: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Line $lineNumber", fontWeight = FontWeight.Bold)
            Text(evaluation, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Text(line, style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(1f))
        }
    }
}
