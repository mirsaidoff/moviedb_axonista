package uz.mirsaidoff.moviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mirsaidoff.moviedb.data.model.MovieInfo
import uz.mirsaidoff.moviedb.data.network.Const
import uz.mirsaidoff.moviedb.databinding.ItemMovieBinding

class MoviesAdapter : PagedListAdapter<MovieInfo, MoviesAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MovieInfo>() {
            override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieInfo?) {
            binding.tvMovieTitle.text = item?.title
            binding.tvMovieDate.text = item?.releaseDate
            binding.tvRate.text = item?.voteAverage.toString()
            Glide.with(binding.root)
                .load("${Const.RES_URL_500}${item?.posterPath}")
                .centerCrop()
                .into(binding.ivImage)
        }
    }
}