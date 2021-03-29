package uz.mirsaidoff.moviedb.data

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import uz.mirsaidoff.moviedb.data.model.MovieInfo
import uz.mirsaidoff.moviedb.data.network.MovieService

class MoviesDataSource(
    private val scope: CoroutineScope,
    private val service: MovieService
) : PageKeyedDataSource<Int, MovieInfo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieInfo>
    ) {
        scope.launch {
            try {
                val popularMovies = service.getPopularMovies(page = 1)
                callback.onResult(popularMovies.results, null, 2)
            } catch (e: Exception) {
                Log.e("Movies paging request", e.message.toString())
                callback.onResult(listOf(), null, null)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieInfo>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieInfo>) {
        scope.launch {
            try {
                val popularMovies = service.getPopularMovies(page = params.key)
                callback.onResult(popularMovies.results, params.key + 1)
            } catch (e: Exception) {
                Log.e("Movies paging request", e.message.toString())
                callback.onResult(listOf(), null)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}

class MoviesDataSourceFactory(
    private val scope: CoroutineScope,
    private val service: MovieService
) : DataSource.Factory<Int, MovieInfo>() {

    private lateinit var moviesDataSource: MoviesDataSource

    override fun create(): DataSource<Int, MovieInfo> {
        moviesDataSource = MoviesDataSource(scope, service)
        return moviesDataSource
    }

    fun refresh() {
        if (!moviesDataSource.isInvalid)
            moviesDataSource::invalidate
    }
}