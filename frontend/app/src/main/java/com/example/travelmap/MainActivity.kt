package com.example.travelmap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.travelmap.presentation.navigation.BottomNavigationBar
import com.example.travelmap.presentation.navigation.HomeScreen
import com.example.travelmap.presentation.navigation.NavGraph
import com.example.travelmap.presentation.navigation.TOP_LEVEL_ROUTES
import com.example.travelmap.presentation.navigation.WelcomeScreen
import com.example.travelmap.presentation.welcome.WelcomeScreen
import com.example.travelmap.ui.theme.TravelMapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

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
                ) {
                    NavGraph(navController = navController, startDestination = WelcomeScreen)
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