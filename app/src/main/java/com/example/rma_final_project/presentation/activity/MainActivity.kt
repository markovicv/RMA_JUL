package com.example.rma_final_project.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rma_final_project.MapsActivity
import com.example.rma_final_project.R
import com.example.rma_final_project.presentation.Konstants
import com.example.rma_final_project.presentation.adapter.WeatherForecastAdapter
import com.example.rma_final_project.presentation.contract.WeatherContract
import com.example.rma_final_project.presentation.state.WeatherState
import com.example.rma_final_project.presentation.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val weatherForecastViewModel: WeatherContract.ViewModel by viewModel<WeatherViewModel>()
    private lateinit var weatherAdapter: WeatherForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvID.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherForecastAdapter({
            val intent = Intent(this,MapsActivity::class.java)
            intent.putExtra("DETAILS",it)
            startActivity(intent)
        })
        rvID.adapter = weatherAdapter

        weatherForecastViewModel.weatherForecastState.observe(this, Observer {
            renderState(it)
        })

        btnPrikaz.setOnClickListener {
            if(etPlace.text.toString().isNullOrBlank() || etDays.text.toString().isNullOrBlank()){
                Toast.makeText(this,Konstants.EMPTY,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(Integer.parseInt(etDays.text.toString())>10){
                Toast.makeText(this,Konstants.LIMIT,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            weatherForecastViewModel.getWeatherByCity(etPlace.text.toString(),Integer.parseInt(etDays.text.toString()))
            weatherForecastViewModel.fetchWeatherByCity(etPlace.text.toString(),Integer.parseInt(etDays.text.toString()))
        }
    }
    private fun renderState(state: WeatherState){
        when(state){
            is WeatherState.Succes->{
                weatherAdapter.setWeatherList(state.weatherForecast)
            }
            is WeatherState.Error->{
                Toast.makeText(this,state.message, Toast.LENGTH_SHORT).show()
            }
            is WeatherState.DataFetched->{
                Toast.makeText(this,"Data fetched!!!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}