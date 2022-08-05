package com.example.compose.rally.ui.bottomNagigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.loginGraph() {
    navigation(startDestination = "username", route = "login") {
        composable("username") { Login.UsernameBody() }
        composable("password") { Login.PasswordBody() }
        composable("registration") { Login.Registration() }
    }
}

fun NavGraphBuilder.GamingGraph() {
    navigation(startDestination = "gameStart", route = "game") {
        composable("gameStart") { Game.GameStartBody() }
        composable("gameStop") { Game.GameStopBody() }
        composable("gameSetting") { Game.GameSetting() }
    }
}

@Composable
fun NestedNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "game",
        modifier = Modifier.padding(innerPadding)
    ) {
        GamingGraph()
        loginGraph()
    }
}

class Login private constructor() {
    companion object {
        @Composable
        fun UsernameBody() {
            Text(text = "username")
        }

        @Composable
        fun PasswordBody() {
            Text(text = "password")
        }

        @Composable
        fun Registration() {
            Text(text = "registration")
        }
    }
}

class Game private constructor() {
    companion object {
        @Composable
        fun GameStartBody() {
            Text(text = "start")
        }

        @Composable
        fun GameStopBody() {
            Text(text = "stop")
        }

        @Composable
        fun GameSetting() {
            Text(text = "setting")
        }
    }
}