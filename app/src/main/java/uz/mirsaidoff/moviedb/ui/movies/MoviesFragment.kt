package uz.mirsaidoff.moviedb.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.mirsaidoff.moviedb.MovieApp
import uz.mirsaidoff.moviedb.databinding.FragmentMoviesBinding
import javax.inject.Inject

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance(): MoviesFragment {
            val args = Bundle()

            val fragment = MoviesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var factory: MoviesViewModel.Factory
    private val viewModel by viewModels<MoviesViewModel> { factory }
    private lateinit var binding: FragmentMoviesBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApp)
            .mainComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}