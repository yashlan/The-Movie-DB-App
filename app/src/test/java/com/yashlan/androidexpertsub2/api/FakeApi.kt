/*
 * Created by Muhammad Yashlan Iskandar on 7/12/22, 7:47 PM
 * Last modified 7/12/22, 7:47 PM
 */

package com.yashlan.androidexpertsub2.api

import com.yashlan.androidexpertsub2.utils.DataSample
import com.yashlan.core.data.source.remote.network.ApiService
import com.yashlan.core.data.source.remote.response.MovieResponse

class FakeApi : ApiService {
    override suspend fun getListMovie(): MovieResponse {
        return DataSample.generateDummyMoviesResponse()
    }
}