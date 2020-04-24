package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.character.CharactersViewModel
import com.example.rickandmortyhub.utils.MainCoroutinesRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @MockK
    private lateinit var repository: RemoteRepository

    @SpyK
    var dataStateObserver = Observer<DataState> {  }

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = CharactersViewModel(repository)

        viewModel.dataState.observeForever(dataStateObserver)
    }

    @Test
    fun `getCharacters successful call correctly call downloadCharacters repository method`() {
        // === Setup ===
        coEvery { repository.downloadCharacters() } returns mockk()

        // === Call ===
        viewModel.getCharacters()

        // === Assertions ===
        coVerify { repository.downloadCharacters() }
    }

    @Test
    fun `getCharacters call correctly change the value of data state to loading`() {
        // === Setup ===
        coEvery { repository.downloadCharacters() } returns mockk()

        // === Call ===
        viewModel.getCharacters()

        // === Assertions ===
        verify { dataStateObserver.onChanged(DataState.Loading) }
    }

    @Test
    fun `getCharacters successful call correctly change the value of data state to success`() {
        // === Setup ===
        val expectedResult = DataState.Success(listOf<Character>())
        coEvery { repository.downloadCharacters() } returns listOf()

        // === Call ===
        viewModel.getCharacters()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }

    @Test
    fun `getCharacters unsuccessful call correctly change the value of data state to failure`() {
        // === Setup ===
        val error = "error"
        val expectedResult = DataState.Failure(error)
        coEvery { repository.downloadCharacters() } throws Exception(error)

        // === Call ===
        viewModel.getCharacters()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }
}