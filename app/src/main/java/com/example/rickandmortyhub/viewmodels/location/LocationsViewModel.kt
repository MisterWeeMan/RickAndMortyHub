package com.example.rickandmortyhub.viewmodels.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val repository: RemoteRepository
): ViewModel() {

    private val mDataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = mDataState

    fun getLocations() {
        mDataState.value = DataState.Loading

        viewModelScope.launch {
            try {
                val locations = repository.downloadLocations()

                mDataState.value = DataState.Success(locations)
            } catch (exc: Exception) {
                mDataState.value = DataState.Failure(exc.message)
            }
        }
    }
}