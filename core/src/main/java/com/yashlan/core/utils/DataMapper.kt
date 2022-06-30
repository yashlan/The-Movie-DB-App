/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:24 PM
 * Last modified 6/25/22, 10:24 PM
 */

package com.yashlan.core.utils

import com.yashlan.core.data.source.local.entity.MovieEntity
import com.yashlan.core.data.source.remote.response.MovieResponse
import com.yashlan.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse.Results>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                posterPath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate,
                overview = it.overview,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}