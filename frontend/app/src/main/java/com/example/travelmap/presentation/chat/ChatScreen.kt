package com.example.travelmap.presentation.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun ChatScreen() {

    Column (
        modifier = Modifier.fillMaxSize(),
    ) {

        Column (
            modifier = Modifier.weight(1f)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            ChatItem(message = "Hello", isFromMe = false)

        }

        ChatInputField()

    }

}

@Preview
@Composable
fun ChatScreenPreview(){
    TravelMapTheme {
        ChatScreen()
    }
}