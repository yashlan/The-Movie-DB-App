/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 11:29 PM
 * Last modified 6/25/22, 11:29 PM
 */

package com.yashlan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<Results>
) {
    data class Results(
        @SerializedName("id")
        val movieId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String
    )
}