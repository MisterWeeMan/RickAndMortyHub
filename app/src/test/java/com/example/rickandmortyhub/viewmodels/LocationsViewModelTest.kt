package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.common.network.model.location.Location
import com.example.rickandmortyhub.viewmodels.location.LocationsViewModel
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.utils.MainCoroutinesRule
import com.example.rickandmortyhub.viewmodels.utils.locationMock
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
class LocationsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @MockK
    private lateinit var repository: RemoteRepository

    @SpyK
    var locationListObserver = Observer<List<Location>> {  }

    @SpyK
    var errorMessageObserver = Observer<String> {  }

    private lateinit var viewModel: LocationsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LocationsViewModel(repository)

        viewModel.apply {
            locationList.observeForever(locationListObserver)
            errorMessage.observeForever(errorMessageObserver)
        }
    }

    @Test
    fun `getLocations successful call correctly change the value of location list`() {
        // Given
        val locationList = (1..10).map { locationMock.copy(id = it) }
        coEvery { repository.downloadLocations() } returns locationList

        // When
        runBlocking { viewModel.getLocations() }

        // Then
        assertEquals(locationList, viewModel.locationList.value)
        verify { locationListObserver.onChanged(locationList) }
    }

    @Test
    fun `getLocations unsuccessful call correctly change the value of error message`() {
        // Given
        val error = "error"
        coEvery { repository.downloadLocations() } throws RuntimeException(error)

        // When
        runBlocking { viewModel.getLocations() }

        // Then
        assertEquals(error, viewModel.errorMessage.value)
        verify { errorMessageObserver.onChanged(error) }
    }

    @Test
    fun `getLocations successful call correctly call downloadLocations repository method`() {
        // Given
        coEvery { repository.downloadLocations() } returns listOf()

        // When
        runBlocking { viewModel.getLocations() }

        // Then
        coVerify { repository.downloadLocations() }
    }
}