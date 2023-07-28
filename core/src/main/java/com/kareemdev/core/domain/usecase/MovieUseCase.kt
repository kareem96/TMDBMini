package com.kareemdev.core.domain.usecase

import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Review
import com.kareemdev.core.domain.model.Trailers
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopular(): Flow<Resource<List<Movie>>>
    fun getTopRated(): Flow<Resource<List<Movie>>>
    fun getNowPlaying(): Flow<Resource<List<Movie>>>
    fun getUpComing(): Flow<Resource<List<Movie>>>
    fun getReview(movieId:Int): Flow<Resource<List<Review>>>
    fun getTrailersMovie(movieId:Int): Flow<Resource<List<Trailers>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}