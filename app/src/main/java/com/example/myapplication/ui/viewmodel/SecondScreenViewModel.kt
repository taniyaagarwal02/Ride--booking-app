package com.example.myapplication.ui.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SecondScreenViewModel : ViewModel() {

    private val _currentLocation = MutableStateFlow("")
    val currentLocation: StateFlow<String> = _currentLocation

    private val _destination = MutableStateFlow("")
    val destination: StateFlow<String> = _destination

    fun onCurrentLocationChange(value: String) {
        _currentLocation.value = value
    }

    fun onDestinationChange(value: String) {
        _destination.value = value
    }

    fun useCurrentLocation() {
        _currentLocation.value = "My Current Location"
    }
}
