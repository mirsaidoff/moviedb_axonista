package uz.mirsaidoff.moviedb.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.mirsaidoff.moviedb.data.network.Const.API_KEY
import uz.mirsaidoff.moviedb.data.network.MovieService
import uz.mirsaidoff.moviedb.data.network.Const.BASE_URL

@Module
class NetworkModule {

    companion object {
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