package com.example.myapplication.ui.screen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SecondScreen(navController: NavController) {

    var currentLocation by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        /* 🔹 TOP BLUE SECTION (INCREASED HEIGHT) */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp) // ⬆️ increased height
                .clip(
                    RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .background(Color(0xFF87CEEB))
                .padding(16.dp)
        ) {
            Column {

                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Plan your trip",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(18.dp))

                /* 📍 CURRENT LOCATION */
                OutlinedTextField(
                    value = currentLocation,
                    onValueChange = { currentLocation = it },
                    placeholder = { Text("Enter current location") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors()
                )

                Spacer(modifier = Modifier.height(8.dp))

                /* 📌 USE CURRENT LOCATION */
                Row(
                    modifier = Modifier
                        .clickable {
                            currentLocation = "My Current Location"
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.MyLocation,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        "Use current location",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                /* 🎯 DESTINATION */
                OutlinedTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = { Text("Enter destination") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors()
                )
            }
        }

        /* 🔽 SPACE BEFORE BUTTON (NOT PUSHED TO BOTTOM) */
        Spacer(modifier = Modifier.height(40.dp))

        /* ➡️ CONTINUE BUTTON (SHIFTED UP) */
        Button(
            onClick = {
                navController.navigate(
                    "third?from=${Uri.encode(currentLocation)}&to=${Uri.encode(destination)}"
                )
            },
            enabled = currentLocation.isNotBlank() && destination.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp) // ⬆️ lifted from bottom
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue", fontSize = 18.sp)
        }
    }
}

/* 🎨 TEXT FIELD COLORS */
@Composable
private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color.White,
    unfocusedBorderColor = Color.White,
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,
    cursorColor = Color.White,
    focusedPlaceholderColor = Color.White.copy(alpha = 0.7f),
    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f)
)
