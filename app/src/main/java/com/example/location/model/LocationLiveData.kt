package com.example.location.model

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import android.Manifest
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices

class LocationLiveData(private val context: Context): LiveData<Coordinates>( ) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val TAG = "LocationLiveData"

    override fun onActive() {
        super.onActive()
        Log.d(TAG, "LocationLiveData is active")
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
            Log.w(TAG, "Location permissions are not granted")
            return
        }
        Log.w(TAG, "Location permissions are granted")
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location.also {
                setLocationData(it)
            }

        }
    }

    private fun setLocationData(location: Location?) {
        location?.let {
            value = (Coordinates(it.latitude, it.longitude))
        }
    }
}