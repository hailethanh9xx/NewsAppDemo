package data.remote

import com.aiden.core.BuildConfig
import domain.entities.News
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
/**
 * Service to fetch data using endpoint [NEWS_API_URL].
 */
interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") key: String = BuildConfig.NEWS_API_KEY,
        @Query("country") query: String = BuildConfig.NEWS_COUNTRY_CODE
    ): Response<News>

    companion object {
        const val NEWS_API_URL = "https://newsapi.org/v2/"
    }
}