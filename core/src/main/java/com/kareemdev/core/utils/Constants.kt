package com.kareemdev.core.utils

import com.kareemdev.core.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = BuildConfig.BASE_URL

    //For load image
    const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500"

    //For poster image
    const val POSTER_TARGET_WIDTH = 200
    const val POSTER_TARGET_HEIGHT = 300

    //For backdrop image
    const val BACKDROP_TARGET_WIDTH = 1280
    const val BACKDROP_TARGET_HEIGHT = 720

    //For knowing the show type (Movie/Series)
    const val POPULAR_TYPE = 0
    const val TOP_RATED_TYPE = 1
    const val NOW_PLAYING_TYPE = 2
    const val UP_COMING_TYPE = 3

    const val URL_POPULAR = "movie/popular"
    const val URL_TOP_RATED = "movie/top_rated"
    const val URL_UPCOMING = "movie/upcoming"
    const val URL_NOW_PLAYING = "movie/now_playing"

}