package com.kareemdev.core.data.source.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status_code")
    @Expose
    val statusCode: String?,
    @SerializedName("status_message")
    @Expose
    val statusMessage: String?,
    @SerializedName("success")
    @Expose
    val success: Boolean,
    @SerializedName("total_results")
    val totalResults: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("results")
    val results: T?
)