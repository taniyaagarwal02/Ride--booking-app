package com.example.myapplication.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DateChip(text: String, selected: Boolean = false) {
    Text(
        text = text, fontSize = 20.sp,
        color = if (selected) Color(0xFF87CEEB) else Color.Gray,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    )
}