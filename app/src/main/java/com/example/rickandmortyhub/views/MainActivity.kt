package com.example.rickandmortyhub.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.views.character.CharactersFragment
import com.example.rickandmortyhub.views.episode.EpisodesFragment
import com.example.rickandmortyhub.views.location.LocationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigate(CharactersFragment())
        initBottomNavigation()
    }

    private fun navigate(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_container, fragment)
            .commit()
    }

    private fun initBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_characters -> navigate(CharactersFragment())
                R.id.nav_locations -> navigate(LocationsFragment())
                R.id.nav_episodes -> navigate(EpisodesFragment())
            }

            true
        }
    }
}
