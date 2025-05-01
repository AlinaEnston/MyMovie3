package com.larina.mymovie

import FilmListRecyclerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
            Film("Aladdin", R.drawable.poster_8, "Aladdin, a kind thief, woos Jasmine, the princess of Agrabah, with the help of Genie. When Jafar, the grand vizier, tries to usurp the king, Jasmine, Aladdin and Genie must stop him from succeeding."),
            Film("Why Women Kill", R.drawable.poster_7, "An anthology series that follows three women in different decades all living in the same house, as they deal with infidelity and betrayals in their marriages."),
            Film("Le fabuleux destin d'Amélie Poulain", R.drawable.poster_6, "Despite being caught in her imaginative world, young waitress Amelie decides to help people find happiness. Her quest to spread joy leads her on a journey during which she finds true love."),
            Film("Luck", R.drawable.poster_5, "The curtain is pulled back on the millennia-old battle between the organizations of good luck and bad luck that secretly affects everyday lives."),
            Film("Wicked", R.drawable.poster_4, "Elphaba, a young woman ridiculed for her green skin, and Galinda, a popular girl, become friends at Shiz University in the Land of Oz. After an encounter with the Wonderful Wizard of Oz, their friendship reaches a crossroads."),
            Film("W.I.T.C.H", R.drawable.poster_3, "Five teenage girls learn that they have been chosen to guard the walls between parallel universes. For this purpose, they have been given the powers of the elements."),
            Film("Paddington", R.drawable.poster_10, "A young Peruvian bear travels to London in search of a home. Finding himself lost and alone at Paddington Station, he meets the kindly Brown family, who offer him a temporary haven."),
            Film("La La Land", R.drawable.poster_1, "When Sebastian, a pianist, and Mia, an actress, follow their passion and achieve success in their respective fields, they find themselves torn between their love for each other and their careers.")
        )

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.main_recycler) // Инициализация RecyclerView

        // Применение адаптера и других настроек к RecyclerView
        recyclerView.apply {
            // Инициализируем наш адаптер в конструктор передаем анонимно инициализированный интерфейс
            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    // Создаем бандл и кладем туда объект с данными фильма
                    val bundle = Bundle()
                    // // Первым параметром указывается ключ, по которому потом будем искать, вторым сам
                    // передаваемый объект
                    bundle.putParcelable("film", film)
                    // Запускаем наше активити
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    // Прикрепляем бандл к интенту
                    intent.putExtras(bundle)
                    // Запускаем активити через интент
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
    class YourBottomSheetFragment : BottomSheetDialogFragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.bottomsheet, container, false)
        }
    }
}