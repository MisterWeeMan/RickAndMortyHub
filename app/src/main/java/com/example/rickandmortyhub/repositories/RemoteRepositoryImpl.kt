package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.network.model.location.Location
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val rickMortyClient: RickMortyClient
): RemoteRepository {

    override suspend fun downloadCharacters(): List<Character> {
        return rickMortyClient
            .getCharacters()
            .characters
    }

    override suspend fun downloadLocations(): List<Location> {
        return rickMortyClient
            .getLocations()
            .locations
    }

    override suspend fun downloadEpisodes(): List<Episode> {
        return rickMortyClient
            .getEpisodes()
            .episodes
    }
}