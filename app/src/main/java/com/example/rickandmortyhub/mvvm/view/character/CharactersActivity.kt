package com.example.rickandmortyhub.mvvm.view.character

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModelFactory
import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepositoryImpl
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity() {

    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val repository = RickMortyRemoteRepositoryImpl(RetrofitFactory.rickMortyClient)
        val viewModelFactory = CharactersViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharactersViewModel::class.java)

        initObservableData()
        viewModel.getCharacters()
    }

    private fun initObservableData() {
        viewModel.apply {
            characterList.observe(this@CharactersActivity, Observer { characterList ->
                rv_characters.apply {
                    adapter = CharactersAdapter(characterList)
                    layoutManager = LinearLayoutManager(this@CharactersActivity)
                }
            })

            errorMessage.observe(this@CharactersActivity, Observer { error ->
                showError(error)
            })
        }
    }

    private fun showError(error: String?) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(error)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}
