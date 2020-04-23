package com.example.rickandmortyhub.dagger.modules

import com.example.rickandmortyhub.repositories.RemoteRepository
import com.example.rickandmortyhub.repositories.RemoteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(remoteRepository: RemoteRepositoryImpl): RemoteRepository
}