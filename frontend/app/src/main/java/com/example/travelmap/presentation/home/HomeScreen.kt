package com.example.travelmap.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mapbox.maps.extension.compose.MapboxMap

@Composable
fun HomeScreen() {
    MapboxMap(
        modifier = Modifier.fillMaxSize()
    )
}