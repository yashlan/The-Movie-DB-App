/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 9:35 PM
 * Last modified 6/26/22, 9:35 PM
 */

package com.yashlan.favorite.di

import com.yashlan.androidexpertsub1.favorite.FavoriteMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteMovieViewModel(get())
    }
}