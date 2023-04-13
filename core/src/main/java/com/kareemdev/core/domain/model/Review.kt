package com.kareemdev.core.domain.model

import com.google.gson.annotations.SerializedName

data class Review(
    val author: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val url: String,
    val id: String,
)
