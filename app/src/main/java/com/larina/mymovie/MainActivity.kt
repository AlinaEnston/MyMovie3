package com.larina.mymovie

import FilmListRecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var recyclerView: RecyclerView // Объявляем RecyclerView
    private lateinit var filmsAdapter: FilmListRecyclerAdapter // Объявляем адаптер

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Инициализация MaterialToolbar
        topAppBar = findViewById(R.id.topAppBar)

        // Устанавливаем слушатель нажатия на иконку навигации
        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Когда-нибудь здесь будет навигация...", Toast.LENGTH_SHORT).show()
        }

        // Устанавливаем слушатель для обработки нажатий на элементы меню
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Поиск", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.more -> {
                    Toast.makeText(this, "Еще", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Инициализация BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Устанавливаем слушатель для обработки нажатий на элементы BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Данные для RecyclerView
        val filmsDataBase = listOf(
            Film("Aladdin", R.drawable.poster_8, "Aladdin, a kind thief..."),
            Film("Why Women Kill", R.drawable.poster_7, "An anthology series..."),
            Film("Le fabuleux destin d'Amélie Poulain", R.drawable.poster_6, "Despite being caught..."),
            Film("Luck", R.drawable.poster_5, "The curtain is pulled back..."),
            Film("Wicked", R.drawable.poster_4, "Elphaba, a young woman..."),
            Film("W.I.T.C.H", R.drawable.poster_3, "Five teenage girls learn..."),
            Film("Doornroosje", R.drawable.poster_2, "After being snubbed..."),
            Film("La La Land", R.drawable.poster_1, "When Sebastian, a pianist...")
        )

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.main_recycler) // Инициализация RecyclerView

        // Применение адаптера и других настроек к RecyclerView
        recyclerView.apply {
            // Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    // Запускаем DetailsActivity при нажатии на фильм
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    // Передаем данные о фильме, если нужно
                    intent.putExtra("film_title", film.title)
                    intent.putExtra("film_description", film.description)
                    intent.putExtra("film_poster", film.posterResId)
                    startActivity(intent)
                }
            })
            // Присваиваем адаптер
            adapter = filmsAdapter
            // Присваиваем LayoutManager
            layoutManager = LinearLayoutManager(this@MainActivity)
            // Применяем декоратор для отступов
            addItemDecoration(TopSpacingItemDecoration(8))
        }

        // Заполнение адаптера данными
        filmsAdapter.addItems(filmsDataBase)
    }
}