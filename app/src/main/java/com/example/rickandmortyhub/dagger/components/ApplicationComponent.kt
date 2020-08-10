package com.example.rickandmortyhub.dagger.components

import com.example.rickandmortyhub.RickMortyApplication
import com.example.rickandmortyhub.common.database.RickMortyDao
import com.example.rickandmortyhub.common.network.RickMortyClient
import com.example.rickandmortyhub.dagger.modules.DatabaseModule
import com.example.rickandmortyhub.dagger.modules.NetworkModule
import com.example.rickandmortyhub.dagger.modules.RepositoryModule
import com.example.rickandmortyhub.repositories.RemoteRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
@Singleton
interface ApplicationComponent {

    fun getRickMortyClient(): RickMortyClient

    fun getRickMortyDao(): RickMortyDao

    fun getRickMortyRemoteRepository(): RemoteRepository

    fun inject(application: RickMortyApplication)
}