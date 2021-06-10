package com.example.weatherstudy.model.network

import com.google.gson.annotations.SerializedName

data class WeatherCityModel(
    val cod: Long,
    val list: List<WeatherList>,
    val city: City
)

data class WeatherList(
    @SerializedName("dt")
    val timeStamp: Long,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Double,
)

data class Weather(
    val description: String
)

data class City(
    val name: String,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)