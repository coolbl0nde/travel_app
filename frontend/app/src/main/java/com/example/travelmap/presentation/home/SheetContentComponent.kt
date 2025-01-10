package com.example.travelmap.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelmap.R
import com.example.travelmap.presentation.common.CustomButton
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun SheetContentComponent (
    viewModel: CountryViewModel = hiltViewModel()
) {

    val userCountries = viewModel.userCountries.collectAsState()

    val shouldShowCountryDialog = remember {
        mutableStateOf(false)
    }

    if (shouldShowCountryDialog.value) {
        CountryListDialog(
            shouldShowCountryDialog = shouldShowCountryDialog,
            viewModel
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Hello, Maria. You visited",
            fontSize = 20.sp,
            fontWeight = FontWeight(300),
            fontFamily = FontFamily(Font(R.font.poppins)),
            color = Color.Black,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = userCountries.value.size.toString(),
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Countries",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Row {
            CustomButton(
                text = "Add country",
                onClick = { shouldShowCountryDialog.value = true },
                fontWeight = FontWeight(300),
                textSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            )

            CustomButton(
                text = "Add new route",
                onClick = { /*TODO*/ },
                fontWeight = FontWeight(300),
                textSize = 14.sp,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                colorText = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun SheetContentPreview(){
    TravelMapTheme {
        SheetContentComponent()
    }
}