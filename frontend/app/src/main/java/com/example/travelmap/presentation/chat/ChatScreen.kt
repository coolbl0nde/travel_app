package com.example.travelmap.presentation.chat

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.domain.model.MessageRole
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {

    val messages by viewModel.messages.collectAsState()
    val isAnswerLoading by viewModel.isAnswerLoading.collectAsState()

    val listState = rememberLazyListState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        ChatTopBar(
            navController = navController
        )

        if (messages.isEmpty()){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 120.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sun_icon),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 150.dp, height = 150.dp)
                )

                Text(
                    text = "It's your personal chat assistant.\nSend any " +
                            "message to start the process of planning your trip.",
                    color = Color(0xFF5F5F5F),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                )
            }
        }


        LaunchedEffect(messages) {
            if (messages.isNotEmpty()){
                listState.animateScrollToItem(messages.size)
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            reverseLayout = false,
            state = listState
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

        if (isAnswerLoading) {
            Text(
                text = "chat is typing...",
            )
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