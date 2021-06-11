package com.example.weatherstudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherstudy.model.network.WeatherCityModel
import com.example.weatherstudy.repository.WeatherRepository
import kotlinx.coroutines.launch

class MoreInfoViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _weather = MutableLiveData<WeatherCityModel>()

    val weather = _weather

    // method where I'll call retrofit service and handle JSON response
    fun getCityWeather(city: String?) {
        viewModelScope.launch {
                val response = repository.getCityWeather(city)
                _weather.value = response
        }
    }
}