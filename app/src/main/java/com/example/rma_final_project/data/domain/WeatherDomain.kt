package com.example.rma_final_project.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherDomain(
    val id:Long,
    var temperature:Double,
    var min_temperature:Double,
    var max_temperature:Double,
    var pressure:Double,
    var humidity:Double,
    var date:String,
    var wind:Double,
    var city:String,
    var icon:String,
    val lat:Double,
    val lon:Double
): Parcelable {
}