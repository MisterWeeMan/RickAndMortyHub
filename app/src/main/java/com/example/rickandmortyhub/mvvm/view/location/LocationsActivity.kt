package com.example.rickandmortyhub.mvvm.view.location

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.mvvm.viewmodel.location.LocationsViewModel
import kotlinx.android.synthetic.main.activity_locations.*

class LocationsActivity : AppCompatActivity() {

    private lateinit var viewModel: LocationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        viewModel = ViewModelProvider(this).get(LocationsViewModel::class.java)

        viewModel.apply {
            getLocations()

            locationList.observe(this@LocationsActivity, Observer { locationList ->
                rv_locations.adapter = LocationsAdapter(locationList)
                rv_locations.layoutManager = LinearLayoutManager(this@LocationsActivity)
            })

            errorMessage.observe(this@LocationsActivity, Observer { error ->
                showError(error)
            })
        }
    }

    private fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}
