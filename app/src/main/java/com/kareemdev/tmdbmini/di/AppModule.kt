package com.kareemdev.tmdbmini.di

import com.kareemdev.core.domain.usecase.MovieInteractor
import com.kareemdev.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieUseIteractor: MovieInteractor): MovieUseCase
}