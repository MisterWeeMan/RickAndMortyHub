package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.LocationsViewModelModule
import com.example.rickandmortyhub.dagger.scopes.LocationsActivityScope
import com.example.rickandmortyhub.mvvm.view.location.LocationsActivity
import dagger.Component

@LocationsActivityScope
@Component(modules = [LocationsViewModelModule::class], dependencies = [ApplicationComponent::class])
interface LocationsActivityComponent {

    fun inject(locationsActivity: LocationsActivity)
}