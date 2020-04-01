package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.network.model.episode.Episode
import com.example.rickandmortyhub.network.model.location.Location

interface RickMortyRemoteRepository {

    suspend fun downloadCharacters(): List<Character>

    suspend fun downloadLocations(): List<Location>

    suspend fun downloadEpisodes(): List<Episode>
}