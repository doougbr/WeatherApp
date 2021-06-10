package com.example.weatherstudy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherstudy.R
import com.example.weatherstudy.model.network.WeatherList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt



class MoreInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<MoreInfoAdapter.MoreInfoViewHolder>() {

    private var dataSet = mutableListOf<WeatherList>()

    inner class MoreInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val time: TextView = view.findViewById(R.id.text_view_time)
        private val temp: TextView = view.findViewById(R.id.text_view_temp)
        private val condition: TextView = view.findViewById(R.id.text_view_condition)


        fun bind(weatherList: WeatherList) {
            time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(weatherList.timeStamp)
            temp.text = weatherList.main.temp.roundToInt().toString() + "ºC"
            condition.text = weatherList.weather.first().description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoreInfoAdapter.MoreInfoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false)
        return MoreInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoreInfoAdapter.MoreInfoViewHolder, position: Int) {
        val currentItem = dataSet[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = dataSet.size

    fun setItems(newItems: List<WeatherList>) {
        dataSet = newItems as MutableList<WeatherList>
        notifyDataSetChanged()
    }
}


