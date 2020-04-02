package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.LocationsViewModelModule
import com.example.rickandmortyhub.dagger.scopes.LocationsFragmentScope
import com.example.rickandmortyhub.mvvm.view.location.LocationsFragment
import dagger.Component

@LocationsFragmentScope
@Component(modules = [LocationsViewModelModule::class], dependencies = [ApplicationComponent::class])
interface LocationsFragmentComponent {

    fun inject(fragment: LocationsFragment)
}