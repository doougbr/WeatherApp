package com.example.weatherstudy.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherstudy.R
import com.example.weatherstudy.adapters.MoreInfoAdapter
import com.example.weatherstudy.viewmodel.MoreInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_more_info.*

class MoreInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: MoreInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        setupViewModel()
        setupObserver()
        setupRecyclerView()
        getCityWeather()
        refreshApp()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)
            .get(MoreInfoViewModel::class.java)
    }

    private fun setupObserver(){
        progress_bar.visibility = View.VISIBLE
        viewModel.weather.observe(this) {
            (recyclerView.adapter as MoreInfoAdapter).setItems(it.list)
            progress_bar.visibility = View.GONE
        }

    }

    private fun getCityWeather(){
        var city = intent.getStringExtra("city")
        viewModel.getCityWeather(city)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            adapter = MoreInfoAdapter(this@MoreInfoActivity)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun refreshApp() {
        refresh_layout_2.setOnRefreshListener {
            getCityWeather()
            refresh_layout_2.isRefreshing = false
        }
    }

}