package com.kareemdev.core.data.source.remote.network

import com.kareemdev.core.BuildConfig
import com.kareemdev.core.data.source.remote.response.ListMovieResponse
import com.kareemdev.core.data.source.remote.response.ListReviewResponse
import com.kareemdev.core.utils.Constants.URL_NOW_PLAYING
import com.kareemdev.core.utils.Constants.URL_POPULAR
import com.kareemdev.core.utils.Constants.URL_TOP_RATED
import com.kareemdev.core.utils.Constants.URL_UPCOMING
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(URL_POPULAR)
    suspend fun getPopular(
        @Query("api_key") apikey: String,
        @Query("language") language:String,
        @Query("page") page:Int
    ):ListMovieResponse

    @GET(URL_TOP_RATED)
    suspend fun getTopRated(
        @Query("api_key") apikey: String,
        @Query("language") language:String,
        @Query("page") page:Int
    ):ListMovieResponse

    @GET(URL_NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") apikey: String,
        @Query("language") language:String,
        @Query("page") page:Int
    ):ListMovieResponse

    @GET(URL_UPCOMING)
    suspend fun getUpComing(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:Int
    ):ListMovieResponse

    @GET("movie/{movieId}/reviews")
    suspend fun getReview(
        @Path("movieId") movieId:Int,
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:Int
    ):ListReviewResponse
}