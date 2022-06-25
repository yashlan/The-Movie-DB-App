/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 9:58 PM
 * Last modified 6/25/22, 9:58 PM
 */

package com.yashlan.core.data.source.remote

import android.util.Log
import com.yashlan.core.data.source.remote.network.ApiResponse
import com.yashlan.core.data.source.remote.network.ApiService
import com.yashlan.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse.Results>>> {
        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}