package com.kareemdev.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @field:SerializedName("release_date")
    val releaseDate: String,
    @field:SerializedName("popularity")
    val popularity: Double,
    @field:SerializedName("vote_average")
    val voteAverage: Double,
    @field:SerializedName("vote_count")
    val voteCount: Int,
    val isFavorite:Boolean,
    ): Parcelable {
    fun createVoteCountToString(): String =
        "$voteAverage from $voteCount reviews"
}