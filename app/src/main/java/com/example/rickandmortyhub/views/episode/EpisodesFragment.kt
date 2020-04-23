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
            episodeList.observe(this@EpisodesFragment, Observer {
                rv_episodes_frag.apply {
                    adapter = EpisodesAdapter(it)
                    layoutManager = LinearLayoutManager(this@EpisodesFragment.requireContext())
                }
            })

            errorMessage.observe(this@EpisodesFragment, Observer {
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