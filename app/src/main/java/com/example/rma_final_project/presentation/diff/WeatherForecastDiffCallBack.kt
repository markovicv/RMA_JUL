package com.example.rma_final_project.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.rma_final_project.data.domain.WeatherDomain

class WeatherForecastDiffCallBack(val oldWeatherList:List<WeatherDomain>, val newWeatherList:List<WeatherDomain>):
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldWeatherList.get(oldItemPosition).id == newWeatherList.get(newItemPosition).id
    }

    override fun getOldListSize(): Int {
        return oldWeatherList.size
    }

    override fun getNewListSize(): Int {
        return newWeatherList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val weatherOld = oldWeatherList.get(oldItemPosition)
        val weatherNew = newWeatherList.get(newItemPosition)

        return weatherNew.city == weatherOld.city && weatherNew.date == weatherOld.date &&
                weatherNew.humidity == weatherOld.humidity && weatherNew.temperature == weatherOld.temperature&&
                weatherNew.max_temperature == weatherOld.max_temperature && weatherNew.min_temperature == weatherOld.min_temperature
    }
}