package com.kareemdev.core.domain.usecase

import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Review
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getPopular(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopular()
    }

    override fun getTopRated(): Flow<Resource<List<Movie>>> {
        return movieRepository.getTopRated()
    }

    override fun getNowPlaying(): Flow<Resource<List<Movie>>> {
        return movieRepository.getNowPlaying()
    }

    override fun getUpComing(): Flow<Resource<List<Movie>>> {
        return movieRepository.getUpComing()
    }

    override fun getReview(movieId: Int): Flow<Resource<List<Review>>> {
        return movieRepository.getReview(movieId)
    }

    override fun getTrailersMovie(movieId: Int): Flow<Resource<List<Trailers>>> {
        return movieRepository.getTrailersMovie(movieId)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}