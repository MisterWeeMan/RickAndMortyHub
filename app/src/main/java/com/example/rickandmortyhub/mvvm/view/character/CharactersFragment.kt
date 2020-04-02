package com.example.rickandmortyhub.mvvm.view.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.RickMortyApplication
import com.example.rickandmortyhub.dagger.components.DaggerCharactersFragmentComponent
import com.example.rickandmortyhub.dagger.modules.CharactersViewModelModule
import com.example.rickandmortyhub.mvvm.viewmodel.character.CharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModel: CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDagger()
        initObservableData()

        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacters()
    }

    private fun initDagger() {
        DaggerCharactersFragmentComponent.builder()
            .applicationComponent((activity?.application as RickMortyApplication).applicationComponent)
            .charactersViewModelModule(CharactersViewModelModule(this))
            .build()
            .inject(this)
    }

    private fun initObservableData() {
        viewModel.apply {
            characterList.observe(this@CharactersFragment, Observer {
                rv_characters_frag.apply {
                    adapter = CharactersAdapter(it)
                    layoutManager = LinearLayoutManager(this@CharactersFragment.requireContext())
                }
            })

            errorMessage.observe(this@CharactersFragment, Observer {
                showError(it)
            })
        }
    }

    private fun showError(error: String) {
        AlertDialog.Builder(this.requireContext())
            .setCancelable(false)
            .setMessage(error)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}
