package com.example.weatherapp.data.repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.data.retrofit.WeatherApiFactory
import io.reactivex.rxjava3.core.Single

private const val UNITS = "metric"
private const val LANG = "ru"
private const val CURRENT = "minutely,hourly,daily,alerts"
private const val DAILY = "current,minutely,hourly,alerts"

class WeatherRepositoryImpl : WeatherRepository {

    private val weatherApi = WeatherApiFactory.create()

    private val data = arrayListOf<City>(
        City("Москва", 55.755826, 37.617299900000035),
        City("Санкт-Петербург", 59.9342802, 30.335098600000038),
        City("Новосибирск", 55.00835259999999, 82.93573270000002),
        City("Екатеринбург", 56.83892609999999, 60.60570250000001),
        City("Нижний Новгород", 56.2965039, 43.936059),
        City("Казань", 55.8304307, 49.06608060000008),
        City("Челябинск", 55.1644419, 61.4368432),
        City("Омск", 54.9884804, 73.32423610000001),
        City("Ростов-на-Дону", 47.2357137, 39.701505),
        City("Уфа", 54.7387621, 55.972055400000045)
    )

    override fun getCities(): ArrayList<City> = data

    override fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): Single<CurrentWeather> =
        weatherApi.getCurrentWeather(BuildConfig.WEATHER_API_KEY, UNITS, LANG, CURRENT, lat, lon)


    override fun getDailyWeather(
        lat: Double,
        lon: Double
    ): Single<DailyWeather> =
        weatherApi.getDailyWeather(BuildConfig.WEATHER_API_KEY, UNITS, LANG, DAILY, lat, lon)

}