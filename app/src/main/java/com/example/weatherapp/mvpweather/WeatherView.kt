package com.example.weatherapp.mvpweather

import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface WeatherView : MvpView {
    fun showWeather(weather: CurrentWeather, city: City)
    fun showDailyWeather(weather: DailyWeather)
    fun showError(message: String)
}