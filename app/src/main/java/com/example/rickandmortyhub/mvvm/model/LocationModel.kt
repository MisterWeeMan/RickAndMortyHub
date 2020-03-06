package com.example.rickandmortyhub.mvvm.model

import com.example.rickandmortyhub.network.RetrofitFactory
import com.example.rickandmortyhub.network.model.location.LocationsInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocationModel {

    fun downloadLocation(): Single<LocationsInfo> {
        return RetrofitFactory
            .rickMortyClient
            .getLocations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}