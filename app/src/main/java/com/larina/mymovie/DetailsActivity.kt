package com.larina.mymovie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize
import android.os.Parcelable


class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsDescription: TextView
    private lateinit var detailsPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        detailsDescription = findViewById(R.id.details_description)
        detailsPoster = findViewById(R.id.details_poster)

        // Получаем объект Film из Intent
        val film: Film? = intent.getParcelableExtra("film")

        // Проверяем, что объект не null
        film?.let {
            detailsDescription.text = it.description
            detailsPoster.setImageResource(it.poster)
        } ?: run {
            // Обработка случая, если фильм не был передан
            detailsDescription.text = "Описание недоступно"
            detailsPoster.setImageResource(R.drawable.poster_1)
        }
    }
}