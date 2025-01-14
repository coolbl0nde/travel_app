package com.example.travelmap.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    pagesSize: Int,
    indexCurrentPage: Int,
    selectedColor: Color = Color(0xFFFFA183),
    unselectedColor: Color = Color(0xFFB7B7B7)
){

    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(times = pagesSize){page ->

            Box (
                modifier = Modifier
                    .size(if (page == indexCurrentPage) 6.dp else 4.dp)
                    .clip(CircleShape)
                    .background(color = if (page == indexCurrentPage) selectedColor
                                                                            else unselectedColor)
            )

        }

    }
}