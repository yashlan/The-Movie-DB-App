/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:26 PM
 * Last modified 6/25/22, 10:26 PM
 */

package com.yashlan.core.data.source.remote.network

import com.yashlan.core.BuildConfig
import com.yashlan.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("discover/movie?api_key=${BuildConfig.API_KEY_V3}")
    suspend fun getListMovie(): MovieResponse
}