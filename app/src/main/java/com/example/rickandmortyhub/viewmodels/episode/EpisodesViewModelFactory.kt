package com.example.rickandmortyhub.viewmodels.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.repositories.RemoteRepository
import javax.inject.Inject

class EpisodesViewModelFactory @Inject constructor(
    private val repository: RemoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodesViewModel(repository) as T
    }
}