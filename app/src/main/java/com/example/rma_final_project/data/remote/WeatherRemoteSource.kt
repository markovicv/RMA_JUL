package com.example.rma_final_project.data.remote

import com.example.rma_final_project.data.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteSource {
    @GET("data/2.5/forecast?")
    fun getWeatherForCity(@Query("q")city:String, @Query("cnt")day:Int, @Query("APPID") app_id: String): Observable<WeatherResponse>
}