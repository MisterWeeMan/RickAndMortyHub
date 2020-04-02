package com.example.rickandmortyhub

import android.app.Application
import com.example.rickandmortyhub.dagger.components.ApplicationComponent
import com.example.rickandmortyhub.dagger.components.DaggerApplicationComponent

class RickMortyApplication: Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}