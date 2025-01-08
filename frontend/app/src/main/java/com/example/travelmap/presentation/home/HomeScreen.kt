package com.example.travelmap.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelmap.ui.theme.TravelMapTheme
import com.mapbox.maps.extension.compose.MapboxMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContent = {
            SheetContentComponent()
        },
        scaffoldState = scaffoldState,
        sheetContainerColor = Color.White,
        sheetPeekHeight = 50.dp
    ) {
        MapboxMap(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    TravelMapTheme {
        HomeScreen()
    }
}