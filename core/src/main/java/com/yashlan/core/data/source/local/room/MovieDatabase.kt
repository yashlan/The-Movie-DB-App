/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 11:19 PM
 * Last modified 6/25/22, 11:19 PM
 */

package com.yashlan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yashlan.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}