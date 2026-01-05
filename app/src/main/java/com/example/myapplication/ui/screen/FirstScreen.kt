package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun FirstScreen(name: String, modifier: Modifier = Modifier, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()


    )
    {
        Image(
            painterResource(id = R.drawable.car),
            contentDescription = null ,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(350.dp)
        )




        Text(
            color = Color.Black,
            text = "CarPooling",
            fontSize = 33.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            color = Color.Black,
            text = "Drive & Save Money",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp)

        )
        IconButton(
            onClick = {
                navController.navigate("second")
            },
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF87CEEB), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Go to Second Screen",
                tint = Color.White
            )
        }

    }
}




