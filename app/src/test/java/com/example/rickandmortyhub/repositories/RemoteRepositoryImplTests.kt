package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.common.network.model.character.CharactersInfo
import com.example.rickandmortyhub.common.network.model.episode.EpisodesInfo
import com.example.rickandmortyhub.common.network.model.location.LocationsInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTests {

    @MockK
    lateinit var client: RickMortyClient

    lateinit var repository: RemoteRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = RemoteRepositoryImpl(client)
    }

    @Test
    fun `downloadCharacters call correctly calls client method`() {
        // === Setup ===
        val fakeInfo = CharactersInfo(mockk(), listOf())
        coEvery { client.getCharacters() } returns fakeInfo

        // === Call ===
        runBlocking { repository.downloadCharacters() }

        // === Assertions ===
        coVerify { client.getCharacters() }
    }

    @Test
    fun `downloadLocations call correctly calls client method`() {
        // === Setup ===
        val fakeInfo = LocationsInfo(mockk(), listOf())
        coEvery { client.getLocations() } returns fakeInfo

        // === Call ===
        runBlocking { repository.downloadLocations() }

        // === Assertions ===
        coVerify { client.getLocations() }
    }

    @Test
    fun `downloadEpisodes call correctly calls client method`() {
        // === Setup ===
        val fakeInfo = EpisodesInfo(mockk(), listOf())
        coEvery { client.getEpisodes() } returns fakeInfo

        // === Call ===
        runBlocking { repository.downloadEpisodes() }

        // === Assertions ===
        coVerify { client.getEpisodes() }
    }
}