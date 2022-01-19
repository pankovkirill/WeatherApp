package com.example.weatherapp.mvpweather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.weatherapp.data.models.City
import com.github.terrakok.cicerone.androidx.FragmentScreen

class WeatherScreen(
    private val city: City
) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        WeatherFragment.newInstance(city)
}