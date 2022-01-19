package com.example.weatherapp.data.retrofit

import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("2.5/onecall")
    fun getCurrentWeather(
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("exclude") exclude: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<CurrentWeather>

    @GET("2.5/onecall")
    fun getDailyWeather(
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("exclude") exclude: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<DailyWeather>

}