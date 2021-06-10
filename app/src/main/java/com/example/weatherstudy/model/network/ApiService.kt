package com.example.weatherstudy.model.network

import retrofit2.http.GET
import retrofit2.http.Query

// interface com as requisicoes que serao feitas ao servidor
interface ApiService {

    @GET("forecast?")
    suspend fun getCityWeather(
        @Query("q") cityName: String?,
        @Query("appid") app_id: String,
        @Query("units") units: String = "metric"
    ): WeatherCityModel
}