import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.larina.mymovie.Film
import com.larina.mymovie.R

class FilmListRecyclerAdapter(
    private var filmsList: MutableList<Film>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FilmListRecyclerAdapter.FilmViewHolder>() {

    interface OnItemClickListener {
        fun click(film: Film)
    }

    class FilmViewHolder(itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.poster)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(film: Film) {
            //Указываем контейнер, в котором будет "жить" наша картинка
            Glide.with(itemView)
                //Загружаем сам ресурс
                .load(film.poster)
                //Центруем изображение
                .centerCrop()
                //Указываем ImageView, куда будем загружать изображение
                .into(poster)
            title.text = film.title
            description.text = film.description

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
        holder.bind(filmsList[position])
    }

    override fun getItemCount(): Int = filmsList.size

    // Метод для добавления элементов
    fun addItems(newFilms: List<Film>) {
        filmsList.clear()
        filmsList.addAll(newFilms)
        notifyDataSetChanged()
    }

    // Метод для обновления данных
    fun updateData(newList: List<Film>) {
        filmsList.clear()
        filmsList.addAll(newList)
        notifyDataSetChanged()
    }
}
