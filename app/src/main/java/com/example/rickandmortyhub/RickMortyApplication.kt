package com.example.rickandmortyhub

import android.app.Application
import com.example.rickandmortyhub.dagger.components.ApplicationComponent
import com.example.rickandmortyhub.dagger.components.DaggerApplicationComponent
import com.example.rickandmortyhub.dagger.modules.DatabaseModule

class RickMortyApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }
}