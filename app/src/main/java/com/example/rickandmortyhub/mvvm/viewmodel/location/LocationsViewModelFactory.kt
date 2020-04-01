package com.example.rickandmortyhub.mvvm.viewmodel.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository

class LocationsViewModelFactory(
    private val repository: RickMortyRemoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationsViewModel(repository) as T
    }
}