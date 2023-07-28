package com.kareemdev.core.utils

import com.kareemdev.core.data.source.local.entity.MovieEntity
import com.kareemdev.core.data.source.remote.response.MovieResponse
import com.kareemdev.core.data.source.remote.response.ReviewResponse
import com.kareemdev.core.data.source.remote.response.TrailersResponse
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Review
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.core.utils.Constants.NOW_PLAYING_TYPE
import com.kareemdev.core.utils.Constants.POPULAR_TYPE
import com.kareemdev.core.utils.Constants.TOP_RATED_TYPE
import com.kareemdev.core.utils.Constants.UP_COMING_TYPE

object DataMapper {
    fun mapPopularResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false,
                showType = POPULAR_TYPE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTopRatedResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false,
                showType = TOP_RATED_TYPE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapNowPlayingResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false,
                showType = NOW_PLAYING_TYPE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapUpComingResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                isFavorite = false,
                showType = UP_COMING_TYPE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        Movie(
            movieId = it.movieId,
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate ?: "",
            posterPath = it.posterPath,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            popularity = it.popularity,
            isFavorite = it.isFavorite,
            backdropPath = it.posterPath,
        )
    }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite,
    )

    /*fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> = input.map {
        Movie(
            movieId = it.movieId,
            title = it.title,
            overview = it.overview,
            releaseDate = it.releaseDate,
            posterPath = it.posterPath,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            popularity = it.popularity,
            isFavorite = it.isFavorite,
        )
    }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite,
    )*/

    fun mapMovieResponseToDomain(
        input: MovieResponse,
    ) = Movie(
        movieId = input.id ?: 0,
        title = input.title ?: "Unknown",
        releaseDate = input.releaseDate ?: "Unknown",
        overview = input.overview ?: "Unknown",
        posterPath = input.posterPath ?: "Unknown",
        backdropPath = input.backdropPath ?: "Unknown",
        isFavorite = input.isFavorite,
        voteCount = input.voteCount ?: 0,
        popularity = (input.popularity ?: 0) as Double,
        voteAverage = (input.voteAverage ?: 0) as Double,
    )

    fun mapReviewResponseToDomain(
        input: ReviewResponse,
    ) = Review(
        author = input.author,
        content = input.content,
        createdAt = input.createdAt,
        updatedAt = input.updatedAt,
        url = input.url,
        id = input.id,
    )

    fun mapTrailerResponseToDomain(input: TrailersResponse) =
        Trailers(
            id = input.id,
            key = input.key,
            name = input.name,
            published = input.publishedAt,
        )



    fun List<TrailersResponse>.toTrailers() = map {
        Trailers(
            id = it.id,
            key = it.key,
            name = it.name,
            published = it.publishedAt,
        )
    }
}
