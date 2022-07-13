/*
 * Created by Muhammad Yashlan Iskandar on 7/12/22, 5:17 PM
 * Last modified 7/12/22, 5:17 PM
 */

package com.yashlan.androidexpertsub2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.yashlan.androidexpertsub2.utils.Result
import com.yashlan.core.data.source.local.entity.MovieEntity
import com.yashlan.core.data.source.remote.network.ApiService

class FakeHomeViewModel(
    private val apiService: ApiService
) {
    fun getAllMovieEntity(): LiveData<Result<List<MovieEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getListMovie()
            val list = response.results.map {
                MovieEntity(
                    id = 0,
                    movieId = it.movieId,
                    title = it.title,
                    releaseDate = it.releaseDate,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    isFavorite = false
                )
            }
            emit(Result.Success(list))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}