package uz.mirsaidoff.moviedb.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mirsaidoff.moviedb.R
import uz.mirsaidoff.moviedb.data.model.Cast
import uz.mirsaidoff.moviedb.data.network.Const
import uz.mirsaidoff.moviedb.databinding.ItemCastBinding

class MovieCastsAdapter(
    private val casts: MutableList<Cast> = mutableListOf()
) : RecyclerView.Adapter<MovieCastsAdapter.ViewHolder>() {

    fun setItems(newItems: List<Cast>) {
        casts.clear()
        casts.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(casts[position])
    }

    override fun getItemCount() = casts.size

    class ViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            binding.tvName.text = cast.name
            binding.tvCharacter.text = cast.character

            Glide.with(binding.root)
                .load(Const.RES_URL_500 + cast.profilePath)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.ivProfileImage)
        }
    }
}