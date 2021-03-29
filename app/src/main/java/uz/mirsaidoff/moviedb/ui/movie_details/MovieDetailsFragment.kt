package uz.mirsaidoff.moviedb.ui.movie_details

import android.content.Context
import androidx.fragment.app.Fragment
import uz.mirsaidoff.moviedb.MovieApp

class MovieDetailsFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApp)
            .mainComponent
            .inject(this)
    }
}