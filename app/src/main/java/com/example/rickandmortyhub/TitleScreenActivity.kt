package com.example.rickandmortyhub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmortyhub.mvvm.view.character.CharactersActivity
import com.example.rickandmortyhub.mvvm.view.episode.EpisodesActivity
import com.example.rickandmortyhub.mvvm.view.location.LocationsActivity
import kotlinx.android.synthetic.main.activity_title_screen.*

class TitleScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        setButtonsNavigation()
    }

    private fun setButtonsNavigation() {
        btn_show_characters.setOnClickListener {
            startActivity(
                Intent(this, CharactersActivity::class.java)
            )
        }

        btn_show_locations.setOnClickListener {
            startActivity(
                Intent(this, LocationsActivity::class.java)
            )
        }

        btn_show_episodes.setOnClickListener {
            startActivity(
                Intent(this, EpisodesActivity::class.java)
            )
        }
    }
}
