package com.example.weatherapp.mvpweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.models.City
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.data.repository.WeatherRepositoryFactory
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.recycler.DailyWeatherAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class WeatherFragment(city: City) : MvpAppCompatFragment(), WeatherView {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val repository = WeatherRepositoryFactory.create()
    private val adapter = DailyWeatherAdapter()

    private val presenter by moxyPresenter {
        WeatherPresenter(city, repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.fragmentCitiesRecyclerView

        recyclerView.let { recyclerView ->
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(city: City) = WeatherFragment(city)
    }

    override fun showWeather(weather: CurrentWeather, city: City) {
        with(binding) {
            textLocation.text = city.city
            textTemp.text = "${weather.current.temp}°с"
            textDescription.text = weather.current.weather[0].description
        }
    }

    override fun showDailyWeather(weather: DailyWeather) {
        adapter.setUsers(weather.daily)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}