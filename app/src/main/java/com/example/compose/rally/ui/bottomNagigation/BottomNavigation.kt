package com.example.compose.rally.ui.bottomNagigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

// Screen Class
sealed class BottomScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Setting : BottomScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )
}

// NavHost
@Composable
fun BottomNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = BottomScreen.Home.route) {
            BottomNavigationComp.HomeScreen()
        }
        composable(route = BottomScreen.Profile.route) {
            BottomNavigationComp.ProfileScreen()
        }
        composable(route = BottomScreen.Setting.route) {
            BottomNavigationComp.SettingScreen()
        }
    }
}

// BottomScreen Compose
class BottomNavigationComp() {
    companion object {
        @Composable
        fun HomeScreen() {
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Home",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        @Composable
        fun ProfileScreen() {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Profile",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        @Composable
        fun SettingScreen() {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Setting",
                    fontSize = MaterialTheme.typography.h3.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

// BottomBar
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomScreen.Home,
        BottomScreen.Profile,
        BottomScreen.Setting,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    // create bottomNav item in BottomNavigation
    BottomNavigation() {
        screens.forEach { screen ->
            AddBottomNavItem(screen, currentDestination, navController)
        }
    }
}

// BottomNav Item
@Composable
fun RowScope.AddBottomNavItem(
    screen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "nav item") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    // save the state that get pop up to
                    saveState = true
                }
                // when you navigate to this screen and if have a state available for the screen, restore it
                restoreState = true

                launchSingleTop = true
            }
        }
    )
}