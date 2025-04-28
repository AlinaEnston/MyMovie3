package com.larina.mymovie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsDescription: TextView
    private lateinit var detailsPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailsDescription = findViewById(R.id.details_description)
        detailsPoster = findViewById(R.id.details_poster)

        val filmTitle = intent.getStringExtra("film_title")
        val filmDescription = intent.getStringExtra("film_description")
        val filmPosterResId = intent.getIntExtra("film_poster", 0)

        detailsDescription.text = filmDescription ?: "Описание недоступно"
        detailsPoster.setImageResource(if (filmPosterResId != 0) filmPosterResId else R.drawable.poster_1)
    }
}
