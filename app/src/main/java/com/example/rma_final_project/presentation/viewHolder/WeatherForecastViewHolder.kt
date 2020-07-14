package com.example.rma_final_project.presentation.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rma_final_project.data.domain.WeatherDomain
import com.example.rma_final_project.presentation.Konstants
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherForecastViewHolder(itemView: View, private val onItemClicked:(Int)->Unit):
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onItemClicked.invoke(adapterPosition)
        }
    }

    fun bind(weatherForecast: WeatherDomain){
        itemView.tempId.setText((weatherForecast.temperature-Konstants.KELVIN_TO_C).toString())
        itemView.cityId.setText(weatherForecast.city)
        itemView.dateId.setText(weatherForecast.date)
        Glide.with(itemView)
            .load("http://openweathermap.org/img/w/" + weatherForecast.icon + ".png")
            .into(itemView.weatherIconId)
    }
}