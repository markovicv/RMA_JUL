package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Wind(
    @Json(name = "speed")
    val speed:Double
) {
}