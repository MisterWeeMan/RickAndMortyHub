package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.dagger.modules.CharactersViewModelModule
import com.example.rickandmortyhub.dagger.scopes.CharactersFragmentScope
import com.example.rickandmortyhub.views.character.CharactersFragment
import dagger.Component

@CharactersFragmentScope
@Component(modules = [CharactersViewModelModule::class], dependencies = [ApplicationComponent::class])
interface CharactersFragmentComponent {

    fun inject(fragment: CharactersFragment)
}