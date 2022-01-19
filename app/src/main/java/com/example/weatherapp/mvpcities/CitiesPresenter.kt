package com.example.weatherapp.mvpcities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.weatherapp.App.Companion.router
import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.mvpweather.WeatherScreen
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.io.IOException

class CitiesPresenter(
    private val repository: WeatherRepository,
    private val lat: Double,
    private val lon: Double
) : MvpPresenter<CitiesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        showDailyWeather(lat, lon)
        showWeather(lat, lon)
    }

    private fun showDailyWeather(lat: Double, lon: Double) {
        repository.getDailyWeather(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather: DailyWeather ->
                viewState.showDailyWeather(weather)
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })

    }

    private fun showWeather(lat: Double, lon: Double) {
        repository.getCurrentWeather(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather: CurrentWeather ->
                viewState.showWeather(weather)
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })

    }

    fun searchByAddress(city: String, context: Context) {
        val geocoder = Geocoder(context)

        Thread {
            try {
                val addresses = geocoder.getFromLocationName(city, 1)
                if (addresses.size > 0) {
                    getFindAddress(addresses, city)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun getFindAddress(
        addresses: MutableList<Address>,
        searchText: String
    ) {
        val location = LatLng(
            addresses[0].latitude,
            addresses[0].longitude
        )
        val city = City(searchText, location.latitude, location.longitude)
        showWeatherFragment(city)
    }

    fun showWeatherFragment(city: City) {
        router.navigateTo(WeatherScreen(city))
    }
}