package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name="lat")
    val lat:Double,
    @Json(name="lon")
    val lon:Double

) {
}