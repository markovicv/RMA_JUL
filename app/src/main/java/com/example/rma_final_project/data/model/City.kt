package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(

    @Json(name = "coord")
    val coord:Coordinates
) {
}