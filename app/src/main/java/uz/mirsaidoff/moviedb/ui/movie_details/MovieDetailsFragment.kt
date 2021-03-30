package uz.mirsaidoff.moviedb.ui.movie_details

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.Resource
import uz.mirsaidoff.moviedb.MovieApp
import uz.mirsaidoff.moviedb.R
import uz.mirsaidoff.moviedb.data.model.MovieDetails
import uz.mirsaidoff.moviedb.data.network.Const
import uz.mirsaidoff.moviedb.databinding.FragmentMovieDetailsBinding
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            fragment.arguments = bundleOf(ARG_MOVIE_ID to movieId)
            return fragment
        }
    }

    @Inject
    lateinit var factory: MovieDetailsViewModel.Factory
    private val viewModel by viewModels<MovieDetailsViewModel> { factory }
    private lateinit var binding: FragmentMovieDetailsBinding
    private val movieCastsAdapter by lazy { MovieCastsAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApp)
            .mainComponent
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetails(requireArguments().getInt(ARG_MOVIE_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initRecycler()
    }

    private fun observe() {
        viewModel.apply {
            result.observe(viewLifecycleOwner, {
                onResult(it)
            })

            error.observe(viewLifecycleOwner, {
                Log.e("Error loading movie details", it)
                showError(it)
            })
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setNeutralButton("OK") { d, _ ->
                d.dismiss()
            }
            .create()
            .show()
    }

    private fun onResult(details: MovieDetails) {
        val glide = Glide.with(this)
        glide.load("${Const.RES_URL_500}${details.backdropPath}")
            .centerCrop()
            .into(binding.ivBackground)
        glide.load("${Const.RES_URL_500}${details.posterPath}")
            .centerCrop()
            .into(binding.ivImage)
        binding.ivBackground.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )

        binding.tvMovieTitle.text = details.title
        binding.tvDuration.text = details.runtime.toString()
        binding.tvOverview.text = details.overview
        binding.tvRating.text = details.voteAverage.toString()
        binding.tvReleased.text = details.releaseDate

        movieCastsAdapter.setItems(details.credit?.casts?: listOf())
    }

    private fun initRecycler() {
        binding.rvCasts.adapter = movieCastsAdapter
    }
}