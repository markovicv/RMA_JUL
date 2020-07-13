package com.example.rma_final_project.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rma_final_project.data.model.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun getWeatherDao():WeatherDao
}