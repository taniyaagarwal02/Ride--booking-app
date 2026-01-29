package com.example.myapplication.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rides")
data class RideEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fromLocation: String,
    val toLocation: String,
    val date: String,
    val time: String,
    val passengers: Int
)
