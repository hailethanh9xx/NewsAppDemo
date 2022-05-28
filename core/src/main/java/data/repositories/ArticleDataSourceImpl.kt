package data.repositories

import data.entities.Article
import domain.entities.News
import data.local.dao.ArticleDao
import data.remote.NewsApiService
import domain.repositories.ArticleDataSourceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
@ExperimentalCoroutinesApi
class ArticleDataSourceImpl @Inject constructor(
    private val dao: ArticleDao,
    private val service: NewsApiService
) : ArticleDataSourceRepository {

    /**
     * Fetching data from the server and store to the local database then emit the local database
     */
    override fun getArticles(): Flow<Resource<List<Article>>> {
        return object : FetchDataFlow<List<Article>, News>() {

            override suspend fun storeNewsToLocalDatabase(response: News) = dao.addArticles(response.articles)

            override fun fetchNewsFromLocalDatabase(): Flow<List<Article>> = dao.getAllArticles()

            override suspend fun fetchNewsFromServer(): Response<News> = service.getNews()

        }.asFlow()
    }

    /**
     * Remove all news item from database
     */
    override fun clearArticles(): Flow<Resource<List<Article>>> {
        return object : FetchDataFlow<List<Article>, News>() {

            override suspend fun storeNewsToLocalDatabase(response: News) = dao.deleteAllArticles()

            override fun fetchNewsFromLocalDatabase(): Flow<List<Article>> = dao.getAllArticles()

            override suspend fun fetchNewsFromServer(): Response<News> = service.getNews()

        }.asFlow()
    }
}