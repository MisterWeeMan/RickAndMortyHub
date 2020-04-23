package com.example.rickandmortyhub.viewmodels.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: RemoteRepository
): ViewModel() {

    private val mDataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = mDataState

    fun getCharacters() {
        mDataState.value = DataState.Loading

        viewModelScope.launch {
            try {
                val characters = repository.downloadCharacters()

                mDataState.value = DataState.Success(characters)
            } catch (exc: Exception) {
                mDataState.value = DataState.Failure(exc.message)
            }
        }
    }
}