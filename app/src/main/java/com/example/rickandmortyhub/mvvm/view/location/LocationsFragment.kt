package com.example.rickandmortyhub.mvvm.view.location

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
import com.example.rickandmortyhub.dagger.components.DaggerLocationsFragmentComponent
import com.example.rickandmortyhub.dagger.modules.LocationsViewModelModule
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
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
            locationList.observe(this@LocationsFragment, Observer {
                rv_locations_frag.apply {
                    adapter = LocationsAdapter(it)
                    layoutManager = LinearLayoutManager(this@LocationsFragment.requireContext())
                }
            })

            errorMessage.observe(this@LocationsFragment, Observer {
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