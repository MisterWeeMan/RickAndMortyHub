package com.example.rickandmortyhub.mvvm.view.episode

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.RickMortyApplication
import com.example.rickandmortyhub.dagger.components.DaggerEpisodesActivityComponent
import com.example.rickandmortyhub.dagger.modules.EpisodesViewModelModule
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModel
import kotlinx.android.synthetic.main.activity_episode.*
import javax.inject.Inject

class EpisodesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: EpisodesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        initDagger()
        initObservableData()
        viewModel.getEpisodes()
    }

    private fun initDagger() {
        DaggerEpisodesActivityComponent.builder()
            .applicationComponent((application as RickMortyApplication).applicationComponent)
            .episodesViewModelModule(EpisodesViewModelModule(this))
            .build()
            .inject(this)
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
