package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.LocationsFragmentScope
import com.example.rickandmortyhub.mvvm.view.location.LocationsFragment
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LocationsViewModelModule(
    private val fragment: LocationsFragment
) {

    @Provides
    @LocationsFragmentScope
    fun provideLocationsViewModel(viewModelFactory: LocationsViewModelFactory): LocationsViewModel {
        return ViewModelProvider(fragment, viewModelFactory).get(LocationsViewModel::class.java)
    }
}