package uz.mirsaidoff.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import uz.mirsaidoff.moviedb.databinding.ActivityMainBinding
import uz.mirsaidoff.moviedb.ui.movie_details.MovieDetailsFragment
import uz.mirsaidoff.moviedb.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity(), Navigation {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.containerFragment.id, MoviesFragment.newInstance())
                .addToBackStack(MoviesFragment::class.java.name)
                .commit()
        }
    }

    override fun onNavigateToMovieDetails(movieId: Int?) {
        if (movieId != null)
            supportFragmentManager.beginTransaction()
                .replace(binding.containerFragment.id, MovieDetailsFragment.newInstance(movieId))
                .addToBackStack(MovieDetailsFragment::class.java.name)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}