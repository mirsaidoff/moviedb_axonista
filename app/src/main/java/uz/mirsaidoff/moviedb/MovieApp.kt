package uz.mirsaidoff.moviedb

import android.app.Application
import uz.mirsaidoff.moviedb.di.DaggerMainComponent
import uz.mirsaidoff.moviedb.di.MainComponent

class MovieApp : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.create()
    }
}