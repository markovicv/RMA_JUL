package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "cnt")
    val cnt:Int,
    @Json(name ="list")
    val list:List<ListItem>,
    @Json(name = "city")
    val city:City
) {


}