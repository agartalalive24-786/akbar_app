package com.akbar.chessvisionpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    var playSound by remember { mutableStateOf(true) }
    var autoLoad by remember { mutableStateOf(true) }
    var flipBoard by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf("Light") }
    var selectedLanguage by remember { mutableStateOf("English") }
    
    Scaffold(
        topBar ={
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
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
                Text("Display", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            item {
                SettingItem("Board Theme", selectedTheme) { /* Change theme */ }
            }
            
            item {
                SettingToggle("Flip Board", flipBoard) { flipBoard = !flipBoard }
            }
            
            item {
                SettingToggle("Show Coordinates", true) { }
            }
            
            item {
                Text("Audio", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            item {
                SettingToggle("Move Sounds", playSound) { playSound = !playSound }
            }
            
            item {
                Text("Gameplay", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            item {
                SettingToggle("Auto Load Next", autoLoad) { autoLoad = !autoLoad }
            }
            
            item {
                Text("Localization", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            item {
                SettingItem("Language", selectedLanguage) { /* Change language */ }
            }
            
            item {
                Divider()
            }
            
            item {
                Button(
                    onClick = { /* Clear cache */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text("Clear Cache", color = MaterialTheme.colorScheme.onErrorContainer)
                }
            }
            
            item {
                Text("Chess Vision Pro v4.5.0", style = MaterialTheme.typography.labelSmall)
                Text("© 2026 Agartala Chess Academy", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun SettingItem(title: String, value: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title)
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SettingToggle(title: String, enabled: Boolean, onToggle: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title)
            Switch(checked = enabled, onCheckedChange = { onToggle() })
        }
    }
}
