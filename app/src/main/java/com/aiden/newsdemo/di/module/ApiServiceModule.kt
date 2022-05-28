package com.aiden.newsdemo.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): NewsApiService = Retrofit.Builder()
        .baseUrl(NewsApiService.NEWS_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(NewsApiService::class.java)
}
