package com.example.rickandmortyhub.mvvm.view.location

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.common.network.model.location.Location
import kotlinx.android.synthetic.main.item_location.view.*

class LocationsAdapter(
    private val locationList: List<Location>
): RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentLocation = locationList[position]

        holder.bind(currentLocation)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    class LocationViewHolder(
        private val viewItem: View
    ): RecyclerView.ViewHolder(viewItem) {

        fun bind(currentLocation: Location) {
            viewItem.apply {
                tv_location_name.text = currentLocation.name
                tv_location_type.text = currentLocation.type
                tv_location_dimension.text = currentLocation.dimension
                tv_location_habitant_number.text = currentLocation.residents.size.toString()
            }
        }
    }
}