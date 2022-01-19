package com.example.weatherapp.mvpcities

import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface CitiesView : MvpView {
    fun showDailyWeather(weather: DailyWeather)
    fun showWeather(weather: CurrentWeather)
    fun showError(message: String)
}