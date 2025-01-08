package com.example.travelmap.presentation.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelmap.domain.model.Country
import com.example.travelmap.ui.theme.TravelMapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryListDialog (
    shouldShowCountryDialog: MutableState<Boolean> = mutableStateOf(true),
    viewModel: CountryViewModel = hiltViewModel()
) {

    val searchText by viewModel.searchText.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()
    val countriesList by viewModel.countries.collectAsState()

    var selectedCountry by remember {
        mutableStateOf<Country?>(null)
    }

    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            Button(
                onClick = {
                    shouldShowCountryDialog.value = false
                    if (selectedCountry != null){
                        viewModel.addCountry(selectedCountry!!)
                    }
                }
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White
                )
            }
        },
        title = { Text(text = "Add new country") },
        text = {
            Column {
                DockedSearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = searchText,
                            placeholder = { Text(text = "Start typing...")},
                            onQueryChange = {
                                viewModel.updateSearchText(it)
                            },
                            onSearch = {
                                viewModel.performSearch()
                            },
                            expanded = isExpanded,
                            onExpandedChange = { viewModel.toggleExpanded() }
                        )
                    },
                    colors = SearchBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        dividerColor = Color.Black),
                    expanded = isExpanded && countriesList.isNotEmpty(),
                    onExpandedChange = { viewModel.toggleExpanded() },
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    LazyColumn {
                        items(items = countriesList) { country ->
                            ListItem(
                                headlineContent = { Text(country.name) },
                                //supportingContent = { Text("Координаты: ${country.latitude}, ${country.longitude}") },
                                //leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                                modifier = Modifier
                                    .clickable {
                                        viewModel.updateSearchText(country.name)
                                        selectedCountry = country
                                        viewModel.performSearch()
                                    }
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

        }
    )
}

@Preview
@Composable
fun CountryListDialogPreview(){
    TravelMapTheme {
        CountryListDialog()
    }
}