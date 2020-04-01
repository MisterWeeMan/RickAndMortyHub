package com.example.rickandmortyhub.mvvm.view.character

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModel
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity : AppCompatActivity() {

    private lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        viewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

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
