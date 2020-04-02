package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.EpisodesViewModelModule
import com.example.rickandmortyhub.dagger.scopes.EpisodesFragmentScope
import com.example.rickandmortyhub.mvvm.view.episode.EpisodesFragment
import dagger.Component

@EpisodesFragmentScope
@Component(modules = [EpisodesViewModelModule::class], dependencies = [ApplicationComponent::class])
interface EpisodesFragmentComponent {

    fun inject(fragment: EpisodesFragment)
}