package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.EpisodesFragmentScope
import com.example.rickandmortyhub.views.episode.EpisodesFragment
import com.example.rickandmortyhub.viewmodels.episode.EpisodesViewModel
import com.example.rickandmortyhub.viewmodels.episode.EpisodesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class EpisodesViewModelModule(
    private val fragment: EpisodesFragment
) {

    @Provides
    @EpisodesFragmentScope
    fun provideEpisodesViewModel(viewModelFactory: EpisodesViewModelFactory): EpisodesViewModel {
        return ViewModelProvider(fragment, viewModelFactory).get(EpisodesViewModel::class.java)
    }
}