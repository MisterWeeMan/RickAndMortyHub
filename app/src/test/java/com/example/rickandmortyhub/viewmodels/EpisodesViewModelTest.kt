package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.episode.EpisodesViewModel
import com.example.rickandmortyhub.viewmodels.utils.MainCoroutinesRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    var dataStateObserver = Observer<DataState> {  }

    private lateinit var viewModel: EpisodesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = EpisodesViewModel(repository)

        viewModel.dataState.observeForever(dataStateObserver)
    }

    @Test
    fun `getEpisodes successful call correctly call downloadEpisodes repository method`() {
        // === Setup ===
        coEvery { repository.downloadEpisodes() } returns mockk()

        // === Call ===
        viewModel.getEpisodes()

        // === Assertions ===
        coVerify { repository.downloadEpisodes() }
    }

    @Test
    fun `getEpisodes call correctly change the value of data state to loading`() {
        // === Setup ===
        coEvery { repository.downloadEpisodes() } returns mockk()

        // === Call ===
        viewModel.getEpisodes()

        // === Assertions ===
        verify { dataStateObserver.onChanged(DataState.Loading) }
    }

    @Test
    fun `getEpisodes successful call correctly change the value of data state to success`() {
        // === Setup ===
        val expectedResult = DataState.Success(listOf<Episode>())
        coEvery { repository.downloadEpisodes() } returns listOf()

        // === Call ===
        viewModel.getEpisodes()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }

    @Test
    fun `getEpisodes unsuccessful call correctly change the value of data state to failure`() {
        // === Setup ===
        val error = "error"
        val expectedResult = DataState.Failure(error)
        coEvery { repository.downloadEpisodes() } throws Exception(error)

        // === Call ===
        viewModel.getEpisodes()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }
}