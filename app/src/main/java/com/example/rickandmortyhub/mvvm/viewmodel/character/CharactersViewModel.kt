package com.example.rickandmortyhub.mvvm.viewmodel.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyhub.mvvm.model.CharacterModel
import com.example.rickandmortyhub.network.model.character.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val characterModel = CharacterModel()

    val characterList: LiveData<List<Character>>
        get() = mCharacterList
    private val mCharacterList = MutableLiveData<List<Character>>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage
    private var mErrorMessage = MutableLiveData<String>()

    fun getCharacters() {
        compositeDisposable.add(
            characterModel
                .downloadCharacters()
                .map { it.characters }
                .subscribe(
                    { list -> mCharacterList.value = list },
                    { error -> mErrorMessage.value = error.message }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}