package com.example.rickandmortyhub.mvvm.viewmodel.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyhub.mvvm.model.LocationModel
import com.example.rickandmortyhub.network.model.location.Location
import io.reactivex.disposables.CompositeDisposable

class LocationsViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val locationModel = LocationModel()

    private val mutableLocationList = MutableLiveData<List<Location>>()
    val locationList: LiveData<List<Location>>
        get() = mutableLocationList

    private val mutableErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = mutableErrorMessage

    fun getLocations() {
        compositeDisposable.add(
            locationModel
                .downloadLocation()
                .map { info -> info.locations }
                .subscribe(
                    { item ->
                        mutableLocationList.value = item
                    },
                    { error ->
                        mutableErrorMessage.value = error.message
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}