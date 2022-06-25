/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 11:20 PM
 * Last modified 6/25/22, 11:20 PM
 */

package com.yashlan.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val movieId: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String,
    var isFavorite: Boolean
)