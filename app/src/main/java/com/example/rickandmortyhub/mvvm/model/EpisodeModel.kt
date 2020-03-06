package com.example.rickandmortyhub.mvvm.model

import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.network.model.episode.EpisodesInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EpisodeModel {

    fun downloadEpisodes(): Single<EpisodesInfo> {
        return RetrofitFactory
            .rickMortyClient
            .getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}