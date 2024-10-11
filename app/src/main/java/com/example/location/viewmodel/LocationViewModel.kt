package com.example.location.viewmodel

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModel
import com.example.location.model.LocationLiveData

class LocationViewModel(context: Context):ViewModel() {
    private val locationLiveData = LocationLiveData(context)

    fun getLocationLiveData() = locationLiveData
}

fun Location(viewModel: LocationViewModel) {
    val location by viewModel.getLocationLiveData().observeAsState()

    if (location !== null) {

    }
    Column() {
        Text(location?.latitude.toString())
        Text(location?.longitude.toString())
    }
}
