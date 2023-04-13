package com.kareemdev.core.data.source.local

import com.kareemdev.core.data.source.local.entity.MovieEntity
import com.kareemdev.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getPopular(): Flow<List<MovieEntity>> = movieDao.getPopular()
    fun getTopRated(): Flow<List<MovieEntity>> = movieDao.getTopRated()
    fun getNowPlaying(): Flow<List<MovieEntity>> = movieDao.getNowPlaying()
    fun getUpComing(): Flow<List<MovieEntity>> = movieDao.getUpComing()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)


    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}