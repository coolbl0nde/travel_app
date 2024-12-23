package com.example.travelmap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelmap.presentation.auth.AuthChoiceScreen
import com.example.travelmap.presentation.auth.login.LoginScreen
import com.example.travelmap.presentation.welcome.WelcomeScreen
import com.example.travelmap.presentation.auth.register.RegisterScreen
import com.example.travelmap.presentation.chat.ChatScreen
import com.example.travelmap.presentation.home.HomeScreen
import com.example.travelmap.presentation.profile.ProfileScreen
import com.example.travelmap.presentation.routes.RoutesScreen


@Composable
fun NavGraph (navController: NavHostController, startDestination: Any) {

    NavHost(navController = navController, startDestination = startDestination) {
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

        composable<HomeScreen> {
            HomeScreen()
        }

        composable<RoutesScreen> {
            RoutesScreen()
        }

        composable<ChatScreen> {
            ChatScreen()
        }

        composable<ProfileScreen> {
            ProfileScreen()
        }
    }
}