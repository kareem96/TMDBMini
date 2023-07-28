package com.kareemdev.tmdbmini.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kareemdev.core.data.Resource
import com.kareemdev.core.domain.model.Movie
import com.kareemdev.core.domain.model.Trailers
import com.kareemdev.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

    fun getReview(movieId:Int) = movieUseCase.getReview(movieId).asLiveData()
    fun getTrailer(movieId:Int) = movieUseCase.getTrailersMovie(movieId).asLiveData()

}