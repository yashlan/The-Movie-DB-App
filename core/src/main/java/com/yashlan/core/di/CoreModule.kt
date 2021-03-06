/*
 * Created by Muhammad Yashlan Iskandar on 6/25/22, 9:59 PM
 * Last modified 6/25/22, 9:59 PM
 */

package com.yashlan.core.di


import androidx.room.Room
import com.yashlan.core.BuildConfig
import com.yashlan.core.data.MovieRepository
import com.yashlan.core.data.source.local.LocalDataSource
import com.yashlan.core.data.source.local.room.MovieDatabase
import com.yashlan.core.data.source.remote.RemoteDataSource
import com.yashlan.core.data.source.remote.network.ApiService
import com.yashlan.core.domain.repository.IMovieRepository
import com.yashlan.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movies".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movies"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_1)
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_2)
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_3)
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_4)
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_5)
            .add(BuildConfig.HOST_URL, BuildConfig.PIN_SHA256_6)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}