package com.example.rickandmortyhub.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyhub.dagger.scopes.CharactersFragmentScope
import com.example.rickandmortyhub.mvvm.view.character.CharactersFragment
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharactersViewModelModule(
    private val fragment: CharactersFragment
) {

    @Provides
    @CharactersFragmentScope
    fun provideCharactersViewModel(viewModelFactory: CharactersViewModelFactory): CharactersViewModel {
        return ViewModelProvider(fragment, viewModelFactory).get(CharactersViewModel::class.java)
    }
}