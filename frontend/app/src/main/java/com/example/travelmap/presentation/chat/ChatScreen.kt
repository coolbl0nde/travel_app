package com.example.travelmap.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.domain.model.MessageRole
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val messages by viewModel.messages.collectAsState()

    /*val messages = List(5) { index ->
        """
        # Markdown Example $index
        **Bold text**, *italic text*, and [a link](https://example.com).
        - List item 1
        - List item 2
    """.trimIndent()
    }*/

    val listState = rememberLazyListState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        ChatTopBar(
            navController = navController
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            reverseLayout = false
        ) {

            /*items(messages) { message ->
                ChatItem(
                    message = message,
                    role = MessageRole.assistant,
                    onFavoriteClick = { /* Handle favorite click */ },
                    isFavorite = true
                )
            }*/

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