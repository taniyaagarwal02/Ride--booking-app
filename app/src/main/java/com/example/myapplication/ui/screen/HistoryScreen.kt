package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.data.RideDatabase
import com.example.myapplication.ui.data.RideEntity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.ui.components.IconDetailRow


@Composable
fun HistoryScreen(navController: NavController) {

    val context = LocalContext.current
    val dao = RideDatabase.getDatabase(context).rideDao()

    val rides by dao.getAllRides().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        /*  TOP BAR */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.left),
                    contentDescription = null,
                    tint = Color.Black
                )
            }


            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Ride History",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20.dp)
            )
        }

        /* 🔹 HISTORY LIST */
        if (rides.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(
                    text = "No rides booked yet",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(rides) { ride ->
                    RideHistoryCard(ride)
                }
            }
        }
    }
}

/* 🔹 SINGLE RIDE CARD (same style as FourthScreen) */
@Composable
fun RideHistoryCard(ride: RideEntity) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F5FF)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "${ride.fromLocation} → ${ride.toLocation}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            IconDetailRow(
                iconRes = R.drawable.calendar,
                value = ride.date
            )

            Spacer(modifier = Modifier.height(6.dp))

            IconDetailRow(
                iconRes = R.drawable.time,
                value = ride.time
            )

            Spacer(modifier = Modifier.height(6.dp))

            IconDetailRow(
                iconRes = R.drawable.passenger,
                value = "${ride.passengers} Passengers"
            )
        }
    }
}



@Composable
fun HistoryContent(
    rides: List<RideEntity>,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Ride History",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 11.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(rides) { ride ->
                RideHistoryCard(ride)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryScreenPreview() {

    val fakeRides = listOf(
        RideEntity(
            id = 1,
            fromLocation = "Meerut",
            toLocation = "Delhi",
            date = "2026-01-07",
            time = "10:00 AM",
            passengers = 2
        ),
        RideEntity(
            id = 2,
            fromLocation = "Noida",
            toLocation = "Gurgaon",
            date = "2026-01-08",
            time = "6:00 PM",
            passengers = 1
        )
    )

    HistoryContent(rides = fakeRides)
}

