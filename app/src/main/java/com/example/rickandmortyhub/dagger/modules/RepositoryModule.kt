package com.example.rickandmortyhub.dagger.modules

import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(remoteRepository: RickMortyRemoteRepositoryImpl): RickMortyRemoteRepository
}