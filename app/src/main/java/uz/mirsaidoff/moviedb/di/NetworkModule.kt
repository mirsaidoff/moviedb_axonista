package uz.mirsaidoff.moviedb.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.mirsaidoff.moviedb.network.MovieService

@Module
class NetworkModule {

    companion object {
        const val API_KEY = "274f828ad283bd634ef4fc1ee4af255f"
        const val BASE_URL = "https://api.themoviedb.org"
    }

    @Provides
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url()
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                original.newBuilder()
                    .url(newUrl)
                    .build()
                    .let { request ->
                        return@addInterceptor chain.proceed(request)
                    }
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): MovieService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}