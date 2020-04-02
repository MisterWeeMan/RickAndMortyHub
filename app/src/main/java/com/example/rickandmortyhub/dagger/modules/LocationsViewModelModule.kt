package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.LocationsActivityScope
import com.example.rickandmortyhub.mvvm.view.location.LocationsActivity
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LocationsViewModelModule(
    private val activity: LocationsActivity
) {

    @Provides
    @LocationsActivityScope
    fun provideLocationsViewModel(viewModelFactory: LocationsViewModelFactory): LocationsViewModel {
        return ViewModelProvider(activity, viewModelFactory).get(LocationsViewModel::class.java)
    }
}