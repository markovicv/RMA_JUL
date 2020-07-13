package com.example.rma_final_project.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rma_final_project.presentation.state.WeatherState

interface WeatherContract {
    interface ViewModel{

        val weatherForecastState: LiveData<WeatherState>
        fun fetchWeatherByCity(city:String,days:Int)
        fun getWeatherByCity(city:String,days:Int)
    }
}