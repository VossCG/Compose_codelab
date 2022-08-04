package com.example.compose.rally.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.RallyScreen

@Composable
fun MyNavHost(navController: NavHostController,innerPadding:PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = RallyScreen.Overview.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(RallyScreen.Overview.name) {
            Text(RallyScreen.Overview.name)
        }
        composable(RallyScreen.Accounts.name) {
            Text(RallyScreen.Accounts.name)
        }
        composable(RallyScreen.Bills.name) {
            Text(RallyScreen.Bills.name)
        }
    }
}