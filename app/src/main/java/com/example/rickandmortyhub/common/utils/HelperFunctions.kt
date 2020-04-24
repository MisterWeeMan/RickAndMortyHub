package com.example.rickandmortyhub.common.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, object: TypeToken<T>() {}.type)

fun <T> Collection<T>?.isNotNullAndNotEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}