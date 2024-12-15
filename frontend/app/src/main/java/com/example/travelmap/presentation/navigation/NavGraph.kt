package com.example.travelmap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelmap.presentation.auth.screens.AuthChoiceScreen
import com.example.travelmap.presentation.welcome.WelcomeScreen

@Composable
fun NavGraph (navController: NavHostController, startDestination: Any) {

    NavHost(navController = navController, startDestination = WelcomeScreen) {
        composable<WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }

        composable<AuthChoiceScreen> {
            AuthChoiceScreen()
        }
    }
}