package com.example.rickandmortyhub.mvvm.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.rickandmortyhub.network.RickMortyClient
import com.example.rickandmortyhub.network.model.character.Character
import io.reactivex.disposables.CompositeDisposable

class CharacterDataSource(
    private val rickMortyClient: RickMortyClient,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int, Character>() {

    companion object {
        private const val TAG = "CharacterDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        compositeDisposable.add(
            rickMortyClient
                .getCharacters()
                .subscribe(
                    { response ->
                        callback.onResult(response.characters, null, 2)
                    },
                    { error ->
                        val errorMessage = error.message
                        if (errorMessage != null) {
                            Log.e(TAG, errorMessage)
                        } else {
                            Log.e(TAG, "Something gone wrong (loadInitial)")
                        }
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        compositeDisposable.add(
            rickMortyClient
                .getCharacters(params.key)
                .subscribe(
                    { response ->
                        callback.onResult(response.characters, params.key + 1)
                    },
                    { error ->
                        val errorMessage = error.message
                        if (errorMessage != null) {
                            Log.e(TAG, errorMessage)
                        } else {
                            Log.e(TAG, "Something gone wrong (loadAfter)")
                        }
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}