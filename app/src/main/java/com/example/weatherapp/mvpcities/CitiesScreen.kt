package com.example.weatherapp.mvpcities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CitiesScreen(private val lat: Double, private val lon: Double) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        CitiesFragment.newInstance(lat, lon)
}