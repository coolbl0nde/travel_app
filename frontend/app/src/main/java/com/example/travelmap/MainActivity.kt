package com.example.travelmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.presentation.navigation.NavGraph
import com.example.travelmap.presentation.navigation.WelcomeScreen
import com.example.travelmap.presentation.welcome.OnboardingItem
import com.example.travelmap.presentation.welcome.WelcomeScreen
import com.example.travelmap.ui.theme.TravelMapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navHostController = rememberNavController()

            TravelMapTheme(
                dynamicColor = false
            ) {
                NavGraph(navController = navHostController, startDestination = WelcomeScreen)
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