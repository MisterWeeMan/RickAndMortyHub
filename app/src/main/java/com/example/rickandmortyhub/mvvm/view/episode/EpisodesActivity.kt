package com.example.rickandmortyhub.mvvm.view.episode

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModelFactory
import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepositoryImpl
import kotlinx.android.synthetic.main.activity_episode.*

class EpisodesActivity : AppCompatActivity() {

    private lateinit var viewModel: EpisodesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        val repository = RickMortyRemoteRepositoryImpl(RetrofitFactory.rickMortyClient)
        val viewModelFactory = EpisodesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EpisodesViewModel::class.java)

        initObservableData()
        viewModel.getEpisodes()
    }

    private fun initObservableData() {
        viewModel.apply {
            episodeList.observe(this@EpisodesActivity, Observer { episodeList ->
                rv_episodes.apply {
                    adapter = EpisodesAdapter(episodeList)
                    layoutManager = LinearLayoutManager(this@EpisodesActivity)
                }
            })

            errorMessage.observe(this@EpisodesActivity, Observer { error ->
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
