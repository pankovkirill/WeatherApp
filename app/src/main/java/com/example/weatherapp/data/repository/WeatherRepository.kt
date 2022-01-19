package com.example.weatherapp.data.repository

import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getCities(): ArrayList<City>
    fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Single<CurrentWeather>

    fun getDailyWeather(
        lat: Double,
        lon: Double
    ): Single<DailyWeather>
}