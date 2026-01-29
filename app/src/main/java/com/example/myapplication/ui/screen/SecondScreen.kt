package com.example.myapplication.ui.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.viewmodel.SecondScreenViewModel

@Composable
fun SecondScreen(
    navController: NavController,
    viewModel: SecondScreenViewModel = viewModel()
) {
    val currentLocation by viewModel.currentLocation.collectAsState()
    val destination by viewModel.destination.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        /*  TOP IMAGE SECTION */
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
                painter = painterResource(id = R.drawable.loc4),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

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
        }

        /*  BOTTOM CONTENT */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Choose your route",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            /*  CARD FOR LOCATIONS */
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF6F5FF)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    OutlinedTextField(
                        value = currentLocation,
                        onValueChange = { viewModel.onCurrentLocationChange(it) },
                        placeholder = { Text("Enter current location") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .clickable { viewModel.useCurrentLocation() }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.MyLocation,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Use current location")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = destination,
                        onValueChange = { viewModel.onDestinationChange(it) },
                        placeholder = { Text("Enter destination") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            /*  CONTINUE BUTTON */
            Button(
                onClick = {
                    navController.navigate(
                        "third?from=${Uri.encode(currentLocation)}&to=${Uri.encode(destination)}"
                    )
                },
                enabled = currentLocation.isNotBlank() && destination.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    ,
                shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                        containerColor = Purple40,
                contentColor = Color.White
            )
            ) {
                Text(
                    text = "Continue",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    val navController = rememberNavController()
    SecondScreen(navController = navController)
}