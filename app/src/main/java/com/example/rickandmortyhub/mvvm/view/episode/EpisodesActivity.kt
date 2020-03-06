package com.example.rickandmortyhub.mvvm.view.episode

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.episode.EpisodesViewModel
import kotlinx.android.synthetic.main.activity_episode.*

class EpisodesActivity : AppCompatActivity() {

    private lateinit var viewModel: EpisodesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)

        viewModel = ViewModelProvider(this).get(EpisodesViewModel::class.java)

        viewModel.apply {
            getEpisodes()

            episodeList.observe(this@EpisodesActivity, Observer { episodeList ->
                rv_episodes.adapter = EpisodesAdapter(episodeList)
                rv_episodes.layoutManager = LinearLayoutManager(this@EpisodesActivity)
            })

            errorMessage.observe(this@EpisodesActivity, Observer { error ->
                showError(error)
            })
        }
    }

    private fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
