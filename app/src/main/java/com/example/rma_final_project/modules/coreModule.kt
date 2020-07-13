package com.example.rma_final_project.modules

import androidx.room.Room
import com.example.rma_final_project.data.local.WeatherDatabase

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val coreModule = module {

    single { createMoshi() }

    single { createOkHttpClient() }

    single { createRetrofit(moshi = get(),httpClient = get()) }

    single {
        Room.databaseBuilder(androidContext(), WeatherDatabase::class.java,"NotesDB")
            .fallbackToDestructiveMigration()
            .build()
    }



}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()
}
fun createRetrofit(moshi: Moshi, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(httpClient)
        .build()
}
fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(60, TimeUnit.SECONDS)
    httpClient.connectTimeout(60, TimeUnit.SECONDS)
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    return httpClient.build()

}
inline fun <reified T> create(retrofit: Retrofit):T{
    return retrofit.create<T>(T::class.java)
}