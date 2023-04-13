package com.kareemdev.tmdbmini.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel(){
    val popular = movieUseCase.getPopular().asLiveData()
    val topRated = movieUseCase.getTopRated().asLiveData()
    val nowPlaying = movieUseCase.getNowPlaying().asLiveData()
    val upComing = movieUseCase.getUpComing().asLiveData()
}