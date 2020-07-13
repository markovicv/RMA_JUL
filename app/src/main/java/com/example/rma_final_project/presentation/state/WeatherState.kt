package com.example.rma_final_project.presentation.state

import com.example.rma_final_project.data.domain.WeatherDomain

sealed class WeatherState {
    object Loading:WeatherState()
    object DataFetched:WeatherState()

    data class Succes(val weatherForecast:List<WeatherDomain>):WeatherState()
    data class Error(val message:String):WeatherState()
}