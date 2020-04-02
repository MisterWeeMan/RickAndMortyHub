package com.example.rickandmortyhub.mvvm.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val repository: RickMortyRemoteRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(repository) as T
    }
}