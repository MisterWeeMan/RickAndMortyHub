package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.EpisodesViewModelModule
import com.example.rickandmortyhub.dagger.scopes.EpisodesActivityScope
import com.example.rickandmortyhub.mvvm.view.episode.EpisodesActivity
import dagger.Component

@EpisodesActivityScope
@Component(modules = [EpisodesViewModelModule::class], dependencies = [ApplicationComponent::class])
interface EpisodesActivityComponent {

    fun inject(episodesActivity: EpisodesActivity)
}