/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:14 PM
 * Last modified 6/25/22, 10:14 PM
 */

package com.yashlan.core.domain.usecase

import com.yashlan.core.data.Resource
import com.yashlan.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}