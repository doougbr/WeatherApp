package com.example.weatherstudy.model.network

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("current")
    val currentWeather: Current,
    val timezone: String,
    val hourly: List<Hourly>
)

data class Current(
    @SerializedName("temp")
    val currentTemp: Double
)

data class Hourly(
    val dt: Long,
    val temp: Double,
    val humidity: Double,
)

