package com.example.rickandmortyhub.views.character

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
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.dagger.components.DaggerCharactersFragmentComponent
import com.example.rickandmortyhub.dagger.modules.CharactersViewModelModule
import com.example.rickandmortyhub.viewmodels.character.CharactersViewModel
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

        initData()
    }

    private fun initData() {
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
            dataState.observe(this@CharactersFragment, Observer { state ->
                when (state) {
                    is DataState.Loading -> {
                        hideCharacterList()
                        showLoading()
                    }
                    is DataState.Success<*> -> {
                        val characterList = (state.data as List<*>).filterIsInstance<Character>()

                        hideLoading()
                        setupRecyclerView(characterList)
                        showCharacterList()
                    }
                    is DataState.Failure -> {
                        showError(state.message)
                    }
                }
            })
        }
    }

    private fun setupRecyclerView(characterList: List<Character>) {
        rv_characters.apply {
            adapter = CharactersAdapter(characterList)
            layoutManager = LinearLayoutManager(this@CharactersFragment.requireContext())
        }
    }

    private fun showError(error: String?) {
        val message = error ?: getString(R.string.general_error_message)

        AlertDialog.Builder(this.requireContext())
            .setCancelable(false)
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setNeutralButton(getString(R.string.reload)) { _, _ -> initData() }
            .show()
    }

    private fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
    }

    private fun showCharacterList() {
        rv_characters.visibility = View.VISIBLE
    }

    private fun hideCharacterList() {
        rv_characters.visibility = View.GONE
    }
}
