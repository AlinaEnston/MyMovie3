import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.larina.mymovie.Film
import com.larina.mymovie.FilmViewHolder
import com.larina.mymovie.R

class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<FilmViewHolder>() {
    private val items = mutableListOf<Film>() // Список элементов

    // Возвращает количество элементов в списке
    override fun getItemCount() = items.size

    // Создает новый ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(view)
    }

    // Привязывает данные к ViewHolder
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(items[position]) // Привязываем данные
        holder.itemView.setOnClickListener {
            clickListener.click(items[position]) // Обработка клика
        }
    }

    // Метод для добавления элементов в список
    fun addItems(list: List<Film>) {
        items.clear() // Очищаем текущий список
        items.addAll(list) // Добавляем новые элементы
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    // Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(film: Film)
    }
}