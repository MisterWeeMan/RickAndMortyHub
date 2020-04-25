package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.database.RickMortyDao
import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.model.Episode
import com.example.rickandmortyhub.common.model.Location
import com.example.rickandmortyhub.common.utils.converters.CharactersConverter
import com.example.rickandmortyhub.common.utils.isNotNullAndNotEmpty
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val rickMortyClient: RickMortyClient,
    private val rickMortyDao: RickMortyDao,
    private val converters: CharactersConverter
) : RemoteRepository {

    override suspend fun downloadCharacters(): List<Character> {
        var charactersDb = rickMortyDao.getCharacters()

        if (charactersDb.isNotNullAndNotEmpty()) {
            return charactersDb.map { converters(it) }
        }

        val characters = rickMortyClient.getCharacters().characters

        charactersDb = characters.map { converters(it) }
        rickMortyDao.insertAllCharacters(charactersDb)

        return characters
    }

    override suspend fun downloadLocations(): List<Location> {
        val locationsDb = rickMortyDao.getLocations()

        if (locationsDb.isNotNullAndNotEmpty()) {
            return locationsDb
        }

        val locations = rickMortyClient.getLocations().locations

        rickMortyDao.insertAllLocations(locations)

        return locations
    }

    override suspend fun downloadEpisodes(): List<Episode> {
        val episodesDb = rickMortyDao.getEpisodes()

        if (episodesDb.isNotNullAndNotEmpty()) {
            return episodesDb
        }

        val episodes = rickMortyClient.getEpisodes().episodes

        rickMortyDao.insertAllEpisodes(episodes)

        return episodes
    }
}