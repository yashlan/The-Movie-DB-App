/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 9:57 PM
 * Last modified 6/25/22, 9:57 PM
 */

package com.yashlan.core.data.source.local

import com.yashlan.core.data.source.local.entity.MovieEntity
import com.yashlan.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie.movieId, movie.isFavorite)
    }
}