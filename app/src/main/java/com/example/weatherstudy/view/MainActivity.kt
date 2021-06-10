package com.example.weatherstudy.view

import android.content.Intent
import android.content.res.Configuration.*
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherstudy.R
import com.example.weatherstudy.adapters.WeatherAdapter
import com.example.weatherstudy.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_more_info.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        getCityWeather()
        setupObserver()
        refreshApp()
        setupRecyclerView()

        button_more_details.setOnClickListener {
            goToNextPage()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(WeatherViewModel::class.java)
    }

    private fun getCityWeather() {
        spinner_cities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var city = spinner_cities.selectedItem.toString()
                viewModel.getCityWeather(city)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        viewModel.getCurrentTime()
    }

    private fun setupObserver() {
        progress_bar_main_activity.visibility = View.VISIBLE
        text_view_last_updated.visibility = View.GONE
        viewModel.weather.observe(this) {
            text_view_main_temp.text = "${it.list.first().main.temp.roundToInt()}ºC"
            text_view_timezone.text = it.city.name
            text_view_main_condition.text = it.list.first().weather.first().description
            (recycler_view.adapter as WeatherAdapter).setItems(it.list)
            text_view_last_updated.visibility = View.VISIBLE
            progress_bar_main_activity.visibility = View.GONE
        }

        viewModel.time.observe(this) {
            text_view_last_updated.text = it
        }
    }

    private fun refreshApp() {
        refresh_layout.setOnRefreshListener {
            getCityWeather()
            refresh_layout.isRefreshing = false
        }

    }

    private fun setupRecyclerView() {
        recycler_view.apply {
            adapter = WeatherAdapter(this@MainActivity)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun goToNextPage() {
        var city = spinner_cities.selectedItem.toString()
        val intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra("city", city)
        startActivity(intent)
    }
}