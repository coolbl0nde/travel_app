package com.example.travelmap.presentation.home

import MapScreen
import android.util.Log
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelmap.ui.theme.TravelMapTheme
import com.mapbox.geojson.Point

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: CountryViewModel = hiltViewModel()
) {

    val scaffoldState = rememberBottomSheetScaffoldState()

    val userCountries = viewModel.userCountries.collectAsState()

    val countriesCoordinate = remember(userCountries.value) {
        userCountries.value.map { country ->
            Log.e("COORDINATES", "COORDINATES")
            Point.fromLngLat(
                country.longitude,
                country.latitude
            )
        }
    }

    Log.e("COORDINATES", "${countriesCoordinate.size}")


    BottomSheetScaffold(
        sheetContent = {
            SheetContentComponent(
                viewModel
            )
        },
        scaffoldState = scaffoldState,
        sheetContainerColor = Color.White,
        sheetPeekHeight = 50.dp
    ) {
        /*MapboxMap(
            modifier = Modifier.fillMaxSize()
        )*/
        MapScreen( coordinates = countriesCoordinate )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    TravelMapTheme {
        HomeScreen()
    }
}