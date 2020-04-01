package com.example.rickandmortyhub.network

import com.example.rickandmortyhub.common.*
import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.network.model.character.CharactersInfo
import com.example.rickandmortyhub.network.model.episode.Episode
import com.example.rickandmortyhub.network.model.episode.EpisodesInfo
import com.example.rickandmortyhub.network.model.location.Location
import com.example.rickandmortyhub.network.model.location.LocationsInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyClient {

    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacters(@Query("page") page: Int = 1): CharactersInfo

    @GET(SINGLE_CHARACTER_ENDPOINT)
    suspend fun getSingleCharacter(@Path("characterId") id: Int): Character

    @GET(LOCATION_ENDPOINT)
    suspend fun getLocations(): LocationsInfo

    @GET(SINGLE_LOCATION_ENDPOINT)
    suspend fun getSingleLocation(@Path("locationId") id: Int): Location

    @GET(EPISODE_ENDPOINT)
    suspend fun getEpisodes(): EpisodesInfo

    @GET(SINGLE_EPISODE_ENDPOINT)
    suspend fun getSingleEpisode(@Path("episodeId") id: Int): Episode
}