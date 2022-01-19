package com.example.weatherapp.mvpweather

import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.data.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class WeatherPresenter(
    private val city: City,
    private val repository: WeatherRepository
) : MvpPresenter<WeatherView>() {

    override fun onFirstViewAttach() {
        showWeather(city)
        showDailyWeather(city)
    }

    private fun showDailyWeather(city: City) {
        repository.getDailyWeather(city.lat, city.lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather: DailyWeather ->
                viewState.showDailyWeather(weather)
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })

    }

    private fun showWeather(city: City) {
        repository.getCurrentWeather(city.lat, city.lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ weather: CurrentWeather ->
                viewState.showWeather(weather, city)
            }, { error: Throwable ->
                viewState.showError(error.message.toString())
            })
    }
}