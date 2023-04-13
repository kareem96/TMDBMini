package com.kareemdev.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListReviewResponse(
    @field:SerializedName("results")
    val result: List<ReviewResponse>
)