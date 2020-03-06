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
    fun getCharacters(
        @Query("page") page: Int = 1
    ): Single<CharactersInfo>

    @GET(SINGLE_CHARACTER_ENDPOINT)
    fun getSingleCharacter(
        @Path("characterId") id: Int
    ): Single<Character>

    @GET(LOCATION_ENDPOINT)
    fun getLocations(): Single<LocationsInfo>

    @GET(SINGLE_LOCATION_ENDPOINT)
    fun getSingleLocation(
        @Path("locationId") id: Int
    ): Single<Location>

    @GET(EPISODE_ENDPOINT)
    fun getEpisodes(): Single<EpisodesInfo>

    @GET(SINGLE_EPISODE_ENDPOINT)
    fun getSingleEpisode(
        @Path("episodeId") id: Int
    ): Single<Episode>
}