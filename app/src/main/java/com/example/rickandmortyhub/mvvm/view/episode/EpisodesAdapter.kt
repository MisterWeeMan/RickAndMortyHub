package com.example.rickandmortyhub.mvvm.view.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyhub.R
import com.example.rickandmortyhub.common.network.model.episode.Episode
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodesAdapter(
    private val episodeList: List<Episode>
): RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val currentEpisode = episodeList[position]

        holder.bind(currentEpisode)
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }

    class EpisodeViewHolder(
        private val viewItem: View
    ): RecyclerView.ViewHolder(viewItem) {

        fun bind(currentEpisode: Episode) {
            viewItem.apply {
                tv_episode_name.text = currentEpisode.name
                tv_episode_number.text = currentEpisode.episode
                tv_episode_character_number.text = currentEpisode.characters.size.toString()
            }
        }

    }
}