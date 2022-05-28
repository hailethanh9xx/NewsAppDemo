package com.aiden.newsdemo.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import domain.repositories.ArticleDataSourceRepository
import data.repositories.ArticleDataSourceImpl

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ArticleRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindArticleRepository(repository: ArticleDataSourceImpl): ArticleDataSourceRepository

}