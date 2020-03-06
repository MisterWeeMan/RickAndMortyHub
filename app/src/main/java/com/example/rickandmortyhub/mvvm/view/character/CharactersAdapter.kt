package com.example.rickandmortyhub.mvvm.view.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.network.model.character.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter: PagedListAdapter<Character, CharactersAdapter.CharacterViewHolder>(charactersDiffCallback) {

    companion object {
        private val charactersDiffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = getItem(position)

        holder.bind(currentCharacter)
    }

    class CharacterViewHolder(
        private val viewItem: View
    ): RecyclerView.ViewHolder(viewItem) {

        fun bind(currentCharacter: Character?) {
            viewItem.apply {
                tv_character_name.text = currentCharacter?.name
                tv_character_status.text = currentCharacter?.status
                tv_character_species.text = currentCharacter?.species
                tv_character_origin.text = currentCharacter?.origin?.name
                tv_character_location.text = currentCharacter?.location?.name

                Picasso
                    .get()
                    .load(currentCharacter?.image)
                    .into(img_character_avatar)
            }
        }

    }
}