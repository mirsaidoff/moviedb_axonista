package uz.mirsaidoff.moviedb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import uz.mirsaidoff.moviedb.data.MoviesDataSourceFactory
import uz.mirsaidoff.moviedb.data.model.MovieInfo
import uz.mirsaidoff.moviedb.data.network.MovieService
import javax.inject.Inject

class MoviesViewModel(
    movieService: MovieService
) : ViewModel() {

    val movies: LiveData<PagedList<MovieInfo>> = LivePagedListBuilder(
        MoviesDataSourceFactory(viewModelScope, movieService),
        20
    ).build()

    //todo Fragment scope
    class Factory @Inject constructor(private val movieService: MovieService) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MoviesViewModel(movieService) as T
        }

    }
}