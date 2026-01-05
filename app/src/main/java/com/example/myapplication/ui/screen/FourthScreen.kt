package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.net.URLDecoder

@Composable
fun FourthScreen(
    navController: NavController,
    from: String,
    to: String,
    date: String,
    time: String,
    passengers: Int
) {

    val decodedFrom = URLDecoder.decode(from, "UTF-8")
    val decodedTo = URLDecoder.decode(to, "UTF-8")
    val decodedDate = URLDecoder.decode(date, "UTF-8")
    val decodedTime = URLDecoder.decode(time, "UTF-8")

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            /* 🔹 TOP BLUE SECTION */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f) // reduced height
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(Color(0xFF87CEEB))
                    .padding(14.dp)
            ) {
                Column {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Text(
                        "Confirm Ride",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    RideInfoItem("From", decodedFrom)
                    RideInfoItem("To", decodedTo)
                }
            }

            /* 🔹 BOTTOM WHITE SECTION */
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    DetailRow("Date", decodedDate)
                    DetailRow("Time", decodedTime)
                    DetailRow("Passengers", passengers.toString())

                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Driver Details",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF5F7FA)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            DriverRow("👤 Name", "Rahul (Mock)")
                            DriverRow("🚗 Vehicle", "Swift Dzire")
                            DriverRow("💰 Fare", "₹120")
                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(30.dp),
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "✅ Ride booked successfully!",
                                duration = SnackbarDuration.Short
                            )
                        }


                    }
                ) {
                    Text("Confirm Ride", fontSize = 18.sp)
                }
            }
        }
    }
}

/* 🔹 SMALL REUSABLE COMPOSABLES */

@Composable
fun RideInfoItem(title: String, value: String) {
    Column {
        Text(title, color = Color.White)
        Text(
            value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(14.dp))
        Divider(color = Color.White)
        Spacer(modifier = Modifier.height(14.dp))
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun DriverRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.height(8.dp))
}
