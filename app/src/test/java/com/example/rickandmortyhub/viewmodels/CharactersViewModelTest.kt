package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.viewmodels.utils.MainCoroutinesRule
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.viewmodels.character.CharactersViewModel
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.utils.characterMock
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
class CharactersViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @MockK
    private lateinit var repository: RemoteRepository

    @SpyK
    var characterListObserver = Observer<List<Character>> {  }

    @SpyK
    var errorMessageObserver = Observer<String> {  }

    private lateinit var viewModel: CharactersViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = CharactersViewModel(repository)

        viewModel.apply {
            characterList.observeForever(characterListObserver)
            errorMessage.observeForever(errorMessageObserver)
        }
    }

    @Test
    fun `getCharacters successful call correctly change the value of character list`() {
        // Given
        val characterList = (1..10).map { characterMock.copy(id = it) }
        coEvery { repository.downloadCharacters() } returns characterList

        // When
        runBlocking { viewModel.getCharacters() }

        // Then
        assertEquals(characterList, viewModel.characterList.value)
        verify { characterListObserver.onChanged(characterList) }
    }

    @Test
    fun `getCharacters unsuccessful call correctly change the value of error message`() {
        // Given
        val error = "error"
        coEvery { repository.downloadCharacters() } throws RuntimeException(error)

        // When
        runBlocking { viewModel.getCharacters() }

        // Then
        assertEquals(error, viewModel.errorMessage.value)
        verify { errorMessageObserver.onChanged(error) }
    }

    @Test
    fun `getCharacters successful call correctly call downloadCharacters repository method`() {
        // Given
        coEvery { repository.downloadCharacters() } returns listOf()

        // When
        runBlocking { viewModel.getCharacters() }

        // Then
        coVerify { repository.downloadCharacters() }
    }
}