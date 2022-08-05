package com.example.compose.rally.ui.bottomNagigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun BottomNavigationPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomBar(navController = navController) }
        ) { innerPadding ->
            BottomNavHost(navController = navController, innerPadding = innerPadding)
        }
    }
}