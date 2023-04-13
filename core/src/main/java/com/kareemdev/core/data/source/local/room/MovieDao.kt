package com.kareemdev.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kareemdev.core.data.source.local.entity.MovieEntity
import com.kareemdev.core.utils.Constants.NOW_PLAYING_TYPE
import com.kareemdev.core.utils.Constants.POPULAR_TYPE
import com.kareemdev.core.utils.Constants.TOP_RATED_TYPE
import com.kareemdev.core.utils.Constants.UP_COMING_TYPE
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie where showType = $POPULAR_TYPE")
    fun getPopular(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where showType = $TOP_RATED_TYPE")
    fun getTopRated(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where showType = $NOW_PLAYING_TYPE")
    fun getNowPlaying(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where showType = $UP_COMING_TYPE")
    fun getUpComing(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

}