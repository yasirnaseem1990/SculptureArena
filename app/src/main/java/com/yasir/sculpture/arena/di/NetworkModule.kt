package com.yasir.sculpture.arena.di

import com.moczul.ok2curl.CurlInterceptor
import com.yasir.sculpture.arena.BuildConfig
import com.yasir.sculpture.arena.api.endpoint.PhotosEndpoint
import com.yasir.sculpture.arena.repository.PhotosRepository
import com.yasir.sculpture.arena.utils.AppConstants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkModule = module {
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder().header(AppConstants.Headers.AUTHORIZATION, AppConstants.KEY.API_KEY)
            chain.proceed(newRequest.build())
        }.addInterceptor(CurlInterceptor { message -> Timber.d(message) })

        return httpClient.build()
    }

    single { provideOkHttpClient() }


    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        provideRetrofit(get())
    }

    fun providePhotosApi(retrofit: Retrofit): PhotosEndpoint {
        return retrofit.create(PhotosEndpoint::class.java)
    }

    single { providePhotosApi(get()) }

    single { PhotosRepository(get()) }


}