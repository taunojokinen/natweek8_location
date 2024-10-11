package com.example.location.model

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.location.Manifest
import com.google.android.gms.location.LocationServices

class LocationLiveData(private val context: Context): LiveData<Coordinates>( ) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onActive() {
        super.onActive()
        getLocationData()
    }

    fun getLocationData() {
        if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener {
            location -> location.also {
            setLocationData(it)
            }
        }

    }
}

private fun setLocationData(location: Location?) {
    location?.let {
        value = Coordinates(it.latitude, it.longitude)
    }
}