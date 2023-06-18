package com.d3if0154.listmovie.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.d3if0154.listmovie.model.DaftarMovie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "fathan77/DaftarMovie/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DaftarMovieApiService {
    @GET("movie.json")
    suspend fun getDaftarMovie() : List<DaftarMovie>
}

object DaftarMovieApi {
    val service: DaftarMovieApiService by lazy {
        retrofit.create(DaftarMovieApiService::class.java)
    }

    fun getDaftarMovieUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}