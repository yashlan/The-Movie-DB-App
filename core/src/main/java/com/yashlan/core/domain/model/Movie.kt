/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:09 PM
 * Last modified 6/25/22, 10:09 PM
 */

package com.yashlan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable
