package com.example.rickandmortyhub.mvvm.model

import com.example.rickandmortyhub.network.RickMortyClient
import com.example.rickandmortyhub.network.model.character.CharactersInfo
import com.example.rickandmortyhub.network.model.episode.EpisodesInfo
import com.example.rickandmortyhub.network.model.location.LocationsInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RickAndMortyRepositoryImpl(
    private val rickMortyClient: RickMortyClient
): RickAndMortyRepositoryInterface {

    override fun downloadCharacters(): Single<CharactersInfo> {
        return subscribeObserve(rickMortyClient.getCharacters())
    }

    override fun downloadLocations(): Single<LocationsInfo> {
        return subscribeObserve(rickMortyClient.getLocations())
    }

    override fun downloadEpisodes(): Single<EpisodesInfo> {
        return  subscribeObserve(rickMortyClient.getEpisodes())
    }

    private fun <T> subscribeObserve(observable: Single<T>): Single<T> {
        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}