package com.example.rickandmortyhub.mvvm.view.location

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.RickMortyApplication
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
import com.example.rickandmortyhub.dagger.components.DaggerLocationsActivityComponent
import com.example.rickandmortyhub.dagger.modules.LocationsViewModelModule
import kotlinx.android.synthetic.main.activity_locations.*
import javax.inject.Inject

class LocationsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        initDagger()
        initObservableData()
        viewModel.getLocations()
    }

    private fun initDagger() {
        DaggerLocationsActivityComponent.builder()
            .applicationComponent((application as RickMortyApplication).applicationComponent)
            .locationsViewModelModule(LocationsViewModelModule(this))
            .build()
            .inject(this)
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
