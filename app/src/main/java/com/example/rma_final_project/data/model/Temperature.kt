package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Temperature(
    @Json(name="temp")
    val temp:Double,
    @Json(name="temp_min")
    val temp_min:Double,
    @Json(name="temp_max")
    val temp_max :Double,
    @Json(name="pressure")
    val pressure:Double,
    @Json(name="humidity")
    val humidity:Double
) {
}