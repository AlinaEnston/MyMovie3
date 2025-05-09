import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.larina.mymovie.Film
import com.larina.mymovie.R

class FilmListRecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<FilmListRecyclerAdapter.FilmViewHolder>() {

    private val films = mutableListOf<Film>()

    interface OnItemClickListener {
        fun click(film: Film)
    }

    class FilmViewHolder(itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(film: Film) {
            // Убедитесь, что posterResId - это Int
            poster.setImageResource(film.poster)
            title.text = film.title
            description.text = film.description

            // Обработка нажатия на элемент
            itemView.setOnClickListener {
                listener.click(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        // Передаем элемент в метод bind() ViewHolder
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }

    fun addItems(newFilms: List<Film>) {
        films.clear()
        films.addAll(newFilms)
        notifyDataSetChanged()
    }

    fun add(films: List<Film>) {}
}