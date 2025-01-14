package com.example.travelmap.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun CustomTextField (
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: Painter? = null,
    trailingIconButton: Painter? = null,
    contentDescription: String? = null,
    textSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight(400),
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {

    var hideText by remember {
        mutableStateOf(isPassword)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label, fontSize = textSize,
                fontWeight = fontWeight, textAlign = TextAlign.Center)
        },
        textStyle = TextStyle(fontSize = textSize, fontWeight = fontWeight),
        trailingIcon = {
            if (trailingIcon != null){
                Icon(
                    painter = trailingIcon,
                    contentDescription = contentDescription,
                    tint = Color.Unspecified
                )
            } else if (trailingIconButton != null) {
                IconButton(onClick = { hideText = !hideText }) {
                    Icon(
                        painter = trailingIconButton,
                        contentDescription = contentDescription,
                        tint = Color.Unspecified
                    )
                }
            }
        },
        visualTransformation = if (hideText) PasswordVisualTransformation()
                                    else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun CustomOutlinedTextFieldPreview(){
    TravelMapTheme {
        CustomTextField(
            value = "user name",
            onValueChange = { "user name" },
            label = "user name"
        )
    }
}