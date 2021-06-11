package com.example.weatherstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherstudy.model.network.WeatherCityModel
import com.example.weatherstudy.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _weather = MutableLiveData<WeatherCityModel>()
    private val _time = MutableLiveData<String>()

    // valores que serão observados pela MainActivity
    val weather = _weather
    val time = _time

    fun getCityWeather(city: String) {
        viewModelScope.launch {
                val response = repository.getCityWeather(city)
                _weather.value = response
        }
    }

    fun getCurrentTime() {
        val sdf = SimpleDateFormat("MM-dd hh:mm", Locale.getDefault())
        val currentDate = sdf.format(Date())
        _time.value = currentDate
    }

}