package com.example.travelmap.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.substring
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelmap.R
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun OnboardingItem(
    modifier: Modifier = Modifier,
    fullText: String
){

    val styledText = buildAnnotatedString {
        append(fullText.substring(0, fullText.lastIndexOf(" ") + 1))

        withStyle(
            style = SpanStyle(
                color = Color(0xFFFFA183)
            )
        ) {
            append(fullText.substring(fullText.lastIndexOf(" ") + 1))
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
    ){

        Text(
            text = styledText,
            modifier = modifier
                .padding(30.dp)
                .fillMaxWidth(),
            fontSize = 36.sp,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(700),
            color = Color.White
        )
        
    }
}

@Preview(
    showBackground = true
    )
@Composable
fun OnBoardingItemPreview() {
    TravelMapTheme {
        OnboardingItem(fullText = "Let’s start\n your Vacation!")
    }
}
