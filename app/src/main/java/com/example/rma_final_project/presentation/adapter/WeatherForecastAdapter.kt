package com.example.rma_final_project.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_final_project.R
import com.example.rma_final_project.data.domain.WeatherDomain
import com.example.rma_final_project.presentation.diff.WeatherForecastDiffCallBack
import com.example.rma_final_project.presentation.viewHolder.WeatherForecastViewHolder

class WeatherForecastAdapter(private val onItemClicked:(WeatherDomain)->Unit): RecyclerView.Adapter<WeatherForecastViewHolder>() {

    private var weatherForecastList = mutableListOf<WeatherDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.weather_item,parent,false)
        return WeatherForecastViewHolder(containerView,{
            val weatherItem = weatherForecastList.get(it)
            onItemClicked.invoke(weatherItem)

        })

    }

    override fun getItemCount(): Int {
        return weatherForecastList.size

    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.bind(weatherForecastList.get(position))
    }
    fun setWeatherList(newList:List<WeatherDomain>){
        val diffCallBack = WeatherForecastDiffCallBack(weatherForecastList,newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        weatherForecastList.clear()
        weatherForecastList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }


}