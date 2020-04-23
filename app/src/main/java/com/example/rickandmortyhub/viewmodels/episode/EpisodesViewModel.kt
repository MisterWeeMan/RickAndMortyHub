package com.example.rickandmortyhub.viewmodels.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.repositories.RemoteRepository
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val repository: RemoteRepository
): ViewModel() {

    val episodeList: LiveData<List<Episode>>
        get() = mEpisodeList
    private val mEpisodeList = MutableLiveData<List<Episode>>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage
    private val mErrorMessage = MutableLiveData<String>()

    fun getEpisodes() {
        viewModelScope.launch {
            try {
                mEpisodeList.value = repository.downloadEpisodes()
            } catch (exc: Exception) {
                mErrorMessage.value = exc.message
            }
        }
    }
}