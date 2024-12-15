package com.example.travelmap.presentation.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.travelmap.presentation.common.CustomTextField
import com.example.travelmap.presentation.navigation.AuthChoiceScreen
import com.example.travelmap.presentation.navigation.LoginScreen
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun RegisterScreen(
    navController: NavHostController = rememberNavController()
) {

    var userName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg_auth),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.BottomCenter
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp)
            ) {

                IconButton(onClick = { navController.navigate(AuthChoiceScreen) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "back"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontSize = 17.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier
                        .height(23.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            navController.navigate(LoginScreen)
                        }
                )

            }

            Spacer(modifier = Modifier.height(90.dp))

            Text(
                text = "Register",
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = userName,
                onValueChange = { userName = it },
                label = "User Name",
                //trailingIcon = Icons.Default.Check,
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = "Register",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            )
        }
    }

}

@Preview
@Composable
fun RegistrationScreenPreview(){
    TravelMapTheme {
        RegisterScreen()
    }
}