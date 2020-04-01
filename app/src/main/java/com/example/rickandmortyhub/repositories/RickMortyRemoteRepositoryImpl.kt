package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.network.RickMortyClient
import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.network.model.episode.Episode
import com.example.rickandmortyhub.network.model.location.Location

class RickMortyRemoteRepositoryImpl(
    private val rickMortyClient: RickMortyClient
): RickMortyRemoteRepository {

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