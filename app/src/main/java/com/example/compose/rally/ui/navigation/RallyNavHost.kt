package com.example.compose.rally.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose.rally.RallyScreen
import com.example.compose.rally.data.UserData
import com.example.compose.rally.ui.accounts.AccountsBody
import com.example.compose.rally.ui.accounts.SingleAccountBody
import com.example.compose.rally.ui.bills.BillsBody
import com.example.compose.rally.ui.overview.OverviewBody

val accountsName = RallyScreen.Accounts.name

@Composable
fun RallyNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = RallyScreen.Overview.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(RallyScreen.Overview.name) {
            OverviewBody(
                onClickSeeAllAccounts = { navController.navigate(RallyScreen.Accounts.name) },
                onClickSeeAllBills = { navController.navigate(RallyScreen.Bills.name) },
                onAccountClick = { name -> navigateToSingleAccount(navController, name) }
            )
        }
        composable(RallyScreen.Accounts.name) {
            AccountsBody(accounts = UserData.accounts, onAccountClick = { name ->
                navigateToSingleAccount(navController, name)
            })
        }
        composable(RallyScreen.Bills.name) {
            BillsBody(bills = UserData.bills)
        }
        composable(
            route = "$accountsName/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val accountName = backStackEntry.arguments?.getString("name")
            val account = UserData.getAccount(accountName)
            SingleAccountBody(account = account)
        }
    }
}

private fun navigateToSingleAccount(
    navController: NavHostController,
    accountName: String
) {
    navController.navigate("${RallyScreen.Accounts.name}/$accountName")
}