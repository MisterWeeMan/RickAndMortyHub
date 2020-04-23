package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.viewmodels.episode.EpisodesViewModel
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.utils.MainCoroutinesRule
import com.example.rickandmortyhub.viewmodels.utils.episodeMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EpisodesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @MockK
    private lateinit var repository: RemoteRepository

    @SpyK
    var episodeListObserver = Observer<List<Episode>> {  }

    @SpyK
    var errorMessageObserver = Observer<String> {  }

    private lateinit var viewModel: EpisodesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = EpisodesViewModel(repository)

        viewModel.apply {
            episodeList.observeForever(episodeListObserver)
            errorMessage.observeForever(errorMessageObserver)
        }
    }

    @Test
    fun `getEpisodes successful call correctly change the value of episode list`() {
        // Given
        val episodeList = (1..10).map { episodeMock.copy(id = it) }
        coEvery { repository.downloadEpisodes() } returns episodeList

        // When
        runBlocking { viewModel.getEpisodes() }

        // Then
        assertEquals(episodeList, viewModel.episodeList.value)
        verify { episodeListObserver.onChanged(episodeList) }
    }

    @Test
    fun `getEpisodes unsuccessful call correctly change the value of error message`() {
        // Given
        val error = "error"
        coEvery { repository.downloadEpisodes() } throws RuntimeException(error)

        // When
        runBlocking { viewModel.getEpisodes() }

        // Then
        assertEquals(error, viewModel.errorMessage.value)
        verify { errorMessageObserver.onChanged(error) }
    }

    @Test
    fun `getEpisodes successful call correctly call downloadEpisodes repository method`() {
        // Given
        coEvery { repository.downloadEpisodes() } returns listOf()

        // When
        runBlocking { viewModel.getEpisodes() }

        // Then
        coVerify { repository.downloadEpisodes() }
    }
}