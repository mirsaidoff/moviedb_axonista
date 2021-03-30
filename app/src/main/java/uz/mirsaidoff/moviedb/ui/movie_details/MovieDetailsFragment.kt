package uz.mirsaidoff.moviedb.ui.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.mirsaidoff.moviedb.MovieApp
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
    }

    private fun observe() {
        viewModel.apply {

        }
    }
}