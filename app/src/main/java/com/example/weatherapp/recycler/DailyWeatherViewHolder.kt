package com.example.weatherapp.recycler

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.databinding.WeatherListItemBinding
import java.text.SimpleDateFormat

class DailyWeatherViewHolder(private val binding: WeatherListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat("dd/M")

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(weather: DailyWeather.Daily) {
        val dt = simpleDateFormat.format(weather.dt.toLong() * 1000L)
        with(binding) {
            weatherListItemDate.text = dt
            weatherListItemMax.text = weather.temp.max.toString()
            weatherListItemMin.text = weather.temp.min.toString()
        }
    }
}