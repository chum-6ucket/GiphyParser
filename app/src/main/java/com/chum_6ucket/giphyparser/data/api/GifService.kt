package com.chum_6ucket.giphyparser.data.api

import com.chum_6ucket.giphyparser.BuildConfig
import com.chum_6ucket.giphyparser.data.models.GiphyResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("trending?")
    suspend fun getTrending(
        @Query("offset") offset: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.GIPHY_API_KEY
    ): GiphyResponse

    @GET("search?")
    suspend fun getSearchedGif(
        @Query("q")
        query: String,
        @Query("limit")
        limit: Int = 25,
        @Query("offset")
        offset: Int = 0,
        @Query("api_key")
        apiKey: String = BuildConfig.GIPHY_API_KEY
    ): GiphyResponse

    companion object {
        private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

        fun create(): NetworkService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)

        }
    }

}