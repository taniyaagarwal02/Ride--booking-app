package com.example.myapplication.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
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
import com.example.myapplication.R
import com.example.myapplication.ui.components.PassengerSegmentedSelector
import com.example.myapplication.ui.components.SegmentedDateSelector
import com.example.myapplication.ui.viewmodel.ThirdScreenViewModel
import java.net.URLEncoder
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.Purple40
import java.net.URLDecoder


@Composable
fun ThirdScreen(
    navController: NavController,
    viewModel: ThirdScreenViewModel = viewModel(),
    from: String,
    to: String
) {
    val decodedFrom = URLDecoder.decode(from, "UTF-8")
    val decodedTo = URLDecoder.decode(to, "UTF-8")




    Column(modifier = Modifier.fillMaxSize()) {

        /* 🔹 TOP BLUE SECTION (REDUCED HEIGHT) */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // ⬅️ controlled height
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

        /* 🔹 BOTTOM WHITE SECTION */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                "Ride Details",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp)

            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF6F5FF)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    com.example.myapplication.ui.components.InfoRow("From", decodedFrom)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    com.example.myapplication.ui.components.InfoRow("To", decodedTo)
                }
            }




            /* 📅 DATE */
            Text(
                text = "Date",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            SegmentedDateSelector(
                options = listOf("Today", "Tomorrow", "Other"),
                selectedOption = viewModel.selectedDate,
                onOptionSelected = { viewModel.onDateSelected(it) }
            )



            Spacer(modifier = Modifier.height(18.dp))

            /* ⏰ TIME */
            Text(
                text = "Time",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            SegmentedDateSelector(
                options = listOf("Morning", "Afternoon", "Evening"),
                selectedOption = viewModel.selectedTime,
                onOptionSelected = { viewModel.onTimeSelected(it) }
            )



            Spacer(modifier = Modifier.height(18.dp))

            /* 👥 PASSENGERS */
            Text(
                text = "Passengers",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            PassengerSegmentedSelector(
                selectedPassengers = viewModel.selectedPassengers,
                onSelected = { viewModel.onPassengersSelected(it) }
            )




            Spacer(modifier = Modifier.height(32.dp))

            /* ➡️ CONTINUE BUTTON */
            Button(
                onClick = {
                    val encodedFrom = URLEncoder.encode(from, "UTF-8")
                    val encodedTo = URLEncoder.encode(to, "UTF-8")
                    val encodedDate = URLEncoder.encode(viewModel.selectedDate, "UTF-8")
                    val encodedTime = URLEncoder.encode(viewModel.selectedTime, "UTF-8")

                    navController.navigate(
                        "fourth/$encodedFrom/$encodedTo/$encodedDate/$encodedTime/${viewModel.selectedPassengers}"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(30.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Purple40,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Continue",
                    fontSize = 18.sp
                )
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    ThirdScreen(
        navController = rememberNavController(),
        from = "Meerut",
        to = "Delhi"
    )
}
