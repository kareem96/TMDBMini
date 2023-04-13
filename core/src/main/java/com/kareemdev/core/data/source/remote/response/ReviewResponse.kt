package com.kareemdev.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewResponse(
    @field:SerializedName("author")
    val author: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("created_at")
    val createdAt: String,
    @field:SerializedName("updated_at")
    val updatedAt: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("id")
    val id: String,
) : Parcelable