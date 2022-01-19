package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.weatherapp.App.Companion.navigationHolder
import com.example.weatherapp.App.Companion.router
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.mvpcities.CitiesScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

private const val REFRESH_PERIOD = 60000L
private const val MINIMAL_DISTANCE = 100f

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            when {
                result -> getLocation()
                !ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    Toast.makeText(
                        this,
                        "Разрешите доступ к геолокации в настройках",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> Toast.makeText(this, "T_T", Toast.LENGTH_SHORT).show()
            }
        }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                REFRESH_PERIOD,
                MINIMAL_DISTANCE,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        showFragment(location)
                    }

                    override fun onStatusChanged(
                        provider: String?,
                        status: Int,
                        extras: Bundle?
                    ) {
                        // do nothing
                    }
                }
            )
        } else {
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)?.let {
                showFragment(it)
            }
        }
    }

    private fun showFragment(location: Location) {
        router.navigateTo(CitiesScreen(location.latitude, location.longitude))
    }

    private val navigator = AppNavigator(this, R.id.activity_main_container)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}