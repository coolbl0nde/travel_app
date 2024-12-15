package com.example.travelmap.presentation.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.presentation.common.CustomButton
import com.example.travelmap.presentation.navigation.LoginScreen
import com.example.travelmap.presentation.navigation.RegisterScreen
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun AuthChoiceScreen (
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.bg_auth),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.BottomCenter
        )

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.padding(100.dp))

            Text(
                text = "Let’s enjoy\nyour Vacation!",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                fontSize = 36.sp,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.padding(10.dp))

            CustomButton(
                text = "Register",
                onClick = { navController.navigate(RegisterScreen) },
                modifier = Modifier
                    .width(220.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CustomButton(
                text = "Login",
                onClick = { navController.navigate(LoginScreen) },
                backgroundColor = Color(0xFFFFA183),
                modifier = Modifier
                    .width(220.dp)
                    .height(50.dp)
            )
        }

    }
}

@Preview
@Composable
fun AuthChoiceScreenPreview(){
    TravelMapTheme {
        AuthChoiceScreen()
    }
}