/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:12 PM
 * Last modified 6/25/22, 10:12 PM
 */

package com.yashlan.core.domain.usecase

import com.yashlan.core.data.Resource
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}