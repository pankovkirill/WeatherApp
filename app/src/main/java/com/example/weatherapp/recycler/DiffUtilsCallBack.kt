package com.example.weatherapp.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.data.models.DailyWeather

class DiffUtilsCallBack(
    private val oldWeatherList: List<DailyWeather.Daily>,
    private val newWeatherList: List<DailyWeather.Daily>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldWeatherList.size

    override fun getNewListSize() = newWeatherList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldWeatherList[oldItemPosition].dt == newWeatherList[newItemPosition].dt


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, oldTemp) = oldWeatherList[oldItemPosition]
        val (_, newTemp) = newWeatherList[newItemPosition]

        return oldTemp == newTemp
    }
}