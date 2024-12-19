package com.example.travelmap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelmap.presentation.auth.AuthChoiceScreen
import com.example.travelmap.presentation.auth.login.LoginScreen
import com.example.travelmap.presentation.welcome.WelcomeScreen
import com.example.travelmap.presentation.auth.register.RegisterScreen



@Composable
fun NavGraph (navController: NavHostController, startDestination: Any) {

    NavHost(navController = navController, startDestination = WelcomeScreen) {
        composable<WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }

        composable<AuthChoiceScreen> {
            AuthChoiceScreen(navController = navController)
        }

        composable<RegisterScreen> {
            RegisterScreen(navController = navController)
        }

        composable<LoginScreen> {
            LoginScreen(navController = navController)
        }
    }
}