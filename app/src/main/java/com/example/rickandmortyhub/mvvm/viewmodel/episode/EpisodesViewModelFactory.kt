package com.example.rickandmortyhub.mvvm.viewmodel.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository

class EpisodesViewModelFactory(
    private val repository: RickMortyRemoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodesViewModel(repository) as T
    }
}