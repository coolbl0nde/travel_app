package com.example.travelmap.presentation.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.presentation.navigation.AuthChoiceScreen
import com.example.travelmap.ui.theme.TravelMapTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val slides = listOf<String>(
        "Let’s start\n your Vacation!",
        "Explore the\n Earth",
        "A new way\n to Travel"
    )

    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        slides.size
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5B4DBC))
    ) {
        val scope = rememberCoroutineScope()

        Image(
            painter = painterResource(id = R.drawable.bg_welcome_page),
            contentDescription = null,
            alignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
        )

        HorizontalPager(state = pagerState) { index ->
            OnboardingItem(
                fullText = slides[index],
                modifier = Modifier.padding(top = 5.dp)
            )
        }


        DotsIndicator(
            pagesSize = slides.size,
            indexCurrentPage = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(44.dp)
                .padding(top = 180.dp)
        )

        Button(
            onClick = {
                if (pagerState.currentPage == slides.size - 1) {
                    viewModel.completeOnBoarding()
                    navController.navigate(AuthChoiceScreen){
                        popUpTo(0) {inclusive = true}
                    }
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            page = pagerState.currentPage + 1
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .width(90.dp)
                .height(45.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "next"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TravelMapTheme {
        WelcomeScreen()
    }
}