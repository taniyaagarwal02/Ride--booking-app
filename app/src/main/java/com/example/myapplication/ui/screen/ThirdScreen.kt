package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.components.PassengerCircle
import com.example.myapplication.ui.components.RouteIndicator
import com.example.myapplication.ui.components.SelectChip
import java.net.URLEncoder

@Composable
fun ThirdScreen(
    navController: NavController,
    from: String,
    to: String
) {

    var selectedDate by remember { mutableStateOf("Today") }
    var selectedTime by remember { mutableStateOf("Now") }
    var selectedPassengers by remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize()) {

        /* 🔹 TOP BLUE SECTION (REDUCED HEIGHT) */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // ⬅️ controlled height
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

                Text(
                    "Ride Details",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.Top) {
//                    RouteIndicator()
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("From", color = Color.White, fontSize = 14.sp)
                        Text(
                            from,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(color = Color.White)

                        Spacer(modifier = Modifier.height(10.dp))
                        Text("To", color = Color.White, fontSize = 14.sp)
                        Text(
                            to,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        /* 🔹 BOTTOM WHITE SECTION */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            /* 📅 DATE */
            Text("Date", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                SelectChip("Today", selectedDate) { selectedDate = it }
                Spacer(Modifier.width(8.dp))
                SelectChip("Tomorrow", selectedDate) { selectedDate = it }
                Spacer(Modifier.width(8.dp))
                SelectChip("Other", selectedDate) { selectedDate = it }
            }

            Spacer(modifier = Modifier.height(18.dp))

            /* ⏰ TIME */
            Text("Time", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                SelectChip("Morning", selectedTime) { selectedTime = it }
                Spacer(Modifier.width(8.dp))
                SelectChip("Afternoon", selectedTime) { selectedTime = it }
                Spacer(Modifier.width(8.dp))
                SelectChip("Evening", selectedTime) { selectedTime = it }
            }

            Spacer(modifier = Modifier.height(18.dp))

            /* 👥 PASSENGERS */
            Text("Passengers", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                (1..4).forEach { i ->
                    PassengerCircle(
                        text = i.toString(),
                        selected = selectedPassengers == i
                    ) {
                        selectedPassengers = i
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            /* ➡️ CONTINUE BUTTON */
            Button(
                onClick = {
                    val encodedFrom = URLEncoder.encode(from, "UTF-8")
                    val encodedTo = URLEncoder.encode(to, "UTF-8")
                    val encodedDate = URLEncoder.encode(selectedDate, "UTF-8")
                    val encodedTime = URLEncoder.encode(selectedTime, "UTF-8")

                    navController.navigate(
                        "fourth/$encodedFrom/$encodedTo/$encodedDate/$encodedTime/$selectedPassengers"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Continue", fontSize = 18.sp)
            }
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ThirdScreenPreview() {
//    ThirdScreen(
//        navController = rememberNavController(),
//        from = "Connaught Place",
//        to = "India Gate"
//    )
//}
