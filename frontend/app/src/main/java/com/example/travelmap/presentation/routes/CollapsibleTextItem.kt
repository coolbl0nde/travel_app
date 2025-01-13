package com.example.travelmap.presentation.routes

import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import io.noties.markwon.Markwon

@Composable
fun CollapsibleTextItem(
    route: String,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {

    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(15.dp)
    ) {
        AndroidView(
            factory = { context ->
                TextView(context).apply {
                    setTextColor(Color.Black.toArgb())
                    textSize = 16f
                    setOnClickListener { onToggle() }
                }
            },
            update = { textView ->
                val displayText = if (isExpanded) {
                    route
                } else {
                    route.take(70) + "..."
                }
                markwon.setMarkdown(textView, displayText)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }

}