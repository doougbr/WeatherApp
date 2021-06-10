package com.example.weatherstudy.repository

import com.example.weatherstudy.model.network.ApiService
import com.example.weatherstudy.model.network.RetrofitClient
import com.example.weatherstudy.model.network.helper.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val appKey = Constants.APP_KEY

class WeatherRepository {

    private val retrofitClient = RetrofitClient()
        .createService(ApiService::class.java)

    suspend fun getCityWeather(city: String?) = withContext(Dispatchers.IO) {
        retrofitClient.getCityWeather(city, appKey)
    }
}