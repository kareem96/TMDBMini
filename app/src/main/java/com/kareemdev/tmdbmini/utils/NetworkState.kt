package com.kareemdev.tmdbmini.utils

sealed class NetworkState {
    object Loading:NetworkState()
    data class Success(val message:String): NetworkState()
    data class Error(val message:String): NetworkState()
}