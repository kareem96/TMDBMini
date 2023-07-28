package com.kareemdev.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailers(
    val id: String,
    val key: String,
    val name: String,
    val published: String,
):Parcelable