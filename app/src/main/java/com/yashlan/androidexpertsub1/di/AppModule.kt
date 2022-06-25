/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:39 AM
 * Last modified 6/26/22, 12:39 AM
 */

package com.yashlan.androidexpertsub1.di

import com.yashlan.androidexpertsub1.detail.DetailMovieViewModel
import com.yashlan.androidexpertsub1.favorite.FavoriteMovieViewModel
import com.yashlan.androidexpertsub1.home.HomeViewModel
import com.yashlan.core.domain.usecase.MovieInteractor
import com.yashlan.core.domain.usecase.MovieUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}