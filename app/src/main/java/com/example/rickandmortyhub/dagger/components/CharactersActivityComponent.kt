package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.CharactersViewModelModule
import com.example.rickandmortyhub.dagger.modules.RepositoryModule
import com.example.rickandmortyhub.dagger.scopes.CharactersActivityScope
import com.example.rickandmortyhub.mvvm.view.character.CharactersActivity
import dagger.Component

@CharactersActivityScope
@Component(modules = [CharactersViewModelModule::class], dependencies = [ApplicationComponent::class])
interface CharactersActivityComponent {

    fun inject(charactersActivity: CharactersActivity)
}