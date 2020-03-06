package com.example.rickandmortyhub.mvvm.model

import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.network.model.character.CharactersInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterModel {

    fun downloadCharacters(): Single<CharactersInfo> {
        return RetrofitFactory
            .rickMortyClient
            .getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
