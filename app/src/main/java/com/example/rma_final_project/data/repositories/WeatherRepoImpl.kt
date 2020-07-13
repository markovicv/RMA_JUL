package com.example.rma_final_project.data.repositories

import com.example.rma_final_project.data.domain.WeatherDomain
import com.example.rma_final_project.data.local.WeatherDao
import com.example.rma_final_project.data.model.Resource
import com.example.rma_final_project.data.model.WeatherEntity
import com.example.rma_final_project.data.remote.WeatherRemoteSource
import com.example.rma_final_project.presentation.Konstants
import io.reactivex.Observable

class WeatherRepoImpl(val weatherRemoteSource: WeatherRemoteSource, val weatherLocalSource: WeatherDao):WeatherRepo {

    override fun fetchWeatherForCity(city: String, days: Int): Observable<Resource<Unit>> {
        return weatherRemoteSource.getWeatherForCity(city,days, Konstants.API_KEY).doOnNext {
            val weatherList = it.list
            val weatherEntities:MutableList<WeatherEntity> = mutableListOf<WeatherEntity>()

            for(w in weatherList){
                val weather:WeatherEntity = WeatherEntity(0,city,w.main.temp,w.main.temp_min,w.main.temp_max,w.main.pressure,w.main.humidity,w
                    .date,w.wind.speed,w.weatherList.get(0).icon,it.city.coord.lat,it.city.coord.lon)
                weatherEntities.add(weather)

            }
            weatherLocalSource.deleteAndInsertAll(weatherEntities)
        }

            .map {
                Resource.Success(Unit)
            }
    }

    override fun getWeatherByCity(city: String, days: Int): Observable<List<WeatherDomain>> {
        return weatherLocalSource.getAllForCity(city,days).map {
            it.map {
                WeatherDomain(it.id,it.temperature,it.min_temperature,it.max_temperature,it.pressure,it.humidity,it.date,it.wind,city,it.icon,it.lat,it.lon)
            }
        }
    }
}