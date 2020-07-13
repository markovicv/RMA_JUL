package com.example.rma_final_project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var city:String,
    var temperature:Double,
    var min_temperature:Double,
    var max_temperature:Double,
    var pressure:Double,
    var humidity:Double,
    var date:String,
    var wind:Double,
    var icon:String,
    val lat:Double,
    val lon:Double
) {
}