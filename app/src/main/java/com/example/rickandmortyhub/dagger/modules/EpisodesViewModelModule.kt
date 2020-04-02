package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.EpisodesActivityScope
import com.example.rickandmortyhub.mvvm.view.episode.EpisodesActivity
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class EpisodesViewModelModule(
    private val activity: EpisodesActivity
) {

    @Provides
    @EpisodesActivityScope
    fun provideEpisodesViewModel(viewModelFactory: EpisodesViewModelFactory): EpisodesViewModel {
        return ViewModelProvider(activity, viewModelFactory).get(EpisodesViewModel::class.java)
    }
}