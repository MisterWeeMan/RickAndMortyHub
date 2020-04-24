package com.example.rickandmortyhub.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rickandmortyhub.common.network.model.location.Location
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.viewmodels.location.LocationsViewModel
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
class LocationsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @MockK
    private lateinit var repository: RemoteRepository

    @SpyK
    var dataStateObserver = Observer<DataState> {  }

    private lateinit var viewModel: LocationsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LocationsViewModel(repository)

        viewModel.dataState.observeForever(dataStateObserver)
    }

    @Test
    fun `getLocations successful call correctly call downloadLocations repository method`() {
        // === Setup ===
        coEvery { repository.downloadLocations() } returns mockk()

        // === Call ===
        viewModel.getLocations()

        // === Assertions ===
        coVerify { repository.downloadLocations() }
    }

    @Test
    fun `getLocations call correctly change the value of data state to loading`() {
        // === Setup ===
        coEvery { repository.downloadLocations() } returns mockk()

        // === Call ===
        viewModel.getLocations()

        // === Assertions ===
        verify { dataStateObserver.onChanged(DataState.Loading) }
    }

    @Test
    fun `getLocations successful call correctly change the value of data state to success`() {
        // === Setup ===
        val expectedResult = DataState.Success(listOf<Location>())
        coEvery { repository.downloadLocations() } returns listOf()

        // === Call ===
        viewModel.getLocations()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }

    @Test
    fun `getLocations unsuccessful call correctly change the value of data state to failure`() {
        // === Setup ===
        val error = "error"
        val expectedResult = DataState.Failure(error)
        coEvery { repository.downloadLocations() } throws Exception(error)

        // === Call ===
        viewModel.getLocations()

        // === Assertions ===
        verify { dataStateObserver.onChanged(expectedResult) }
        assertEquals(expectedResult, viewModel.dataState.value)
    }
}