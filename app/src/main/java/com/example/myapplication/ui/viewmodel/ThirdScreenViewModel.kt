package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThirdScreenViewModel : ViewModel() {

    // 📅 Date
    var selectedDate by mutableStateOf("Today")
        private set

    // ⏰ Time
    var selectedTime by mutableStateOf("Morning")
        private set

    // 👥 Passengers
    var selectedPassengers by mutableStateOf(1)
        private set

    /* -------- EVENTS -------- */

    fun onDateSelected(date: String) {
        selectedDate = date
    }

    fun onTimeSelected(time: String) {
        selectedTime = time
    }

    fun onPassengersSelected(count: Int) {
        selectedPassengers = count
    }
}
