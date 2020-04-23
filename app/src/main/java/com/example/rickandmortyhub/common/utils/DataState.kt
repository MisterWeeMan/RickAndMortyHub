package com.example.rickandmortyhub.common.utils

sealed class DataState {
    object Loading: DataState()
    data class Success<T>(val data: T): DataState()
    data class Failure(val message: String?): DataState()
}