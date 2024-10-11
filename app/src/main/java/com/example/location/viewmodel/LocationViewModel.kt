package com.example.location.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import com.example.location.model.LocationLiveData

class LocationViewModel(context: Context):ViewModel() {
    private val locationLiveData = LocationLiveData(context)
    private val TAG = "LocationViewModel"

    init {
        Log.d(TAG, "LocationViewModel initialized")
    }

    fun getLocationLiveData() = locationLiveData
}
@Composable
fun Location(viewModel: LocationViewModel) {
    val location by viewModel.getLocationLiveData().observeAsState()

    if (location !== null) {
        Text("Location available")
    } else { Text("No location available" )}
    Column() {
        if(location !== null) {
            Text(location?.latitude.toString())
            Text(location?.longitude.toString())
        } else {
            Log.e("error","no location")
            Text("Location not available")
        }
    }
}
