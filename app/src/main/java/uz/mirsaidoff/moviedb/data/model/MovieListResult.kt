package uz.mirsaidoff.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResult(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieInfo>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)