/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 11:18 PM
 * Last modified 6/25/22, 11:18 PM
 */

package com.yashlan.core.data.source.local.room

import androidx.room.*
import com.yashlan.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE movieId = :movieId")
    fun updateFavoriteMovie(movieId: Int, isFavorite: Boolean)
}