package uz.mirsaidoff.moviedb.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MovieDetailsViewModel : ViewModel() {

    //todo Fragment scope
    class Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel() as T
        }

    }
}