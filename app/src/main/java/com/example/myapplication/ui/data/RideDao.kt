package com.example.myapplication.ui.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RideDao {

    @Insert
    suspend fun insertRide(ride: RideEntity)

//    @Query("SELECT * FROM rides ORDER BY id DESC")
//    suspend fun getAllRides(): List<RideEntity>


    @Query("SELECT * FROM rides ORDER BY id DESC")
    fun getAllRides(): kotlinx.coroutines.flow.Flow<List<RideEntity>>

}
