package com.larina.mymovie

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.larina.mymovie.Film
import com.larina.mymovie.R

class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val poster: ImageView = itemView.findViewById(R.id.poster)

    fun bind(film: Film) {
        title.text = film.title
        description.text = film.description
        poster.setImageResource(film.poster)
    }
}

