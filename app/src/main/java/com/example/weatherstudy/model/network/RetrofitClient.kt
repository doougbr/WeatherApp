package com.example.weatherstudy.model.network

import com.example.weatherstudy.model.network.helper.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// classe responsável pela inicializacao do Retrofit
class RetrofitClient {

    private val baseUrl = Constants.BASE_URL
    private lateinit var retrofit: Retrofit

    private fun getRetrofitInstance(): Retrofit {

        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    // essa funcao permite que seja criado vários services utilizando a mesma funcao
    // de tipo genérico, que no caso é o <T>, ou seja, se eu tiver vários services
    // diferentes (GET, POST, etc), eu posso usar a mesma funcao para todos
    fun <T> createService(serviceClass: Class<T>): T {
        return getRetrofitInstance().create(serviceClass)
    }
}