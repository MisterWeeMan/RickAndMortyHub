package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.network.model.location.Location

interface RemoteRepository {

    suspend fun downloadCharacters(): List<Character>

    suspend fun downloadLocations(): List<Location>

    suspend fun downloadEpisodes(): List<Episode>
}