package com.kareemdev.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    var backdropPath: String,
    val popularity: Double,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean,
//    val isFavorite: Int,

    ) : Parcelable {
    fun createVoteCountToString(): String =
        "$voteAverage from $voteCount reviews"
}