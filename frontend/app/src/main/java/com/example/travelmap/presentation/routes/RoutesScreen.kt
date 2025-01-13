package com.example.travelmap.presentation.routes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel()
) {

    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }
    val routes by viewModel.routes.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        RoutesTopBar()

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(routes) { index, route ->
                CollapsibleTextItem(
                    route = route.content,
                    isExpanded = expandedStates[index] == true,
                    onToggle = {
                        expandedStates[index] = !(expandedStates[index] ?: false)
                    }
                )
            }
        }
    }
}