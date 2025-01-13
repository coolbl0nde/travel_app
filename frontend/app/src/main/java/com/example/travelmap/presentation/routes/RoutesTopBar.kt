package com.example.travelmap.presentation.routes

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
fun RoutesTopBar() {
    TopAppBar(
        title = { Text(
            text = "Your Saved Routes",
            style = MaterialTheme.typography.titleMedium
        ) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor  = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        )
    )
}