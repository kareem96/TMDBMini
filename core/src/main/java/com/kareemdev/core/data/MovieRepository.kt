package com.kareemdev.core.data

import com.kareemdev.core.data.source.local.LocalDataSource
import com.kareemdev.core.data.source.remote.RemoteDataSource
import com.kareemdev.core.data.source.remote.network.ApiResponse
import com.kareemdev.core.data.source.remote.response.MovieResponse
import com.kareemdev.core.data.source.remote.response.ReviewResponse
import com.kareemdev.core.data.source.remote.response.TrailersResponse
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Review
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.core.domain.repository.IMovieRepository
import com.kareemdev.core.utils.AppExecutors
import com.kareemdev.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    /*override fun getPopular(): Flow<Resource<List<Movie>>> {
        return object : RemoteSource<List<Movie>, List<MovieResponse>>() {
            override fun emptyResult(): Flow<List<Movie>> {
                return flow { emit(emptyList<Movie>()) }
            }

            override fun convertCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopular()
            }

        }.asFLow()
    }*/

    override fun getPopular(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override suspend fun saveCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val movieList = DataMapper.mapPopularResponsesToEntities(data)
                localDataSource.insertMovie(movieList)

                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopular()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopular().map { DataMapper.mapEntitiesToDomain(it) }
            }

        }.asFlow()
    }
    override fun getTopRated(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override suspend fun saveCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val movieList = DataMapper.mapTopRatedResponsesToEntities(data)
                localDataSource.insertMovie(movieList)

                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRated()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTopRated().map { DataMapper.mapEntitiesToDomain(it) }
            }

        }.asFlow()
    }
    override fun getNowPlaying(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override suspend fun saveCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val movieList = DataMapper.mapNowPlayingResponsesToEntities(data)
                localDataSource.insertMovie(movieList)

                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlaying()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getNowPlaying().map { DataMapper.mapEntitiesToDomain(it) }
            }

        }.asFlow()
    }
    override fun getUpComing(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override suspend fun saveCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val movieList = DataMapper.mapUpComingResponsesToEntities(data)
                localDataSource.insertMovie(movieList)

                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getUpComing()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getUpComing().map { DataMapper.mapEntitiesToDomain(it) }
            }

        }.asFlow()
    }

    override fun getReview(movieId: Int): Flow<Resource<List<Review>>> {
        return object : RemoteSource<List<Review>, List<ReviewResponse>>(){
            override fun emptyResult(): Flow<List<Review>> {
                return flow { emit(emptyList<Review>()) }
            }

            override fun convertCallResult(data: List<ReviewResponse>): Flow<List<Review>> {
                val result = data.map {
                    DataMapper.mapReviewResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ReviewResponse>>> {
                return remoteDataSource.getReview(movieId)
            }

        }.asFLow()
    }

    override fun getTrailersMovie(movieId: Int): Flow<Resource<List<Trailers>>> {
        return object : RemoteSource<List<Trailers>, List<TrailersResponse>>() {
            override fun emptyResult(): Flow<List<Trailers>> {
                return flow { emit(emptyList()) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TrailersResponse>>> {
                return remoteDataSource.getTrailerMovie(movieId)
            }

            override fun convertCallResult(data: List<TrailersResponse>): Flow<List<Trailers>> {
                val result = data.map {
                    DataMapper.mapTrailerResponseToDomain(it)
                }
                return flow { emit(result) }
            }

        }.asFLow()
    }


    /*object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
        override suspend fun saveCallResult(data: List<MovieResponse>) {
            val movieList = DataMapper.mapPopularResponsesToEntities(data)
            localDataSource.insertMovie(movieList)
        }

        override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
            return remoteDataSource.getPopular()
        }

        *//*override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }*//*

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopular().map { DataMapper.mapEntitiesToDomain(it) }
            }
        }.asFlow()*/

    /*override fun getPopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapPopularResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopular()
            }

            *//*override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }*//*

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopular().map { DataMapper.mapEntitiesToDomain(it) }
            }
        }.asFlow()

    override fun getTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapPopularResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRated()
            }

            *//*override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }*//*

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTopRated().map { DataMapper.mapEntitiesToDomain(it) }
            }
        }.asFlow()

    override fun getNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapPopularResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlaying()
            }

            *//*override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }*//*

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getNowPlaying().map { DataMapper.mapEntitiesToDomain(it) }
            }
        }.asFlow()

    override fun getUpComing(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapPopularResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getUpComing()
            }

            *//*override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }*//*

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getUpComing().map { DataMapper.mapEntitiesToDomain(it) }
            }
        }.asFlow()*/


    /*override fun getNowPlaying(): Flow<Resource<List<Movie>>> {
        return object : RemoteSource<List<Movie>, List<MovieResponse>>() {
            override fun emptyResult(): Flow<List<Movie>> {
                return flow { emit(emptyList<Movie>()) }
            }

            override fun convertCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getNowPlaying()
            }

        }.asFLow()
    }
    override fun getTopRated(): Flow<Resource<List<Movie>>> {
        return object : RemoteSource<List<Movie>, List<MovieResponse>>() {
            override fun emptyResult(): Flow<List<Movie>> {
                return flow { emit(emptyList<Movie>()) }
            }

            override fun convertCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getTopRated()
            }

        }.asFLow()
    }
    override fun getUpComing(): Flow<Resource<List<Movie>>> {
        return object : RemoteSource<List<Movie>, List<MovieResponse>>() {
            override fun emptyResult(): Flow<List<Movie>> {
                return flow { emit(emptyList<Movie>()) }
            }

            override fun convertCallResult(data: List<MovieResponse>): Flow<List<Movie>> {
                val result = data.map {
                    DataMapper.mapMovieResponseToDomain(it)
                }
                return flow { emit(result) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getUpComing()
            }

        }.asFLow()
    }*/

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}