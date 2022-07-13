/*
 * Created by Muhammad Yashlan Iskandar on 6/26/22, 12:41 AM
 * Last modified 6/26/22, 12:41 AM
 */

package com.yashlan.androidexpertsub2

import android.app.Application
import com.yashlan.androidexpertsub2.di.useCaseModule
import com.yashlan.androidexpertsub2.di.viewModelModule
import com.yashlan.core.di.databaseModule
import com.yashlan.core.di.networkModule
import com.yashlan.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}