package uz.mirsaidoff.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import uz.mirsaidoff.moviedb.R
import uz.mirsaidoff.moviedb.databinding.ActivityMainBinding
import uz.mirsaidoff.moviedb.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

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
}