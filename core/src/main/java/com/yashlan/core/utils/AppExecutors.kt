/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 10:24 PM
 * Last modified 6/25/22, 10:24 PM
 */

package com.yashlan.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor
) {
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO
}