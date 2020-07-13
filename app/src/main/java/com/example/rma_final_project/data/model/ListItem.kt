package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListItem(
    @Json(name = "main")
    val main:Temperature,
    @Json(name = "dt_txt")
    val date:String,
    @Json(name = "wind")
    val wind:Wind,
    @Json(name="weather")
    val weatherList:List<Weather>
) {
}