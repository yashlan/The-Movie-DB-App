/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:29 AM
 * Last modified 6/26/22, 12:29 AM
 */

package com.yashlan.androidexpertsub1.detail

import androidx.lifecycle.ViewModel
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}