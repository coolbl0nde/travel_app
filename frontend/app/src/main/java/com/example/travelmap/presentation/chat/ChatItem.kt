package com.example.travelmap.presentation.chat

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.travelmap.R
import com.example.travelmap.domain.model.MessageRole
import io.noties.markwon.Markwon

@Composable
fun ChatItem(
    message: String,
    role: MessageRole,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {
    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) } // Инициализация Markwon

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = if (role.name == "user") Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                //.align(if (role.name == "user") Alignment.End else Alignment.Start)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (role.name == "user") 48f else 0f,
                        bottomEnd = if (role.name == "user") 0f else 48f
                    )
                )
                .background(MaterialTheme.colorScheme.secondary)
                .padding(10.dp)
                .sizeIn(maxWidth = 270.dp)
        ) {
            //Text(text = message)

            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        setTextColor(Color.Black.toArgb())
                        textSize = 16f
                    }
                },
                update = { textView ->
                    markwon.setMarkdown(textView, message)
                }
            )
        }

        if (role == MessageRole.assistant) {
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { onFavoriteClick() },
                modifier = Modifier
                    .size(width = 22.dp, height = 22.dp)
                    .padding(bottom = 5.dp)
                    .align(alignment = Alignment.Bottom)
            ) {
                Icon(
                    painter = if (isFavorite) painterResource(id = R.drawable.heart_icon)
                    else painterResource(id = R.drawable.heart_border_icon),
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    modifier = Modifier.size(width = 20.dp, height = 20.dp)
                )
            }
        }
    }
}