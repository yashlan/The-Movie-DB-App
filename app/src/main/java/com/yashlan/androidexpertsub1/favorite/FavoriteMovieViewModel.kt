/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:34 AM
 * Last modified 6/26/22, 12:31 AM
 */

package com.yashlan.androidexpertsub1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yashlan.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}