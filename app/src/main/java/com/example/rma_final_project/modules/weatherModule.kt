package com.example.rma_final_project.modules

import com.example.rma_final_project.data.local.WeatherDatabase
import com.example.rma_final_project.data.remote.WeatherRemoteSource
import com.example.rma_final_project.data.repositories.WeatherRepo
import com.example.rma_final_project.data.repositories.WeatherRepoImpl
import com.example.rma_final_project.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    viewModel {
        WeatherViewModel(get())
    }

    single<WeatherRepo>{
        WeatherRepoImpl(weatherRemoteSource = get(),weatherLocalSource =get())
    }
    single{
        get<WeatherDatabase>().getWeatherDao()
    }
    single<WeatherRemoteSource>{
        create(get())
    }
}