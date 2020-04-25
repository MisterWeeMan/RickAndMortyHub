package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.model.Episode
import com.example.rickandmortyhub.common.model.Location

interface RemoteRepository {

    suspend fun downloadCharacters(): List<Character>

    suspend fun downloadLocations(): List<Location>

    suspend fun downloadEpisodes(): List<Episode>
}