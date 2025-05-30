package com.larina.mymovie

import FilmListRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private lateinit var searchView: SearchView

    private lateinit var filmsDataBase: List<Film>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.main_recycler)
        searchView = view.findViewById(R.id.search_view)

        filmsDataBase = listOf(
            Film("Aladdin", R.drawable.poster_8, "Aladdin, a kind thief, woos Jasmine, the princess of Agrabah, with the help of Genie. When Jafar, the grand vizier, tries to usurp the king, Jasmine, Aladdin and Genie must stop him from succeeding."),
            Film("Why Women Kill", R.drawable.poster_7, "An anthology series that follows three women in different decades all living in the same house, as they deal with infidelity and betrayals in their marriages."),
            Film("Le fabuleux destin d'Amélie Poulain", R.drawable.poster_6, "Despite being caught in her imaginative world, young waitress Amelie decides to help people find happiness. Her quest to spread joy leads her on a journey during which she finds true love."),
            Film("Luck", R.drawable.poster_5, "The curtain is pulled back on the millennia-old battle between the organizations of good luck and bad luck that secretly affects everyday lives."),
            Film("Wicked", R.drawable.poster_4, "Elphaba, a young woman ridiculed for her green skin, and Galinda, a popular girl, become friends at Shiz University in the Land of Oz. After an encounter with the Wonderful Wizard of Oz, their friendship reaches a crossroads."),
            Film("W.I.T.C.H", R.drawable.poster_3, "Five teenage girls learn that they have been chosen to guard the walls between parallel universes. For this purpose, they have been given the powers of the elements."),
            Film("Paddington", R.drawable.poster_10, "A young Peruvian bear travels to London in search of a home. Finding himself lost and alone at Paddington Station, he meets the kindly Brown family, who offer him a temporary haven."),
            Film("La La Land", R.drawable.poster_1, "When Sebastian, a pianist, and Mia, an actress, follow their passion and achieve success in their respective fields, they find themselves torn between their love for each other and their careers.")
        )

        filmsAdapter = FilmListRecyclerAdapter(mutableListOf(), object : FilmListRecyclerAdapter.OnItemClickListener {
            override fun click(film: Film) {
                (requireActivity() as MainActivity).launchDetailsFragment(film)
            }
        })

        recyclerView.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(TopSpacingItemDecoration(8))
        }

        filmsAdapter.addItems(filmsDataBase)

        searchView.setOnClickListener {
            searchView.isIconified = false
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Фильтрация списка фильмов по введенному тексту
                val filteredList = if (!newText.isNullOrEmpty()) {
                    filmsDataBase.filter {
                        it.title.contains(newText, ignoreCase = true) ||
                                it.description?.contains(newText, ignoreCase = true) == true // Используем безопасный вызов
                    }
                } else {
                    filmsDataBase
                }
                filmsAdapter.updateData(filteredList)
                return true
            }
        })
    }
}
