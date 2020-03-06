package com.example.rickandmortyhub.mvvm.viewmodel.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyhub.mvvm.model.EpisodeModel
import com.example.rickandmortyhub.network.model.episode.Episode
import io.reactivex.disposables.CompositeDisposable

class EpisodesViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val episodeModel = EpisodeModel()

    val episodeList: LiveData<List<Episode>>
        get() = mutableEpisodeList
    private val mutableEpisodeList = MutableLiveData<List<Episode>>()

    val errorMessage: LiveData<String>
        get() = mutableErrorMessage
    private val mutableErrorMessage = MutableLiveData<String>()

    fun getEpisodes() {
        compositeDisposable.add(
            episodeModel
                .downloadEpisodes()
                .map { info -> info.episodes }
                .subscribe(
                    { list ->
                        mutableEpisodeList.value = list
                    },
                    { error ->
                        mutableErrorMessage.value = error.message
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}