package com.example.travelmap.presentation.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.travelmap.R
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod

@Composable
fun ChatInputField (
    viewModel: ChatViewModel
) {

    var message by remember {
        mutableStateOf("")
    }

    Row (
        modifier = Modifier.padding(10.dp)
    ) {
        TextField(
            value = message,
            onValueChange = {newMessage ->
                message = newMessage
            },
            placeholder = {
                Text(text = "Type your message")
            },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {
                viewModel.addMessage(content = message)
                message = ""
                      },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = "Send",
                //modifier = Modifier.fillMaxSize()
            )
        }

    }

}