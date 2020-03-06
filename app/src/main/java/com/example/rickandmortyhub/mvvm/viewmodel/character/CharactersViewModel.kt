package com.example.rickandmortyhub.mvvm.viewmodel.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickandmortyhub.mvvm.model.CharacterModel
import com.example.rickandmortyhub.mvvm.paging.CharacterDataSourceFactory
import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.network.model.character.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val characterModel = CharacterModel()

    val characterList: LiveData<PagedList<Character>>

    val errorMessage: LiveData<String>
        get() = mutableErrorMessage
    private var mutableErrorMessage = MutableLiveData<String>()

    private val characterDataSourceFactory: CharacterDataSourceFactory

    init {
        characterDataSourceFactory = CharacterDataSourceFactory(RetrofitFactory.rickMortyClient, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(25)
            .setEnablePlaceholders(false)
            .setMaxSize(493)
            .build()
        characterList = LivePagedListBuilder<Int, Character>(characterDataSourceFactory, config).build()
    }

//    fun getCharacters() {
//        compositeDisposable.add(
//            characterModel
//                .downloadCharacters()
//                .map { info -> info.characters }
//                .subscribe(
//                    { item ->
//                        mutableCharacterList.value = item
//                    },
//                    { error ->
//                        mutableErrorMessage.value = error.message
//                    }
//                )
//        )
//    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}