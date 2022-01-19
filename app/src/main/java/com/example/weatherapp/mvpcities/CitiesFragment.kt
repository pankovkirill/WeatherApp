package com.example.weatherapp.mvpcities

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.models.CurrentWeather
import com.example.weatherapp.data.models.DailyWeather
import com.example.weatherapp.data.repository.WeatherRepositoryFactory
import com.example.weatherapp.databinding.FragmentCitiesBinding
import com.example.weatherapp.recycler.DailyWeatherAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class CitiesFragment(lat: Double, lon: Double) : MvpAppCompatFragment(), CitiesView {

    companion object {
        fun newInstance(lat: Double, lon: Double) = CitiesFragment(lat, lon)
    }

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private val repository = WeatherRepositoryFactory.create()
    private val adapter = DailyWeatherAdapter()

    private val presenter by moxyPresenter {
        CitiesPresenter(repository, lat, lon)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.fragmentCitiesRecyclerView

        recyclerView.let { recyclerView ->
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        findCity()
    }

    private fun findCity() {
        binding.inputLayout.setEndIconOnClickListener {
            context?.let { it ->
                presenter.searchByAddress(
                    binding.inputEditText.text.toString(),
                    it
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showDailyWeather(weather: DailyWeather) {
        adapter.setUsers(weather.daily)
    }

    override fun showWeather(weather: CurrentWeather) {
        with(binding) {
            textTemp.text = "${weather.current.temp}°с"
            textDescription.text = weather.current.weather[0].description
            textLocation.text = weather.timezone
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}