package uz.mirsaidoff.moviedb.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mirsaidoff.moviedb.data.model.MovieDetails
import uz.mirsaidoff.moviedb.data.model.MovieListResult

interface MovieService {

    @GET(Const.GET_POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MovieListResult

    @GET(Const.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): MovieDetails
}