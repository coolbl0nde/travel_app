package com.example.travelmap.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.presentation.navigation.AuthChoiceScreen
import com.example.travelmap.presentation.navigation.BottomNavigationBar
import com.example.travelmap.presentation.navigation.HomeScreen
import com.example.travelmap.presentation.navigation.NavGraph
import com.example.travelmap.presentation.navigation.TOP_LEVEL_ROUTES
import com.example.travelmap.presentation.profile.ProfileViewModel
import com.example.travelmap.presentation.welcome.WelcomeScreen
import com.example.travelmap.ui.theme.TravelMapTheme
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            if (!Places.isInitialized()) {
                Places.initialize(applicationContext, getString(R.string.google_api_key))
            }

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val mainViewModel: MainViewModel = hiltViewModel()
            val startDestination by mainViewModel.startDestination.collectAsState()

            val profileViewModel: ProfileViewModel = hiltViewModel()
            val logOutEvent by profileViewModel.logOutEvent.collectAsState()

            LaunchedEffect(logOutEvent) {
                if (logOutEvent) {
                    mainViewModel.logOutUser()
                    profileViewModel.resetLogOutEvent()
                }
            }

            TravelMapTheme(
                dynamicColor = false
            ) {
                Scaffold (
                    bottomBar = {
                        TOP_LEVEL_ROUTES.forEach {topLevelRoute ->
                            if(currentDestination?.hierarchy?.any{
                                    it.hasRoute(route = topLevelRoute.route::class)
                                } == true) {
                                BottomNavigationBar(navController)
                            }
                        }

                    }
                ) { paddingValues ->
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        startDestination.let { destination ->
                            NavGraph(
                                navController = navController,
                                startDestination = when (destination) {
                                    "home" -> HomeScreen
                                    "auth" -> AuthChoiceScreen
                                    else -> AuthChoiceScreen
                                },
                                profileViewModel = profileViewModel
                            )
                        }
                        //NavGraph(navController = navController, startDestination = HomeScreen)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelMapTheme {
        WelcomeScreen()
    }
}