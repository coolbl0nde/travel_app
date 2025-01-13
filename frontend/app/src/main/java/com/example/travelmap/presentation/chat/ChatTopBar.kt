package com.example.travelmap.presentation.chat

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.presentation.navigation.RouteOptionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    navController: NavHostController = rememberNavController()
) {
    TopAppBar(
        title = { Text(
            text = "Chat Assistant",
            style = MaterialTheme.typography.titleMedium
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor  = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        actions = {
            /*TextButton(onClick = { navController.navigate(RouteOptionsScreen) }) {
                Text(
                    text = "Route Options",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White
                )
            }*/
        }
    )
}