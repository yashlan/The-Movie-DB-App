/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:10 PM
 * Last modified 6/25/22, 10:10 PM
 */

package com.yashlan.core.domain.repository

import com.yashlan.core.data.Resource
import com.yashlan.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}