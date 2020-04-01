package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.network.RickMortyClient
import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.network.model.character.Location
import com.example.rickandmortyhub.network.model.episode.Episode

class RemoteRepository(
    private val rickMortyClient: RickMortyClient
): RickMortyRepository {

    override fun fetchCharacters(): List<Character> {
        TODO("Not yet implemented")
    }

    override fun fetchLocations(): List<Location> {
        TODO("Not yet implemented")
    }

    override fun fetchEpisodes(): List<Episode> {
        TODO("Not yet implemented")
    }
}