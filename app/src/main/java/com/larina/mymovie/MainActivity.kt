package com.larina.mymovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottomNavigationView: BottomNavigationView

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }
    data class Film(
        val title: String,
        val poster: Int,
        val description: String
    )
    val filmsDataBase = listOf(
        Film("Aladdin", R.drawable.poster_8, "Aladdin, a kind thief, woos Jasmine, the princess of Agrabah, with the help of Genie. When Jafar, the grand vizier, tries to usurp the king, Jasmine, Aladdin and Genie must stop him from succeeding."),
        Film("Why Women Kill", R.drawable.poster_7, "An anthology series that follows three women in different decades all living in the same house, as they deal with infidelity and betrayals in their marriages."),
        Film("Le fabuleux destin d'Amélie Poulain", R.drawable.poster_6, "Despite being caught in her imaginative world, young waitress Amelie decides to help people find happiness. Her quest to spread joy leads her on a journey during which she finds true love."),
        Film("Luck", R.drawable.poster_5, "The curtain is pulled back on the millennia-old battle between the organizations of good luck and bad luck that secretly affects everyday lives."),
        Film("Wicked", R.drawable.poster_4, "Elphaba, a young woman ridiculed for her green skin, and Galinda, a popular girl, become friends at Shiz University in the Land of Oz. After an encounter with the Wonderful Wizard of Oz, their friendship reaches a crossroads."),
        Film("W.I.T.C.H", R.drawable.poster_3, "Five teenage girls learn that they have been chosen to guard the walls between parallel universes. For this purpose, they have been given the powers of the elements."),
        Film("Doornroosje", R.drawable.poster_2, "After being snubbed by the royal family, a malevolent fairy places a curse on a princess which only a prince can break, along with the help of three good fairies."),
        Film("La La Land", R.drawable.poster_1, "When Sebastian, a pianist, and Mia, an actress, follow their passion and achieve success in their respective fields, they find themselves torn between their love for each other and their careers."),
    ) }