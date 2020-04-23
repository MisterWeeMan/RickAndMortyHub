package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.dagger.modules.NetworkModule
import com.example.rickandmortyhub.dagger.modules.RepositoryModule
import com.example.rickandmortyhub.repositories.RemoteRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, RepositoryModule::class])
@Singleton
interface ApplicationComponent {

    fun getRickMortyClient(): RickMortyClient

    fun getRickMortyRemoteRepository(): RemoteRepository
}