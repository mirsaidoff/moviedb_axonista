package uz.mirsaidoff.moviedb.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MoviesViewModel : ViewModel() {

    //todo Fragment scope
    class Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MoviesViewModel() as T
        }

    }
}