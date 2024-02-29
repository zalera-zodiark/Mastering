package com.charros_software.mastering.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleText(text: String) {
    Text(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
        text = text,
        textAlign = TextAlign.Left,
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
        lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
        letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
        fontWeight = MaterialTheme.typography.titleLarge.fontWeight
    )
}