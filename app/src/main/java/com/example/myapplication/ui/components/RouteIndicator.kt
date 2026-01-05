package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RouteIndicator() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(start = 20.dp)

    ) {

        // Top dot
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Color.White, CircleShape)

        )

        // Vertical line
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(130.dp)
                .background(Color.White)
                .padding(bottom = 20.dp)
        )

        // Bottom dot
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Color.White, CircleShape)
                .padding(bottom = 30.dp)
        )


    }
}