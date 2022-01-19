package com.example.weatherapp.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class DailyWeather(
    val daily: List<Daily>
) : Parcelable {
    @Parcelize
    data class Daily(
        val dt: String,
        val temp: Temp
    ) : Parcelable {
        @Parcelize
        data class Temp(
            val min: Double,
            val max: Double
        ) : Parcelable
    }
}