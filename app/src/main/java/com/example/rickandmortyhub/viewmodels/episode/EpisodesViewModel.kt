package com.example.rickandmortyhub.viewmodels.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val repository: RemoteRepository
): ViewModel() {

    private val mDataState = MutableLiveData<DataState>()
    val dataState: LiveData<DataState> = mDataState

    fun getEpisodes() {
        mDataState.value = DataState.Loading

        viewModelScope.launch {
            try {
                val episodes = repository.downloadEpisodes()

                mDataState.value = DataState.Success(episodes)
            } catch (exc: Exception) {
                mDataState.value = DataState.Failure(exc.message)
            }
        }
    }
}