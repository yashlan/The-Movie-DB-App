/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 4:19 PM
 * Last modified 6/25/22, 4:03 PM
 */

package com.yashlan.androidexpertsub2.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yashlan.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}