package com.example.rickandmortyhub.views.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.RickMortyApplication
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.dagger.components.DaggerEpisodesFragmentComponent
import com.example.rickandmortyhub.dagger.modules.EpisodesViewModelModule
import com.example.rickandmortyhub.viewmodels.episode.EpisodesViewModel
import kotlinx.android.synthetic.main.fragment_episodes.*
import javax.inject.Inject

class EpisodesFragment: Fragment() {

    @Inject
    lateinit var viewModel: EpisodesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDagger()
        initObservableData()

        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        viewModel.getEpisodes()
    }

    private fun initDagger() {
        DaggerEpisodesFragmentComponent.builder()
            .applicationComponent((activity?.application as RickMortyApplication).applicationComponent)
            .episodesViewModelModule(EpisodesViewModelModule(this))
            .build()
            .inject(this)
    }

    private fun initObservableData() {
        viewModel.apply {
            dataState.observe(this@EpisodesFragment, Observer { state ->
                when (state) {
                    is DataState.Loading -> {
                        hideEpisodeList()
                        showLoading()
                    }
                    is DataState.Success<*> -> {
                        val episodesList = (state.data as List<*>).filterIsInstance<Episode>()

                        hideLoading()
                        setupRecyclerView(episodesList)
                        showEpisodeList()
                    }
                    is DataState.Failure -> {
                        showError(state.message)
                    }
                }
            })
        }
    }

    private fun setupRecyclerView(episodesList: List<Episode>) {
        rv_episodes.apply {
            adapter = EpisodesAdapter(episodesList)
            layoutManager = LinearLayoutManager(this@EpisodesFragment.requireContext())
        }
    }

    private fun showError(error: String?) {
        val message = error ?: getString(R.string.general_error_message)

        AlertDialog.Builder(this.requireContext())
            .setCancelable(false)
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(getString(R.string.reload)) { _, _ -> initData() }
            .show()
    }

    private fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_loading.visibility = View.GONE
    }

    private fun showEpisodeList() {
        rv_episodes.visibility = View.VISIBLE
    }

    private fun hideEpisodeList() {
        rv_episodes.visibility = View.GONE
    }
}