package com.example.rickandmortyhub.mvvm.viewmodel.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyhub.network.model.character.Character
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepository
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: RickMortyRemoteRepository
): ViewModel() {

    val characterList: LiveData<List<Character>>
        get() = mCharacterList
    private val mCharacterList = MutableLiveData<List<Character>>()

    val errorMessage: LiveData<String>
        get() = mErrorMessage
    private var mErrorMessage = MutableLiveData<String>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                mCharacterList.value = repository.downloadCharacters()
            } catch (exc: Exception) {
                mErrorMessage.value = exc.message
            }
        }
    }
}