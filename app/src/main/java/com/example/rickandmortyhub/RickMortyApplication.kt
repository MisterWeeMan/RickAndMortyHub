package com.example.rickandmortyhub

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.rickandmortyhub.common.EXPIRING_DATA_PREFERENCES
import com.example.rickandmortyhub.common.EXPIRING_DOWNLOAD_MILLIS
import com.example.rickandmortyhub.common.LAST_DOWNLOAD_KEY
import com.example.rickandmortyhub.common.database.RickMortyDatabase
import com.example.rickandmortyhub.dagger.components.ApplicationComponent
import com.example.rickandmortyhub.dagger.components.DaggerApplicationComponent
import com.example.rickandmortyhub.dagger.modules.DatabaseModule
import kotlinx.coroutines.*
import javax.inject.Inject

class RickMortyApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }

    private val expiringDataPreference: SharedPreferences by lazy {
        getSharedPreferences(EXPIRING_DATA_PREFERENCES, Context.MODE_PRIVATE)
    }

    private val ioScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var database: RickMortyDatabase

    override fun onCreate() {
        super.onCreate()

        applicationComponent.inject(this)
        checkExpiringData()
    }

    private fun checkExpiringData() {
        val now = System.currentTimeMillis()
        val lastDownload = expiringDataPreference.getLong(LAST_DOWNLOAD_KEY, 0L)
        val millisDifference = now - lastDownload

        if (lastDownload == 0L) {
            saveLastDownload()
        } else {
            if (millisDifference > EXPIRING_DOWNLOAD_MILLIS) {
                clearTables() // clear tables so it is going to use network again
                saveLastDownload()
            }
        }
    }

    private fun saveLastDownload() {
        val now = System.currentTimeMillis()

        expiringDataPreference.edit {
            putLong(LAST_DOWNLOAD_KEY, now)
        }
    }

    private fun clearTables() {
        ioScope.launch {
            database.clearAllTables()
        }
    }

    override fun onTerminate() {
        if (ioScope.isActive) ioScope.cancel()

        super.onTerminate()
    }
}