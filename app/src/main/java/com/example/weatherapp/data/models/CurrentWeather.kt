package com.example.weatherapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val timezone: String,
    val current: Current
) : Parcelable {
    @Parcelize
    data class Current(
        val temp: Double,
        val weather: MutableList<Weather>
    ) : Parcelable {
        @Parcelize
        data class Weather(
            val description: String
        ) : Parcelable
    }
}