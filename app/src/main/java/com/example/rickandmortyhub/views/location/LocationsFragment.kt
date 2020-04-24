package com.example.rickandmortyhub.views.location

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
import com.example.rickandmortyhub.common.network.model.location.Location
import com.example.rickandmortyhub.common.utils.DataState
import com.example.rickandmortyhub.dagger.components.DaggerLocationsFragmentComponent
import com.example.rickandmortyhub.dagger.modules.LocationsViewModelModule
import com.example.rickandmortyhub.viewmodels.location.LocationsViewModel
import kotlinx.android.synthetic.main.fragment_locations.*
import javax.inject.Inject

class LocationsFragment: Fragment() {

    @Inject
    lateinit var viewModel: LocationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initDagger()
        initObservableData()

        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        viewModel.getLocations()
    }

    private fun initDagger() {
        DaggerLocationsFragmentComponent.builder()
            .applicationComponent((activity?.application as RickMortyApplication).applicationComponent)
            .locationsViewModelModule(LocationsViewModelModule(this))
            .build()
            .inject(this)
    }

    private fun initObservableData() {
        viewModel.apply {
            dataState.observe(this@LocationsFragment, Observer { state ->
                when (state) {
                    is DataState.Loading -> {
                        hideLocationsList()
                        showLoading()
                    }
                    is DataState.Success<*> -> {
                        val locationList = (state.data as List<*>).filterIsInstance<Location>()

                        hideLoading()
                        setupRecyclerView(locationList)
                        showLocationsList()
                    }
                    is DataState.Failure -> {
                        showError(state.message)
                    }
                }

            })
        }
    }

    private fun setupRecyclerView(locationList: List<Location>) {
        rv_locations.apply {
            adapter = LocationsAdapter(locationList)
            layoutManager = LinearLayoutManager(this@LocationsFragment.requireContext())
        }
    }

    private fun showError(error: String?) {
        val message = error ?: getString(R.string.general_error_message)

        AlertDialog.Builder(this.requireContext())
            .setCancelable(false)
            .setTitle(R.string.error)
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

    private fun showLocationsList() {
        rv_locations.visibility = View.VISIBLE
    }

    private fun hideLocationsList() {
        rv_locations.visibility = View.GONE
    }
}