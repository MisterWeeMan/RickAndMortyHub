package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.network.model.character.Location
import com.example.rickandmortyhub.network.model.episode.Episode

/**
 * Retrieve data from data sources
 */
interface RickMortyRepository {

    fun fetchCharacters(): List<Character>

    fun fetchLocations(): List<Location>

    fun fetchEpisodes(): List<Episode>
}