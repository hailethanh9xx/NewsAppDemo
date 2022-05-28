package domain.repositories

import data.entities.Article
import data.repositories.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleDataSourceRepository {
    fun getArticles(): Flow<Resource<List<Article>>>
    fun clearArticles(): Flow<Resource<List<Article>>>
}
