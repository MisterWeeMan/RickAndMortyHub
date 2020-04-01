package com.example.rickandmortyhub.mvvm.model

import com.example.rickandmortyhub.network.model.character.CharactersInfo
import com.example.rickandmortyhub.network.model.episode.EpisodesInfo
import com.example.rickandmortyhub.network.model.location.LocationsInfo
import io.reactivex.Single

interface RickAndMortyRepositoryInterface {

    fun downloadCharacters(): Single<CharactersInfo>

    fun downloadLocations(): Single<LocationsInfo>

    fun downloadEpisodes(): Single<EpisodesInfo>
}