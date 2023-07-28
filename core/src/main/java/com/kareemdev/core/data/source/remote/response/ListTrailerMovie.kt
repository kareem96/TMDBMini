package com.kareemdev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTrailerMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<TrailersResponse>,
)