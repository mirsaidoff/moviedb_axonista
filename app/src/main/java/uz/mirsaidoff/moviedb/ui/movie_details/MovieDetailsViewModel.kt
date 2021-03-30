package uz.mirsaidoff.moviedb.ui.movie_details

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import uz.mirsaidoff.moviedb.data.model.MovieDetails
import uz.mirsaidoff.moviedb.data.network.MovieService
import java.lang.Exception
import javax.inject.Inject

class MovieDetailsViewModel(private val movieService: MovieService) : ViewModel() {

    private val _result = MutableLiveData<MovieDetails>()
    val result: LiveData<MovieDetails>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetails = movieService.getMovieDetails(movieId)
                _result.value = (movieDetails)
            } catch (e: Exception) {
                _error.value = (e.message)
            }
        }
    }

    //todo Fragment scope
    class Factory @Inject constructor(private val movieService: MovieService) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsViewModel(movieService) as T
        }

    }
}