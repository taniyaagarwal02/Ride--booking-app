package com.example.myapplication.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import com.example.myapplication.R
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.components.DriverIconRow
import com.example.myapplication.ui.components.IconDetailRow
import com.example.myapplication.ui.components.InfoRow
import java.net.URLDecoder
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.data.RideDatabase
import com.example.myapplication.ui.data.RideEntity
import kotlinx.coroutines.launch


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
    val context = LocalContext.current
    val db = RideDatabase.getDatabase(context)
    val dao = db.rideDao()


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {


            /* 🔹 TOP IMAGE + BACK */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 40.dp,
                            bottomEnd = 40.dp
                        )
                    )

            ) {
                Image(
                    painter = painterResource(id = R.drawable.loc4), // your image
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.left),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }


                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Confirm Ride",
//
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    /*  FROM / TO CARD */
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF6F5FF)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            InfoRow("From", decodedFrom)
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                            InfoRow("To", decodedTo)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    /* 📅 DETAILS */
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF6F5FF)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            IconDetailRow(
                                iconRes = R.drawable.calendar,
                                value = decodedDate
                            )

                            IconDetailRow(
                                iconRes = R.drawable.time,
                                value = decodedTime
                            )

                            IconDetailRow(
                                iconRes = R.drawable.passenger,
                                value = "$passengers Passengers"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    /*  DRIVER DETAILS */
                    Text(
                        text = "Driver Details",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF6F5FF)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            DriverIconRow(
                                iconRes = R.drawable.user,
                                value = "Rahul (Mock)"
                            )

                            DriverIconRow(
                                iconRes = R.drawable.car,
                                value = "Swift Dzire"

                            )

                            DriverIconRow(
                                iconRes = R.drawable.coin,
                                value = "₹120",

                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }


                    /*  CONFIRM BUTTON */
                    Button(
                        onClick = {
//

                            scope.launch {
                                dao.insertRide(
                                    RideEntity(
                                        fromLocation = decodedFrom,
                                        toLocation = decodedTo,
                                        date = decodedDate,
                                        time = decodedTime,
                                        passengers = passengers
                                    )
                                )
                                Log.d("ROOM_TEST", "Ride inserted into DB")
                                snackbarHostState.showSnackbar("✅ Ride booked successfully!")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6A5ACD)
                        )
                    ) {
                        Text("Confirm Ride",  style = MaterialTheme.typography.titleLarge)
                    }

                }
            }
        }
    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FourthScreenPreview() {
    FourthScreen(
        navController = rememberNavController(),
        from = "Meerut",
        to = "Delhi",
        date = "2026-01-07",
        time = "10:00 AM",
        passengers = 2
    )
}
