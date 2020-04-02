package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.CharactersActivityScope
import com.example.rickandmortyhub.mvvm.view.character.CharactersActivity
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModelFactory
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository
import dagger.Module
import dagger.Provides

@Module
class CharactersViewModelModule(
    private val activity: CharactersActivity
) {

    @Provides
    @CharactersActivityScope
    fun provideCharactersViewModel(viewModelFactory: CharactersViewModelFactory): CharactersViewModel {
        return ViewModelProvider(activity, viewModelFactory).get(CharactersViewModel::class.java)
    }
}