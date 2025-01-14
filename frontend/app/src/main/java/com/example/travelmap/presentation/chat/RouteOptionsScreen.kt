package com.example.travelmap.presentation.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelmap.R
import com.example.travelmap.ui.theme.TravelMapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteOptionsScreen() {

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        TopAppBar(
            title = { Text(
                text = "Add Route Oprions",
                style = MaterialTheme.typography.titleMedium
            ) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor  = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            ),
            actions = {
                IconButton (onClick = {  }) {
                   Icon(
                       painter = painterResource(id = R.drawable.close_icon),
                       contentDescription = "back",
                       tint = Color.White
                   )
                }
            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}

@Preview
@Composable
fun RouteOptionsScreenPreview(){
    TravelMapTheme {
        RouteOptionsScreen()
    }
}