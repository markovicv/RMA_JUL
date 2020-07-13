package com.example.rma_final_project.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rma_final_project.data.model.Resource
import com.example.rma_final_project.data.repositories.WeatherRepo
import com.example.rma_final_project.presentation.contract.WeatherContract
import com.example.rma_final_project.presentation.state.WeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(private val weatherRepo: WeatherRepo): ViewModel(), WeatherContract.ViewModel {

    override val weatherForecastState: MutableLiveData<WeatherState> = MutableLiveData()
    private val subscriptions = CompositeDisposable()

    override fun fetchWeatherByCity(city: String, days: Int) {
        val subscription = weatherRepo.fetchWeatherForCity(city,days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it){
                    is Resource.Loading->weatherForecastState.value = WeatherState.Loading
                    is Resource.Success-> weatherForecastState.value = WeatherState.DataFetched
                    is Resource.Error -> weatherForecastState.value = WeatherState.Error("error while fetching data")
                }
            },{
                weatherForecastState.value = WeatherState.Error("Weather is from db")
            })
        subscriptions.add(subscription)
    }

    override fun getWeatherByCity(city: String, days: Int) {
        val subscription = weatherRepo.getWeatherByCity(city,days)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                weatherForecastState.value = WeatherState.Succes(it)
            },{
                weatherForecastState.value = WeatherState.Error("error while getting data")
            })

        subscriptions.add(subscription)

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}