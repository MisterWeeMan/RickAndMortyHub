package com.example.rickandmortyhub.mvvm.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rickandmortyhub.network.RickMortyClient
import com.example.rickandmortyhub.network.model.character.Character
import io.reactivex.disposables.CompositeDisposable

class CharacterDataSourceFactory(
    private val rickMortyClient: RickMortyClient,
    private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Character>() {

    val characterDataSourceLiveData = MutableLiveData<CharacterDataSource>()

    override fun create(): DataSource<Int, Character> {
        val characterDataSource = CharacterDataSource(rickMortyClient, compositeDisposable)
        characterDataSourceLiveData.postValue(characterDataSource)
        return characterDataSource
    }
}