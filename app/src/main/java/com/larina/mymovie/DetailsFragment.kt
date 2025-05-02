package com.larina.mymovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {
    private lateinit var detailsDescription: TextView
    private lateinit var detailsPoster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsDescription = view.findViewById(R.id.details_description)
        detailsPoster = view.findViewById(R.id.details_poster)

        // Получаем объект Film из аргументов
        val film = arguments?.getParcelable<Film>("film")

        // Проверяем, что объект не null
        film?.let {
            detailsDescription.text = it.description
            // Исправлено имя поля (исходя из вашего кода: поле может называться poster)
            detailsPoster.setImageResource(it.poster)
        } ?: run {
            // Обработка случая, если фильм не был передан
            detailsDescription.text = "Описание недоступно"
            detailsPoster.setImageResource(R.drawable.poster_1) // Изображение по умолчанию
        }
    }
}
