package uz.mirsaidoff.moviedb.di

import dagger.Component
import uz.mirsaidoff.moviedb.ui.movie_details.MovieDetailsFragment
import uz.mirsaidoff.moviedb.ui.movies.MoviesFragment

@Component(modules = [NetworkModule::class])
interface MainComponent {
    fun inject(fragment: MoviesFragment)
    fun inject(fragment: MovieDetailsFragment)
}