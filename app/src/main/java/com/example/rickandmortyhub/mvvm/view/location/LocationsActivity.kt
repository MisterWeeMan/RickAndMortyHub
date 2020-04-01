package com.example.rickandmortyhub.mvvm.view.location

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModelFactory
import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.repositories.RickMortyRemoteRepositoryImpl
import kotlinx.android.synthetic.main.activity_locations.*

class LocationsActivity : AppCompatActivity() {

    private lateinit var viewModel: LocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        val repository = RickMortyRemoteRepositoryImpl(RetrofitFactory.rickMortyClient)
        val viewModelFactory = LocationsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LocationsViewModel::class.java)

        initObservableData()
        viewModel.getLocations()
    }

    private fun initObservableData() {
        viewModel.apply {
            locationList.observe(this@LocationsActivity, Observer { locationList ->
                rv_locations.apply {
                    adapter = LocationsAdapter(locationList)
                    layoutManager = LinearLayoutManager(this@LocationsActivity)
                }
            })

            errorMessage.observe(this@LocationsActivity, Observer { error ->
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
