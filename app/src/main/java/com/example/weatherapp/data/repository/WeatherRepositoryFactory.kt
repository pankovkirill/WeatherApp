package com.example.weatherapp.data.repository

object WeatherRepositoryFactory {
    fun create(): WeatherRepository = WeatherRepositoryImpl()
}