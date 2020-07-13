package com.example.rma_final_project.data.repositories

import com.example.rma_final_project.data.domain.WeatherDomain
import com.example.rma_final_project.data.model.Resource
import io.reactivex.Observable

interface WeatherRepo {
    fun fetchWeatherForCity(city:String,days:Int): Observable<Resource<Unit>>
    fun getWeatherByCity(city:String,days: Int): Observable<List<WeatherDomain>>
}