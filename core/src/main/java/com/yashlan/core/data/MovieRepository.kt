/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 9:55 PM
 * Last modified 6/25/22, 9:55 PM
 */

package com.yashlan.core.data

import com.yashlan.core.data.source.local.LocalDataSource
import com.yashlan.core.data.source.remote.RemoteDataSource
import com.yashlan.core.data.source.remote.network.ApiResponse
import com.yashlan.core.data.source.remote.response.MovieResponse
import com.yashlan.core.domain.model.Movie
import com.yashlan.core.domain.repository.IMovieRepository
import com.yashlan.core.utils.AppExecutors
import com.yashlan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse.Results>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse.Results>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse.Results>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}