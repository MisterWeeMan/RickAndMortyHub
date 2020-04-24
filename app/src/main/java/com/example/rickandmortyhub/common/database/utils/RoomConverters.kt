package com.example.rickandmortyhub.common.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

object RoomConverters {

    val gson by lazy { Gson() }

    @TypeConverter
    @JvmStatic // so Room can use them as regular static functions
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic // so Room can use them as regular static functions
    fun fromJsonString(value: String): List<String> {
        return gson.fromJson(value)
    }
}