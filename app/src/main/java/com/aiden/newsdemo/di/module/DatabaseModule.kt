package com.aiden.newsdemo.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.local.ArticleDatabase
import javax.inject.Singleton

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(application: Application) = ArticleDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDatabase) = database.getArticleDao()
}
