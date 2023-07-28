package com.kareemdev.core.data.source.remote

import com.kareemdev.core.BuildConfig.API_KEY
import com.kareemdev.core.R
import com.kareemdev.core.data.source.remote.network.ApiResponse
import com.kareemdev.core.data.source.remote.network.ApiService
import com.kareemdev.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val language = "en-US"
    private val page: Int = 1
    suspend fun getPopular() = flow {
        try {
            val response = apiService.getPopular(API_KEY, language = language, page = page)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTopRated() = flow {
        try {
            val response = apiService.getTopRated(API_KEY, language = language, page = page)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getNowPlaying() = flow {
        try {
            val response = apiService.getNowPlaying(API_KEY, language = language, page = page)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUpComing() = flow {
        try {
            val response = apiService.getUpComing(API_KEY, language = language, page = page)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getReview(movieId:Int) = flow {
        try {
            val response = apiService.getReview( movieId = movieId, API_KEY, language = language, page = page)
            val dataArray = response.result
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.result))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTrailerMovie(movieId:Int) = flow {
        try {
            val response = apiService.getTrailersMovie( movieId = movieId, API_KEY)
            val dataArray = response.results
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.results))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

}
