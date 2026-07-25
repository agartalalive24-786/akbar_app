package com.akbar.chessvisionpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun SearchScreen(navController: NavController) {
    var minRating by remember { mutableStateOf(1000) }
    var maxRating by remember { mutableStateOf(2000) }
    var selectedTheme by remember { mutableStateOf("All") }
    var selectedOpening by remember { mutableStateOf("All") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Puzzles", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text("Rating Range", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                RangeSlider(
                    value = minRating.toFloat()..maxRating.toFloat(),
                    onValueChange = {
                        minRating = it.start.toInt()
                        maxRating = it.endInclusive.toInt()
                    },
                    valueRange = 800f..2800f
                )
                Text("$minRating - $maxRating", style = MaterialTheme.typography.bodySmall)
            }
            
            item {
                Text("Tactical Theme", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                FilterChips("Theme")
            }
            
            item {
                Text("Opening", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                FilterChips("Opening")
            }
            
            item {
                Button(
                    onClick = { /* Search */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Search", fontWeight = FontWeight.Bold)
                }
            }
            
            item {
                Text("Recent Searches", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            items(5) {
                SearchResultItem()
            }
        }
    }
}

@Composable
fun FilterChips(type: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) {
            FilterChip(
                selected = it == 0,
                onClick = { },
                label = { Text("Option $it") }
            )
        }
    }
}

@Composable
fun SearchResultItem() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Puzzle #1234", fontWeight = FontWeight.Bold)
                Text("Rating: 1650", style = MaterialTheme.typography.labelSmall)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "Navigate")
        }
    }
}
