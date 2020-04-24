package com.example.rickandmortyhub.repositories

import com.example.rickandmortyhub.common.database.RickMortyDao
import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.character.CharactersInfo
import com.example.rickandmortyhub.common.network.model.episode.EpisodesInfo
import com.example.rickandmortyhub.common.network.model.location.LocationsInfo
import com.example.rickandmortyhub.common.utils.converters.CharactersConverter
import com.example.rickandmortyhub.utils.characterDbMock
import com.example.rickandmortyhub.utils.characterMock
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTests {

    @MockK
    lateinit var client: RickMortyClient

    @MockK
    lateinit var dao: RickMortyDao

    @MockK
    lateinit var converter: CharactersConverter

    lateinit var repository: RemoteRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repository = RemoteRepositoryImpl(client, dao, converter)
    }

    @Test
    fun `downloadCharacters call with empty dao list correctly calls every methods in order`() {
        // === Setup ===
        val fakeInfo = CharactersInfo(mockk(), listOf(characterMock))
        coEvery { dao.getCharacters() } returns listOf()
        coEvery { client.getCharacters() } returns fakeInfo
        coEvery { dao.insertAllCharacters(any()) } just runs
        every { converter.invoke(any() as Character) } returns characterDbMock

        // === Call ===
        runBlocking { repository.downloadCharacters() }

        // === Assertions ===
        coVerifyOrder {
            dao.getCharacters()
            client.getCharacters()
            converter.invoke(characterMock)
            dao.insertAllCharacters(listOf(characterDbMock))
        }
    }

    @Test
    fun `downloadCharacters call with non empty dao list calls converter and return`() {
        // === Setup ===
        coEvery { dao.getCharacters() } returns listOf(characterDbMock)
        every { converter.invoke(any() as CharacterDb) } returns mockk()

        // === Call ===
        runBlocking { repository.downloadCharacters() }

        // === Assertions ===
        verify { converter.invoke(characterDbMock) }
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