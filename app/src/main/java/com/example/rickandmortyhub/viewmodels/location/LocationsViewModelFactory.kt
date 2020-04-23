package com.example.rickandmortyhub.viewmodels.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.repositories.RemoteRepository
import javax.inject.Inject

class LocationsViewModelFactory @Inject constructor(
    private val repository: RemoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationsViewModel(repository) as T
    }
}