package com.example.rickandmortyhub.viewmodels.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.common.network.model.location.Location
import com.example.rickandmortyhub.repositories.RemoteRepository
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val repository: RemoteRepository
): ViewModel() {

    val locationList: LiveData<List<Location>>
        get() = mLocationList
    private val mLocationList = MutableLiveData<List<Location>>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage
    private val mErrorMessage = MutableLiveData<String>()

    fun getLocations() {
        viewModelScope.launch {
            try {
                mLocationList.value = repository.downloadLocations()
            } catch (exc: Exception) {
                mErrorMessage.value = exc.message
            }
        }
    }
}