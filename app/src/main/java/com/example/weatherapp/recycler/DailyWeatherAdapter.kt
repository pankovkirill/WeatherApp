package com.example.weatherapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.databinding.WeatherListItemBinding

class DailyWeatherAdapter() :
    RecyclerView.Adapter<DailyWeatherViewHolder>() {

    private var weather = ArrayList<DailyWeather.Daily>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val binding =
            WeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(weather[position])
    }

    override fun getItemCount() = weather.size

    fun setUsers(newWeather: List<DailyWeather.Daily>) {
        val diffUtilsCallBack = DiffUtilsCallBack(weather, newWeather)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallBack)
        weather.clear()
        weather.addAll(newWeather)
        diffResult.dispatchUpdatesTo(this)
    }
}