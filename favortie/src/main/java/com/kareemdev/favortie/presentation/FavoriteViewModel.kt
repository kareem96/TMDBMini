package com.kareemdev.favortie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}