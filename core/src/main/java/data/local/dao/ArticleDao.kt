package data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.entities.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aiden ( hai Le Thanh ) on 27/05/2022.
 * aiden9xx@gmail.com
 */
@Dao
interface ArticleDao {
    /**
     * Inserts [article] into the [Article.TABLE_ARTICLE] table.
     * @param article
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(article: List<Article>)

    /**
     * Fetches all the data from the [Article.TABLE_ARTICLE] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Article.TABLE_ARTICLE}")
    fun getAllArticles(): Flow< List<Article>>

    /**
     * Remove all the data from the [Article.TABLE_ARTICLE] table.
     */
    @Query("DELETE FROM ${Article.TABLE_ARTICLE}")
    suspend fun deleteAllArticles()
}