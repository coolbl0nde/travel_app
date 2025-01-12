package com.example.travelmap.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelmap.domain.model.MessageRole
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {

    val messages by viewModel.messages.collectAsState()

    val listState = rememberLazyListState()

    Column (
        modifier = Modifier.fillMaxSize(),
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = false
        ) {
            items(messages, key = {it.id}) { message ->
                ChatItem(
                    message = message.content,
                    role = message.role,
                    isFavorite = message.isSaved,
                    onFavoriteClick = {
                        viewModel.updateMessage(message.id, !message.isSaved)
                    }
                )
            }
        }

        LaunchedEffect(messages) {
            listState.scrollToItem(0)
        }

        ChatInputField(viewModel)

    }

}

@Preview
@Composable
fun ChatScreenPreview(){
    TravelMapTheme {
        ChatScreen()
    }
}