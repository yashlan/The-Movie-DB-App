/*
 * Created by Muhammad Yashlan Iskandar on 7/12/22, 4:47 PM
 * Last modified 7/12/22, 4:47 PM
 */

package com.yashlan.androidexpertsub2.utils

import com.yashlan.core.data.source.local.entity.MovieEntity
import com.yashlan.core.data.source.remote.response.MovieResponse

object DataSample {

    fun generateDummyMoviesEntity(): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        for (i in 0..20) {
            val movieEntity = MovieEntity(
                id = i.toLong(),
                movieId = (0..1000000).random(),
                title = "Movies $i",
                releaseDate = "$i-07-2009",
                "this is overview of movie $i",
                "aknvlpojspaosjlksndsjdn$i",
                isFavorite = false
            )
            list.add(movieEntity)
        }
        return list
    }

    fun generateDummyMoviesResponse(): MovieResponse {
        val list = ArrayList<MovieResponse.Results>()
        for (i in 0..20) {
            val movie = MovieResponse.Results(
                movieId = i,
                title = "Movies $i",
                releaseDate = "$i-07-2009",
                "this is overview of movie $i",
                "aknvlpojspaosjlksndsjdn$i"
            )
            list.add(movie)
        }
        return MovieResponse(results = list)
    }
}