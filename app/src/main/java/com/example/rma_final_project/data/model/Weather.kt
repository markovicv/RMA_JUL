package com.example.rma_final_project.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Weather(
    @Json(name="icon")
    val icon:String
) {
}